package grids;

import com.gerg2008.app.model.Output;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class OutputGrid extends VerticalLayout {

    Grid<Output> outputGrid = new Grid<>();

    public OutputGrid() {
        outputGrid.addColumn(output -> String.format("%.2e", output.getZ())).setHeader("Z").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(output -> String.format("%.2e", output.getKT())).setHeader("KT").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(output -> String.format("%.2e", output.getCv())).setHeader("Cv").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getCp())).setHeader("Cp").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getRho())).setHeader("Density").setAutoWidth(true).setFlexGrow(0);;
        outputGrid.addColumn(output -> String.format("%.2e", output.getMuJT())).setHeader("JT coeficient").setAutoWidth(true).setFlexGrow(0);
        outputGrid.addColumn(Output::getType).setAutoWidth(true).setFlexGrow(0);
        outputGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        outputGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

        H2 title = new H2("Physical-Chemical Properties Output");

        add(title, outputGrid);

        this.setSizeFull();
        this.setAlignItems(Alignment.CENTER);
        this.getStyle().set("margin", "0px 50px");


    }

    public void setItems(List<Output> outputs) {
      outputGrid.setItems(outputs);
    }
}
