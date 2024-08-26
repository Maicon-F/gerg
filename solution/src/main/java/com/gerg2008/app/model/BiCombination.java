package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="BICOMBINATION")
public class BiCombination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double f_ij = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="FK_BICOMBINATION")
    private List<Alpha_res_ij> alphaRes_ij;

    @ManyToOne
    @JoinColumn(name="FK_BICOMBINATION")
    private ReducedMixVariables redPars;


}
