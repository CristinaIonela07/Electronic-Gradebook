import java.util.TreeSet;

public class BestPartialScore implements Strategy{
    @Override
    public Student BestScore(TreeSet<Grade> grades) {
        Grade max = grades.first();
        for (Grade grade : grades)
            if (grade.getPartialScore() > max.getPartialScore())
                max = grade;

        return max.getStudent();
    }
}
