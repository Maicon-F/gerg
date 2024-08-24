package com.gerg2008.app.controller;

import com.gerg2008.app.model.Alpha_Ideal_oi;
import com.gerg2008.app.model.BiCombination;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.ReducedMixVariables;

import java.util.List;
import java.util.stream.Collectors;

import static com.gerg2008.app.Constants.*;


public class controller {

    private double rho;
    private double temperature;

    private double redTemperature;
    private double redRho;


    //ideal pure
    public double calculateAlphaIdeal_oi(Component c){
        int[] steps = {4,6};
        double t = c.getT_ci()/temperature;
        double a = Math.log(rho/c.getRho_ci()) + (Rstar/Rstd)*(getNoik(c,1) + getNoik(c,2)*t + getNoik(c,3)*Math.log(t)) + sumTerms(t, c);
        return a;
    }

    private Double sumTerms(Double t,  Component c) {
        int[] steps = {4,6};
        double sinh,cosh, sum = 0.0;

        for(int k: steps){
            sinh = Math.abs(Math.sinh(getTetaoik(c, k)*t));
            cosh = Math.abs(Math.cosh(getTetaoik(c, k)*t));
            sum = sum + getNoik(c, k)*(Math.log(sinh)) -  getNoik(c, k+1)*(Math.log(cosh));
        }
        return sum;
    }

    private double getNoik(Component c, int k){
        double noik = c.getAIdeal().stream().filter(i -> i.getK() == 1).collect(Collectors.toList()).getFirst().getN_oik();
        return noik;
    }

    private double getTetaoik(Component c, int k){
        double tetaoik = c.getAIdeal().stream().filter(i -> i.getK() == 1).collect(Collectors.toList()).getFirst().getTeta_oik();
        return tetaoik;
    }


    //reducedVars
    public void calculateReducedVariables(List<Component> list){
        double redRho, rhoRes1 = 0.0, rhoRes2 = 0.0;
        double redt, tRes1 = 0.0, tRes2 = 0.0;

        double xi, xj, betaV, betaT, gamaV, gamaT, rhoci, rhocj, tci, tcj;
        int N = list.size();
        Component ci, cj;

        for(Component c: list){
            rhoRes1= (Math.pow(c.getComposition(),2))/c.getRho_ci() + rhoRes1;
            tRes1 = (Math.pow(c.getComposition(),2))*c.getT_ci() + tRes1;
        }

        for(int i=0; i <= N-1; i++){
            for(int j=0; j <= N; j++){
                ci = list.get(i);
                cj = list.get(j);
                rhoci = ci.getRho_ci();
                rhocj = cj.getRho_ci();
                tci = ci.getT_ci();
                tcj = cj.getT_ci();
                BiCombination comb = ci.getBinaryCombination(cj);
                xi =ci.getComposition();
                xj = cj.getComposition();
                ReducedMixVariables pars = comb.getRedPars();
                betaV= pars.getBetaVij();
                betaT = pars.getBetaTij();
                gamaV = pars.getGammaVij();
                gamaT = pars.getGammaTij();

                rhoRes2 = 2*xi*xj*betaV*gamaV*((xi+xj)/(Math.pow(betaV,2)*xi + xj))*(1/8)*Math.pow((1/Math.pow(rhoci,1/3) + 1/Math.pow(rhocj,1/3)),3) + rhoRes2;
                tRes2 = 2*xi*xj*betaV*gamaT*((xi+xj)/(Math.pow(betaT,2)*xi + xj))*(Math.pow(tci*tcj,0.5)) + tRes2;

            }
        }

        this.redRho = rhoRes1 + rhoRes2;
        this.redTemperature = tRes1 + tRes2;

    }



    //residual pure

    //residual binary

    //Mix ideal

    //Mix Residual

    //Mix total


}
