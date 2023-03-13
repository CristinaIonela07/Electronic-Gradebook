import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateStudent {
    JFrame frame = new JFrame("Student_Name");
    JButton next;
    JLabel lname;
    JTextField name;
    public DateStudent() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(500,300);

        JPanel panel1 = new JPanel(null);
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        next = new JButton("Next");
        next.setBounds(350,130,80,40);
        panel1.add(next);

        JLabel label = new JLabel("Please enter your name");
        label.setFont(new Font("Verdana", Font.BOLD, 15));
        label.setBounds(80,40,240,60);

        name = new JTextField("", 20);
        name.setFont(new Font("Verdana", Font.BOLD, 15));
        name.setBounds(150,100,300,20);
        lname = new JLabel("Name:");
        lname. setFont(new Font("Verdana", Font.BOLD, 15));
        lname.setBounds(80,100,100,20);

        panel1.add(label);
        panel1.add(lname);
        panel1.add(name);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stud_name = name.getText();
                for (Course course : Catalog.getInstance().getCourses()) {
                    for (Group g : course.getGroups().values()) {
                        for (Student s : g) {
                            if (s.toString().equals(stud_name)) {
                                new StudentPage(s);
                                break;
                            }
                        }
                    }
                }
            }
        };

        next.addActionListener(al);
        frame.add(panel1);
        frame.setVisible(true);
    }
}
