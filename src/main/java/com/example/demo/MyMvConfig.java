package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SuppressWarnings("deprecation")
@Component
public class MyMvConfig extends WebMvcConfigurerAdapter{
  @Autowired
  private AuthInterceptor auth;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(auth);
  }
}