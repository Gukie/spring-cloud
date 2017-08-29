package com.demo.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gushu
 * @date 2017/08/29
 */
@FeignClient("eureka-client")
public interface EurekaServiceClient {
	
	@RequestMapping("/index")
	String index();

}
