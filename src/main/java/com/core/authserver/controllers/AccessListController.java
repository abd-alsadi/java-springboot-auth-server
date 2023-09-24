package com.core.authserver.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import com.core.authserver.services.*;
import com.core.authserver.models.*;
import com.core.authserver.requests.FilterDataRequest;
import com.core.authserver.responses.DataResponse;
import com.core.authserver.responses.FilterDataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import java.util.*;

import javax.validation.Valid;
import com.core.authserver.models.ui.*;
import com.core.authserver.constants.ModConstant;
import com.core.authserver.helpers.AuthHelper;

@RestController
@RequestMapping("api/AccessList")
@RequiredArgsConstructor
public class AccessListController{

    @Autowired
    private AccessListService service;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/GetAll")
    public ResponseEntity<DataResponse> GetAll(Authentication auth){
        DataResponse response=new DataResponse();
		try{
            AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
			List<AccessListModel> data= service.GetAll(authModel);
			response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
		}catch(Exception e){
			response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
			logger.info(ModConstant.NOT_SUCCESS_TAG);
		}
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/Filter")
    public ResponseEntity<FilterDataResponse> Filter(@Valid @RequestBody FilterDataRequest object,Authentication auth){
        FilterDataResponse response=new FilterDataResponse();
        try{
            AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
            List<AccessListModel> data= service.Filter(object,authModel);
            int count= service.GetCount(object,authModel);
            response = new FilterDataResponse(null, data, count,ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
            response = new FilterDataResponse(e.getMessage(), null, 0,ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/GetByID/{id}")
    public ResponseEntity<DataResponse> GetByID(@Valid  @PathVariable("id") String id,Authentication auth){
        DataResponse response=new DataResponse();
        try{
            AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
            AccessListModel data= service.GetByID(id,authModel);
             response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
             response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PostMapping("/Add")
     public ResponseEntity<DataResponse> Add( @RequestBody AccessListModel object,Authentication auth){
         DataResponse response=new DataResponse();
         try{
            AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
            AccessListModel data= service.Add(object,authModel);
             response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
         }catch(Exception e){
             response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
         }
         return new ResponseEntity(response, HttpStatus.OK);
    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<DataResponse> Delete(@Valid  @PathVariable("id") String id,Authentication auth){
        DataResponse response=new DataResponse();
        try{
            AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
            AccessListModel data= service.Delete(id,authModel);
            response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
           response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<DataResponse> Update(@Valid  @PathVariable("id") String id,@Valid  @RequestBody AccessListModel object,Authentication auth){
        DataResponse response=new DataResponse();
       try{
        AuthunticationModel authModel = AuthHelper.getAuthunticationUser(auth);
        AccessListModel data= service.Update(id,object,authModel);
            response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
       }catch(Exception e){
            response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
       }
       return new ResponseEntity(response, HttpStatus.OK);
    }

}