package com.gerg2008.app.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String formula;

    @Transient
    private double composition;
    private double rho_ci;
    private double t_ci;
    private double mm;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_Ideal_oi> aIdeal;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="FK_COMPONENT")
    private List<Alpha_res_oi> aRes;

    @ManyToMany
    @JoinTable(
            name = "component-bi",
            joinColumns = @JoinColumn(name = "bi_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private ArrayList<BiCombination> binaries;


    public BiCombination getBinaryCombination(Component c2){
        for(BiCombination b: binaries){
            for(BiCombination b2: c2.getBinaries()){
                if(b.equals(b2))
                    return b;
            }
        }
        return null; //TODO: implement try/catch because not always we will find a combination btw two parameters
    }
}
