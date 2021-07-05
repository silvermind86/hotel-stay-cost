package br.com.cvc.hotelbff.adapters;

import br.com.cvc.hotelbff.exceptions.AdapterHttpException;
import br.com.cvc.hotelbff.models.dtos.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CvcBackendHotelAdapter {

    private static final String CVC_BACKEND_HOST = "https://cvcbackendhotel.herokuapp.com/";
    @Autowired
    RestTemplate restTemplate;

    @Cacheable("costs")
    public List<HotelDto> getHotels(Long cityCode) {
        List<HotelDto> hotels = new ArrayList<>();

        try{
            HotelDto[] hotelsArrays = restTemplate.getForObject(CVC_BACKEND_HOST + "/hotels/avail/" + cityCode, HotelDto[].class);
            hotels.addAll(Arrays.asList(hotelsArrays));
            return hotels;
        } catch (HttpClientErrorException httpEx) {
            throw new AdapterHttpException("There's a problem to resolve remote server dependency.", httpEx);
        } catch (Exception e) {
          throw e;
        }
    }
}
