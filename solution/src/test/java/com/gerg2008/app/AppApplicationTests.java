package com.gerg2008.app;

import com.gerg2008.app.controller.HelmholtzCalculator;
import com.gerg2008.app.factory.ComponentFactory;
import com.gerg2008.app.model.Component;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AppApplicationTests {

	private static HelmholtzCalculator calculator;


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
		System.out.println("a ideal: " + a);
		assertTrue(Math.abs(a) - 4.61 < 0.1);

	}


	@Test
	public void TestAlphaResPentane() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component p =factory.createPentane();
		p.setComposition(1);

		List<Component> list = new ArrayList<>();
		list.add(p);

		calculator = new HelmholtzCalculator(66.824581, 300, list);
		double a = calculator.calculateAlphaResoi(p);
		System.out.println("Res pentane: " + Math.abs(a));
		assertTrue(Math.abs(a) - 0.77 < 0.1);
	}

	@Test
	public void TestAlphaResCO2() throws Exception {
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
	public void TestAlphaRealCO2() throws Exception {
		ComponentFactory factory = new ComponentFactory();
		Component co2 =factory.createCO2();
		co2.setComposition(1);

		List<Component> list = new ArrayList<>();
		list.add(co2);

		calculator = new HelmholtzCalculator(66.824581, 300, list);

		double a = calculator.aReal();
		System.out.println(Math.abs(a));
		assertTrue(Math.abs(a) - 0.520 < 0.1);
	}

}
