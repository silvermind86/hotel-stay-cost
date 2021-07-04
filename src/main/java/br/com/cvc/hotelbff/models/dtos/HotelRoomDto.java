package br.com.cvc.hotelbff.models.dtos;

public class HotelRoomDto {

    private Long roomID;
    private String categoryName;
    private PriceDto price;

    public HotelRoomDto() {
    }

    public HotelRoomDto(Long roomID, String categoryName, PriceDto price) {
        this.roomID = roomID;
        this.categoryName = categoryName;
        this.price = price;
    }

    public Long getRoomID() {
        return roomID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setPrice(PriceDto price) {
        this.price = price;
    }
}
