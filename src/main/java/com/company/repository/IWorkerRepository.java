package com.company.repository;

import com.company.entity.WorkerEntity;
import com.company.payload.requests.NameHandlerDTO;
import com.company.payload.requests.SurnameHandlerDTO;
import com.company.payload.requests.UpdateWorkerDTO;

import java.util.List;

public interface IWorkerRepository {
    int create(WorkerEntity workerEntity);

    WorkerEntity getById(Integer id);

    List<WorkerEntity> getAll();

    int update(UpdateWorkerDTO workerDTO);

    List<WorkerEntity> getAllByName(NameHandlerDTO nameHandlerDTO);

    List<WorkerEntity> getAllBySurname(SurnameHandlerDTO surnameHandlerDTO);

    int deleteById(Integer id);

    WorkerEntity getByNameAndSurname(String name, String surname);
}
