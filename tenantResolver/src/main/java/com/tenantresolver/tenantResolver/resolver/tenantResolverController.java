package com.tenantresolver.tenantResolver.resolver;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class tenantResolverController {
	
	@Autowired DBConfigurationRepository dbConfigurationRepository;
	
	@GetMapping("/{id}")
	public DBConfiguration getTenantConfiguration(@PathVariable("id") Integer id) {
		System.out.println(">>>>>>>>>>>>>" + id);
		return dbConfigurationRepository.findById(id).get();
		
	}
	
	@PostMapping("/")
	public DBConfiguration setTenantConfiguration(@RequestBody DBConfiguration dbConfig) {
		System.out.println(dbConfig);
		return dbConfigurationRepository.save(dbConfig);
		
	}

}
