package com.gerg2008.app;

import com.gerg2008.app.controller.HelmholtzCalculator;
import com.gerg2008.app.factory.ComponentFactory;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utils.ObjFunction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AppApplicationTests {

	private static HelmholtzCalculator calculator;

	@Autowired
	private ComponentServiceImpl service;

	@Test
	void contextLoads() {
	}


	@Test
	public void testAlphaIdealCO2() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component co2 =factory.createCO2();
		List<Component> list = new ArrayList<>();
		list.add(co2);

		calculator = new HelmholtzCalculator(66.824581, 300, list);
		double a = calculator.calculateAlphaIdeal_oi(co2);
		assertTrue(Math.abs(a) - 0.520 < 0.1);
	}

	@Test
	public void testAlphaIdealPentane() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component pentane =factory.createPentane();
		List<Component> list = new ArrayList<>();
		list.add(pentane);

		calculator = new HelmholtzCalculator(66.824581, 300, list);
		double a = calculator.calculateAlphaIdeal_oi(pentane);
		assertTrue(Math.abs(a) - 4.61 < 0.1);

	}


	@Test
	public void testAlphaResPentane() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component p =factory.createPentane();
		p.setComposition(1);

		List<Component> list = new ArrayList<>();
		list.add(p);

		calculator = new HelmholtzCalculator(66.824581, 300, list);
		double a = calculator.calculateAlphaResoi(p);
		assertTrue(Math.abs(a) - 0.77 < 0.1);
	}

	@Test
	public void testAlphaResCO2() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component co2 =factory.createCO2();
		co2.setComposition(1);

		List<Component> list = new ArrayList<>();
		list.add(co2);
		calculator = new HelmholtzCalculator(66.824581, 300, list);

		double a = calculator.calculateAlphaResoi(co2);
		assertTrue(Math.abs(a) - 0.520 < 0.1);
	}

	@Test
	public void testAlphaRealCO2() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component co2 =factory.createCO2();
		co2.setComposition(1);

		List<Component> list = new ArrayList<>();
		list.add(co2);

		calculator = new HelmholtzCalculator(66.824581, 300, list);

		double a = calculator.aReal();
		assertTrue(Math.abs(a) - 0.520 < 0.1);
	}


	@Test
	public void testMixtureCO2Pentane() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		List<Component> list = factory.createCO2PentaneEquiMixture();

		calculator = new HelmholtzCalculator(66.8245, 300, list);

		double a = calculator.aReal();
		System.out.println("AAAAA: " + a);
		assertTrue(Math.abs(a) - 3.28 < 0.1);
	}

	@Test
	public void testRhoMixtureCO2Pentane() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		List<Component> list = factory.createCO2PentaneEquiMixture();

		ObjFunction f = new ObjFunction();
		double result = f.solve(69139,300,100000, list);

		System.out.println("RESULT: "+ result);

	}

	@Test
	public void testOfBivariantSolver(){
		UnivariateFunction function = x -> (x - 3) * (x - 3);

		UnivariateFunction derivative = x -> 2 * (x - 3);

		// Create an instance of BrentSolver
		BrentSolver solver = new BrentSolver(1e-8, 1e-8);

		// Define the interval to search in
		double lowerBound = 0;
		double upperBound = 6;

		// Perform the optimization by finding the root of the derivative function
		double minimumPoint = solver.solve(1000, derivative, lowerBound, upperBound);

		// Evaluate the function at the found point
		double minimumValue = function.value(minimumPoint);

		// Output the result
		System.out.println("Minimum value: " + minimumValue);
		System.out.println("Point of minimum: " + minimumPoint);
	}


}
