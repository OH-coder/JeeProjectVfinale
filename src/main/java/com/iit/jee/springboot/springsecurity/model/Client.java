package com.iit.jee.springboot.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	@Column(unique=true)
	private String cin;
	private String name;
	private String adresse;
}
