package com.ubudeheSystem.Ubudehe.App.Repositories;

import java.util.List;
import java.util.Optional;

import com.ubudeheSystem.Ubudehe.App.Domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
    List<User> findByRoles(String roles);
    
}