package com.gerg2008.app.controller;

import com.gerg2008.app.model.BiCombination;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.ReducedMixVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gerg2008.app.Constants.*;


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


    //ideal pure
    public double calculateAlphaIdeal_oi(Component c){
        double t = c.getT_ci()/temperature;
        double a, rhoc = c.getRho_ci();
        double n1, n2, n3;
        n1 = getNoik(c,1);
        n2=  getNoik(c,2);
        n3 = getNoik(c,3);
        a = Math.log(rho/rhoc) + (Rstar/Rstd)*(n1 +n2*t + n3*Math.log(t));
        a = a + sumTerms(t, c);
        return a;
    }

    private Double sumTerms(Double t,  Component c) {
        int[] steps = {4,6};
        double sinh,cosh, sum = 0.0;
        double teta, tetaplus, n, nplus;
        for(int k: steps){
            teta = getTetaoik(c, k);
            tetaplus = getTetaoik(c,k+1);
            n = getNoik(c, k);
            nplus = getNoik(c, k+1);

            //TODO: check whether I should have ignored

            sinh = Math.abs(Math.sinh(teta*t));
            cosh = Math.abs(Math.cosh(tetaplus*t));
            sum = sum + n*(Math.log(sinh)) -  nplus*(Math.log(cosh));


        }
        return sum;
    }


    //TODO: Use OOP to remove this secondary methods out of the controller AND OR to simplify this
    private double getNoik(Component c, int k){
        double noik = c.getAIdeal().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getN_oik();
        return noik;
    }


    private double getTetaoik(Component c, int k){
        double tetaoik = c.getAIdeal().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getTeta_oik();
        return tetaoik;
    }


    private double getResNoik(Component c, int k){
        double noik = c.getARes().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getN_oik();
        return noik;
    }


    private double getDoik(Component c, int k){
        double doik = c.getARes().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getD_oik();
        return doik;
    }

    private double getToik(Component c, int k){
        double toik = c.getARes().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getT_oik();
        return toik;
    }
    private double getCoik(Component c, int k){
        double coik = c.getARes().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getC_oik();
        return coik;
    }

    private double getNijk(BiCombination bi, int k){
        double nij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getN_ijk();
        return nij;
    }

    private double getDijk(BiCombination bi, int k){
        double dij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getD_ijk();
        return dij;
    }

    private double getTijk(BiCombination bi, int k){
        double tij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getT_ijk();
        return tij;
    }

    private double getBetaijk(BiCombination bi, int k){
        double betaij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getBeta_ijk();
        return betaij;
    }

    private double getgGamaijk(BiCombination bi, int k){
        double gamaij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getGama_ijk();
        return gamaij;
    }

    private double getEpisolonijk(BiCombination bi, int k){
        double episolonij = bi.getAlphaRes_ij().stream().filter(i -> i.getK() == k).collect(Collectors.toList()).get(0).getEpisilon_ijk();
        return episolonij;
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

    //residual pure

    public double calculateAlphaResoi(Component c) throws Exception {
     int kPOL = calculateKexp(c)[0];
     int kEXP = calculateKexp(c)[1];
    double sum1 = 0.0, sum2 = 0.0;

    for(int i=1; i <= kPOL; i++){
        sum1 = getResNoik(c, i)*Math.pow(redRho, getDoik(c,i))*Math.pow(redTemperature,getToik(c,i)) + sum1;
    }


    for(int j=kPOL+1; j <= kPOL + kEXP; j++){
            sum2 = getResNoik(c, j)*Math.pow(redRho, getDoik(c,j))*Math.pow(redTemperature,getToik(c,j))*Math.exp(-Math.pow(redRho,getCoik(c,j))) + sum2;
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
            sum1 = getNijk(bi, i)*Math.pow(redRho, getDijk(bi,i))*Math.pow(redTemperature,getTijk(bi,i)) + sum1;
        }

        //TODO: Confirm exponential position. Is it multipling by Tij or the parent term?
        for(int j=kPOL+1; j <= kPOL + kEXP; j++){
            exp = Math.exp(-getNijk(bi,j)*Math.pow(redRho - getEpisolonijk(bi,j),2) - getBetaijk(bi,j)*(redRho -getgGamaijk(bi,j)));
            sum2 = getNijk(bi, j)*Math.pow(redRho, getDijk(bi,j))*Math.pow(redTemperature,getTijk(bi,j)*exp) + sum2;
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
        return mixAIdeal() + mixResidual();
    }



}
