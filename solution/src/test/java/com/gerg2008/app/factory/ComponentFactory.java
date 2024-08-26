package com.gerg2008.app.factory;

import com.gerg2008.app.model.Alpha_Ideal_oi;
import com.gerg2008.app.model.Alpha_res_oi;
import com.gerg2008.app.model.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentFactory {


 public Component createCO2(){
     Component c = new Component();
     c.setName("Carbon Dioxide");
     c.setFormula("CO2");
     c.setMm(44.0095*0.001);
     c.setRho_ci(10.624978698*1000);
     c.setT_ci(304.128);

     ComponentBuilder builder = new ComponentBuilder(c);
     builder.withAlphaIdealoi(aIdealCO2()).withAlphaResoi(aresCO2());

     return c;
    }

    public Component createPentane(){
        Component c = new Component();
        c.setName("Pentane");
        c.setFormula("n-C5H12");
        c.setMm(72.14878*0.001);
        c.setRho_ci(3.215577588*1000);
        c.setT_ci(469.7);

        ComponentBuilder builder = new ComponentBuilder(c);
        builder.withAlphaIdealoi(aIdealPentane()).withAlphaResoi(aresPentane());

        return c;
    }


 private List<Alpha_Ideal_oi> aIdealCO2(){
     Alpha_Ideal_oi a1 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a2 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a3 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a4 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a5 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a6 = new Alpha_Ideal_oi();
     Alpha_Ideal_oi a7 = new Alpha_Ideal_oi();

     a1.setK(1);
     a1.setName("Carbon Dioxide");
     a1.setN_oik(11.925152758);
     a1.setTeta_oik(0.0);

     a2.setK(2);
     a2.setName("Carbon Dioxide");
     a2.setN_oik(-16.118762264);
     a2.setTeta_oik(0.0);

     a3.setK(3);
     a3.setName("Carbon Dioxide");
     a3.setN_oik(2.50002);
     a3.setTeta_oik(0.0);

     a4.setK(4);
     a4.setName("Carbon Dioxide");
     a4.setN_oik(2.04452);
     a4.setTeta_oik(3.022758166);

     a5.setK(5);
     a5.setName("Carbon Dioxide");
     a5.setN_oik(-1.06044);
     a5.setTeta_oik(-2.844425476);

     a6.setK(6);
     a6.setName("Carbon Dioxide");
     a6.setN_oik(2.03366);
     a6.setTeta_oik(1.589964364);

     a7.setK(7);
     a7.setName("Carbon Dioxide");
     a7.setN_oik(0.01393);
     a7.setTeta_oik(1.121596090);

     ArrayList<Alpha_Ideal_oi> list = new ArrayList<>();
     list.add(a1);
     list.add(a2);
     list.add(a3);
     list.add(a4);
     list.add(a5);
     list.add(a6);
     list.add(a7);

     return list;
 }

 private List<Alpha_res_oi> aresCO2(){
     Alpha_res_oi a1 = new Alpha_res_oi();
     Alpha_res_oi a2 = new Alpha_res_oi();
     Alpha_res_oi a3 = new Alpha_res_oi();
     Alpha_res_oi a4 = new Alpha_res_oi();
     Alpha_res_oi a5 = new Alpha_res_oi();
     Alpha_res_oi a6 = new Alpha_res_oi();
     Alpha_res_oi a7 = new Alpha_res_oi();
     Alpha_res_oi a8 = new Alpha_res_oi();
     Alpha_res_oi a9 = new Alpha_res_oi();
     Alpha_res_oi a10 = new Alpha_res_oi();
     Alpha_res_oi a11 = new Alpha_res_oi();
     Alpha_res_oi a12 = new Alpha_res_oi();
     Alpha_res_oi a13 = new Alpha_res_oi();
     Alpha_res_oi a14 = new Alpha_res_oi();
     Alpha_res_oi a15 = new Alpha_res_oi();
     Alpha_res_oi a16 = new Alpha_res_oi();
     Alpha_res_oi a17 = new Alpha_res_oi();
     Alpha_res_oi a18 = new Alpha_res_oi();
     Alpha_res_oi a19 = new Alpha_res_oi();
     Alpha_res_oi a20 = new Alpha_res_oi();
     Alpha_res_oi a21 = new Alpha_res_oi();
     Alpha_res_oi a22 = new Alpha_res_oi();

     a1.setK(1);
     a1.setN_oik(0.52646564804653);
     a1.setC_oik(0.0);
     a1.setD_oik(1);
     a1.setT_oik(0.0);

     a2.setK(2);
     a2.setN_oik(-0.14995725042592*10);
     a2.setC_oik(0.0);
     a2.setD_oik(1);
     a2.setT_oik(1.250);

     a3.setK(3);
     a3.setN_oik(0.27329786733782);
     a3.setC_oik(0.0);
     a3.setD_oik(2);
     a3.setT_oik(1.625);

     a4.setK(4);
     a4.setN_oik(0.12949500022786);
     a4.setC_oik(0.0);
     a4.setD_oik(3);
     a4.setT_oik(0.375);

     a5.setK(5);
     a5.setN_oik( 0.15404088341841);
     a5.setC_oik(1.0);
     a5.setD_oik(3);
     a5.setT_oik(0.375);

     a6.setK(6);
     a6.setN_oik(-0.58186950946814);
     a6.setC_oik(1);
     a6.setD_oik(3);
     a6.setT_oik(1.375);

     a7.setK(7);
     a7.setN_oik(-0.18022494838296);
     a7.setC_oik(1.0);
     a7.setD_oik(4);
     a7.setT_oik(1.125);

     a8.setK(8);
     a8.setN_oik(-0.95389904072812*0.1);
     a8.setC_oik(1.0);
     a8.setD_oik(5);
     a8.setT_oik(1.375);

     a9.setK(9);
     a9.setN_oik(-0.80486819317679*0.01);
     a9.setC_oik(1.0);
     a9.setD_oik(6);
     a9.setT_oik(0.125);

     a10.setK(10);
     a10.setN_oik(-0.35547751273090*0.1);
     a10.setC_oik(1.0);
     a10.setD_oik(6);
     a10.setT_oik(1.625);

     a11.setK(11);
     a11.setN_oik(-0.28079014882405);
     a11.setC_oik(2.0);
     a11.setD_oik(1);
     a11.setT_oik(3.750);

     a12.setK(12);
     a12.setN_oik(-0.82435890081677*0.1);
     a12.setC_oik(2.0);
     a12.setD_oik(4);
     a12.setT_oik(3.500);

     a13.setK(13);
     a13.setN_oik(0.10832427979006*0.1);
     a13.setC_oik(3);
     a13.setD_oik(1);
     a13.setT_oik(7.500);

     a14.setK(14);
     a14.setN_oik(-0.67073993161097*0.01);
     a14.setC_oik(3);
     a14.setD_oik(1);
     a14.setT_oik(8.000);

     a15.setK(15);
     a15.setN_oik(-0.46827907600524*0.01);
     a15.setC_oik(3);
     a15.setD_oik(3);
     a15.setT_oik(6.0);

     a16.setK(16);
     a16.setN_oik(-0.28359911832177*0.1);
     a16.setC_oik(3);
     a16.setD_oik(3);
     a16.setT_oik(16.0);

     a17.setK(17);
     a17.setN_oik(0.19500174744098*0.1);
     a17.setC_oik(3);
     a17.setD_oik(4);
     a17.setT_oik(11.0);

     a18.setK(18);
     a18.setN_oik(-0.21609137507166);
     a18.setC_oik(5);
     a18.setD_oik(5);
     a18.setT_oik(24.0);

     a19.setK(19);
     a19.setN_oik(0.43772794926972);
     a19.setC_oik(5);
     a19.setD_oik(5);
     a19.setT_oik(26.0);

     a20.setK(20);
     a20.setN_oik(-0.22130790113593);
     a20.setC_oik(5);
     a20.setD_oik(5);
     a20.setT_oik(28.0);

     a21.setK(21);
     a21.setN_oik(0.15190189957331*0.1);
     a21.setC_oik(6);
     a21.setD_oik(5);
     a21.setT_oik(24.0);

     a22.setK(22);
     a22.setN_oik(-0.15380948953300*0.1);
     a22.setC_oik(6);
     a22.setD_oik(5);
     a22.setT_oik(26.0);


     List<Alpha_res_oi> list = new ArrayList<>();
     list.add(a1);
     list.add(a2);
     list.add(a3);
     list.add(a4);
     list.add(a5);
     list.add(a6);
     list.add(a7);
     list.add(a8);
     list.add(a9);
     list.add(a10);
     list.add(a11);
     list.add(a12);
     list.add(a13);
     list.add(a14);
     list.add(a15);
     list.add(a16);
     list.add(a17);
     list.add(a18);
     list.add(a19);
     list.add(a20);
     list.add(a21);
     list.add(a22);

     return list;
 }


    private List<Alpha_Ideal_oi> aIdealPentane(){
        Alpha_Ideal_oi a1 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a2 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a3 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a4 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a5 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a6 = new Alpha_Ideal_oi();
        Alpha_Ideal_oi a7 = new Alpha_Ideal_oi();

        a1.setK(1);
        a1.setName("Pentane");
        a1.setN_oik(14.536611217);
        a1.setTeta_oik(0.0);

        a2.setK(2);
        a2.setName("Pentane");
        a2.setN_oik(-89.919548319);
        a2.setTeta_oik(0.0);

        a3.setK(3);
        a3.setName("Pentane");
        a3.setN_oik(3.00000);
        a3.setTeta_oik(0.0);

        a4.setK(4);
        a4.setName("Pentane");
        a4.setN_oik(8.95043);
        a4.setTeta_oik(0.380391739);

        a5.setK(5);
        a5.setName("Pentane");
        a5.setN_oik(21.83600);
        a5.setTeta_oik(1.789520971);

        a6.setK(6);
        a6.setName("Pentane");
        a6.setN_oik(33.40320);
        a6.setTeta_oik(3.777411113);

        a7.setK(7);
        a7.setName("Pentane");
        a7.setN_oik(0.0);
        a7.setTeta_oik(0.0);

        ArrayList<Alpha_Ideal_oi> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);

        return list;
    }


    private List<Alpha_res_oi> aresPentane(){
        Alpha_res_oi a1 = new Alpha_res_oi();
        Alpha_res_oi a2 = new Alpha_res_oi();
        Alpha_res_oi a3 = new Alpha_res_oi();
        Alpha_res_oi a4 = new Alpha_res_oi();
        Alpha_res_oi a5 = new Alpha_res_oi();
        Alpha_res_oi a6 = new Alpha_res_oi();
        Alpha_res_oi a7 = new Alpha_res_oi();
        Alpha_res_oi a8 = new Alpha_res_oi();
        Alpha_res_oi a9 = new Alpha_res_oi();
        Alpha_res_oi a10 = new Alpha_res_oi();
        Alpha_res_oi a11 = new Alpha_res_oi();
        Alpha_res_oi a12 = new Alpha_res_oi();

        a1.setK(1);
        a1.setN_oik(0.10968643098001*10);
        a1.setC_oik(0.0);
        a1.setD_oik(1);
        a1.setT_oik(0.250);

        a2.setK(2);
        a2.setN_oik(-0.29988888298061*10);
        a2.setC_oik(0.0);
        a2.setD_oik(1);
        a2.setT_oik(1.125);

        a3.setK(3);
        a3.setN_oik(0.99516886799212);
        a3.setC_oik(0.0);
        a3.setD_oik(1);
        a3.setT_oik(1.500);


        a4.setK(4);
        a4.setN_oik(-0.16170708558539);
        a4.setC_oik(0.0);
        a4.setD_oik(2);
        a4.setT_oik(1.375);

        a5.setK(5);
        a5.setN_oik(0.11334460072775);
        a5.setC_oik(0.0);
        a5.setD_oik(3);
        a5.setT_oik(0.250);

        a6.setK(6);
        a6.setN_oik(0.26760595150748*0.001);
        a6.setC_oik(0.0);
        a6.setD_oik(7);
        a6.setT_oik(0.875);

        a7.setK(7);
        a7.setN_oik(0.40979881986931);
        a7.setC_oik(1.0);
        a7.setD_oik(2);
        a7.setT_oik(0.625);

        a8.setK(8);
        a8.setN_oik(-0.40876423083075*0.1);
        a8.setC_oik(1.0);
        a8.setD_oik(5);
        a8.setT_oik(1.750);

        a9.setK(9);
        a9.setN_oik(-0.38169482469447);
        a9.setC_oik(2.0);
        a9.setD_oik(1);
        a9.setT_oik(3.625);

        a10.setK(10);
        a10.setN_oik(-0.10931956843993);
        a10.setC_oik(2.0);
        a10.setD_oik(4);
        a10.setT_oik(3.625);

        a11.setK(11);
        a11.setN_oik(-0.32073223327990*0.1);
        a11.setC_oik(3.0);
        a11.setD_oik(3);
        a11.setT_oik(14.500);

        a12.setK(12);
        a12.setN_oik(0.16877016216975*0.1);
        a12.setC_oik(3.0);
        a12.setD_oik(4);
        a12.setT_oik(12.000);

        List<Alpha_res_oi> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);
        list.add(a9);
        list.add(a10);
        list.add(a11);
        list.add(a12);

        return list;
    }



}
