package org.second.kill.client;

import org.second.kill.base.BaseResult;
import org.second.kill.service.SecondKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = { "org.second.kill.service" })
@EnableEurekaClient
@RestController
public class SecondKillApp {
	public static void main(String[] args) {
		SpringApplication.run(SecondKillApp.class, args);
	}

	@Autowired
	private SecondKillService secondKillService;
	
	
	@RequestMapping("/reset")
	public String resetOrder(){
		secondKillService.resetOrder();
		return BaseResult.SUCCESS;
	}

	@RequestMapping("/buy")
	public String buy(String userId) {
		if (StringUtils.isEmpty(userId)) {
			return BaseResult.NOTHING;
		}
		return secondKillService.buy(userId);
	}
}
