public class Grade implements Comparable<Grade>, Cloneable{
    private Double partialScore, examScore;
    private Student student;
    private String course;

    public Grade(Student student, String course){
        this.partialScore = 0.00;
        this.examScore = 0.00;
        this.student = student;
        this.course = course;
    }

    public Grade(Double partialScore, Double examScore, Student student, String course){
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.student = student;
        this.course = course;
    }

    public Double getPartialScore() {
        return partialScore;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public Double getTotal(){
        return partialScore + examScore;
    }

    @Override
    public int compareTo(Grade o) {
        if (this.getTotal() < o.getTotal())
            return -1;
        else if (this.getTotal() == o.getTotal())
            return 0;
        else return 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
