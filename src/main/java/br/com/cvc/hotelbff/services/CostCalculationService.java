package br.com.cvc.hotelbff.services;

import br.com.cvc.hotelbff.adapters.CvcBackendHotelAdapter;
import br.com.cvc.hotelbff.models.dtos.HotelDto;
import br.com.cvc.hotelbff.models.dtos.StayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostCalculationService {

    @Autowired
    private CvcBackendHotelAdapter cvcBackendHotelAdapter;

    public List<StayDto> pricefy(Long cityCost, LocalDate checkIn, LocalDate checkOut, Long adultCount, Long childCount) {
        List<StayDto> calculatedStay = new ArrayList<>();
        List<HotelDto> hotels = cvcBackendHotelAdapter.getHotels(cityCost);
        if( hotels.isEmpty()) {
            return calculatedStay;
        }

        Long days = Duration.between(checkIn.atStartOfDay(ZoneOffset.UTC),
                                     checkOut.atStartOfDay(ZoneOffset.UTC)).toDays();

        calculatedStay.addAll(hotels.stream().map(
                h-> new StayDto(h, days, adultCount, childCount)
        ).collect(Collectors.toList()));
        return calculatedStay;
    }

}
