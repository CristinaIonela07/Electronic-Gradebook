import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartInterface {
    JFrame frame = new JFrame("Select");

    JButton type_s, type_a, type_t, type_p;
    JLabel status;
    public StartInterface(ScoreVisitor scoreVisitor) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(400,400);
        frame.setLayout(new GridLayout(2, 1));

        type_s = new JButton("Student");
        type_a = new JButton("Assistant");
        type_t = new JButton("Teacher");
        type_p = new JButton("Parent");
        status = new JLabel("Status");

        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(40, 80, 0, 0));

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel label = new JLabel("Status");
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setFont(new Font("Verdana", Font.BOLD, 30));
        label.setPreferredSize(new Dimension(200, 100));
        panel1.add(label);

        type_s.setFont(new Font("Verdana", Font.BOLD, 20));
        type_s.setBounds(0, 0, 30, 20);
        buttonPane.add(type_s);

        buttonPane.add(Box.createRigidArea(new Dimension(20, 20)));
        type_a.setFont(new Font("Verdana", Font.BOLD, 20));
        buttonPane.add(type_a);

        buttonPane.add(Box.createRigidArea(new Dimension(0, 0)));
        type_t.setFont(new Font("Verdana", Font.BOLD, 20));
        buttonPane.add(type_t);

        buttonPane.add(Box.createRigidArea(new Dimension(20, 20)));
        type_p.setFont(new Font("Verdana", Font.BOLD, 20));
        buttonPane.add(type_p);

        ActionListener als = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DateStudent();
            }
        };

        type_s.addActionListener(als);

        ActionListener alt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DateTeacher(scoreVisitor);
            }
        };

        type_t.addActionListener(alt);

        ActionListener alp = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DateParent();
            }
        };

        type_p.addActionListener(alp);

        ActionListener ala = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DateAssistant(scoreVisitor);
            }
        };

        type_a.addActionListener(ala);

        frame.add(panel1);
        frame.add(buttonPane);
        frame.setVisible(true);
    }
}
