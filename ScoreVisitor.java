import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ScoreVisitor implements Visitor {
    HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> examScores;
    HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> partialScores;

    public ScoreVisitor() {
        this.examScores = new HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>>();
        this.partialScores = new HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>>();
    }

    public HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> getExamScores() {
        return examScores;
    }

    public HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> getPartialScores() {
        return partialScores;
    }


    public Vector<String> getDouble(Teacher teacher, String curs){
        Vector<String> vector = new Vector<String>();
        for (Tuple<Student, String, Double> t : examScores.get(teacher)){
            if (t.getCourse().equals(curs)){
                vector.add(t.getStudent().toString() + " " + t.getGrade().toString());
            }
        }
        return vector;
    }

    public Vector<String> getDouble(Assistant assistant, String curs){
        Vector<String> vector = new Vector<String>();
        for (Tuple<Student, String, Double> t : partialScores.get(assistant)){
            if (t.getCourse().equals(curs)){
                vector.add(t.getStudent().toString() + " " + t.getGrade().toString());
            }
        }
        return vector;
    }

    public void addExamScore(Teacher teacher, Grade grade, Double x) {
        if (examScores.containsKey(teacher)) {
            ArrayList<Tuple<Student, String, Double>> value = examScores.get(teacher);
            Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), x);
            value.add(tuple);
            examScores.replace(teacher, value);
        }
        else {
            ArrayList<Tuple<Student, String, Double>> value = new ArrayList<Tuple<Student, String, Double>>();
            Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), x);
            value.add(tuple);
            examScores.put(teacher, value);
        }
    }



    public void addPartialScore(Assistant assistant, Grade grade, Double x) {
        if (partialScores.containsKey(assistant)) {
            ArrayList<Tuple<Student, String, Double>> value = partialScores.get(assistant);
            Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), x);
            value.add(tuple);
            partialScores.replace(assistant, value);
        }
        else {
            ArrayList<Tuple<Student, String, Double>> value = new ArrayList<Tuple<Student, String, Double>>();
            Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), x);
            value.add(tuple);
            partialScores.put(assistant, value);
        }
    }

    @Override
    public void visit(Assistant assistant) {
        ArrayList<Tuple<Student, String, Double>> value = partialScores.get(assistant);
        ArrayList<Course> courses = (Catalog.getInstance()).getCourses();

        if (value != null) {
            Course course = null;
            for (Tuple<Student, String, Double> tuple : value) {
                for (Course c : courses)
                    if (c.getName().equals(tuple.getCourse()))
                        course = c;

                if (course != null) {
                    Catalog.getInstance().addObserver(tuple.getStudent().getMother());
                    Catalog.getInstance().addObserver(tuple.getStudent().getFather());
                    Double exam = 0.00;
                    for (Grade g : course.getGrades()) {
                        if (g.getStudent().toString().equals(tuple.getStudent().toString())
                                && g.getCourse().equals(tuple.getCourse())){
                            exam = g.getExamScore();
                        }
                    }

                    Grade grade = new Grade(tuple.getGrade(), exam, tuple.getStudent(), tuple.getCourse());
                    course.addGrade(grade);
                    Catalog.getInstance().notifyObservers(grade);
                }
            }
        }

    }

    @Override
    public void visit(Teacher teacher) {
        ArrayList<Tuple<Student, String, Double>> value = examScores.get(teacher);
        ArrayList<Course> courses = Catalog.getInstance().getCourses();

        if (value != null) {
            Course curs = null;
            for(Tuple<Student, String, Double> tuple : value){
                for(Course c : courses) {
                    if (c.getName().equals(tuple.getCourse()))
                        curs = c;
                }

                if (curs != null){
                    Catalog.getInstance().addObserver(tuple.getStudent().getMother());
                    Catalog.getInstance().addObserver(tuple.getStudent().getFather());
                    Double partial = 0.00;
                    for (Grade g : curs.getGrades()) {
                        if (g.getStudent().equals(tuple.getStudent())
                                && g.getCourse().equals(tuple.getCourse())) {
                            partial = g.getPartialScore();
                            break;
                        }
                    }
                    Grade grade = new Grade(partial, tuple.getGrade(), tuple.getStudent(), tuple.getCourse());
                    curs.addGrade(grade);
                    Catalog.getInstance().notifyObservers(grade);
                }
            }
        }
    }


    private class Tuple<S, G, D>{
        S student;
        G course;
        D grade;

        public Tuple(S student, G course, D grade) {
            this.student = student;
            this.course = course;
            this.grade = grade;
        }

        public S getStudent() {
            return student;
        }

        public void setStudent(S student) {
            this.student = student;
        }

        public G getCourse() {
            return course;
        }

        public void setCourse(G course) {
            this.course = course;
        }

        public D getGrade() {
            return grade;
        }

        public void setGrade(D grade) {
            this.grade = grade;
        }
    }
}
