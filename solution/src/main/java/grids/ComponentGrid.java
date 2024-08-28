package grids;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.service.impl.ComponentServiceImpl;

import com.gerg2008.app.views.HomeView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ComponentGrid extends BaseGrid<Component> {


    private final ComponentServiceImpl dataService;


    private HomeView view;
    public ComponentGrid(HomeView view) {
        super(Component.class);

        this.dataService= view.service;
        this.addColumn(Component::getName).setHeader("Component");
        this.addColumn(Component::getFormula).setHeader("Molecular Formula");

        Iterable<Component> iterable = dataService.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());


        this.setWidthFull();
        this.setHeightFull();
        this.setSizeFull();
        this.setItems(list);

    }


}
