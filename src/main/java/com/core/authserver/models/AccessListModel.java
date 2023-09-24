package com.core.authserver.models;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import org.springframework.core.annotation.AliasFor;

import com.core.authserver.models.ui.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Getter
@Setter
@Table(name="access_list",
		uniqueConstraints={
	     @UniqueConstraint(columnNames = {"access_list_$$$_object_id", "access_list_$$$_role_id"}),
		 @UniqueConstraint(columnNames = {"access_list_$$$_object_id", "access_list_$$$_user_id"})
	 })
@Entity
public class AccessListModel implements  Serializable{
	
	private static final long serialVersionUID = 1511237283578478119L;

	@Id
	@Column(name="access_list_$$$_id")
	private String id;
	
	@Column(name="access_list_$$$_permissions",nullable=false)
	private int permissions;

	@Column(name="access_list_$$$_object_id",nullable=false)
	private  String objectId;

	@Column(name="access_list_$$$_role_id")
	private  String roleId;

	@Column(name="access_list_$$$_user_id")
	private  String userId;

	@Column(name="access_list_$$$_type",nullable=false)
	private  String type;

	@Transient
	private ObjectAccessListModel object;


	@Transient
	private UserModel user;


	@Transient
	private RoleModel role;
}

