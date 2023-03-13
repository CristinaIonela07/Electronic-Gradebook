import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class ParentPage {
    private ArrayList<Notification> listnot;
    Vector<String> notifications;
    JButton refresh;
    JFrame frame = new JFrame("ParentPage");

    public ParentPage(Parent parent){
        listnot = parent.getNotifications();
        notifications = new Vector<String>();
        for (Notification n : listnot)
            notifications.add(n.toString());

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(800,600);
        frame.setLayout((new GridLayout(1, 2)));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBorder(new EmptyBorder(10, 10, 0, 10));
        JScrollPane scrollPane;

        refresh = new JButton("Refresh");
        refresh.setBounds(150,500,80,40);
        panel1.add(refresh);

        JList list = new JList<String>(notifications);
        JList<String> model = new JList<>();

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listnot = parent.getNotifications();
                notifications = new Vector<String>();
                for (Notification n : listnot)
                    notifications.add(n.toString());
                model.setListData(notifications);
            }
        };

        scrollPane = new JScrollPane(model);
        panel1.add(scrollPane);
        refresh.addActionListener(al);
        frame.add(panel1);
        frame.setVisible(true);
    }
}
