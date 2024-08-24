package grids;

import com.gerg2008.app.model.Component;
import com.gerg2008.app.service.ComponentService;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComponentGrid extends Div {


    private final ComponentServiceImpl dataService;

    @Autowired
    public ComponentGrid(ComponentServiceImpl dataService) {
        this.dataService = dataService;
        Grid<Component> grid = new Grid<>(Component.class, false);
        grid.addColumn(Component::getName).setHeader("First name");
        grid.addColumn(Component::getFormula).setHeader("Last name");

        Iterable<Component> iterable = dataService.getAll();
        List<Component> list = StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());


        grid.setItems(list);
        add(grid);
    }


}
