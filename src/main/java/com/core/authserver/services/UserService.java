package com.core.authserver.services;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.authserver.iservice.*;
import com.core.authserver.repositories.*;
import com.core.authserver.models.*;
import com.core.authserver.models.ui.*;
import com.core.authserver.dto.*;
import com.core.authserver.helpers.*;
import com.core.authserver.requests.*;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.hibernate.Session;
@Service
public class UserService implements IUserService{
	
	Logger log=LoggerFactory.getLogger(UserService.class);

	@Autowired
    private UserRepository repo;

    @Autowired
    EntityManager em;


    @Override
    public List<UserModel> GetAll(AuthunticationModel auth){
        try{
            List<UserModel> data= repo.findAll();
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<UserModel> Filter(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.UserBaseQuery,QueryModel.UserMAP,false);
        if(baseQuery==null)
        return null;

        try{
            Session session = em.unwrap(Session.class);
           org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery)
           .addEntity(QueryModel.UserTableName,UserModel.class)
           .addEntity(QueryModel.RoleTableName,RoleModel.class);
            
           nativeQuery=QueryContractHelper.buildPagging(baseQuery, nativeQuery, obj);
           List<Object[]> list  = nativeQuery.list();
           List<UserModel> result = new ArrayList<UserModel>();
            if(list!=null){
                for(Object[] row : list) {
                    UserModel user = (UserModel)row[0];
                    RoleModel role = (RoleModel)row[1];
                    user.setRole(role);
                    result.add(user);
                }
            }
            return result;
        }catch(Exception e){
            return null;
        }
        
    }

    @Override
    public int GetCount(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.UserBaseQueryCount, QueryModel.UserMAP,true);
        if(baseQuery==null)
        return -1;

        try{
             Session session = em.unwrap(Session.class);
             org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery);
             int count = ((Number) nativeQuery.getSingleResult()).intValue();
            return count;
        }catch(Exception e){
            return 0;
        }
       
    }

    @Override
    public UserModel GetByID(String id,AuthunticationModel auth){
      try{
            FilterDataRequest filterObj = QueryContractHelper.buildFiltersByID("id", id);
            List<UserModel> models = Filter(filterObj,auth);
            if(models!=null){
                return models.get(0);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public UserModel Add(UserModel object,AuthunticationModel auth){
        try{
            object.setId(UUID.randomUUID().toString()); 
           repo.save(object);
            return object;
         }catch(Exception e){
          return null;
        }
        
    }
    @Override
    public UserModel Delete(String id,AuthunticationModel auth){
        try{
            UserModel data=GetByID(id,auth);
            repo.deleteById(id);
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public UserModel Update(String id,UserModel object,AuthunticationModel auth){
        try{
            object.setId(id);
            UserModel data= repo.save(object);
            return data;
        }catch(Exception e){
            return null;
        }
    }

	
}
