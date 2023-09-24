package com.core.authserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.authserver.models.*;


@Repository
public interface ClientRepository extends JpaRepository<ClientModel, String> {

	ClientModel findByClientId(String clientId);

}
