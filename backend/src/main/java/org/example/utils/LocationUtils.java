package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.data.entity.Location;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationUtils {


    @Data
    @AllArgsConstructor
    public static class FiasIdWithType{
        public enum FiasType {HOUSE, STREET, CITY, SETTLEMENT, AREA, REGION}

        UUID fiasId;
        FiasType fiasType;
    }

    public FiasIdWithType findTheMostAccurateFias(Location location){
        UUID fiasId = location.getHouseFiasId();
        FiasIdWithType.FiasType type = FiasIdWithType.FiasType.HOUSE;
        if(fiasId == null){
            fiasId = location.getStreetFiasId();
            type = FiasIdWithType.FiasType.STREET;
        }
        if(fiasId == null){
            fiasId =location.getCityFiasId();
            type = FiasIdWithType.FiasType.CITY;
        }
        if(fiasId == null){
            fiasId =location.getSettlementFiasId();
            type = FiasIdWithType.FiasType.SETTLEMENT;
        }
        if(fiasId == null){
            fiasId =location.getAreaFiasId();
            type = FiasIdWithType.FiasType.AREA;
        }
        if(fiasId == null){
            fiasId =location.getRegionFiasId();
            type = FiasIdWithType.FiasType.REGION;
        }

        return new FiasIdWithType(fiasId, type);
    }

}
