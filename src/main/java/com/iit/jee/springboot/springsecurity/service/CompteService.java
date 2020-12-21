package com.iit.jee.springboot.springsecurity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.jee.springboot.springsecurity.model.Compte;
import com.iit.jee.springboot.springsecurity.repository.CompteRepository;

@Service
@Transactional
public class CompteService {

	@Autowired
	private CompteRepository repository;

	public List<Compte> listAllCompte(Long id) {
		List<Compte> comptes = repository.findAllByClientId(id);
		return comptes;
	}

	public void save(Compte compte) {
		repository.saveAndFlush(compte);
	}

	public Compte get(long rib) {
		return repository.findById(rib).get();
	}
	public void delete(long rib){repository.deleteById(rib);}
}
