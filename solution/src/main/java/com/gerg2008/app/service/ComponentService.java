package com.gerg2008.app.service;

import com.gerg2008.app.model.Component;

public interface ComponentService {

    Iterable<Component> getAll();

    Component getByName(String name);

    Component save(Component o);

    Component update(Component o);
}
