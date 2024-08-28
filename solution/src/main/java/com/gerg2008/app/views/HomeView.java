package com.gerg2008.app.views;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
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

    Grid<Map.Entry<String, Double>> grid = new Grid<>();

    public HomeView(@Autowired ComponentServiceImpl service) {
        this.service = service;
        add(new H1("GERG 2008 EOS Application"));
        createCombobox();
        createMapGrid();

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

    public void submit(){
        Double sum = 0.0;
        for(Double v: map.values()) {
            sum = sum + v;
        }
        if(sum !=0 )
            new CustomNotification("Wront input. The sum of all compositions must be equals to 1");
    }


    public void createCombobox(){
        Iterable<Component> iterable = service.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
        ComboBox<Component> comboBox =new CustomComboBox("Substance");
        comboBox.addValueChangeListener(event -> {s = event.getValue().getName().toString();});
        comboBox.setItems(list);
        comboBox.setItemLabelGenerator(Component::getName);

        HorizontalLayout hl = new HorizontalLayout();
        TextField textField = new CustomTextField("Composition");
        textField.addValueChangeListener(event -> {xi = event.getValue().toString();});

        hl.add(comboBox);
        hl.add(textField);
        hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        hl.addClassName("styled-border-layout");
        hl.setSpacing(true);

        VerticalLayout vl = new VerticalLayout();
        vl.setJustifyContentMode(JustifyContentMode.START);
        vl.add(hl);
        vl.add(createButtons());
        vl.add(grid);
        vl.setAlignItems(Alignment.CENTER);
        vl.setWidth("auto");

        add(vl);
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
        grid.setWidth("auto");



    }

}


