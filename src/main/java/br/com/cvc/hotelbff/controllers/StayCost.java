package br.com.cvc.hotelbff.controllers;

import br.com.cvc.hotelbff.exceptions.DateRangeValidationException;
import br.com.cvc.hotelbff.exceptions.ObjectNotFoundException;
import br.com.cvc.hotelbff.exceptions.WrongParameterException;
import br.com.cvc.hotelbff.models.dtos.StayDto;

import br.com.cvc.hotelbff.services.CostCalculationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stay-cost")
@Validated
public class StayCost {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    @Autowired
    private CostCalculationService costCalculationService;

    @Operation(summary = "Calculate city hotels rooms cost per person.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels find in this city.",
                         content = { @Content(mediaType = "application/json", schema = @Schema(implementation = StayDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid payload supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hotels not found at this city code.", content = @Content),
            @ApiResponse(responseCode = "406", description = "Dates out of range restriction.", content = @Content),
            @ApiResponse(responseCode = "502", description = "Remote server dependency could not respond.", content = @Content)})
    @GetMapping()
    public ResponseEntity<List<StayDto>> calculate(@NotNull Long cityCost,
                                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN) LocalDate checkIn,
                                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN) LocalDate checkOut,
                                                   @NotNull @Min(1) Long adultCount,
                                                   Long childCount){

        validateDateRange(checkIn, checkOut);
        List<StayDto> calculatedCost = costCalculationService.pricefy(cityCost, checkIn, checkOut, adultCount,childCount);
        if (calculatedCost.isEmpty()) {
            throw new ObjectNotFoundException("No city found for this code.");
        }
        return ResponseEntity.ok(calculatedCost);
    }

    private void validateDateRange(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null){
            throw new WrongParameterException("Check-in could not be null");
        }
        if (checkOut == null){
            throw new WrongParameterException("Check-out could not be null");
        }
        if (!checkOut.isAfter(checkIn)){
            throw new DateRangeValidationException("Check-out date must be after Check-in date");
        }
    }


}
