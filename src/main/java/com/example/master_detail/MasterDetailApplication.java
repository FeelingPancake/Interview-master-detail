package com.example.master_detail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MasterDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterDetailApplication.class, args);
	}

}
