package com.tuana9a.learn.hibernate.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String name;
    private Integer year;
}
