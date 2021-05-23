package org.example.data.mapper;

import org.example.data.dto.LocationInDto;
import org.example.data.entity.Location;
import org.example.utils.geoTools.DaDataGeoTool;
import org.example.utils.geoTools.GeoData;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LocationInMapper {

    @Autowired
    private DaDataGeoTool daDataGeoTool;

    public Location locationDtoToLocation(LocationInDto locationInDto){
        GeoData geoData = daDataGeoTool.getGeoDataFromAddress(locationInDto.getAddress());
        return geoDataToLocation(geoData);
    }

    public abstract Location geoDataToLocation(GeoData geoData);
}
