package br.com.teste.spring.security.config.core;

import java.util.Calendar;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("==== Session is created==== "+Calendar.getInstance().getTime());
		event.getSession().setMaxInactiveInterval(60);//60 segundos
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("==== Session is destroyed ==== "+Calendar.getInstance().getTime());
	}
}