package com.core.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.CustomClientDetails;
import com.core.authserver.repositories.*;

@Service
public class CustomClientDetailsService implements ClientDetailsService {
	
	@Autowired
	private ClientRepository cRepo;


	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		ClientModel c=cRepo.findByClientId(clientId);
		
		if(c==null)
			throw new ClientRegistrationException("client with "+clientId +" is not available");
		

		
		return new CustomClientDetails(c);
	}

}
