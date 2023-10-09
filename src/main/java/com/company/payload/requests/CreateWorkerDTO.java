package com.company.payload.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateWorkerDTO {
    private String name;
    private String surname;
}
