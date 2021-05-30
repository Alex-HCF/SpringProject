package org.example.utils.geoTools;

import java.util.UUID;

public interface GeoData {

    UUID getRegionFiasId();

    UUID getAreaFiasId();

    UUID getCityFiasId();

    UUID getSettlementFiasId();

    UUID getStreetFiasId();

    UUID getHouseFiasId();

    String getCountry();
    String getRegion();
    String getSettlement();
    Double getLongitude();
    Double getLatitude();

    String getAddress();

}
