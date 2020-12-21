package com.iit.jee.springboot.springsecurity.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.iit.jee.springboot.springsecurity.model.Compte;
import com.iit.jee.springboot.springsecurity.repository.CompteRepository;
import com.iit.jee.springboot.springsecurity.service.CompteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.iit.jee.springboot.springsecurity.model.Client;
import com.iit.jee.springboot.springsecurity.service.ClientService;

@Controller
public class ClientController {
	@Autowired
	private ClientService service;
	@Autowired
	private CompteRepository compteRepository;

	@RequestMapping("/")
	public String viewHomePage(
			Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) 
	{
		Page<Client> listclients = service.listAll(page, size);
		System.out.println(listclients.getContent());
		
		model.addAttribute("listclients", listclients.getContent());
		model.addAttribute("pages", new int[listclients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "index";
	}
	@RequestMapping("/new")
	public String addClient(
			Model model)
	{
		Client client=new Client();
		model.addAttribute("client",client);
		return "new-client";
	}
	@RequestMapping(value="/saveClient" ,method= RequestMethod.POST)
	public String saveCompte(@ModelAttribute("client") Client client){

		service.save(client);
		return "redirect:/";
	}
	@PostMapping(value="/deleteClient")
		public ResponseEntity deleteClient(@RequestBody  String id){
		String numberOnly= id.replaceAll("[^0-9]", "");
		Long ide=Long.parseLong(numberOnly);
		try {
			List<Compte> comptes = compteRepository.findAllByClientId(ide);
			if (!comptes.isEmpty())
			compteRepository.deleteAll(comptes);
		}catch (NullPointerException e){

		}
		service.remove(ide);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping (value="/delete/{id}")
	public String delete(@PathVariable("id") Long id){
		List<Compte>comptes=compteRepository.findAllByClientId(id);
		compteRepository.deleteAll(comptes);
		service.remove(id);
		return "redirect:/";
	}

	@RequestMapping("/editClient/{id}")
	public String editClient(
			Model model,@PathVariable("id") Long id)
	{
		Client client=service.get(id);
		model.addAttribute("client",client);
		return "edit-client";
	}

	@RequestMapping(value = "/clientNameAutocomplete")
	@ResponseBody
	public List<String> plantNamesAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<String> suggestions = new ArrayList<String>();
		List<Client> clients = new ArrayList<Client>();
		try {
			// only update when term is three characters.
			if (term.length() == 2)
				clients = service.findAllbyName(term);
			for (Client client : clients) {
				suggestions.add(client.getName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return suggestions;
	}

}

