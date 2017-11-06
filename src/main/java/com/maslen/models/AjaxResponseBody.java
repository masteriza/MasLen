package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class AjaxResponseBody {
    String msg;
    String code;
    List<Route> result;
}
