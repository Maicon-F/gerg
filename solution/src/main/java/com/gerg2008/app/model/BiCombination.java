package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="BICOMBINATION")
public class BiCombination {

    @Id
    private String name;
    private double f_ij = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="FK_bi")
    private List<Alpha_res_ij> alphaRes_ij;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="FK_bicomb")
    private ReducedMixVariables redPars;


    @ManyToMany(mappedBy = "binaries", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Component> components = new ArrayList<>();


}
