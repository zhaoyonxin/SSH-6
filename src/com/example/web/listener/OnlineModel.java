package com.example.web.listener;

import java.io.Serializable;

/**
 * 在线用户(登录)信息封装类 
 * @author Administrator
 */
public class OnlineModel implements Serializable {

	private static final long serialVersionUID = 3301426383744731294L;
	
	private Object userId;
	private String username;
	private String ipAddress;
	private String sessionId;
	private String loadTime;
	
	public OnlineModel() {
		super();
	}

	public OnlineModel(Object userId, String username, String ipAddress,
			String sessionId, String loadTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.ipAddress = ipAddress;
		this.sessionId = sessionId;
		this.loadTime = loadTime;
	}

	public Object getUserId() {
		return userId;
	}

	public void setUserId(Object userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OnlineModel other = (OnlineModel) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
