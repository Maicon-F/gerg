package customizedVaadinComponents;

import com.vaadin.flow.component.combobox.ComboBox;

public class CustomComboBox extends ComboBox {
    public CustomComboBox(String label) {
        super(label);
        this.addClassName("custom-input-style");
    }
}
