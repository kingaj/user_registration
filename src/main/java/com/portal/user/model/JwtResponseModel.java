package com.portal.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JwtResponseModel {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private String email;

	public JwtResponseModel() {
	}

	public JwtResponseModel(String token, String refreshToken, String email) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
