package com.company.controller;

import com.company.payload.requests.*;
import com.company.payload.responses.ApiResult;
import com.company.utils.AppConstants;
import org.springframework.web.bind.annotation.*;

@RequestMapping(IWorkerController.BASE_PATH)
public interface IWorkerController {
    String BASE_PATH = AppConstants.BASE_PATH +"/worker";
    String BY_ID = "/by-id";
    String ALL_BY_NAME = "/by-name";
    String ALL_BY_SURNAME = "/by-surname";
    String ALL = "/all";
    String CREATE = "/create";
    String UPDATE = "/update";
    String DELETE_BY_ID = "/delete/by-id";

    @PostMapping(value = CREATE)
    ApiResult<?> create(@RequestBody CreateWorkerDTO createWorkerDTO);

    @GetMapping(value = BY_ID)
    ApiResult<?> byId(@RequestBody IdHandlerDTO checkById);

    @GetMapping(value = ALL_BY_NAME)
    ApiResult<?> byName(@RequestBody NameHandlerDTO nameHandlerDTO);

    @GetMapping(value = ALL_BY_SURNAME)
    ApiResult<?> bySurname(@RequestBody SurnameHandlerDTO surnameHandlerDTO);

    @GetMapping(value = ALL)
    ApiResult<?> getAll();

    @PutMapping(value = UPDATE)
    ApiResult<?> update(@RequestBody UpdateWorkerDTO workerDTO);

    @DeleteMapping(value = DELETE_BY_ID)
    ApiResult<?> deleteById(@RequestBody IdHandlerDTO idHandlerDTO);

}
