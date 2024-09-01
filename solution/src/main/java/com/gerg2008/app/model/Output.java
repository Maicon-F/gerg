package com.gerg2008.app.model;


import com.gerg2008.app.controller.Derivative;
import customizedVaadinComponents.CustomNotification;
import lombok.Getter;
import lombok.Setter;
import utils.ObjFunction;

import java.util.ArrayList;
import java.util.List;

import static com.gerg2008.app.Constants.R;

@Getter
@Setter
public class Output {
    private List<Component> components;
    private double temperature;
    private double pressure;
    private double liquidGuess;
    private double vaporGuess;
    private double z;
    private double cv;
    private double cp;
    private double alpha;
    private double rho;
    private double kT;
    private double u;
    private double muJT;
    private Type type;


    public Output(double z, double cv, double cp, double alpha, double rho, double kT, double u, double muJT, Type type) {
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
