package com.maslen.models;

import lombok.Data;

@Data
public class EmailResponseBody {
    String message;
    String code;
    String email;
}
