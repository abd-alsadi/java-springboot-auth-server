// package com.core.authserver.models;

// import java.io.Serializable;
// import java.util.*;
// import javax.persistence.*;

// import com.fasterxml.jackson.annotation.JsonIgnore;


// @Table(name="permission")
// @Entity
// public class PermissionModel implements  Serializable{
	
// 	private static final long serialVersionUID = 8087275050725156377L;

// 	@Id
// 	@GeneratedValue(strategy=GenerationType.AUTO)
// 	@Column(name="permission_id")
// 	private int permissionId;
	
// 	@Column(name="permission_name")
// 	private String permissionName;
	
// 	@JsonIgnore
// 	@ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
// 	private Set<RoleModel> roles;

// 	public PermissionModel() {
// 		super();
// 	}

// 	public PermissionModel(int permissionId, String permissionName) {
// 		super();
// 		this.permissionId = permissionId;
// 		this.permissionName = permissionName;
// 	}

// 	public int getPermissionId() {
// 		return permissionId;
// 	}

// 	public void setPermissionId(int permissionId) {
// 		this.permissionId = permissionId;
// 	}

// 	public String getPermissionName() {
// 		return permissionName;
// 	}

// 	public void setPermissionName(String permissionName) {
// 		this.permissionName = permissionName;
// 	}

// 	public Set<RoleModel> getRoles() {
// 		return roles;
// 	}

// 	public void setRoles(Set<RoleModel> roles) {
// 		this.roles = roles;
// 	}

// }