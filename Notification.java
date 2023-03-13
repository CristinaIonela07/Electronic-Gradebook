public class Notification {
    private Grade grade;

    public Notification(Grade grade) {
        String curs = grade.getCourse();
        Student stud = grade.getStudent();
        Double partial = grade.getPartialScore();
        Double exam = grade.getExamScore();
        Grade g = new Grade(partial, exam, stud, curs);
        this.grade = g;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        if (grade.getPartialScore() != 0 && grade.getExamScore() == 0)
            return "Notification:" + " Student " + grade.getStudent() +
                    " has at " + grade.getCourse() + " a partial score of " +
                    grade.getPartialScore() +
                    '}';

        if(grade.getPartialScore() == 0.00 && grade.getExamScore() != 0)
            return "Notification:" + " Student " + grade.getStudent() +
                    " has at " + grade.getCourse() + " an exam score of " +
                    grade.getExamScore() +
                    '}';

        return "Notification:" + " Student " + grade.getStudent() +
                " has at " + grade.getCourse() +" a partial score of " + grade.getPartialScore()
                + " and an exam score of " + grade.getExamScore() +
                '}';
    }
}
