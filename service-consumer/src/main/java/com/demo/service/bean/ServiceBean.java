package com.demo.service.bean;

import org.springframework.stereotype.Component;

/**
 * @author gushu
 * @date 2017/08/29
 */
@Component
public class ServiceBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
