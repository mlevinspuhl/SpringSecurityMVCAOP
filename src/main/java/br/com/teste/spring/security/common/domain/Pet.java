package br.com.teste.spring.security.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {
	
	@JsonProperty("id_pet")
	private Integer idPet;
	private String name;
	
	
	
	
	public Pet() {
	}
	public Pet(Integer idPet, String name) {
		this.idPet = idPet;
		this.name = name;
	}

	public Integer getIdPet() {
		return idPet;
	}
	public void setIdPet(Integer idPet) {
		this.idPet = idPet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
