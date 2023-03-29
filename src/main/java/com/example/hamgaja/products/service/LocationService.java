package com.example.hamgaja.products.service;

import com.example.hamgaja.products.entity.Location;
import com.example.hamgaja.products.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    @Transactional(readOnly = true)
    public Location defaultLocation(){
        Location location = new Location("서울특별시 강남구","서울특별시","강남구");
       return location = locationRepository.save(location);
    }
}
