package customizedVaadinComponents;

import com.vaadin.flow.component.button.Button;

public class CustomButton extends Button {

    public CustomButton(String label) {
        super(label);
        this.getStyle().set("background-color", "#007bff")
                .set("color", "#fff")
                .set("border-radius", "8px")
                .set("padding", "10px 20px")
                .set("font-size", "16px")
                .set("border", "none")
                .set("cursor", "pointer");

        this.addClassName("button-hover");
    }
}
