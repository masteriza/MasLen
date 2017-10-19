package com.maslen.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
public class AjaxResponseBody {
    @JsonView(Views.Public.class)
    String msg;

    @JsonView(Views.Public.class)
    String code;

    @JsonView(Views.Public.class)
    List<Route> result;
}
