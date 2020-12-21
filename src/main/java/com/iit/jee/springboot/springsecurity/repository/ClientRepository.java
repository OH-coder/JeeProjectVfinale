package com.iit.jee.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.jee.springboot.springsecurity.model.Client;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByNameContaining(String name);
    Client findClientByNameContaining(String nom);

}
