package com.gerg2008.app.controller;

import com.gerg2008.app.model.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gerg2008.app.Constants.R;
import static com.gerg2008.app.Constants.Rstar;

public class controller {

    private double rho;
    private double temperature;


    private double getNoik(Component c, int k){
        double noik = c.getAIdeal().stream().filter(i -> i.getK() == 1).collect(Collectors.toList()).getFirst().getN_oik();
        return noik;
    }

    //ideal pure
    public double calculateAlphaIdeal_oi(Component c) {

        double a = Math.log(rho/c.getRho_ci()) + (Rstar/R)*(getNoik(c,1));
        return a;
    }
    //reducedVars

    //residual pure

    //residual binary

    //Mix ideal

    //Mix Residual

    //Mix total


}
