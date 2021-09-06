package com.marketlogic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public final class ApplicationProperties {

	private String userName;
	private String password;
	private Boolean enableSecurity;
	private String onesearchUsername;
	private String onesearchPassword;
	
	public String getOnesearchUsername() {
		return onesearchUsername;
	}

	public void setOnesearchUsername(String onesearchUsername) {
		this.onesearchUsername = onesearchUsername;
	}

	public String getOnesearchPassword() {
		return onesearchPassword;
	}

	public void setOnesearchPassword(String onesearchPassword) {
		this.onesearchPassword = onesearchPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnableSecurity(Boolean enableSecurity) {
		this.enableSecurity = enableSecurity;
	}

	public boolean getEnableSecurity() {
		return enableSecurity;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}
}