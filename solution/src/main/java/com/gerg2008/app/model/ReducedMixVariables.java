package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ReducedMixVariables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double betaVij;
    private double gammaVij;

    private double betaTij;
    private double gammaTij;



    public void ReducedMixVariables() {
    //create calculation
    }


}
