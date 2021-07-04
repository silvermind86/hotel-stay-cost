package br.com.cvc.hotelbff.models.dtos;

import java.math.BigDecimal;

public class RoomDto {

    private Long roomId;
    private String categoryName;
    private BigDecimal totalPrice;
    private PriceDetailDto priceDetail;

    public RoomDto(HotelRoomDto room, Long days, Long adultCount, Long childCount) {
        this.roomId = room.getRoomID();
        this.categoryName = room.getCategoryName();
        this.priceDetail = new PriceDetailDto(days, adultCount, childCount, room.getPrice());
        this.totalPrice = this.priceDetail.getPricePerDayAdult().add(this.priceDetail.getPricePerDayChild());
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public PriceDetailDto getPriceDetail() {
        return priceDetail;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPriceDetail(PriceDetailDto priceDetail) {
        this.priceDetail = priceDetail;
    }
}
