package com.calypso.finance.stockprofiles.repository;



import org.springframework.data.repository.CrudRepository;

import com.calypso.finance.stockprofiles.model.UserPersistence;

public interface UserRepository extends CrudRepository<UserPersistence, Integer>{
public UserPersistence findByUsername (String name);
}
