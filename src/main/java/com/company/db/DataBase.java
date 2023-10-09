package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank_system",
                    "bank_manager", "root");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }

    public static void initTable() {

        String workers = "create table if not exists workers ( \n" +
                "             id uuid primary key not null default gen_random_uuid(),\n" +
                "             name varchar(50) not null,\n" +
                "             surname varchar(50) not null\n" + ");";

        String users = "create table if not exists users ( \n" +
                "             id uuid primary key default gen_random_uuid(),\n" +
                "             email varchar(50) not null,\n" +
                "             username varchar(50) ,\n" +
                "             password varchar(50) ,\n" +
                "             created_date date default current_date \n" + ");";

        execute(users);
        execute(workers);
    }

    private static void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
