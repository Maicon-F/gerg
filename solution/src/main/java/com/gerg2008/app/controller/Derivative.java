package com.gerg2008.app.controller;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.Output;

import java.util.List;

import static com.gerg2008.app.Constants.R;

public class Derivative {

    public Output calculate(double rho, double temperature, List<Component> list, String type) throws Exception {
        double tol = 0.0001;
        double ump, umm;
        double ap0, am0, a0m, a00, a0p, app, amm, apm, amp;
        double z, kT, alfa, cv, cp, u, muJT;

        HelmholtzCalculator a = new HelmholtzCalculator( rho, temperature, list );
        ump = 1 + tol; umm = 1 - tol;
        ap0 = new HelmholtzCalculator(rho/ump, temperature, list).aReal();
        am0 = new HelmholtzCalculator(rho/umm, temperature, list).aReal();
        a00 = new HelmholtzCalculator(rho, temperature, list).aReal();
        a0p = new HelmholtzCalculator(rho, temperature*ump, list).aReal();
        a0m = new HelmholtzCalculator(rho, temperature*umm, list).aReal();
        app = new HelmholtzCalculator(rho/ump, temperature*ump, list).aReal();
        amm = new HelmholtzCalculator(rho/umm, temperature*umm, list).aReal();
        apm = new HelmholtzCalculator(rho/ump, temperature*umm, list).aReal();
        amp = new HelmholtzCalculator(rho/umm, temperature*ump, list).aReal();



//Ver Finite difference in several variables em https://en.wikipedia.org/wiki/Finite_difference
        double tol2 = Math.pow(tol,2);
        z = - (ap0 - am0) / (2 * tol * R * temperature);
        System.out.println("Z: "+ z);
        kT = tol2/ (ap0 + am0 - 2 * a00)/ rho;
        alfa = - kT * (app + amm - apm - amp) / (4 * temperature * tol2) * rho;
        cv = -(a0p + a0m - 2 * a00) / tol2 /temperature;
        cp = cv + temperature * Math.pow(alfa,2) / rho / kT;
        muJT = (temperature*alfa-1)/(rho*cp);
        u = calculateU(rho, cp, cv, kT, list);

        Output output = new Output(z, cv, cp, alfa, rho, kT, u, muJT, type);

        return output;
    }

    private double calculateU(double rho, double cp, double cv, double kT,List<Component> list) throws Exception {
        double u = 0.0, xi, mm;
        for(Component c : list) {
            xi = c.getComposition();
            mm = c.getMm();
            u =  Math.pow(cp/(cv*kT*rho*xi*mm), 0.5);
        }
        return u;
    }
}
