package com.company;

import com.company.db.DataBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemBackendApplication {

	public static void main(String[] args) {
		DataBase.initTable();
		SpringApplication.run(SystemBackendApplication.class, args);
	}


}
