import java.util.ArrayList;

public class Parent extends User implements Observer{
    private ArrayList<Notification> notifications;
    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
        notifications = new ArrayList<Notification>();
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }
}
