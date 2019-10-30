package com.training.jdg.config;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdgConfig {
	
	@Autowired
	private JdgProperties infinispanProperties;


	@Bean
    public RemoteCacheManager remoteCacheManager() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
        
		builder.addServers(infinispanProperties.getServerList());
		builder.connectionTimeout(10000);
		builder.maxRetries(5);
		builder.socketTimeout(10000);
        
        return new RemoteCacheManager(builder.build());
    }
	
}
