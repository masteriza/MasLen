package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private String status;
    private List errorList;
}
