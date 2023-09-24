package com.core.authserver.iservice;

import java.util.List;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.requests.*;



public interface IObjectAccessListService {

	public ObjectAccessListModel Add(ObjectAccessListModel model,AuthunticationModel auth);
	
	public ObjectAccessListModel Update(String id,ObjectAccessListModel model,AuthunticationModel auth);
	
	public ObjectAccessListModel Delete(String id,AuthunticationModel auth);
	
	public List<ObjectAccessListModel> GetAll(AuthunticationModel auth);
	
	public ObjectAccessListModel GetByID(String id,AuthunticationModel auth);

	public List<ObjectAccessListModel> Filter(FilterDataRequest obj,AuthunticationModel auth);
	public int GetCount(FilterDataRequest obj,AuthunticationModel auth);
		
}
