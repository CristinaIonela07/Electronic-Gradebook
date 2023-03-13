import java.util.ArrayList;

public class FullCourse extends Course{
    public FullCourse(FullCourseBuilder builder){
        super(builder);
    }
    public static class FullCourseBuilder extends Course.CourseBuilder {
        public FullCourseBuilder(String name, Teacher teacher) {
            super(name, teacher);
        }

        public Course build() {
            return new FullCourse(this);
        }
    }
    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduated = new ArrayList<Student>();
        for (Grade grade : getGrades()) {
            if (grade.getPartialScore() >=3 && grade.getExamScore() >=2)
                graduated.add(grade.getStudent());
        }
        return graduated;
    }
}
