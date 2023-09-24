package com.core.authserver.models;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import com.core.authserver.models.ui.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Getter
@Setter
@Table(name="object_access_list",
		uniqueConstraints={
	     @UniqueConstraint(columnNames = {"object_access_list_$$$_client_id", "object_access_list_$$$_name"})
})
@Entity
public class ObjectAccessListModel implements  Serializable{
	
	private static final long serialVersionUID = 1567637283578478119L;

	@Id
	@Column(name="object_access_list_$$$_id")
	private String id;
	
	@Column(name="object_access_list_$$$_name",nullable=false)
	private String name;
	
	@Column(name="object_access_list_$$$_enabled",nullable=false)
	private boolean enabled;

	@Column(name="object_access_list_$$$_client_id",nullable=false)
	private  String clientId;

	@Transient
	private ClientModel client;

}