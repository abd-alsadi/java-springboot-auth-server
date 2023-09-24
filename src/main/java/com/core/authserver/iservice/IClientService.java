package com.core.authserver.iservice;

import java.util.List;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.requests.*;



public interface IClientService {

	public ClientModel Add(ClientModel model,AuthunticationModel auth);
	
	public ClientModel Update(String id,ClientModel model,AuthunticationModel auth);
	
	public ClientModel Delete(String id,AuthunticationModel auth);
	
	public List<ClientModel> GetAll(AuthunticationModel auth);
	
	public ClientModel GetByID(String id,AuthunticationModel auth);

	public List<ClientModel> Filter(FilterDataRequest obj,AuthunticationModel auth);
	public int GetCount(FilterDataRequest obj,AuthunticationModel auth);
		
}
