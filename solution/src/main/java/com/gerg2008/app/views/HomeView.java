package com.gerg2008.app.views;

import com.gerg2008.app.controller.Result;
import com.gerg2008.app.model.Component;
import com.gerg2008.app.model.Output;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import customizedVaadinComponents.CustomButton;
import customizedVaadinComponents.CustomComboBox;
import customizedVaadinComponents.CustomNotification;
import customizedVaadinComponents.CustomTextField;
import grids.OutputGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import utils.InputValidationException;
import utils.validation.CompositionValidation;
import utils.validation.TemperaturePressureValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    OutputGrid outputGrid = new OutputGrid();
    Grid<Map.Entry<String, Double>> grid = new Grid<>();

    public HomeView(@Autowired ComponentServiceImpl service) throws Exception {
        this.service = service;
        add(new H1("GERG 2008 EOS Application"));
        buildBody();
    }



private void addSubmitListener() {
    submit = new CustomButton("Submit");
    submit.addClickListener(event -> {
        try {
            submitAction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    });
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
      List<Component> list = new ArrayList<>();
      for(String key: map.keySet()) {
            Component c = service.getByName(key);
            c.setComposition(map.get(key));
            list.add(c);
      }
      try {
          validate(list, map);
          Result results = new Result(list, temperature, pressure);
          List<Output> outputs = results.getResults();
          outputGrid.setItems(outputs);
      }catch(InputValidationException e) {
          new CustomNotification(e.getMessage());
      }
    }


    public void buildBody(){
        addSubmitListener();
        createMapGrid();
        Iterable<Component> iterable = service.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
        ComboBox<Component> comboBox =new CustomComboBox("Substance");
        comboBox.addValueChangeListener(event -> {s = event.getValue().getName().toString();});
        comboBox.setItems(list);
        comboBox.setItemLabelGenerator(Component::getName);
        HorizontalLayout hl = createHorizontalLayout(comboBox);
        VerticalLayout vl = createVerticalLayout(hl);
        HorizontalLayout layout = new HorizontalLayout(vl, outputGrid);
        layout.setSizeFull();
        layout.addClassName("centralized-layout");

        add(layout);
    }

    private VerticalLayout createVerticalLayout(HorizontalLayout hl) {
        H2 title = new H2("Input");
        VerticalLayout vl = new VerticalLayout(title, hl, createButtons(),grid, submit);
        vl.setJustifyContentMode(JustifyContentMode.START);
        vl.setAlignItems(Alignment.CENTER);
        vl.setAlignSelf(Alignment.CENTER, grid);
        vl.setSizeFull();

        return vl;
    }

    private HorizontalLayout createHorizontalLayout(ComboBox<Component> comboBox){
        HorizontalLayout hl = new HorizontalLayout();
        new HorizontalLayout();
        TextField textField = new CustomTextField("Composition");
        textField.addValueChangeListener(event -> {xi = event.getValue().toString();});
        TextField tempTextField = new CustomTextField("Temperature (K)");
        tempTextField.addValueChangeListener(event -> {temperature = Double.valueOf(event.getValue().toString());});
        TextField pressTextField = new CustomTextField("Pressure (bar)");
        pressTextField.addValueChangeListener(event -> {pressure = Double.valueOf(event.getValue().toString());});
        hl.add(comboBox, textField, tempTextField, pressTextField);
        hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        hl.addClassName("styled-border-layout");
        hl.setSpacing(true);

        return hl;
    }

    private HorizontalLayout createButtons(){
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
        grid.setWidthFull();
        grid.setWidth("40%");
    }

    private void validate(List<Component> list, HashMap<String, Double> map) throws InputValidationException {
        TemperaturePressureValidation tpValidator = new TemperaturePressureValidation(temperature, pressure, list);
        tpValidator.linkWith(new CompositionValidation(map));
        tpValidator.validate();
    }

}


