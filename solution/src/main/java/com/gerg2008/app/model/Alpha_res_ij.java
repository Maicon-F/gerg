package com.gerg2008.app.model;

import jakarta.persistence.*;

@Entity
public class Alpha_res_ij {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double n_ijk;
    private double d_ijk;
    private double t_ijk;
    private double episilon_ijk;
    private double beta_ijk;
    private double gama_ijk;


}
