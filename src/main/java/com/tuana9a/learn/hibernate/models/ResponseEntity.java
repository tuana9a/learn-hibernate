package com.tuana9a.learn.hibernate.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseEntity {
    private Integer code;
    private String message;
    private Object data;
}
