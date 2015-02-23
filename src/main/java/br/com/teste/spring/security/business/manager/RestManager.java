package br.com.teste.spring.security.business.manager;

import br.com.teste.spring.security.common.domain.Pet;
import br.com.teste.spring.security.common.exception.CommonException;

public interface RestManager {

	public Pet getPet(Integer idPet)throws CommonException;
	
}
