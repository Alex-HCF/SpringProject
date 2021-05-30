package org.example.utils.geoTools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.UUID;


public class GeoDataImpl2 implements GeoData{

    private final JsonObject data;

    public GeoDataImpl2(JsonObject data) {
        this.data = data;
    }

    @Override
    public UUID getRegionFiasId() {
        JsonElement regionFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("region_fias_id");
        return regionFiasId.isJsonNull() ? null : UUID.fromString(regionFiasId.getAsString());
    }

    @Override
    public UUID getAreaFiasId() {
        JsonElement areaFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("area_fias_id");
        return areaFiasId.isJsonNull() ? null : UUID.fromString(areaFiasId.getAsString());

    }

    @Override
    public UUID getCityFiasId() {
        JsonElement cityFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("city_fias_id");
        return cityFiasId.isJsonNull() ? null : UUID.fromString(cityFiasId.getAsString());
    }

    @Override
    public UUID getSettlementFiasId() {
        JsonElement settlementFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("settlement_fias_id");
        return settlementFiasId.isJsonNull() ? null : UUID.fromString(settlementFiasId.getAsString());
    }

    @Override
    public UUID getStreetFiasId() {
        JsonElement streetFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("street_fias_id");
        return streetFiasId.isJsonNull() ? null : UUID.fromString(streetFiasId.getAsString());
    }

    @Override
    public UUID getHouseFiasId() {
        JsonElement houseFiasId = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("house_fias_id");
        return houseFiasId.isJsonNull() ? null : UUID.fromString(houseFiasId.getAsString());
    }

    @Override
    public String getCountry() {
        JsonElement country = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("country");
        return country.isJsonNull() ? null : country.getAsString();
    }

    @Override
    public String getRegion() {
        JsonElement region = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("region_with_type");
        return region.isJsonNull() ? null : region.getAsString();
    }

    @Override
    public String getSettlement() {
        JsonElement city = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("city");
        return city.isJsonNull() ? null : city.getAsString();
    }

    @Override
    public Double getLongitude() {
        JsonElement geo_lon = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("geo_lon");
        return geo_lon.isJsonNull() ? null : geo_lon.getAsDouble();
    }

    @Override
    public Double getLatitude() {
        JsonElement geo_lat = data.getAsJsonObject().get("data")
                .getAsJsonObject().get("geo_lat");
        return geo_lat.isJsonNull() ? null : geo_lat.getAsDouble();
    }

    @Override
    public String getAddress() {
        JsonElement address = data.get("value");
        return address.isJsonNull() ? null : address.getAsString();
    }
}
