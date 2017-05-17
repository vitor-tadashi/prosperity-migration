package br.com.prosperity.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		String springConfig = "spring/batch/jobs/prosperity-batch.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);	
		
		System.out.println("Done");
	}
}