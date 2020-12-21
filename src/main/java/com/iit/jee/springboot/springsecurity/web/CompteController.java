package com.iit.jee.springboot.springsecurity.web;

import java.util.List;

import com.iit.jee.springboot.springsecurity.model.Client;
import com.iit.jee.springboot.springsecurity.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.iit.jee.springboot.springsecurity.model.Compte;
import com.iit.jee.springboot.springsecurity.service.CompteService;

@Controller
public class CompteController {
	@Autowired
	private CompteService compteService;
	@Autowired
	private ClientRepository clientRepository;
private String nom;
private Client cliente=new Client();
	@RequestMapping("/compte/{id}")
	public String viewCompteList(
			Model model,
			@PathVariable(name = "id") Long id) {
		List<Compte> listcomptes = compteService.listAllCompte(id);
		model.addAttribute("listcomptes", listcomptes);
		return "voirCompte";
	}

	@RequestMapping("/new-compte")
	public String newCompte(Model model) {
		Compte compte = new Compte();
		Client client=new Client();
		model.addAttribute("compte", compte);
		model.addAttribute("Client",client);
		return "new-compte";
	}
	@RequestMapping("/editCompte/{id}")
	public String editClient(
			Model model,@PathVariable("id") Long id)
	{
		Compte compte=compteService.get(id);
		model.addAttribute("compte",compte);
		return "edit-compte";
	}
	@PostMapping(value="/deleteCompte")
	public ResponseEntity deleteClient(@RequestBody  String id){
		String numberOnly= id.replaceAll("[^0-9]", "");
		Long ide=Long.parseLong(numberOnly);
		Compte compte=compteService.get(ide);
	compteService.delete(ide);
		return new ResponseEntity(HttpStatus.OK);
	}
	@RequestMapping(value="/saveCompte" ,method= RequestMethod.POST)
	public String saveCompte(@ModelAttribute("compte")Compte compte){
	 nom=compte.getClient().getName();
		System.out.println(nom);
		 cliente=clientRepository.findClientByNameContaining(nom);
		System.out.println(cliente.toString());
		compte.setClient(cliente);
		compteService.save(compte);
		return "redirect:/compte/"+cliente.getId().toString();

	}


}
