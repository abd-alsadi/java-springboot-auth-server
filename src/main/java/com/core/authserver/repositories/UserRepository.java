package com.core.authserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.authserver.models.*;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

	UserModel findByUserName(String userName);

}
