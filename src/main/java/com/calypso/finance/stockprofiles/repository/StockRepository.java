package com.calypso.finance.stockprofiles.repository;

import org.springframework.data.repository.CrudRepository;


import com.calypso.finance.stockprofiles.model.StockPersistence;

public interface StockRepository extends CrudRepository<StockPersistence, Integer>{

}
