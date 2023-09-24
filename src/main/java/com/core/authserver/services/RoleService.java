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
public class RoleService implements IRoleService{
	
	Logger log=LoggerFactory.getLogger(RoleService.class);

	@Autowired
    private RoleRepository repo;

    @Autowired
    EntityManager em;

    @Override
    public List<RoleModel> GetAll(AuthunticationModel auth){
        try{
            List<RoleModel> data= repo.findAll();
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<RoleModel> Filter(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.RoleBaseQuery,QueryModel.RoleMAP,false);
        if(baseQuery==null)
        return null;

        try{
            Session session = em.unwrap(Session.class);
           org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery)
           .addEntity(QueryModel.RoleTableName,RoleModel.class)
           .addEntity(QueryModel.ClientTableName,ClientModel.class);
            
           nativeQuery=QueryContractHelper.buildPagging(baseQuery, nativeQuery, obj);
           List<Object[]> list  = nativeQuery.list();
           List<RoleModel> result = new ArrayList<RoleModel>();
            if(list!=null){
                for(Object[] row : list) {
                    RoleModel role = (RoleModel)row[0];
                    ClientModel client = (ClientModel)row[1];
                    role.setClient(client);
                    result.add(role);
                }
            }
            return result;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public int GetCount(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.RoleBaseQueryCount, QueryModel.RoleMAP,true);
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
    public RoleModel GetByID(String id,AuthunticationModel auth){
        
        try{
            FilterDataRequest filterObj = QueryContractHelper.buildFiltersByID("id", id);
            List<RoleModel> models = Filter(filterObj,auth);
            if(models!=null){
                return models.get(0);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public RoleModel Add(RoleModel object,AuthunticationModel auth){
        try{
            object.setId(UUID.randomUUID().toString()); 
           repo.save(object);
            return object;
         }catch(Exception e){
          return null;
        }
        
    }
    @Override
    public RoleModel Delete(String id,AuthunticationModel auth){
        try{
            RoleModel data=GetByID(id,auth);
            repo.deleteById(id);
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public RoleModel Update(String id,RoleModel object,AuthunticationModel auth){
        try{
            object.setId(id);
            RoleModel data= repo.save(object);
            return data;
        }catch(Exception e){
            return null;
        }
    }

	
}
