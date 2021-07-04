package br.com.cvc.hotelbff.models.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceDetailDto {

    private BigDecimal pricePerDayAdult;
    private BigDecimal pricePerDayChild;

    public PriceDetailDto() {
    }

    public PriceDetailDto(Long days, Long adultCount, Long childCount, PriceDto price) {

        BigDecimal interest = new BigDecimal("1.7");

        this.pricePerDayAdult = price.getAdult().multiply(new BigDecimal(adultCount)).setScale(2, RoundingMode.HALF_UP)
                                                .multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP)
                                                .multiply(interest).setScale(2, RoundingMode.HALF_UP);
        if (null != childCount) {
            this.pricePerDayChild = price.getChild().multiply(new BigDecimal(childCount)).setScale(2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP)
                    .multiply(interest).setScale(2, RoundingMode.HALF_UP);
        } else {
            this.pricePerDayChild = BigDecimal.ZERO;
        }

    }

    public BigDecimal getPricePerDayAdult() {
        return pricePerDayAdult;
    }

    public BigDecimal getPricePerDayChild() {
        return pricePerDayChild;
    }

}
