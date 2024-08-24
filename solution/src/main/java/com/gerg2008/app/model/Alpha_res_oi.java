package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Alpha_res_oi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int k;
    private double n_oik;
    private double d_oik;
    private double t_oik;
    private double c_oik;


}
