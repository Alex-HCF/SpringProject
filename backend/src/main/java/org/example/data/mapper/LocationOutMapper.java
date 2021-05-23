package org.example.data.mapper;

import org.example.utils.LocationUtils;
import org.example.data.dto.LocationOutDto;
import org.example.data.entity.Location;
import org.example.utils.geoTools.GeoData;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class LocationOutMapper {

//    @Autowired
//    private DaDataGeoTool daDataGeoTool;

    @Autowired
    private LocationUtils locationUtils;

    public LocationOutDto locationToLocationOutDto(Location location){
        UUID fiasId = locationUtils.findTheMostAccurateFias(location).getFiasId();
        return new LocationOutDto(fiasId, location.getLongitude(), location.getLatitude());
    }

    public abstract Location geoDataToLocation(GeoData geoData);
}
