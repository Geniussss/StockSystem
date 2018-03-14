package com.calypso.finance.stockprofiles.repository;

import org.springframework.data.repository.CrudRepository;

import com.calypso.finance.stockprofiles.model.PersonPersistence;





public interface PersonRepository extends CrudRepository<PersonPersistence, Integer>{
		
}
