package com.core.authserver.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.authserver.models.AccessTokenModel;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenModel, String> {

    List<AccessTokenModel> findByClientId(String clientId);

    List<AccessTokenModel> findByClientIdAndUserName(String clientId, String username);

    Optional<AccessTokenModel> findByTokenId(String tokenId);

    Optional<AccessTokenModel> findByRefreshToken(String refreshToken);

    Optional<AccessTokenModel> findByAuthenticationId(String authenticationId);

}