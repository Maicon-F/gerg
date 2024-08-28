package com.gerg2008.app.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Component {
    @Id
    private String name;
    private String formula;

    @Transient
    private double composition = 1.0;
    private double rho_ci;
    private double t_ci;
    private double mm;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_Ideal_oi> aIdeal;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_res_oi> aRes;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "component-bi",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "combination_id")
    )
    private List<BiCombination> binaries = new ArrayList<>();


    public BiCombination getBinaryCombination(Component c2){
        for(BiCombination b: binaries){
            for(BiCombination b2: c2.getBinaries()){
                if(b.equals(b2))
                    return b;
            }
        }
        return null; //TODO: implement try/catch because not always we will find a combination btw two parameters
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
