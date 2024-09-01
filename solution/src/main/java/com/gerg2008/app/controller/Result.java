package com.gerg2008.app.controller;


import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.Output;
import com.gerg2008.app.model.Type;
import customizedVaadinComponents.CustomNotification;
import lombok.Getter;
import lombok.Setter;
import utils.ObjFunction;

import java.util.ArrayList;
import java.util.List;

import static com.gerg2008.app.Constants.R;

@Getter
@Setter
public class Result {
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

/*
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


 */

    public Result(List<Component> components, double temperature, double pressure) {
        this.components = components;
        this.temperature = temperature;
        this.pressure = pressure;

        boolean overTemperature = true;
        boolean overPressure = true;
        vaporGuess = (pressure*100000.0)/(R*temperature);

        for(Component c: components){
            if(temperature <= c.getT_ci())
                overTemperature = false;

            liquidGuess = 10*c.getComposition()*c.getRho_ci() + liquidGuess;

            if(vaporGuess <= c.getRho_ci())
                overPressure = false;
        }


        if(overTemperature && overPressure) {
            new CustomNotification("System might operate over critical point. Please review properties and design a system within eos range");
        }
    }


    public List<Output> getResults() throws Exception {
        ObjFunction obj = new ObjFunction();
        double  RhoLiquid = obj.solve(liquidGuess, 300, 100000, components);
        double  rhoVapor = obj.solve(vaporGuess, 300, 100000, components);

        Derivative der = new Derivative();
        Output vaporOut = der.calculate(rhoVapor, temperature, components, Type.LIQUID);
        Output liquidOut = der.calculate(RhoLiquid, temperature, components, Type.VAPOR);

        List<Output> outputs = new ArrayList<>();
        outputs.add(vaporOut);
        outputs.add(liquidOut);

        return outputs;
    }
}
