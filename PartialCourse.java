import java.util.ArrayList;

public class PartialCourse extends Course{
    public PartialCourse(PartialCourseBuilder builder){
        super(builder);
    }
    public static class PartialCourseBuilder extends Course.CourseBuilder {
        public PartialCourseBuilder(String name, Teacher teacher) {
            super(name, teacher);
        }

        public Course build() {
            return new PartialCourse(this);
        }
    }
    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduated = new ArrayList<Student>();
        for (Grade grade : getGrades()) {
            if (grade.getTotal() > 5)
                graduated.add(grade.getStudent());
        }
        return graduated;
    }
}
