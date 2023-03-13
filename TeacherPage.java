import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class TeacherPage {
    private ArrayList<Course> listcourses;
    DefaultListModel<String> nameCourses;
    Vector<String> notes;
    JButton validate;
    JFrame frame = new JFrame("TeacherPage");
    Catalog catalog = Catalog.getInstance();

    public TeacherPage(Teacher teacher, ScoreVisitor scorevisitor) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(800,600);
        frame.setLayout((new GridLayout(1, 2)));

        setListCourses(teacher);
        setNameCourses();

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBorder(new EmptyBorder(10, 10, 70, 10));

        JList list = new JList<String>(nameCourses);
        JScrollPane scrollPane = new JScrollPane(list);
        panel1.add(scrollPane);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setBorder(new EmptyBorder(10, 10, 70, 10));

        JScrollPane scrollPane2;
        JList<String> model = new JList<>();

        ListSelectionListener lsl= new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                if (listcourses.isEmpty()) return;

                JList ls = (JList) e.getSource();
                int selections[] = ls.getSelectedIndices();
                Object selectedValues[] = ls.getSelectedValues();
                Course course = listcourses.get(selections[0]);

                notes = scorevisitor.getDouble(teacher, course.getName());
                model.setListData(notes);
            }
        };

        validate = new JButton("Validate");
        validate.setBounds(150,500,80,40);
        panel2.add(validate);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scorevisitor.visit(teacher);
                if (notes != null)
                    notes.clear();
                model.clearSelection();
            }
        };

        scrollPane2 = new JScrollPane(model);
        panel2.add(scrollPane2);
        frame.add(panel1);
        frame.add(panel2);

        validate.addActionListener(al);
        list.addListSelectionListener(lsl);

        frame.setVisible(true);
    }

    public void setListCourses(Teacher teacher){
        listcourses = new ArrayList<Course>();
        for (Course c : catalog.getCourses())
           if (c.getTeacher().toString().equals(teacher.toString()))
               listcourses.add(c);

    }

    public void setNameCourses(){
        nameCourses = new DefaultListModel<String>();
        for (Course c : listcourses)
            nameCourses.addElement(c.getName());

    }
}
