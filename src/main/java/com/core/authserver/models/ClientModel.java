package com.core.authserver.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import com.core.authserver.models.ui.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@Table(name="oauth_client_details")
public class ClientModel implements  Serializable{
	private static final long serialVersionUID = 983648240006032841L;

	@Id
	@Column(name="client_details_$$$_id")
	private String id;
	
	@Column(name="client_details_$$$_client_id",nullable=false,unique=true)
	//@NotEmpty(message = "empty_$_clientId")
	private String clientId;
	
	@Column(name="client_details_$$$_client_secret",columnDefinition="TEXT",nullable=false)
	//@NotEmpty(message = "empty_$_clientSecret")
	private String clientSecret;
	
	//@NotEmpty(message = "empty_$_accessTokenValidity")
	@Column(name="client_details_$$$_access_token_validity",nullable=false)
	private int accessTokenValidity;
	
	//@NotEmpty(message = "empty_$_scope")
	@Column(name="client_details_$$$_scope",nullable=false)
	private String scope;
	
	//@NotEmpty(message = "empty_$_authorities")
	@Column(name="client_details_$$$_authorities",nullable=false)
	private String authorities;
	
	//@NotEmpty(message = "empty_$_authorizedGrantTypes")
	@Column(name="client_details_$$$_authorized_grant_types",nullable=false)
	private String authorizedGrantTypes;
	
	//@NotEmpty(message = "empty_$_refreshTokenValidity")
	@Column(name="client_details_$$$_refresh_token_validity",nullable=false)
	private int refreshTokenValidity;

	//@NotEmpty(message = "empty_$_resourceIds")
	@Column(name="client_details_$$$_resource_ids",columnDefinition="TEXT",nullable=false)
	private String resourceIds;
	
	@Column(name="client_details_$$$_web_server_redirect_uri",columnDefinition="TEXT")
	private String webServerRedirectUri;
	
	@Column(name="client_details_$$$_autoapprove",nullable=false)
	private Boolean autoApprove=false;
	
	@Column(name="client_details_$$$_additional_information",columnDefinition="TEXT")
	private String addInfo;
	
}