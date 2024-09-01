package com.gerg2008.app.controller;


import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.Output;
import com.gerg2008.app.model.Type;
import lombok.Getter;
import lombok.Setter;
import utils.ObjFunction;

import java.util.ArrayList;
import java.util.List;

import static com.gerg2008.app.Constants.R;

/**
 * calculates the result by output type (liquid | vapor)
 * @author Maicon Fernandes
 */
@Getter
@Setter
public class Result {
    private List<Component> components;
    private double temperature;
    private double pressure;
    private double liquidGuess=0.0;
    private double vaporGuess;
    private Type type;


    public Result(List<Component> components, double temperature, double pressure) {
        this.components = components;
        this.temperature = temperature;
        this.pressure = pressure;

        vaporGuess = (pressure * 100000.0) / (R * temperature);

        for (Component c : components) {
            liquidGuess = 10 * c.getComposition() * c.getRho_ci() + liquidGuess;
        }

    }


    public List<Output> getResults() throws Exception {
        ObjFunction obj = new ObjFunction();
        double  RhoLiquid = obj.solve(liquidGuess, 300, 100000, components);
        double  rhoVapor = obj.solve(vaporGuess, 300, 100000, components);

        Derivative der = new Derivative();
        Output liquidOut = der.calculate(RhoLiquid, temperature, components, Type.LIQUID);
        Output vaporOut = der.calculate(rhoVapor, temperature, components, Type.VAPOR);

        List<Output> outputs = new ArrayList<>();
        outputs.add(vaporOut);
        outputs.add(liquidOut);

        return outputs;
    }
}
