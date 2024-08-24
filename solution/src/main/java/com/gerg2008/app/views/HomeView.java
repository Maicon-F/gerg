package com.gerg2008.app.views;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import grids.ComponentGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route("")
@SpringComponent
public class HomeView extends VerticalLayout {

    @Autowired
    private ComponentServiceImpl service;

    public HomeView(@Autowired ComponentServiceImpl service) {
        this.service = service;
        Grid<Component> grid = new Grid<>(Component.class, false);
        grid.addColumn(Component::getName).setHeader("First name");
        grid.addColumn(Component::getFormula).setHeader("Last name");

        Iterable<Component> iterable = service.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());


        grid.setItems(list);
        add(grid);
        add(new H1("Welcome to your new application"));
        add(new Paragraph("This is the home view"));

        add(new Paragraph("You can edit this view in srcomeView.java"));
        add(grid);

    }
}


