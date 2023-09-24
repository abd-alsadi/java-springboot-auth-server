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
@Table(name="role",
		uniqueConstraints={
	     @UniqueConstraint(columnNames = {"role_$$$_client_id", "role_$$$_name"})
})
@Entity
public class RoleModel implements  Serializable{
	
	private static final long serialVersionUID = 1567637283572978119L;

	@Id
	@Column(name="role_$$$_id")
	private String id;
	
	@Column(name="role_$$$_name",nullable=false)
	private String name;
	

	@Column(name="role_$$$_client_id",nullable=false)
	private  String clientId;

	@Transient
	private ClientModel client;
}