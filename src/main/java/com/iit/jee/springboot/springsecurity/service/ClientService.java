package com.iit.jee.springboot.springsecurity.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iit.jee.springboot.springsecurity.model.Client;
import com.iit.jee.springboot.springsecurity.repository.ClientRepository;

import java.util.List;

@Service
@Transactional
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Page<Client> listAll(int page, int size) {
		Page<Client> clients = repository.findAll(PageRequest.of(page, size));
		return clients;
	}
	public List<Client> findAllbyName( String nom){
		if(nom.equals("")){
			return repository.findAll();
		}
		return repository.findAllByNameContaining(nom);
	}
	public Client findbyName(String nom){
		return repository.findClientByNameContaining(nom);
	}

	public void save(Client client) {
		repository.saveAndFlush(client);
	}

	public Client get(long rib) {
		return repository.findById(rib).get();
	}
	public void remove(long id){
		repository.deleteById(id);
	}
}
