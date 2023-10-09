package com.company.payload.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkerResponseDTO {
    private Integer id;
    private String name;
    private String surname;
}
