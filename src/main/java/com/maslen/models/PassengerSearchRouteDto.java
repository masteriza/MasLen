package com.maslen.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize()

public class PassengerSearchRouteDto {
    private double startRouteMarkerLatitude;
    private double startRouteMarkerLongitude;
    private double endRouteMarkerLatitude;
    private double endRouteMarkerLongitude;

}
