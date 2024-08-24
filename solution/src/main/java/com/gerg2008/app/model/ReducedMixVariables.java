package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="binary_id")
    private Binary binary;

    public void ReducedMixVariables() {
    //create calculation
    }


}
