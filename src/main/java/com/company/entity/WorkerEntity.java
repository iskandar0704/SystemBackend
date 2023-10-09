package com.company.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class WorkerEntity {
    private Integer id;
    private String name;
    private String surname;

}
