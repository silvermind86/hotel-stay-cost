package br.com.cvc.hotelbff.exceptions;

import br.com.cvc.hotelbff.models.dtos.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> argumentHandler(MethodArgumentNotValidException exception){
        List<ErrorDTO> dto = new ArrayList<>();
        List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
        fieldErros.forEach( e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            dto.add(new ErrorDTO(e.getField(), message));
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DateRangeValidationException.class)
    public List<ErrorDTO> dateRangeHandler(DateRangeValidationException exception){
        return Arrays.asList(new ErrorDTO("checkin", "Must be lesser than Check-out"),
                             new ErrorDTO("checkout", "Must be bigger than Check-in"));
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ErrorDTO notFoundHandler(ObjectNotFoundException exception){
        return new ErrorDTO("cityCode", exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(AdapterHttpException.class)
    public ErrorDTO adapterHandler(AdapterHttpException exception){
        return new ErrorDTO(exception.getCause().getMessage(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongParameterException.class)
    public ErrorDTO parameterHandler(WrongParameterException exception){
        return new ErrorDTO("date", exception.getMessage());
    }
}
