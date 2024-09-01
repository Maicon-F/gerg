package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * holds Helmholtz ideal pure parameters
 * @author Maicon Fernandes
 */


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Alpha_Ideal_oi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int k;
    private Double n_oik;
    private Double teta_oik;


}
