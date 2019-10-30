package com.training.jdg.rest;


import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.api.BasicCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Jboss Data Grid Client API")
@RestController
public class JdgClientRestController {
	
	@Autowired
	private RemoteCacheManager remoteCacheManager;
	
	@Value("${cache.name}")
    private String cacheName;
	

	@GetMapping("/get/{key}")
	@ApiOperation(value = "Get Value from Key", response = JdgBean.class)
	public JdgBean getValueFromCache (@PathVariable  String key) {
		
		
		System.out.println("get from datagrid with key:" + key);
		
		try {
			
			
			BasicCache <String, JdgBean> cache = remoteCacheManager.getCache(cacheName);

			JdgBean result = cache.get(key);
			
			if(result == null) throw new RuntimeException("no result on cache");
			
			System.out.println("success get cache:" + result);
			
            return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new JdgBean();
		}
		
		
	}
	
	@PostMapping("/post/{key}")
	@ApiOperation(value = "Post Key and Value", response = JdgBean.class)
	public JdgBean putToCache (@RequestBody  JdgBean jdgBean) {
		
		
		System.out.println("post to datagrid:" + jdgBean);
		
		try {
			
			
			BasicCache <String, JdgBean> cache = remoteCacheManager.getCache(cacheName);

			cache.put(jdgBean.getKey(), jdgBean);
			
			System.out.println("success add cache");
			
            return jdgBean;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return new JdgBean();
		}
	}

	
}
