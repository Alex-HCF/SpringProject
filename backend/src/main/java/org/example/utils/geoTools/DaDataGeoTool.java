package org.example.utils.geoTools;

import com.google.gson.*;
import org.example.exception.BadEntityException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class DaDataGeoTool {

    private static final String DEFAULT_COORD_FROM_ADDRESS_URL = "https://cleaner.dadata.ru/api/v1/clean/address";
    private static final String DEFAULT_ADDRESS_FROM_COORD_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/geolocate/address";
    private static final String DEFAULT_ADDRESS_FROM_FIAS_URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/address";

    private final WebClient webClient;

    public DaDataGeoTool(String authorization, String xSecret) {
        webClient = WebClient.builder()
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Token " + authorization)
                .defaultHeader("X-Secret", xSecret)
                .build();
    }


    public GeoData getGeoDataFromAddress(String address) {
        String jsonInString = webClient.post().uri(DEFAULT_COORD_FROM_ADDRESS_URL).body(Mono.just("[\"" + address + "\"]"), String.class).retrieve().bodyToMono(String.class).block();

        if (jsonInString == null) {
            throw new BadEntityException("Address + " + address + " is not fond");
        }

        JsonObject data = JsonParser.parseString(jsonInString).getAsJsonArray().get(0).getAsJsonObject();

        return new GeoDataImpl(data);
    }


    public GeoData getGeoDataFromCoord(double lat, double lon){
        var jsonInString =  webClient.post().uri(DEFAULT_ADDRESS_FROM_COORD_URL).body(Mono.just("{\"lat\": " + lat + ", \"lon\": " + lon + " }"), String.class).retrieve().bodyToMono(String.class).block();

        if(jsonInString == null){
            throw new BadEntityException("Latitude " + lat + " or Longitude " + lon + " is not found");
        }

        JsonObject jsonObject = JsonParser.parseString(jsonInString).getAsJsonObject();
        JsonObject data =  jsonObject
                .get("suggestions")
                .getAsJsonArray().get(0).getAsJsonObject();

        return new GeoDataImpl2(data);

    }

    public GeoData getGeoDataFromFias(UUID fias){
        String body = "{ \"query\": \"" + fias + "\" }";
        var jsonInString =  webClient.post().uri(DEFAULT_ADDRESS_FROM_FIAS_URL).body(Mono.just(body), String.class).retrieve().bodyToMono(String.class).block();

        if(jsonInString == null){
            throw new BadEntityException(fias + " is not found");
        }

        JsonObject jsonObject = JsonParser.parseString(jsonInString).getAsJsonObject();
        JsonObject data =  jsonObject
                .get("suggestions")
                .getAsJsonArray().get(0).getAsJsonObject();

        return new GeoDataImpl2(data);

    }


}
