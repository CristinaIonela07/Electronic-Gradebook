import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class StudentPage{
    private ArrayList<Course> listcourses;
    DefaultListModel<String> nameCourses;
    DefaultListModel<String> all_assistants;
    JFrame frame = new JFrame("StudentPage");
    JTextField teacher, assistant, partial, exam;
    JList asistenti;

    JLabel lteacher, lassistant, lpartial, lexam, lasistenti;

    Catalog catalog = Catalog.getInstance();

    public StudentPage(Student student) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(800,600);
        frame.setLayout((new GridLayout(1, 2)));

        setListCourses(student);
        setNameCourses();

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        JList list = new JList<String>(nameCourses);
        JScrollPane scrollPane = new JScrollPane(list);
        panel1.add(scrollPane);

        JPanel panel2 = new JPanel(null);
        teacher = new JTextField("", 20);
        teacher.setBounds(120,10,250,20);
        lteacher = new JLabel("Titular:");
        lteacher.setBounds(20,10,100,20);

        ScrollPane sc = new ScrollPane();
        sc.setBounds(120,50,250,40);
        asistenti = new JList();
        sc.add(asistenti);
        lasistenti= new JLabel("Toti asistentii:");
        lasistenti.setBounds(20,50,100,20);

        assistant = new JTextField("", 20);
        assistant.setBounds(120,110,250,20);
        lassistant = new JLabel("Asistent student:");
        lassistant.setBounds(20,110,100,20);

        partial = new JTextField("", 20);
        partial.setBounds(120,150,250,20);
        lpartial = new JLabel("Nota partial:");
        lpartial.setBounds(20,150,100,20);

        exam = new JTextField("", 20);
        exam.setBounds(120,190,250,20);
        lexam = new JLabel("Nota examen:");
        lexam.setBounds(20,190,100,20);

        panel2.add(lteacher);
        panel2.add(teacher);
        panel2.add(lasistenti);
        panel2.add(sc);
        panel2.add(lassistant);
        panel2.add(assistant);
        panel2.add(lpartial);
        panel2.add(partial);
        panel2.add(lexam);
        panel2.add(exam);

        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) return;
                if(listcourses.isEmpty()) return;
                JList ls = (JList) e.getSource();
                int selections[] = ls.getSelectedIndices();
                Object selectedValues[] = ls.getSelectedValues();
                Course course = listcourses.get(selections[0]);

                teacher.setText(course.getTeacher().toString());

                Vector<String> ass = new Vector<String>();
                for (Assistant a : course.getAssistants()){
                    ass.add(a.toString());
                }
                asistenti.setListData(ass);

                for (Group g : course.getGroups().values()){
                    for (Student s : g){
                        if (s.toString().equals(student.toString())){
                            assistant.setText(g.getAssistant().toString());
                            break;
                        }
                    }
                }
                Grade g = course.getGrade(student);
                if (g != null) {
                    partial.setText(g.getPartialScore().toString());
                    exam.setText(g.getExamScore().toString());
                }
                if (g == null){
                    partial.setText("0.00");
                    exam.setText("0.00");
                }
            }
        };

        list.addListSelectionListener(lsl);

        frame.add(panel1);
        frame.add(panel2);
        frame.setVisible(true);
    }

    public void setListCourses(Student student){
        listcourses = new ArrayList<Course>();
        for (Course c : catalog.getCourses())
            for (Group g : c.getGroups().values())
                for (Student s : g)
                    if (s.toString().equals(student.toString()))
                      listcourses.add(c);

    }

    public void setNameCourses(){
        nameCourses = new DefaultListModel<String>();
        for (Course c : listcourses){
            nameCourses.addElement(c.getName());
        }
    }

    public void setAllAssistants(Course curs){
        all_assistants = new DefaultListModel<String>();
        for (Course c : listcourses)
            if (c.getName().equals(curs.getName()))
                for(Assistant a : c.getAssistants())
                    all_assistants.addElement(a.toString());


    }
}
