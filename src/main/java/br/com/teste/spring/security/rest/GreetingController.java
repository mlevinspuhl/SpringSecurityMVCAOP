package br.com.teste.spring.security.rest;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.spring.security.business.manager.RestManager;
import br.com.teste.spring.security.common.domain.Pet;
import br.com.teste.spring.security.common.exception.CommonException;

@RestController
public class GreetingController {
	
	@Value( "${teste.prop}" )
	private String username;
	
	@Autowired
	private RestManager manager;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("${greeting}")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	counter.incrementAndGet();
        return "Hello! "+counter+" - " +username;
    }
    @RequestMapping(value = "/pets/{idPet}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Pet getPet(@PathVariable Integer idPet) throws CommonException {
    	
    	
        return manager.getPet(idPet);
    }
}