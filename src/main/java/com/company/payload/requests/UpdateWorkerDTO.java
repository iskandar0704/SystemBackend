package com.company.payload.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateWorkerDTO {
    private Integer id;
    private String name;
    private String surname;
}
