package com.company.utils;

import com.company.entity.WorkerEntity;
import com.company.payload.requests.UpdateWorkerDTO;

public class WorkerUtil {
    public static UpdateWorkerDTO toDto(WorkerEntity entity){
        UpdateWorkerDTO dto = new UpdateWorkerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }

    public static WorkerEntity toEntity(UpdateWorkerDTO dto){
        WorkerEntity entity = new WorkerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        return entity;
    }

}
