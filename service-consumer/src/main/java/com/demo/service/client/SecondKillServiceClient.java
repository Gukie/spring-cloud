package com.demo.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gushu
 * @date 2017/09/06
 */
@FeignClient("second-kill-client")
public interface SecondKillServiceClient {

	@RequestMapping("/buy")
	String buy(@RequestParam("userId") String userId);
	
	@RequestMapping("reset")
	String reset();

}
