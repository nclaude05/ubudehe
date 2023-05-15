package com.ubudeheSystem.Ubudehe.App;

import com.ubudeheSystem.Ubudehe.App.Services.FilesStorageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class UbudeheSystemAppApplication implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(UbudeheSystemAppApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
