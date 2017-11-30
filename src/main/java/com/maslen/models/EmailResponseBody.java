package com.maslen.models;

import lombok.Data;

@Data
public class EmailResponseBody {
    String msg;
    String code;
    String email;
}
