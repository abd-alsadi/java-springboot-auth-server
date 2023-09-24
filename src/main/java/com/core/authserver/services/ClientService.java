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
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ClientService implements IClientService{
	
	Logger log=LoggerFactory.getLogger(ClientService.class);

	@Autowired
    private ClientRepository repo;

    @Autowired
    EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<ClientModel> GetAll(AuthunticationModel auth){
        try{
            List<ClientModel> data= repo.findAll();
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<ClientModel> Filter(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.ClientBaseQuery,QueryModel.ClientMAP,false);
        if(baseQuery==null)
        return null;

        try{
            Session session = em.unwrap(Session.class);
           org.hibernate.query.Query nativeQuery = session.createNativeQuery(baseQuery)
           .addEntity(QueryModel.ClientTableName,ClientModel.class);
            
           nativeQuery=QueryContractHelper.buildPagging(baseQuery, nativeQuery, obj);
           List<Object[]> list  = nativeQuery.list();
           List<ClientModel> result = new ArrayList<ClientModel>();
            if(list!=null){
                for(Object row : list) {
                    ClientModel client = (ClientModel)row;
                    result.add(client);
                }
            }
            return result;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public int GetCount(FilterDataRequest obj,AuthunticationModel auth){
        String baseQuery =QueryContractHelper.buildFilterData(obj,QueryModel.ClientBaseQueryCount, QueryModel.ClientMAP,true);
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
    public ClientModel GetByID(String id,AuthunticationModel auth){
        try{
            FilterDataRequest filterObj = QueryContractHelper.buildFiltersByID("id", id);
            List<ClientModel> models = Filter(filterObj,auth);
            if(models!=null){
                return models.get(0);
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ClientModel Add(ClientModel object,AuthunticationModel auth){
        try{
            object.setId(UUID.randomUUID().toString()); 
            if(object.getClientSecret()!=null)
                object.setClientSecret(passwordEncoder.encode(object.getClientSecret()));
           repo.save(object);
            return object;
         }catch(Exception e){
          return null;
        }
        
    }
    @Override
    public ClientModel Delete(String id,AuthunticationModel auth){
        try{
            ClientModel data=GetByID(id,auth);
            repo.deleteById(id);
            return data;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ClientModel Update(String id,ClientModel object,AuthunticationModel auth){
        try{
            object.setId(id);
            ClientModel data= repo.save(object);
            return data;
        }catch(Exception e){
            return null;
        }
    }

	
}
