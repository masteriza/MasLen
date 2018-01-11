package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponseBody {
    private List message;
    private String code;
    private String status;
    private List errorList;
}
