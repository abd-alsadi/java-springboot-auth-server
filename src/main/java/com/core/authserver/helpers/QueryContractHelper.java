package com.core.authserver.helpers;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.QueryModel.ColumnData;
import com.core.authserver.dto.*;
import com.core.authserver.requests.*;
import java.util.List;
import java.util.*;
import javax.persistence.*;
public class QueryContractHelper {
    
    public static String buildFilterData(FilterDataRequest obj,String query,Map<String,ColumnData> map,boolean isCount){
            String baseQuery = query;
      
            String sortQuery = "";
            String condQuery = "";
            SortUI sort=obj.getSort();
            List<FilterUI> filters=obj.getFilters();
            if(sort!=null && !isCount){
                ColumnData checkObj = map.get(sort.getColumn());
                if(checkObj==null)
                return null;
                sortQuery=QueryContractHelper.buildSortString(sort,map);
            }
            Integer index=1;
            if(filters!=null && filters.size()>0){
                for (FilterUI filter : filters) {
                    ColumnData checkObj =map.get(filter.getName());
                    if(checkObj==null)
                    return null;
                                     
                    String xx=QueryContractHelper.buildFilterString(filter,map);
                    if(!(xx==null && checkObj.getType()!="string")){
                        if(index>1)
                        condQuery+=" and ";
                        condQuery+=xx;
                        index++;
                    }
                }
                
            }
        
            if(condQuery!=""){
                baseQuery+=" where " + condQuery;
            }
            if(sortQuery!="" && !isCount){
                baseQuery+=" order by " + sortQuery;
            }
            return baseQuery;
            
    }
    public static String buildFilterString(FilterUI filter,Map<String,ColumnData> map){
        String result="";
        String name =filter.getName();
        String value=filter.getValues().get(0);
        String op = filter.getOp();
        String type="string";
        ColumnData col =map.get(name);
        if (col!=null){
            name = col.getDb();
            type = col.getType();
        }
     
        if(type=="int"){
            if(value==null || value.trim()=="")
            return null;
            value=" = " + value + " ";
        }else  if(type=="boolean"){
            if(value==null || value.trim()=="")
            return null;
            if(value.trim().equals("0") || value.trim()=="false")
            value=" = " + false + " ";
            else
            value=" = " + true + " ";
        }
        else if(type=="uid"){
            value=" = '" + value + "' ";
        }else{
            value=" like '%" + value + "%' ";
        }
         
        String tableName=col.getTable();
        result=tableName + "." +name+ " " + value;
        return result;
    }

    public static org.hibernate.query.Query buildPagging(String query ,org.hibernate.query.Query nativeQuery,FilterDataRequest obj){

        int firsIndex =0;
        if(query==null)
        return null;
   
        firsIndex=(obj.getPage() -1) * obj.getLimit();
        
        nativeQuery.setFirstResult(firsIndex);
        nativeQuery.setMaxResults(obj.getLimit());
        return nativeQuery;
    }
    public static String buildSortString(SortUI sort,Map<String,ColumnData> map){
        String result="";
        String name =sort.getColumn();
        String dir=sort.getDirection();
        ColumnData col =map.get(name);
        if (col!=null){
            name = col.getDb();
        }
        String tableName=col.getTable();
        result = tableName + "." +name + " "+ dir;
        return result;
    }
    public static FilterDataRequest buildFiltersByID(String nameParam , String valueParam){
        try{
            FilterDataRequest filterObj = new FilterDataRequest();
            filterObj.setPage(1);
            filterObj.setLimit(1);
            List<String> value = new ArrayList<String>();
            value.add(valueParam);
            FilterUI ff = new FilterUI(nameParam,"",value);
            List<FilterUI> lstFilters = new ArrayList<FilterUI>();
            lstFilters.add(ff);
            filterObj.setFilters(lstFilters);
            return filterObj;
        }catch(Exception e){
            return null;
        }
    }
   
}
