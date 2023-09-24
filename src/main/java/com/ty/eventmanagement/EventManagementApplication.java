package com.ty.eventmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EventManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}

	List<VendorExtension> extensions = new ArrayList<VendorExtension>();
	Contact contact = new Contact("Tejas", "https://sportsclub.com", "tejgowda06@gmail.com");

	ApiInfo apiInfo = new ApiInfo("Sports Club Project", "Apis to book sport", "Snapshot-0.0.1",
			"https://sportsclub.com", contact, "www.sportsclub.com", "terms and conditions", extensions);

	@Bean
	public Docket myDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ty"))
				.build().apiInfo(apiInfo);
	}

}
