package com.company.service;

import com.company.entity.WorkerEntity;
import com.company.payload.requests.*;
import com.company.payload.responses.ApiResult;
import com.company.repository.IWorkerRepository;
import com.company.utils.WorkerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkerService implements IWorkerService{
    private final IWorkerRepository workerRepository;

    @Override
    public ApiResult<?> create(CreateWorkerDTO createWorkerDTO){
        WorkerEntity worker = workerRepository.getByNameAndSurname(createWorkerDTO.getName(),createWorkerDTO.getSurname());

        if(Objects.nonNull(worker)){
            return ApiResult.failResponse("This worker already exists!",402);
        }

        worker = new WorkerEntity();
        worker.setName(createWorkerDTO.getName());
        worker.setSurname(createWorkerDTO.getSurname());

        int n = workerRepository.create(worker);

        if(n<0){
            return ApiResult.failResponse("failed",401);
        }

        return ApiResult.successResponse("saved");
    }
    @Override
    public ApiResult<?> getById(IdHandlerDTO checkById){
        WorkerEntity worker = workerRepository.getById(checkById.getId());

        if(worker == null){
           return ApiResult.failResponse("Not exists",400);
        }

        return ApiResult.successResponse(worker);
    }

    @Override
    public ApiResult<?> byName(NameHandlerDTO nameHandlerDTO) {
        List<WorkerEntity> list = workerRepository.getAllByName(nameHandlerDTO);

        if(list.isEmpty()){
            return ApiResult.failResponse("Not exists",400);
        }

        return ApiResult.successResponse(list);
    }

    @Override
    public ApiResult<?> bySurname(SurnameHandlerDTO surnameHandlerDTO) {
        List<WorkerEntity> list = workerRepository.getAllBySurname(surnameHandlerDTO);

        if(list.isEmpty()){
            return ApiResult.failResponse("Not exists",400);
        }

        return ApiResult.successResponse(list);
    }

    @Override
    public ApiResult<?>  getAll(){
        List<WorkerEntity> list = workerRepository.getAll();
        return ApiResult.successResponse(list);
    }

    @Override
    public ApiResult<?> update(UpdateWorkerDTO workerDTO) {
        WorkerEntity worker = workerRepository.getById(workerDTO.getId());

        if(worker == null){
            return ApiResult.failResponse("Not exists",400);
        }

        int result =  workerRepository.update(workerDTO);

        if(result < 0){
            return ApiResult.failResponse("Worker with this id not found or something went wrong.",401);
        }

        return ApiResult.successResponse("Successfully updated!");
    }

    @Override
    public ApiResult<?> deleteById(IdHandlerDTO idHandlerDTO) {
        int result = workerRepository.deleteById(idHandlerDTO.getId());

        if(result<0){
            return ApiResult.failResponse("Worker with this id not found or something went wrong.",401);
        }

        return ApiResult.successResponse("Successfully deleted!");
    }
}
