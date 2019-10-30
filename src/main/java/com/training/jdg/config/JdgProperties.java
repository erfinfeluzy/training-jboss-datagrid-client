package com.training.jdg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("infinispan.remote")
public class JdgProperties {

	private String serverList;

	private Integer socketTimeout;

	private Integer connectTimeout;

	private Integer maxRetries;
	
}
