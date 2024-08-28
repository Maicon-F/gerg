package customizedVaadinComponents;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class CustomNotification extends Notification {
    public CustomNotification(String message) {
        super(message, 4000, Notification.Position.BOTTOM_END);
        this.addThemeVariants(NotificationVariant.LUMO_ERROR);
        this.getElement().getStyle().set("background-color", "blue");
        this.getElement().getStyle().set("color", "white");
        this.open();
    }
}
