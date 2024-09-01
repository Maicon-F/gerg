package com.gerg2008.app.controller;

import com.gerg2008.app.model.BiCombination;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.ReducedMixVariables;

import java.util.List;

import static com.gerg2008.app.Constants.*;

/**
 * @author Maicon Fernandes
 */
public class HelmholtzCalculator {

    private List<Component> components;
    private double rho;
    private double temperature;
    private double redTemperature;
    private double redRho;

    public HelmholtzCalculator(double rho, double temperature, List<Component> components) {
        this.components = components;
        this.rho = rho;
        this.temperature = temperature;
        calculateReducedVariables();
    }


    public double calculateAlphaIdeal_oi(Component c){
        double t = c.getT_ci()/temperature;
        double a, rhoc = c.getRho_ci();
        double n1, n2, n3;

        n1 = c.getNoik(1);
        n2=  c.getNoik(2);
        n3 = c.getNoik(3);
        a = Math.log(rho/rhoc) + (Rstar/Rstd)*(n1 +n2*t + n3*Math.log(t) + sumTerms(t, c));
        return a;
    }

    private Double sumTerms(Double t,  Component c) {
        int[] steps = {4,6};
        double sinh,cosh, sum = 0.0;
        double teta, tetaplus, n, nplus;
        double sum1=0.0, sum2=0.0;

        for(int k: steps){
            teta = c.getTetaoik(k);
            tetaplus = c.getTetaoik(k+1);
            n = c.getNoik(k);
            nplus = c.getNoik(k+1);

            sinh = Math.abs(Math.sinh(teta*t));
            cosh = Math.cosh(tetaplus*t);

            sum1= sum1 + n*(Math.log(sinh));
            sum2= sum2 +  nplus*(Math.log(cosh));
        }
        sum = sum1 - sum2;

        return sum;
    }



    //reducedVars
    public void calculateReducedVariables(){
        List<Component> list = this.components;

        double rhoRes1 = 0.0, rhoRes2 = 0.0;
        double tRes1 = 0.0, tRes2 = 0.0;

        double xi, xj, betaV, betaT, gamaV, gamaT, rhoci, rhocj, tci, tcj;
        int N = list.size();
        Component ci, cj;

        for(Component c: list){
            xi = c.getComposition();
            rhoci = c.getRho_ci();
            tci = c.getT_ci();
            rhoRes1= (Math.pow(xi,2))/rhoci + rhoRes1;
            tRes1 = (Math.pow(xi,2))*tci + tRes1;
        }

        for(int i=0; i < N-1; i++){
            for(int j=i+1; j < N; j++){
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

                rhoRes2 = 2*xi*xj*betaV*gamaV*((xi+xj)/(Math.pow(betaV,2)*xi + xj))*((double) 1 /8)*Math.pow((1/Math.pow(rhoci, (double) 1 /3) + 1/Math.pow(rhocj, (double) 1 /3)),3) + rhoRes2;
                tRes2 = 2*xi*xj*betaT*gamaT*((xi+xj)/(Math.pow(betaT,2)*xi + xj))*(Math.pow(tci*tcj,0.5)) + tRes2;
            }
        }
        this.redRho = rho*(rhoRes1 + rhoRes2);
        this.redTemperature = (tRes1 + tRes2)/temperature;
    }


    public double calculateAlphaResoi(Component c) throws Exception {
     int kPOL = calculateKexp(c)[0];
     int kEXP = calculateKexp(c)[1];
    double sum1 = 0.0, sum2 = 0.0;

    for(int i=1; i <= kPOL; i++){
        sum1 = c.getResNoik(i)*Math.pow(redRho, c.getDoik(i))*Math.pow(redTemperature,c.getToik(i)) + sum1;
    }


    for(int j=kPOL+1; j <= kPOL + kEXP; j++){
            sum2 = c.getResNoik(j)*Math.pow(redRho, c.getDoik(j))*Math.pow(redTemperature,c.getToik(j))*Math.exp(-Math.pow(redRho,c.getCoik(j))) + sum2;
    }

    return sum1 + sum2;
    }


    private int[] calculateKexp(Component c) throws Exception {
        int kPOL = 6;
        int kEXP=18;
        int k = c.getARes().get(c.getARes().size()-1).getK();

        switch(k){
            case 12:
                kEXP = 6;
                break;
            case 22:
                kPOL = 4;
                break;
            case 24:
                break;
            default: throw new Exception("Wrong number of parameters");
        }

       return new int[]{kPOL, kEXP};
    }


    //residual binary
    public double calculateAlphaoResij(Component c1, Component c2) throws Exception {
        BiCombination bi = c1.getBinaryCombination(c2);

        if(bi.getAlphaRes_ij() == null || bi.getAlphaRes_ij().isEmpty())
            return 0.0;

        int kPOL = calculateKexp(c1)[0];
        int kEXP = calculateKexp(c1)[1];
        double sum1 = 0.0, sum2 = 0.0;
        double exp;

        for(int i=1; i <= kPOL; i++){
            sum1 = bi.getNijk(i)*Math.pow(redRho, bi.getDijk(i))*Math.pow(redTemperature,bi.getTijk(i)) + sum1;
        }

        //TODO: Confirm exponential position. Is it multipling by Tij or the parent term?
        for(int j=kPOL+1; j <= kPOL + kEXP; j++){
            exp = Math.exp(-bi.getNijk(j)*Math.pow(redRho - bi.getEpisolonijk(j),2) - bi.getBetaijk(j)*(redRho -bi.getgGamaijk(j)));
            sum2 = bi.getNijk(j)*Math.pow(redRho, bi.getDijk(j))*Math.pow(redTemperature,bi.getTijk(j)*exp) + sum2;
        }

        return sum1 + sum2;
    }

    //Mix ideal

    public double mixAIdeal(){
        double res = 0.0, xi = 0.0;
        for(Component c: components){
            xi = c.getComposition();
            res = xi*(calculateAlphaIdeal_oi(c) + Math.log(xi)) + res;
        }

        return res;
    }

    //Mix Residual
    //residual binary
    public double mixResidual() throws Exception {
        double sum1 = 0.0, sum2 = 0.0;

        for(int i=0; i < components.size(); i++){
            double xi = components.get(i).getComposition();
            sum1 = xi*calculateAlphaResoi(components.get(i)) + sum1;
        }

        //TODO: Confirm exponential position. Is it multipling by Tij or by the parent term?
        for(int i=0; i < components.size()-1; i++){
            for(int j=i+1; j < components.size(); j++){
                double xi = components.get(i).getComposition();
                double xj = components.get(j).getComposition();
                BiCombination bi = components.get(i).getBinaryCombination(components.get(j));
                double fij = bi.getF_ij();
                double aij = calculateAlphaoResij(components.get(i), components.get(j));
                sum2 = xi*xj*fij*aij + sum2;
            }
        }

        return sum1 + sum2;
    }

    public double aReal() throws Exception {
        return (mixAIdeal() + mixResidual())*R*temperature;
    }



}