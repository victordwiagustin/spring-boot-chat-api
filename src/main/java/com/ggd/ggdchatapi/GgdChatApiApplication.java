package com.ggd.ggdchatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ggd.ggdchatapi"})
public class GgdChatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgdChatApiApplication.class, args);
	}

}

