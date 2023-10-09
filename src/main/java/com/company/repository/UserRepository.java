package com.company.repository;

import com.company.config.Config;
import com.company.entity.User;
import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.requests.SignInDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository{
    private final Config config = new Config();
    private final JdbcTemplate jdbcTemplate;

    {
        jdbcTemplate = config.getTemplate();
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select * from users where username = '"+username+"'";

        User user;

        try{
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "select * from users where username = '"+username+"'";

        try{
           jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        }catch (EmptyResultDataAccessException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "select * from users where email = '"+email+"'";

        try{
            jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        }catch (EmptyResultDataAccessException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean setUsername(SetUsernameDTO usernameHandlerDTO) {
        String query = "update users set username = '"
                +usernameHandlerDTO.getUsername()+"'  where email = '"+usernameHandlerDTO.getEmail()+"'";

        int result = jdbcTemplate.update(query);

        return result > 0;
    }

    @Override
    public boolean setPassword(SetPasswordDTO setPasswordDTO) {
        String sql = "update users set password = '"
                +setPasswordDTO.getPassword()+"' where username = '"+setPasswordDTO.getUsername()+"'";

        int result = jdbcTemplate.update(sql);

        return result > 0;
    }

    @Override
    public boolean checkUsernameAndPassword(SignInDTO signInDTO) {
        String sql = "select * from users where username = '"+signInDTO.getUsername()+"'";
        User user;

        try{
            user =  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        }catch (EmptyResultDataAccessException e){
            return false;
        }

        assert user != null;

        return user.getPassword().equals(signInDTO.getPassword());
    }


    @Override
    public boolean saveNewUser(User user) {
        String sql = "insert into users(email,username,password) values('"+user.getEmail()+
                "','"+user.getUsername()+"','"+user.getPassword()+"')";

        int result =  jdbcTemplate.update(sql);

        if(result <=0){
            return false;
        }

        return true;
    }
}
