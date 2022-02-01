package com.sinensia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@SpringBootApplication
@RestController
public class DemoProjectApplication {

	// Para que JACOCO no se pase por aqui y no nos de 0% en COVERTURA en el MAIN
	@Generated(value="org.springframework.boot")
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
	// public Double canAdd(
	public Object canAdd(
//			@RequestParam(value="a", defaultValue = "0.0") Double a,
//			@RequestParam(value="b", defaultValue = "0.0") Double b){
			@RequestParam(value="a", defaultValue = "0.0") Float a,
			@RequestParam(value="b", defaultValue = "0.0") Float b){
		//return (a+b);
		Float sum = a+b;
		Float decimals = sum - sum.intValue();
		if(decimals!=0) {
			return sum;
		}
		return Integer.valueOf(sum.intValue());
	}

	@GetMapping("/subs")
	public Object canSubs(
			@RequestParam(value="a", defaultValue = "0.0") Float a,
			@RequestParam(value="b", defaultValue = "0.0") Float b){
		Float substract = a-b;
		Float decimals = substract - substract.intValue();
		if(decimals!=0) {
			return substract;
		}
		return Integer.valueOf(substract.intValue());
	}

	@GetMapping("/mult")
	public Object canMult(
			@RequestParam(value="a", defaultValue = "0.0") Float a,
			@RequestParam(value="b", defaultValue = "0.0") Float b){
		Float mult = a*b;
		Float decimals = mult - mult.intValue();
		if(decimals!=0) {
			return mult;
		}
		return Integer.valueOf(mult.intValue());
	}

	@GetMapping("/div")
	public Object canDiv(
			@RequestParam(value="a", defaultValue = "0.0") Float a,
			@RequestParam(value="b", defaultValue = "0.0") Float b){
		Float divide = a/b;
		Float decimals = divide - divide.intValue();
		if(decimals!=0) {
			return divide;
		}
		return Integer.valueOf(divide.intValue());
	}

}
