package com.example.easydrug.NetService.Api;

import java.io.Serializable;

public class User implements Serializable {

	private String username;


	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}
}
