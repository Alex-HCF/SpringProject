package org.example.controller;

import org.example.utils.geoTools.DaDataGeoTool;
import org.example.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class testcont {

    @Autowired
    LocationService locationService;

    @Autowired
    DaDataGeoTool daDataGeoTool;

//    @GetMapping
//    Object test (){
//
//        return locationService.locationFromLocationDto(new LocationOutDto("Воронеж, московский проспект"));
//
//    }
}
