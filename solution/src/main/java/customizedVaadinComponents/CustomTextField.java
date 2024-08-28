package customizedVaadinComponents;


import com.vaadin.flow.component.textfield.TextField;

public class CustomTextField extends TextField {
    public CustomTextField(String label) {
        TextField textField = new TextField();
        this.setLabel(label);
        this.addClassName("custom-input-style");
    }
}
