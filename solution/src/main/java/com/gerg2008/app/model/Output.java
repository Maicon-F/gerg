package com.gerg2008.app.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class Output {
    private double z;
    private double cv;
    private double cp;
    private double alpha;
    private double rho;
    private double kT;
    private double u;
    private double muJT;
    private String type;


    public Output(double z, double cv, double cp, double alpha, double rho, double kT, double u, double muJT, String type) {
    this.z = z;
    this.cv = cv;
    this.cp = cp;
    this.alpha = alpha;
    this.rho = rho;
    this.kT = kT;
    this.u = u;
    this.muJT = muJT;
    this.type = type;

    }
}
