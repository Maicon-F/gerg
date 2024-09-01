
package utils;
import com.gerg2008.app.controller.HelmholtzCalculator;
import com.gerg2008.app.model.Component;
import de.linearbits.newtonraphson.Function2D;
import de.linearbits.newtonraphson.NewtonRaphson2D;
import de.linearbits.newtonraphson.Vector2D;

import java.util.List;

import static com.gerg2008.app.Constants.R;

/**
 * This class uses the implementation of the Newton-Raphson algorithm created by Fabian Prasser
 * See https://github.com/prasser/newtonraphson/blob/master/src/main/de/linearbits/newtonraphson/NewtonRaphson2D.java
 * Solves the bound problem and finds the system density
 * @author Maicon Fernandes
 */
public class ObjFunction {
    public double solve(double rho, double temperature, double P, List<Component> list) throws Exception {
        double delta = 0.001;
        double ump = 1 + delta;
        double umm = 1 - delta;

        Function2D object1 = new Function2D() {
            public Double evaluate(Vector2D input) {
                HelmholtzCalculator calculator1 = new HelmholtzCalculator(input.x * ump, temperature, list);
                HelmholtzCalculator calculator2 = new HelmholtzCalculator(input.x * umm, temperature, list);
                try {
                    double ap1 = calculator1.aReal();
                    double am2 = calculator2.aReal();
                    return ((ap1 - am2) / (2 * delta)) / (R * temperature) - input.y;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Function2D object2 = new Function2D() {
            public Double evaluate(Vector2D input) {
                return input.x * input.y - P / (R * temperature);
            }
        };

        double[][] arr = new double[1][2];
        arr[0][0] = rho;
        arr[0][1] = 1;

        NewtonRaphson2D nr = new NewtonRaphson2D(object1, object2)
                .accuracy(1e-4)
                .iterationsPerTry(5000)
                .iterationsTotal(10000)
                .preparedStartValues(arr);

        Vector2D result = nr.solve(new Vector2D(rho, 1.0d));

        return result.x;
    }
}
