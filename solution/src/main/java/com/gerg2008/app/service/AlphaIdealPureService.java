package com.gerg2008.app.service;

import com.gerg2008.app.model.Alpha_Ideal_oi;

/**
 * @Autho Maicon Fernandes
 */
public interface AlphaIdealPureService {

    Iterable<Alpha_Ideal_oi> getAll();

    Alpha_Ideal_oi getByName(String name);

    Alpha_Ideal_oi add(Alpha_Ideal_oi o);

    Alpha_Ideal_oi update(Alpha_Ideal_oi o);
}
