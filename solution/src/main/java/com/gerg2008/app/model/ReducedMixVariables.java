package com.gerg2008.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 * Class holds reduced variable parameters
 * @author Maicon Fernandes
 */


@Getter
@Setter
@Entity
public class ReducedMixVariables {

    @Id
    @NotNull
    private String name;

    private double betaVij;
    private double gammaVij;

    private double betaTij;
    private double gammaTij;



    public void ReducedMixVariables() {
    //create calculation
    }


}
