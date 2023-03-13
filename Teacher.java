import java.util.Stack;

public class Teacher extends User implements Element{
    private String strategy;
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Teacher(String firstName, String lastName, String strategy) {
        super(firstName, lastName);
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
