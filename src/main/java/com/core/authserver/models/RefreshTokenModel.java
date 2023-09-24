package com.core.authserver.models;

import lombok.*;

import javax.persistence.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import java.io.Serializable;

import com.core.authserver.helpers.SerializableObjectConverterHelper;
import com.core.authserver.models.ui.*;

@Data
@Getter
@Setter
@Entity
@Table(name="oauth_refresh_token")
public class RefreshTokenModel{

    @Id
    @Column(name="refresh_token_$$$_id")
    private String id;


    @Column(name="refresh_token_$$$_token_id",columnDefinition="TEXT",nullable=false)
    private String tokenId;


    @Column(name="refresh_token_$$$_token",columnDefinition="TEXT",nullable=false)
    private String token;


    @Column(name="refresh_token_$$$_authentication",columnDefinition="TEXT",nullable=false)
    private String authentication;


    public OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication)SerializableObjectConverterHelper.deserialize(authentication);
    }

    public void setAuthentication(OAuth2Authentication authentication) {
        this.authentication = SerializableObjectConverterHelper.serialize(authentication);
    }
    public OAuth2RefreshToken getToken() {
        return (OAuth2RefreshToken)SerializableObjectConverterHelper.deserialize(token);
    }

    public void setToken(OAuth2RefreshToken token) {
        this.token = SerializableObjectConverterHelper.serialize(token);
    }
}