package com.company.controller;

import com.company.payload.requests.*;
import com.company.payload.responses.ApiResult;
import com.company.service.IWorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WorkerController implements IWorkerController {
    private final IWorkerService workerService;

    @Override
    public ApiResult<?> create(CreateWorkerDTO createWorkerDTO) {
        return workerService.create(createWorkerDTO);
    }

    @Override
    public ApiResult<?> byId(IdHandlerDTO checkById) {
        return workerService.getById(checkById);
    }

    @Override
    public ApiResult<?> byName(NameHandlerDTO nameHandlerDTO) {
        return workerService.byName(nameHandlerDTO);
    }

    @Override
    public ApiResult<?> bySurname(SurnameHandlerDTO surnameHandlerDTO) {
        return workerService.bySurname(surnameHandlerDTO);
    }

    @Override
    public ApiResult<?> getAll() {
        return workerService.getAll();
    }

    @Override
    public ApiResult<?> update(UpdateWorkerDTO workerDTO) {
        return workerService.update(workerDTO);
    }

    @Override
    public ApiResult<?> deleteById(IdHandlerDTO idHandlerDTO) {
        return workerService.deleteById(idHandlerDTO);
    }
}
