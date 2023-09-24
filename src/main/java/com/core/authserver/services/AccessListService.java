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
public class AccessListService implements IAccessListService{
	
	Logger log=LoggerFactory.getLogger(AccessListService.class);

	@Autowired
    private AccessListRepository repo;

    @Autowired
    EntityManager em;

    @Override
    public List<AccessListModel> GetAll(AuthunticationModel auth){
        try{
            List<AccessListModel> data= repo.findAll();
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<AccessListModel> Filter(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.AccessListBaseQuery,QueryModel.AccessListMAP,false);
        if(baseQuery==null)
        return null;

        try{
            Session session = em.unwrap(Session.class);
           org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery)
           .addEntity(QueryModel.AccessListTableName,AccessListModel.class)
           .addEntity(QueryModel.ObjectAccessListTableName,ObjectAccessListModel.class)
           .addEntity(QueryModel.UserTableName,UserModel.class)
           .addEntity(QueryModel.RoleTableName,RoleModel.class);

           nativeQuery=QueryContractHelper.buildPagging(baseQuery, nativeQuery, obj);
           List<Object[]> list  = nativeQuery.list();
           List<AccessListModel> result = new ArrayList<AccessListModel>();
            if(list!=null){
                for(Object[] row : list) {
                    AccessListModel accessListModel = (AccessListModel)row[0];
                    ObjectAccessListModel objectAccessListModel = (ObjectAccessListModel)row[1];
                    UserModel user = (UserModel)row[2];
                    RoleModel role = (RoleModel)row[3];
                    accessListModel.setObject(objectAccessListModel);
                    accessListModel.setRole(role);
                    accessListModel.setUser(user);
                    result.add(accessListModel);
                }
            }
            return result;
        }catch(Exception e){
            return null;
        }
        
    }

    @Override
    public int GetCount(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.AccessListBaseQueryCount, QueryModel.AccessListMAP,true);
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
    public AccessListModel GetByID(String id,AuthunticationModel auth){
        try{
            FilterDataRequest filterObj = QueryContractHelper.buildFiltersByID("id", id);
            List<AccessListModel> models = Filter(filterObj,auth);
            if(models!=null){
                return models.get(0);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public AccessListModel Add(AccessListModel object,AuthunticationModel auth){
        try{
            object.setId(UUID.randomUUID().toString()); 
           repo.save(object);
            return object;
         }catch(Exception e){
          return null;
        }
        
    }
    @Override
    public AccessListModel Delete(String id,AuthunticationModel auth){
        try{
            AccessListModel data=GetByID(id,auth);
            repo.deleteById(id);
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public AccessListModel Update(String id,AccessListModel object,AuthunticationModel auth){
        try{
            object.setId(id);
            AccessListModel data= repo.save(object);
            return data;
        }catch(Exception e){
            return null;
        }
    }

	
}
