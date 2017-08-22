package com.maslen.models;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponse {
    private String status;
    private List errorList;
}
