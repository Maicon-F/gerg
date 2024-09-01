package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class holds Helmholtz all binary reduced variables
 * @author Maicon Fernandes
 */


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



    public double getNijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getN_ijk();
    }

    public double getDijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getD_ijk();
    }

    public double getTijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getT_ijk();
    }

    public double getBetaijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getBeta_ijk();
    }

    public double getgGamaijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getGama_ijk();
    }

    public double getEpisolonijk(int k){
        return this.getAlphaRes_ij().stream().filter(i -> i.getK() == k).toList().get(0).getEpisilon_ijk();
    }


}
