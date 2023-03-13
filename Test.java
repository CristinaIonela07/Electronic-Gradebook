import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException{
        Catalog catalog = Catalog.getInstance();
        ScoreVisitor scorevisitor = new ScoreVisitor();
        BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
        String linie = br.readLine();
        linie = br.readLine();
        while (!linie.equals("]")) {
            Course course;
            String[] words = linie.split(" ");
            int i = 0;

            String name = "";
            while (!words[i].equals("{")) {
                name = name + " " + words[i];
                i++;
            }
            i = i + 2;
            String type = words[i];
            i = i + 3;
            String strategy = words[i];
            i = i + 3;
            Teacher teacher = new Teacher(words[i], words[i + 1]);
            teacher.setStrategy(strategy);

            if (type == "PartialScore")
                course = new PartialCourse.PartialCourseBuilder(name, teacher).build();
            else
                course = new FullCourse.FullCourseBuilder(name, teacher).build();

            course.setStrategy();
            i = i + 4;

            HashSet<Assistant> assistants = new HashSet<Assistant>();
            assistants.add(new Assistant(words[i], words[i + 1]));
            assistants.add(new Assistant(words[i + 3], words[i + 4]));

            course.setAssistants(assistants);
            catalog.addCourse(course);

            linie = br.readLine();
        }

        linie = br.readLine();
        linie = br.readLine();
        linie = br.readLine();

        while (!linie.equals("]")) {
            String[] intro = linie.split(" ");
            linie = br.readLine();
            TreeSet<Student> students = new TreeSet<Student>();

            while (!linie.equals("}")) {
                String[] words = linie.split(" ");
                Student student = new Student(words[0], words[1]);
                student.setMother(new Parent(words[4], words[5]));
                student.setFather(new Parent(words[8], words[9]));
                students.add(student);
                linie = br.readLine();
            }

            Assistant a1 = new Assistant(intro[2], intro[3]);
            Assistant a2 = new Assistant(intro[5], intro[6]);

            for (Course c : catalog.getCourses()) {
                for (Assistant a : c.getAssistants()) {
                    if (a1.toString().equals(a.toString())) {
                        Group group1 = new Group(intro[0], a);
                        for (Student s : students)
                            group1.add(s);
                        c.addGroup(group1);
                    }

                    if (a2.toString().equals(a.toString())) {
                        Group group2 = new Group(intro[0], a);
                        for (Student s : students)
                            group2.add(s);
                        c.addGroup(group2);
                    }
                }
            }
            linie = br.readLine();
        }

        linie = br.readLine();
        linie = br.readLine();
        linie = br.readLine();

        while (!linie.equals("]")) {
            String[] words = linie.split(" ");
            String stud = words[0] + " " + words[1];
            int i = 3;
            String curs1 = "";

            while (!words[i].equals(":")) {
                curs1 = curs1 + " " + words[i];
                i++;
            }

            i = score_notes(curs1, stud, scorevisitor, words, i);

            i = i + 4;
            String curs2 = "";
            while (!words[i].equals(":")) {
                curs2 = curs2 + " " + words[i];
                i++;
            }
             i = score_notes(curs2, stud, scorevisitor, words, i);

            linie = br.readLine();

        }

        new StartInterface(scorevisitor);
    }

    public static int score_notes(String curs1, String stud, ScoreVisitor scorevisitor, String[] words, int i){
        Student student = Catalog.getInstance().findStudent(curs1, stud);
        Double exam1 = Double.parseDouble(words[i + 1]);
        Double partial1 = Double.parseDouble(words[i + 2]);
        Grade g1 = new Grade(partial1, exam1, student, curs1);

        for (Course course : Catalog.getInstance().getCourses()) {
            if (course.getName().equals(g1.getCourse())) {
                scorevisitor.addExamScore(course.getTeacher(), g1, exam1);
                for (Group g : course.getGroups().values()) {
                    for(Student s : g){
                        if(s.toString().equals(student.toString())) {
                            scorevisitor.addPartialScore(g.getAssistant(), g1, partial1);
                            break;
                        }
                    }
                }
            }
        }
        return i;
    }
}
