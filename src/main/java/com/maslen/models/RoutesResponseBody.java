package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class RoutesResponseBody {
    String message;
    String code;
    List<Route> routes;
}
