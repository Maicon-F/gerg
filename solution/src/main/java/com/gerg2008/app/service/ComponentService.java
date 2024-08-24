package com.gerg2008.app.service;

import com.gerg2008.app.model.Alpha_Ideal_oi;
import com.gerg2008.app.model.Component;

public interface ComponentService {

    Iterable<Component> getAll();

    Component getByName(String name);

    Component add(Component o);

    Component update(Component o);
}
