package com.gerg2008.app.model;


import customizedVaadinComponents.CustomNotification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.InputValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * holds critical parameters, molar mass and all possible binary combinations
 * @author Maicon Fernandes
 */


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Component {
    @Id
    @NotNull
    private String name;

    @NotNull
    private String formula;

    @Transient
    private double composition = 1.0;

    @NotNull
    private double rho_ci;

    @NotNull
    private double t_ci;

    @NotNull
    private double mm;

    @NotNull
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_Ideal_oi> aIdeal;

    @NotNull
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_res_oi> aRes;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "componentbi",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "combination_id")
    )
    private List<BiCombination> binaries = new ArrayList<>();


    public BiCombination getBinaryCombination(Component c2){
        for(BiCombination b: binaries){
            for(BiCombination b2: c2.getBinaries()){
                if(b.getName().equals(b2.getName()))
                    return b;
            }
        }
        String msg = String.format("No binary combination found for %s and %s", this.getName(), c2.getName());
        new CustomNotification(msg);
        throw new InputValidationException(msg);
    }


    public double getNoik(int k){
        double noik = this.getAIdeal().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getN_oik();
        return noik;
    }

    public double getTetaoik(int k){
        return this.getAIdeal().stream().filter(i -> i.getK() == k).toList().get(0).getTeta_oik();
    }

    public double getResNoik(int k){
        return this.getARes().stream().filter(i -> i.getK() == k).toList().get(0).getN_oik();
    }

    public double getDoik(int k){
        return this.getARes().stream().filter(i -> i.getK() == k).toList().get(0).getD_oik();
    }

    public double getToik(int k){
        return this.getARes().stream().filter(i -> i.getK() == k).toList().get(0).getT_oik();
    }
    public double getCoik(int k){
        return this.getARes().stream().filter(i -> i.getK() == k).toList().get(0).getC_oik();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component entityA = (Component) o;
        return Objects.equals(name, entityA.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
