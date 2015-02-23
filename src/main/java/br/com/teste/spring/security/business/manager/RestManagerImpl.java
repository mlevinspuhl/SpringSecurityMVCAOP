package br.com.teste.spring.security.business.manager;

import org.springframework.stereotype.Component;

import br.com.teste.spring.security.common.domain.Pet;
import br.com.teste.spring.security.common.exception.CommonException;
@Component(value="restManager")
public class RestManagerImpl implements RestManager {

	@Override
	public Pet getPet(Integer idPet) throws CommonException {
		
			
			Pet pet = new Pet();
			
			switch(idPet){
			case 0: pet.setName("Kiko");
			break;
			case 1: pet.setName("Willy");
			break;
			default : throw new CommonException("No pet found!");
			}
			pet.setIdPet(idPet);
			return pet;
		}
	}


