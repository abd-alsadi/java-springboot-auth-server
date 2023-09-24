package com.core.authserver.iservice;

import java.util.List;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.requests.*;



public interface IUserService {

	public UserModel Add(UserModel model,AuthunticationModel auth);
	
	public UserModel Update(String id,UserModel model,AuthunticationModel auth);
	
	public UserModel Delete(String id,AuthunticationModel auth);
	
	public List<UserModel> GetAll(AuthunticationModel auth);
	
	public UserModel GetByID(String id,AuthunticationModel auth);

	public List<UserModel> Filter(FilterDataRequest obj,AuthunticationModel auth);
	public int GetCount(FilterDataRequest obj,AuthunticationModel auth);
		
}
