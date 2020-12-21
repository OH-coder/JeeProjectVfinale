package com.iit.jee.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iit.jee.springboot.springsecurity.model.Compte;


@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

	List<Compte> findAllByClientId(Long id);
}
