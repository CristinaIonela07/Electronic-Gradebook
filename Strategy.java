import java.util.ArrayList;
import java.util.TreeSet;

public interface Strategy {
    Student BestScore(TreeSet<Grade> grades);
}
