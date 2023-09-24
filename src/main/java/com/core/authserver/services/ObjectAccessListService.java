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
public class ObjectAccessListService implements IObjectAccessListService{
	
	Logger log=LoggerFactory.getLogger(ObjectAccessListService.class);

	@Autowired
    private ObjectAccessListRepository repo;

    @Autowired
    EntityManager em;

    @Override
    public List<ObjectAccessListModel> GetAll(AuthunticationModel auth){
        try{
            List<ObjectAccessListModel> data= repo.findAll();
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<ObjectAccessListModel> Filter(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.ObjectAccessListBaseQuery,QueryModel.ObjectAccessListMAP,false);
        if(baseQuery==null)
        return null;

        try{
            Session session = em.unwrap(Session.class);
           org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery)
           .addEntity(QueryModel.ObjectAccessListTableName,ObjectAccessListModel.class)
           .addEntity(QueryModel.ClientTableName,ClientModel.class);
            
           nativeQuery=QueryContractHelper.buildPagging(baseQuery, nativeQuery, obj);
           List<Object[]> list  = nativeQuery.list();
           List<ObjectAccessListModel> result = new ArrayList<ObjectAccessListModel>();
            if(list!=null){
                for(Object[] row : list) {
                    ObjectAccessListModel objectAccessListModel = (ObjectAccessListModel)row[0];
                    ClientModel client = (ClientModel)row[1];
                    objectAccessListModel.setClient(client);
                    result.add(objectAccessListModel);
                }
            }
            return result;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public int GetCount(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.ObjectAccessListBaseQueryCount,QueryModel.ObjectAccessListMAP,true);
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
    public ObjectAccessListModel GetByID(String id,AuthunticationModel auth){
        try{
            FilterDataRequest filterObj = QueryContractHelper.buildFiltersByID("id", id);
            List<ObjectAccessListModel> models = Filter(filterObj,auth);
            if(models!=null){
                return models.get(0);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ObjectAccessListModel Add(ObjectAccessListModel object,AuthunticationModel auth){
        try{
            object.setId(UUID.randomUUID().toString()); 
           repo.save(object);
            return object;
         }catch(Exception e){
          return null;
        }
        
    }
    @Override
    public ObjectAccessListModel Delete(String id,AuthunticationModel auth){
        try{
            ObjectAccessListModel data=GetByID(id,auth);
            repo.deleteById(id);
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ObjectAccessListModel Update(String id,ObjectAccessListModel object,AuthunticationModel auth){
        try{
            object.setId(id);
            ObjectAccessListModel data= repo.save(object);
            return data;
        }catch(Exception e){
            return null;
        }
    }

	
}
