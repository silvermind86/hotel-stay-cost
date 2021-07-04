package br.com.cvc.hotelbff.models.dtos;

import java.util.List;

public class HotelDto {

    private Long id;
    private String cityName;
    private List<HotelRoomDto> rooms;

    public HotelDto() {
    }

    public HotelDto(Long id, String cityName, List<HotelRoomDto> rooms) {
        this.id = id;
        this.cityName = cityName;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<HotelRoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoomDto> rooms) {
        this.rooms = rooms;
    }
}
