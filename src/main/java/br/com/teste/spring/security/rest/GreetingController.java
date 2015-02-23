package br.com.teste.spring.security.rest;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Value( "${teste.prop}" )
	private String username;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("${greeting}")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	counter.incrementAndGet();
        return "Hello! "+counter+" - " +username;
    }
}