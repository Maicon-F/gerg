package com.gerg2008.app.repository;

import com.gerg2008.app.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponentRepository extends CrudRepository<Component, String> {
}
