package com.gerg2008.app.service;

import com.gerg2008.app.model.Component;

/**
 * @Autho Maicon Fernandes
 */
public interface ComponentService {

    Iterable<Component> getAll();

    Component getByName(String name);

    Component save(Component o);

    Component update(Component o);
}
