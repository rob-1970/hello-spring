package com.sinensia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}
	
	@GetMapping("/")
	public String start(@RequestParam(value = "branch", defaultValue = "Main") String branch){
		return String.format("This is the %s branch!", branch);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}

	@GetMapping("/add")
	//public String canAdd(@RequestParam(value = "a", defaultValue = "0") String a,
	//					 @RequestParam(value = "b", defaultValue = "0") String b){
	public Integer canAdd(
			@RequestParam(value="a") Integer a,
			@RequestParam(value="b") Integer b){
		//int intFirst=Integer.parseInt(a);
		//int intSecond=Integer.parseInt(b);
		//int intResult=intFirst + intSecond;
		//return String.format("This add (", intFirst,"+" intSecond, intResult);
		return (a+b);
	}
}
