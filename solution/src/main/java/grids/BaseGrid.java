package grids;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;

public class BaseGrid<T> extends Grid<T> {

    public BaseGrid(Class<T> clazz) {
        super(clazz);
        this.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
   //     this.getStyle().set("border", "1px solid black");
     //   this.getStyle().set("overflow", "auto");
        this.setSizeFull();
    }
}
