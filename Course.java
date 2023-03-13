import java.util.*;

public abstract class Course {
    private String name;
    private Teacher teacher;
    private HashSet<Assistant> assistants;
    private TreeSet<Grade> grades;
    private HashMap<String, Group> groups;
    private int credits;
    private Strategy strategy;
    private Snapshot snapshots = new Snapshot();

    Course (CourseBuilder builder){
        this.name = builder.name;
        this.teacher = builder.teacher;
        this.assistants = builder.assistants;
        this.grades = builder.grades;
        this.groups  = builder.groups;
        this.credits = builder.credits;
    }

    public Course(String name, Teacher teacher, HashSet<Assistant> assistants,
                  TreeSet<Grade> grades, HashMap<String, Group> groups,
                  int credits, Strategy strategy) {
        this.name = name;
        this.teacher = teacher;
        this.assistants = assistants;
        this.grades = grades;
        this.groups = groups;
        this.credits = credits;
        this.strategy = strategy;
    }

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        this.credits = 0;
        this.assistants = new HashSet<Assistant>();
        this.grades = new TreeSet<Grade>();
        this.groups = new HashMap<String, Group>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public HashSet<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(HashSet<Assistant> assistants) {
        this.assistants = assistants;
    }

    public TreeSet<Grade> getGrades() {
        return grades;
    }

    public void setGrades(TreeSet<Grade> grades) {
        this.grades = grades;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy() {
        if (teacher.getStrategy() == "BestPartialScore")
            this.strategy = new BestPartialScore();
        else if (teacher.getStrategy() == "BestExamScore")
            this.strategy = new BestExamScore();
        else if (teacher.getStrategy() == "BestTotalScore")
            this.strategy = new BestTotalScore();
    }

    public void addAssistant(String ID, Assistant assistant) {
        (groups.get(ID)).setAssistant(assistant);
        if (!assistants.contains(assistant))
            assistants.add(assistant);
    }

    public void addStudent(String ID, Student student) {
        (groups.get(ID)).add(student);
    }

    public void addGroup(Group group) {
        groups.put(group.getID(), group);
    }

    public void addGroup(String ID, Assistant assistant){
        Group group = new Group(ID, assistant);
        groups.put(group.getID(), group);
        if (!assistants.contains(assistant))
            assistants.add(assistant);
    }

    public void addGroup(String ID, Assistant assistant, Comparator<Student> comp) {
        Group group = new Group(ID, assistant, comp);
        groups.put(group.getID(), group);
    }

    public Grade getGrade(Student student) {
        for (Grade grade : grades) {
            if (grade.getStudent() == student)
                return grade;
        }
        return null;
    }

    public void addGrade(Grade grade) {
        int ok = 0;
        for (Grade g : grades){
            if (g.getStudent().toString().equals(grade.getStudent().toString())){
                Double partial = grade.getPartialScore();
                Double exam = grade.getExamScore();
                g.setPartialScore(partial);
                g.setExamScore(exam);
                ok = 1;
            }
        }
        if (ok == 1){
            TreeSet<Grade> copy = new TreeSet<Grade>();
            for (Grade g: grades) {
                copy.add(g);
            }
            grades = copy;
        }
        else grades.add(grade);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Group gr : groups.values()) {
            for(Student s : gr)
                students.add(s);
        }
        return students;
    }

    public HashMap<Student, Grade> getAllStudentGrades(){
        HashMap<Student, Grade> students_map = new HashMap<Student, Grade>();
        for (Grade grade : grades) {
            students_map.put(grade.getStudent(), grade);
        }
        return students_map;
    }

    public Student getBestStudent(){
        return strategy.BestScore(grades);
    }
    public abstract ArrayList<Student> getGraduatedStudents();

    public ArrayList<Grade> getSnapShot(){
        return snapshots.getSnapshots();
    }

    public void makeBackup() throws CloneNotSupportedException {
        for (Grade grade : grades){
            snapshots.add((Grade) grade.clone());
        }
    }

    public void printStudentGrades(){
        HashMap<Student, Grade> std_grds = getAllStudentGrades();
        for(Student s : std_grds.keySet())
            System.out.println(s.toString() + " = " + std_grds.get(s).getTotal());
    }

    public void undo(){
        grades.clear();
        for (Grade g : snapshots.getSnapshots())
            grades.add(g);
    }

    private class Snapshot{
        private ArrayList<Grade> snapshots;

        public Snapshot(){
            this.snapshots = new ArrayList<Grade>();
        }

        public Snapshot(ArrayList<Grade> snapshots) {
            this.snapshots = snapshots;
        }

        public ArrayList<Grade> getSnapshots() {
            return snapshots;
        }

        public void setSnapshots(ArrayList<Grade> snapshots) {
            this.snapshots = snapshots;
        }

        public void add(Grade grade){
            int ok = 0;
            for (Grade g : snapshots) {
                if (g == grade) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                snapshots.add(grade);
            }
        }
    }



    public static abstract class CourseBuilder{
        private String name;
        private Teacher teacher;
        private HashSet<Assistant> assistants;
        private TreeSet<Grade> grades;
        private HashMap<String, Group> groups;
        private int credits;

        public CourseBuilder(String name, Teacher teacher) {
            this.name = name;
            this.teacher = teacher;
            this.credits = 0;
            this.assistants = new HashSet<Assistant>();
            this.groups = new HashMap<String, Group>();
            this.grades = new TreeSet<Grade>();
        }

        public CourseBuilder name(String name){
            this.name = name;
            return this;
        }

        public CourseBuilder teacher(Teacher teacher){
            this.teacher = teacher;
            return this;
        }

        public CourseBuilder assistants(HashSet<Assistant> assistants){
            this.assistants = assistants;
            return this;
        }

        public CourseBuilder grades(TreeSet<Grade> grades){
            this.grades = grades;
            return this;
        }

        public CourseBuilder groups(HashMap<String, Group> groups){
            this.groups = groups;
            return this;
        }

        public CourseBuilder credits(int credits){
            this.credits  = credits;
            return this;
        }
        public abstract Course build();
    }
}

