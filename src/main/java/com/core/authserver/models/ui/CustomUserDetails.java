package com.core.authserver.models.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.core.authserver.repositories.*;
import com.core.authserver.models.*;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private UserModel user;

	@Autowired
    private RoleRepository roleRepo;
	
	public CustomUserDetails(UserModel user) {
		super();
		this.user=user;
		
//		System.out.println("user in userdetails impl "+user.getUserName()+"  "+user.getPassword());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		List<GrantedAuthority> ga=new ArrayList<GrantedAuthority>();
					
		if(roleRepo!=null){
			RoleModel role = roleRepo.findById(this.user.getRoleId()).orElse(null);
			if(role!=null){
				ga.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
				// this.user.getRole().getPermissions().forEach(permission->{
				// 		ga.add(new SimpleGrantedAuthority(permission.getPermissionName().toUpperCase()));
				// 	});
				//		ga.forEach(	a->System.out.println(a.getAuthority()));
			}
		}

	

		
		return ga;
	}

	@Override
	public String getPassword() {
		
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return this.user.getIsAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.user.getIsAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return this.user.getIsCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		
		return this.user.getIsEnabled();
	}
	
	 public UserModel getUserDetails() {
	        return user;
	    }

}
