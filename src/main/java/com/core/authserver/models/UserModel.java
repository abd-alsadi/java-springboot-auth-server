package com.core.authserver.models;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import com.core.authserver.models.ui.*;
import com.fasterxml.jackson.annotation.*;

import lombok.*;
@Data
@Getter
@Setter
@Table(name="users")
@Entity
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_$$$_id")
	private String id;
	
	@Column(name="user_$$$_user_name",nullable=false,unique=true)
	private String userName;
	
	@Column(name="user_$$$_pass",columnDefinition="TEXT",nullable=false)
	private String password;
	
	@Column(name="user_$$$_type")
	private String type;
	
	@Column(name="user_$$$_email",nullable=false,unique=true)
	private String email;
	
	@Column(name="user_$$$_is_account_non_expired",nullable=false)
	private Boolean isAccountNonExpired=false;

	@Column(name="user_$$$_is_account_non_blocked",nullable=false)
	private Boolean isAccountNonLocked=false;

	@Column(name="user_$$$_is_credentials_non_expired",nullable=false)
	private Boolean isCredentialsNonExpired=false;

	@Column(name="user_$$$_is_enabled",nullable=false)
	private Boolean isEnabled=false;

	@Column(name="user_$$$_role_id",nullable=false)
	private String roleId;
	
	@Transient
	private RoleModel role;

}