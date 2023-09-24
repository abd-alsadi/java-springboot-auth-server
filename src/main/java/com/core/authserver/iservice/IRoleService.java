package com.core.authserver.iservice;

import java.util.List;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.requests.*;



public interface IRoleService {

	public RoleModel Add(RoleModel model,AuthunticationModel auth);
	
	public RoleModel Update(String id,RoleModel model,AuthunticationModel auth);
	
	public RoleModel Delete(String id,AuthunticationModel auth);
	
	public List<RoleModel> GetAll(AuthunticationModel auth);
	
	public RoleModel GetByID(String id,AuthunticationModel auth);

	public List<RoleModel> Filter(FilterDataRequest obj,AuthunticationModel auth);
	public int GetCount(FilterDataRequest obj,AuthunticationModel auth);
		
}
