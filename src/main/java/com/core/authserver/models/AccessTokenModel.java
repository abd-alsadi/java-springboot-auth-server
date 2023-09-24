package com.core.authserver.models;

import lombok.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import com.core.authserver.helpers.*;
import javax.persistence.*;

@Entity
@Table(name="oauth_access_token")
@Data
@Getter
@Setter
public class AccessTokenModel{

    @Id
    @Column(name="access_token_$$$_id")
    private String id;

    @Column(name="access_token_$$$_token_id",columnDefinition="TEXT",nullable=false)
    private String tokenId;

   
    @Column(name="access_token_$$$_token",columnDefinition="TEXT",nullable=false)
    private String token;
    
  
    @Column(name="access_token_$$$_authentication_id",columnDefinition="TEXT",nullable=false)
    private String authenticationId;
    
    @Column(name="access_token_$$$_user_name",nullable=false)
    private String userName;
    
    @Column(name="access_token_$$$_client_id",nullable=false)
    private String clientId;
    

    @Column(name="access_token_$$$_authentication",columnDefinition="TEXT",nullable=false)
    private String authentication;
    

    @Column(name="access_token_$$$_refresh_token",columnDefinition="TEXT",nullable=false)
    private String refreshToken;


    public OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication)SerializableObjectConverterHelper.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverterHelper.serialize(authentication);
    }
    public OAuth2AccessToken getToken() {
        return (OAuth2AccessToken)SerializableObjectConverterHelper.deserialize(token);
    }

    public void setToken(OAuth2AccessToken token) {
        this.token = SerializableObjectConverterHelper.serialize(token);
    }
}