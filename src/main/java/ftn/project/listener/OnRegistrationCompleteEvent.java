package ftn.project.listener;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import ftn.project.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	public OnRegistrationCompleteEvent(
		      User user, Locale locale, String appUrl) {
		        super(user);
		         
		        this.user = user;
		        this.locale = locale;
		        this.appUrl = appUrl;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String appUrl;
	private Locale locale;
	private User user;
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
