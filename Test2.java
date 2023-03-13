import java.util.HashMap;

public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Catalog catalog = Catalog.getInstance();
        Teacher t1 = new Teacher("Florin", "Moldoveanu");


        Course curs1 = (FullCourse) new FullCourse.FullCourseBuilder("AA", t1).build();

        t1.setStrategy("BestTotalScore");
        curs1.setStrategy();

        Assistant a1 = new Assistant("Stefan", "Alexandrescu");
        Assistant a2 = new Assistant("Sara", "Minca");
        Assistant a3 = new Assistant("Carmen", "Sorinela");

        curs1.addGroup("311CC", a1);
        curs1.addGroup("312CC", a2);
        curs1.addGroup("313CC", a3);

        Student s1 = new Student("Maria", "Savu", new Parent("Monica", "Savu"), new Parent("Sorin", "Savu"));
        Student s2 = new Student("Marius", "Ene", new Parent("Paula", "Ene"), new Parent("Vasile", "Ene"));

        curs1.addStudent("312CC", s1);
        curs1.addStudent("313CC", s2);

        Grade g11 = new Grade(3.57, 6.74, s1, curs1.getName());
        Grade g12 = new Grade(1.65, 4.85, s2, curs1.getName());

        curs1.addGrade(g11);
        curs1.addGrade(g12);

        catalog.addCourse(curs1);

        Teacher t2 = new Teacher("Razvan", "Calinescu");
        Course curs2 = (FullCourse) new FullCourse.FullCourseBuilder("POO", t2).build();
        t2.setStrategy("BestExamScore");
        Assistant a4 = new Assistant("Maria", "Mandescu");
        Assistant a5 = new Assistant("Dorin", "Florea");
        Assistant a6 = new Assistant("Raluca", "Oprea");

        curs2.addGroup("311CC", a5);
        curs2.addGroup("312CC", a4);
        curs2.addGroup("313CC", a6);
        curs2.setStrategy();

        Student s3 = new Student("Ana", "Florea", new Parent("Daria", "Florea"), new Parent("Marian", "Florea"));

        curs2.addStudent("312CC", s1);
        curs2.addStudent("313CC", s3);

        Grade g21 = new Grade(2.63, 3.25, s1, curs2.getName());
        Grade g22 = new Grade(4.98, 4.65, s3, curs2.getName());

        curs2.addGrade(g21);
        curs2.addGrade(g22);

        catalog.addCourse(curs2);

        catalog.addObserver(s1.getMother());
        catalog.addObserver(s1.getFather());

        catalog.addObserver(s2.getMother());
        catalog.addObserver(s2.getFather());

        catalog.addObserver(s3.getMother());
        catalog.addObserver(s3.getFather());


        // Testare course: addAssistant(String ID, Assistant assistant)
        System.out.println("Testare course: addAssistant(String ID, Assistant assistant) pentru POO");
        Assistant asis = new Assistant("Anca", "Sorinela");
        for (Course c : catalog.getCourses())
            if (c.getName().equals("POO"))
                c.addAssistant("311CC", asis);

        for (Course c : catalog.getCourses()) {
            if (c.getName().equals("POO")) {
                for (Assistant a : c.getAssistants())
                    System.out.print(a.toString() + ", ");
                System.out.println();
                HashMap<String, Group> groups = c.getGroups();
                for (Group g : groups.values()) {
                    System.out.println("Grupa: " + g.getID() + ", Asistent: " + g.getAssistant());
                }
            }
        }
        System.out.println();


        //Testare course: getAllStudents()
        System.out.println("Testare course: getAllStudents() pentru POO");
        for (Course c : catalog.getCourses()) {
            if (c.getName().equals("POO")) {
                System.out.println(c.getAllStudents());
                break;
            }
        }
        System.out.println();


        // Testare course: getAllStudentGrades()
        System.out.println("Testare course: getAllStudentGrades()");
        for (Course cu : catalog.getCourses()) {
            HashMap<Student, Grade> std_grds = cu.getAllStudentGrades();
            for (Student s : std_grds.keySet())
                System.out.println(s.toString() + " = " + std_grds.get(s).getTotal());
        }
        System.out.println();



        //Testare course: getGraduatedStudents() si getBestStudent()
        System.out.println("Testare course: getGraduatedStudents() si getBestStudent() pentru POO");
        System.out.println(curs2.getGraduatedStudents());
        System.out.println(curs2.getBestStudent());
        System.out.println();


        // Testare makeBackup() si undo()
        System.out.println("Testare makeBackup() si undo() pentru AA");
        System.out.println("Inainte de a motifica o nota:");
        for (Course c : catalog.getCourses()) {
            if (c.getName().equals("AA")) {
                c.printStudentGrades();
                break;
            }
        }

        System.out.println();

        curs1.makeBackup();
        System.out.println("Dupa ce am facut backup si am modificat o nota:");
        g11.setExamScore(6.25);
        for (Course c : catalog.getCourses()) {
            if (c.getName().equals("AA")) {
                c.printStudentGrades();
                break;
            }
        }
        System.out.println();

        curs1.undo();
        System.out.println("Dupa ce am dat undo:");
        for (Course c : catalog.getCourses()) {
            if (c.getName().equals("AA")) {
                c.printStudentGrades();
                break;
            }
        }

        System.out.println();


        // Testare catalog: removeCourse(Course course)
        System.out.println("Testare catalog: removeCourse(Course course)");
        for (Course c : catalog.getCourses())
            if (c.getName().equals("POO")) {
                catalog.removeCourse(c);
                break;
            }

        for (Course c : catalog.getCourses())
            System.out.println("Curs: " + c.getName() + ", Titular: " + c.getTeacher());

    }
}
