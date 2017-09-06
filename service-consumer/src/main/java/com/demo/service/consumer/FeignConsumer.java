package com.demo.service.consumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.demo.service.bean.ServiceBean;
import com.demo.service.client.EurekaServiceClient;
import com.demo.service.client.SecondKillServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * spring boot thymeleaf:
 * <li>
 * https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/
 * 
 * @author gushu
 * @date 2017/08/28
 */
@SpringBootApplication(scanBasePackages = "com.demo.service.bean")
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.demo.service.client")
@Controller
public class FeignConsumer {

	@Autowired
	private EurekaServiceClient eurekaServiceClient;

	@Autowired
	private SecondKillServiceClient secondKillServiceClient;

	@Autowired
	private ServiceBean serviceBean;

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumer.class, args);
	}

	@RequestMapping("/info")
	public String collectServiceInfo(Model model) {
		serviceBean.setName("service bean injection");
		String indexStr = eurekaServiceClient.index();
		model.addAttribute("data", indexStr);
		model.addAttribute("beanInfo", serviceBean.getName());
		return "consumer";
	}

	@RequestMapping("/doLoterry")
	public String doLoterry() {

		for (int round = 0; round < 3; round++) {
			secondKillServiceClient.reset();
			int participantNum = 100;
			final CountDownLatch latch = new CountDownLatch(participantNum);
			Executor threadPool = Executors.newFixedThreadPool(10);
			for (int i = 0; i < participantNum; i++) {
				final int index = i;
				threadPool.execute(new Runnable() {
					public void run() {
						latch.countDown();
						String result = secondKillServiceClient.buy(String.valueOf(index));
						System.out.println(index + ":" + result);
					}
				});
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "secondKill";
	}

}
