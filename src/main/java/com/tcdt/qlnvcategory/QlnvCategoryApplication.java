package com.tcdt.qlnvcategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.tcdt.qlnvcategory.entities","com.tcdt.qlnvcategory.table" })
public class QlnvCategoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(QlnvCategoryApplication.class, args);
	}

}
