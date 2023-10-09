package com.company.service;

import com.company.payload.requests.*;
import com.company.payload.responses.ApiResult;

public interface IWorkerService {
    ApiResult<?> create(CreateWorkerDTO createWorkerDTO);
    ApiResult<?> getById(IdHandlerDTO checkById);
    ApiResult<?>  getAll();

    ApiResult<?> update(UpdateWorkerDTO workerDTO);

    ApiResult<?> byName(NameHandlerDTO nameHandlerDTO);

    ApiResult<?> bySurname(SurnameHandlerDTO surnameHandlerDTO);

    ApiResult<?> deleteById(IdHandlerDTO idHandlerDTO);
}
