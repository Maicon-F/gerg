package com.gerg2008.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Binary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="componentA_id")
    private Component componentA;

    @ManyToOne
    @JoinColumn(name="componentB_id")
    private Component componentB;

    public void Binary(){
        //from component, calculate delta and teta
        //from delta e teta, calculate alpha_res_ij
    }


}
