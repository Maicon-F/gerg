package com.gerg2008.app.service.impl;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.repository.ComponentRepository;
import com.gerg2008.app.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentRepository repository;

    @Override
    public Iterable<Component> getAll() {
       return repository.findAll();
    }

    @Override
    public Component getByName(String name) {
        return repository.findById(name).orElse(null);

    }

    @Override
    public Component save(Component o) {
        return repository.save(o);
    }

    @Override
    public Component update(Component o) {
        repository.save(o);
        return repository.findById(o.getName()).orElse(null);
    }



}
