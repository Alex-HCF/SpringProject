package org.example.data.mapper;

import org.example.data.dto.LocationInDto;
import org.example.data.dto.LocationOutDto;
import org.example.data.entity.Location;
import org.example.utils.LocationUtils;
import org.example.utils.geoTools.DaDataGeoTool;
import org.example.utils.geoTools.GeoData;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class LocationMapper {

    @Autowired
    private DaDataGeoTool daDataGeoTool;

    public Location locationDtoToLocation(LocationInDto locationInDto){
        GeoData geoData = daDataGeoTool.getGeoDataFromAddress(locationInDto.getAddress());
        return geoDataToLocation(geoData);
    }


    @Autowired
    private LocationUtils locationUtils;

    public LocationOutDto locationToLocationOutDto(Location location){
        UUID fiasId = locationUtils.findTheMostAccurateFias(location).getFiasId();
        String address = daDataGeoTool.getGeoDataFromFias(fiasId).getAddress();
        return new LocationOutDto(fiasId, address, location.getLongitude(), location.getLatitude());
    }

    public abstract Location geoDataToLocation(GeoData geoData);

}
