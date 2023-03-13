import java.util.TreeSet;

public class BestExamScore implements Strategy{
    @Override
    public Student BestScore(TreeSet<Grade> grades) {
        Grade max = grades.first();
        for (Grade grade : grades)
                if (grade.getExamScore() > max.getExamScore())
                    max = grade;

        return max.getStudent();
    }
}
