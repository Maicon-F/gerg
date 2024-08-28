package customizedVaadinComponents;

import com.vaadin.flow.component.html.Span;

public class CustomSpan extends Span {
    public CustomSpan(String label) {
        super(label);
        this.addClassName("large-label");
    }
}
