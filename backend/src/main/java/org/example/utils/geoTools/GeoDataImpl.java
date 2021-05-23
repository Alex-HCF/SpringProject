package org.example.utils.geoTools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.UUID;


public class GeoDataImpl implements GeoData{

    private final JsonObject data;

    public GeoDataImpl(JsonObject data) {
        this.data = data;
    }

    @Override
    public UUID getRegionFiasId() {
        JsonElement regionFiasId = data.get("region_fias_id");
        return regionFiasId.isJsonNull() ? null : UUID.fromString(regionFiasId.getAsString());
    }

    @Override
    public UUID getAreaFiasId() {
        JsonElement areaFiasId = data.get("area_fias_id");
        return areaFiasId.isJsonNull() ? null : UUID.fromString(areaFiasId.getAsString());

    }

    @Override
    public UUID getCityFiasId() {
        JsonElement cityFiasId = data.get("city_fias_id");
        return cityFiasId.isJsonNull() ? null : UUID.fromString(cityFiasId.getAsString());
    }

    @Override
    public UUID getSettlementFiasId() {
        JsonElement settlementFiasId = data.get("settlement_fias_id");
        return settlementFiasId.isJsonNull() ? null : UUID.fromString(settlementFiasId.getAsString());
    }

    @Override
    public UUID getStreetFiasId() {
        JsonElement streetFiasId = data.get("street_fias_id");
        return streetFiasId.isJsonNull() ? null : UUID.fromString(streetFiasId.getAsString());
    }

    @Override
    public UUID getHouseFiasId() {
        JsonElement houseFiasId = data.get("house_fias_id");
        return houseFiasId.isJsonNull() ? null : UUID.fromString(houseFiasId.getAsString());
    }

    @Override
    public String getCountry() {
        JsonElement country = data.get("country");
        return country.isJsonNull() ? null : country.getAsString();
    }

    @Override
    public String getRegion() {
        JsonElement region = data.get("region_with_type");
        return region.isJsonNull() ? null : region.getAsString();
    }

    @Override
    public String getSettlement() {
        JsonElement city = data.get("city");
        return city.isJsonNull() ? null : city.getAsString();
    }

    @Override
    public Double getLongitude() {
        JsonElement geo_lon = data.get("geo_lon");
        return geo_lon.isJsonNull() ? null : geo_lon.getAsDouble();
    }

    @Override
    public Double getLatitude() {
        JsonElement geo_lat = data.get("geo_lat");
        return geo_lat.isJsonNull() ? null : geo_lat.getAsDouble();
    }
}
