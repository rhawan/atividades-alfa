package br.com.alfa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zk.ui.http.HttpSessionListener;

@SpringBootApplication
@ComponentScan
public class ContaBancariaWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ContaBancariaWebApplication.class, args);
	}
	
	/*
	 * ZK servlets
	 */
	@Bean
	public ServletRegistrationBean dHtmlLayoutServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau");
		DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
		ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
		reg.setLoadOnStartup(1);
		reg.setInitParameters(params);
		return reg;
	}

	@Bean
	public ServletRegistrationBean dHtmlUpdateServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau/*");
		ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
		reg.setLoadOnStartup(2);
		reg.setInitParameters(params);
		return reg;
	}
	
	@Bean
	public HttpSessionListener httpSessionListener() {
		return new HttpSessionListener();
	}
}
