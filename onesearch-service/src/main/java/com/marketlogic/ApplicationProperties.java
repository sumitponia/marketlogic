package com.marketlogic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public final class ApplicationProperties {

	public String userName;
	public String password;
	public Boolean enableSecurity;

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
