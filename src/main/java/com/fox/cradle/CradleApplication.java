package com.fox.cradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CradleApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CradleApplication.class, args);
	}
}
