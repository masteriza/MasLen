package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private String message;
    private String code;
    private String status;
    private List errorList;
}
