package com.core.authserver.iservice;

import java.util.List;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.requests.*;



public interface IAccessListService {

	public AccessListModel Add(AccessListModel model,AuthunticationModel auth);
	
	public AccessListModel Update(String id,AccessListModel model,AuthunticationModel auth);
	
	public AccessListModel Delete(String id,AuthunticationModel auth);
	
	public List<AccessListModel> GetAll(AuthunticationModel auth);
	
	public AccessListModel GetByID(String id,AuthunticationModel auth);

	public List<AccessListModel> Filter(FilterDataRequest obj,AuthunticationModel auth);
	public int GetCount(FilterDataRequest obj,AuthunticationModel auth);
		
}
