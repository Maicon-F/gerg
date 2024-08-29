package utils;

import com.gerg2008.app.controller.HelmholtzCalculator;
import com.gerg2008.app.model.Component;

import java.util.List;

import static com.gerg2008.app.Constants.R;

public class ObjFunction {

    private double fobj(double rho, double temperature, double P, List<Component> list) throws Exception {
        double delta = 0.0001;
        double Z;
        double ump = 1 + delta;
        double umm = 1 - delta;

        HelmholtzCalculator calculator1 = new HelmholtzCalculator(rho*ump, temperature, list);
        double ap = calculator1.aReal()*R*temperature;

        HelmholtzCalculator calculator2 = new HelmholtzCalculator(rho*umm, temperature, list);
        double am = calculator2.aReal()*R*temperature;

        Z = (ap - am)/(2*delta)/(R*temperature);

        rho = P/(Z*R*temperature);

        return rho;

    }

    public double iterativeSolution(double rho, double temperature, double P, List<Component> list) throws Exception {
        double tol = 0.00001, newRho = 0;
        int step = 0, maxSteps = 1000;
        while (step < maxSteps) {
            newRho = fobj(rho, temperature, P, list);

        if (Math.abs(newRho - rho) < tol)
            return newRho;

        rho =newRho;
        step++;
        }

        return 0.0;
    }
}




