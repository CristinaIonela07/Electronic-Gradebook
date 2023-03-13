import java.util.TreeSet;

public class BestTotalScore implements Strategy{
    @Override
    public Student BestScore(TreeSet<Grade> grades) {
        Grade max = grades.first();
        for (Grade grade : grades)
            if (grade.getTotal() > max.getTotal())
                max = grade;

        return max.getStudent();
    }
}
