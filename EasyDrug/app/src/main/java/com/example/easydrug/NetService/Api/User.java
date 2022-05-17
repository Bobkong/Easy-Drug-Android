package com.example.easydrug.NetService.Api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User implements Serializable {
	@SerializedName("username")
	String username;
	@SerializedName("password")
	String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

