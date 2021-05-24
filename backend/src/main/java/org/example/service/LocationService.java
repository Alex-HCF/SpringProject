package org.example.service;

import org.example.data.dto.LocationInDto;
import org.example.data.entity.Location;
import org.example.data.mapper.LocationMapper;
import org.example.utils.geoTools.DaDataGeoTool;
import org.example.utils.geoTools.GeoData;
import org.example.repository.LocationRepository;
import org.springframework.stereotype.Service;
//import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
//import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;


@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final DaDataGeoTool daDataGeoTool;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, DaDataGeoTool daDataGeoTool, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.daDataGeoTool = daDataGeoTool;
        this.locationMapper = locationMapper;
    }

    public Location locationFromLocationDto(LocationInDto locationInDto){
        GeoData geoData = daDataGeoTool.getGeoDataFromAddress(locationInDto.getAddress());
        return locationMapper.geoDataToLocation(geoData);
    }


}
