package com.company.repository;

import com.company.config.Config;
import com.company.entity.WorkerEntity;
import com.company.payload.requests.NameHandlerDTO;
import com.company.payload.requests.SurnameHandlerDTO;
import com.company.payload.requests.UpdateWorkerDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WorkerRepository implements IWorkerRepository{

    private final Config config = new Config();
    private final JdbcTemplate jdbcTemplate;

    {
        jdbcTemplate = config.getTemplate();
    }


    @Override
    public int create(WorkerEntity workerEntity) {
        String sql = "INSERT INTO workers (name,surname) values(?,?)";

        return jdbcTemplate.update(sql, workerEntity.getName(), workerEntity.getSurname());
    }

    @Override
    public WorkerEntity getByNameAndSurname(String name, String surname) {
        String sql = "select * from workers as w where w.name = '" + name+ "' and w.surname ='"+surname+"'";

        WorkerEntity worker;

        try {
            worker = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(WorkerEntity.class));
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return worker;
    }

    @Override
    public List<WorkerEntity> getAll() {
        String sql = "select * from workers";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkerEntity.class));
    }

    @Override
    public WorkerEntity getById(Integer id) {
        String sql = "select * from workers as w where w.id = " + id;

        WorkerEntity worker;

        try {
            worker = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(WorkerEntity.class));
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return worker;
    }

    @Override
    public List<WorkerEntity> getAllByName(NameHandlerDTO nameHandlerDTO) {
        String sql = "select * from workers as w where w.name = '" + nameHandlerDTO.getName()+ "'";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(WorkerEntity.class));
    }

    @Override
    public List<WorkerEntity> getAllBySurname(SurnameHandlerDTO surnameHandlerDTO) {
        String sql = "select * from workers as w where w.surname = '" + surnameHandlerDTO.getSurname()+"'";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(WorkerEntity.class));
    }

    @Override
    public int update(UpdateWorkerDTO workerDTO) {
        String sql = "update workers set name = '"+workerDTO.getName() +
                "' ,  surname = '" +workerDTO.getSurname()+ "'" + " where id = " +workerDTO.getId();

        return jdbcTemplate.update(sql);
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from workers as w where w.id = " + id;

        return jdbcTemplate.update(sql);
    }
}
