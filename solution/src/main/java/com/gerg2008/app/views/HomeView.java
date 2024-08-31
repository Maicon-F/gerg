package com.gerg2008.app.views;

import com.gerg2008.app.controller.Derivative;
import com.gerg2008.app.controller.HelmholtzCalculator;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.Output;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import customizedVaadinComponents.CustomButton;
import customizedVaadinComponents.CustomComboBox;
import customizedVaadinComponents.CustomNotification;
import customizedVaadinComponents.CustomTextField;
import grids.ComponentGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.ObjFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.gerg2008.app.Constants.R;

@Route("")
@SpringComponent
@Scope("prototype")
public class HomeView extends VerticalLayout {

    public ComponentServiceImpl service;
    private HashMap<String, Double> map = new HashMap<>();
    private String s ="";
    private String xi = "";
    private double temperature = -1.0;
    private double pressure = -1.0;

    private CustomButton submit;

    Grid<Output> outputGrid = new Grid<>();
    Grid<Map.Entry<String, Double>> grid = new Grid<>();

    public HomeView(@Autowired ComponentServiceImpl service) throws Exception {
        this.service = service;
        add(new H1("GERG 2008 EOS Application"));

        submit = new CustomButton("Submit");
        submit.addClickListener(event -> {
            try {
                submitAction();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        createCombobox();


    }




    private void handleClick(String event, String s, String xi) {
        if(event.equals("add")) {
            Double value = Double.valueOf(xi);
            if(value > 1 || value < 0) {
                new CustomNotification("Wrong input. Please add a value between 0 and 1");
                return;
            }
            map.put(s, Double.valueOf(xi));
            grid.setItems(map.entrySet());
        }else if(event.equals("remove")) {
            map.remove(s);
            grid.setItems(map.entrySet());
        }
    }

    public void submitAction() throws Exception {

        if( temperature <=0 || pressure <= 0  )
            new CustomNotification("Invalid input. No negative values allowed for pressure or temperature");


        Double sum = 0.0;
        List<Component> list = new ArrayList<>();
        for(Double v: map.values()) {
            sum = sum + v;
        }
        if(sum !=1 )
            new CustomNotification("Wrong input. The sum of all compositions must be equals to 1");

        for(String key: map.keySet()) {
            Component c = service.getByName(key);
            c.setComposition(map.get(key));
            list.add(c);
        }

        double vaporGuess = (pressure*100000.0)/(R*temperature*0.6);
        double liquidGuess = 0.0;
        boolean overTemperature = true;
        boolean overPressure = true;
        for(Component c: list){
            if(temperature <= c.getT_ci())
                overTemperature = false;
            liquidGuess = 10*c.getComposition()*c.getRho_ci() + liquidGuess;
        }

        if(overTemperature) {
            new CustomNotification("OverTemperature. Please select a value below the mixture critical point");
        }

       ObjFunction obj = new ObjFunction();
       double  RhoLiquid = obj.solve(liquidGuess, 300, 100000, list);
        double  rhoVapor = obj.solve(vaporGuess, 300, 100000, list);

        Derivative der = new Derivative();
        Output vaporOut = der.calculate(rhoVapor, temperature, list, "VAPOR");

        Output liquidOut = der.calculate(RhoLiquid, temperature, list, "LIQUID");

        List<Output> outputs = new ArrayList<>();
        outputs.add(vaporOut);
        outputs.add(liquidOut);


        outputGrid.setItems(outputs);

    }


    public void createCombobox(){
        createMapGrid();

        Iterable<Component> iterable = service.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
        ComboBox<Component> comboBox =new CustomComboBox("Substance");
        comboBox.addValueChangeListener(event -> {s = event.getValue().getName().toString();});
        comboBox.setItems(list);
        comboBox.setItemLabelGenerator(Component::getName);

        HorizontalLayout hl = new HorizontalLayout();
        TextField textField = new CustomTextField("Composition");
        textField.setMaxWidth("130px");

        textField.addValueChangeListener(event -> {xi = event.getValue().toString();});

        TextField tempTextField = new CustomTextField("Temperature (K)");
        tempTextField.setMaxWidth("130px");
        tempTextField.addValueChangeListener(event -> {temperature = Double.valueOf(event.getValue().toString());});

        TextField pressTextField = new CustomTextField("Pressure (bar)");
        pressTextField.setMaxWidth("130px");
        pressTextField.addValueChangeListener(event -> {pressure = Double.valueOf(event.getValue().toString());});


        hl.add(comboBox);
        hl.add(textField);
        hl.add(tempTextField);
        hl.add(pressTextField);
        hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        hl.addClassName("styled-border-layout");
        hl.setSpacing(true);

        H2 title = new H2("Input");
        grid.setWidthFull();
        grid.setWidth("40%");

        VerticalLayout vl = new VerticalLayout(title, hl, createButtons(),grid, submit);
        vl.setJustifyContentMode(JustifyContentMode.START);
        vl.setAlignItems(Alignment.CENTER);
        vl.setAlignSelf(Alignment.CENTER, grid);
        vl.setSizeFull();


        H2 outputTitle = new H2("Physical Chemistry Properties Output");
        VerticalLayout vl2 = new VerticalLayout(outputTitle,outputGrid);
        vl2.setSizeFull();
        vl2.getStyle().set("margin", "0px 50px");



        HorizontalLayout hl2 = new HorizontalLayout(vl, vl2);
        hl2.setSizeFull();
        hl2.addClassName("centralized-layout");

        add(hl2);


    }

    public HorizontalLayout createButtons(){
       Button button1 = new CustomButton("Add");
       button1.addClickListener(event -> {handleClick("add", s, xi);});
       Button button2 = new CustomButton("Remove");
       button2.addClickListener(event -> {handleClick("remove", s, xi);});

       HorizontalLayout hl = new HorizontalLayout();
       hl.add(button1);
       hl.add(button2);
       hl.setSpacing(true);

        return hl;
    }

    private void createMapGrid(){
        grid.addColumn(entry -> entry.getKey()).setHeader("Substance");
        grid.addColumn(entry -> entry.getValue()).setHeader("Composition");
        grid.setItems(map.entrySet());

        outputGrid.addColumn(output -> String.format("%.2e", output.getZ())).setHeader("Z").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(output -> String.format("%.2e", output.getKT())).setHeader("KT").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(output -> String.format("%.2e", output.getCv())).setHeader("Cv").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getCp())).setHeader("Cp").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getRho())).setHeader("Density").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getMuJT())).setHeader("JT coeficient").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(Output::getType).setAutoWidth(true).setFlexGrow(0);


       // outputGrid.setMaxWidth("60%");


        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        outputGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        outputGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);


    }

}


