package com.excilys.CDB.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.CDB.config.AppConfig;

public class AppMain {
	
	
	
	public static void main (String [] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	
		System.out.println();
	
	}
	
	
}
