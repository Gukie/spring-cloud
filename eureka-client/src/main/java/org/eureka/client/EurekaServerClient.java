package org.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaServerClient 
{
    public static void main( String[] args )
    {
       SpringApplication.run(EurekaServerClient.class, args);
    }
    
    @RequestMapping("/index")
    public String index() {
		return "hello from shu.gu";
	}
    
    @RequestMapping("/info")
    public String info() {
    	return "info from eureka server client";
		
	}
}
