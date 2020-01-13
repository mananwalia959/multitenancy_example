package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class TenantConfigurationResolver {
	
	public Mono<DBConfiguration> resolveTenantConfiguration(Object object){
		if(!(object instanceof String))
			throw new TenantNotResolvedException("could not find a tenant");
		String tenantkey = (String)object;
		
		return WebClient.builder().baseUrl("http://localhost:8080/" +tenantkey )
		.build().get().retrieve().bodyToMono(DBConfiguration.class)
		;
		
		
		
//		if(tenantkey.equals("1")) {
//			DBConfiguration dbConfigure = new DBConfiguration();
//			dbConfigure.setHost("localhost");
//			dbConfigure.setDatabase("tenant1");
//			dbConfigure.setUser("sa");
//			dbConfigure.setPassword("catuser@123");
//			dbConfigure.setPort(1433);
//			return Mono.just(dbConfigure);
//		}
		
//		if(tenantkey.equals("2")) {
//			DBConfiguration dbConfigure = new DBConfiguration();
//			dbConfigure.setHost("localhost");
//			dbConfigure.setDatabase("tenant2");
//			dbConfigure.setUser("sa");
//			dbConfigure.setPassword("catuser@123");
//			dbConfigure.setPort(1433);
//			return Mono.just(dbConfigure);
//		}
//		
//		if(tenantkey.equals("3")) {
//			DBConfiguration dbConfigure = new DBConfiguration();
//			dbConfigure.setHost("localhost");
//			dbConfigure.setDatabase("tenant3");
//			dbConfigure.setUser("sa");
//			dbConfigure.setPassword("catuser@123");
//			dbConfigure.setPort(1433);
//			return Mono.just(dbConfigure);
//		}
//		
//		throw new TenantNotResolvedException("could not find a tenant");
//	}
	}
}
