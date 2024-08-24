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
        return null;
    }

    @Override
    public Component add(Component o) {
        return null;
    }

    @Override
    public Component update(Component o) {
        return null;
    }
}
