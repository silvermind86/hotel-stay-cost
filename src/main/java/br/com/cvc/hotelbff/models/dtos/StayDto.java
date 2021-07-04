package br.com.cvc.hotelbff.models.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class StayDto {

    private Long id;
    private String cityName;
    private List<RoomDto> rooms;


    public StayDto(HotelDto hotel, Long days, Long adultCount, Long childCount) {
        this.id = hotel.getId();
        this.cityName = hotel.getCityName();
        this.rooms = hotel.getRooms().stream().map(
                r -> new RoomDto(r, days, adultCount, childCount)
            ).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }
}
