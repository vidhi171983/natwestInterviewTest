package com.natwest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseWrapper <T> {
    private String message;
    private T data;


}
