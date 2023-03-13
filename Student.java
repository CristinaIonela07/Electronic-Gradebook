
public class Student extends User implements Comparable<Student>{
    private Parent mother;
    private Parent father;
    public Student(String firstName, String lastName){
        super(firstName, lastName);
    }
    public Student(String firstName, String lastName, Parent mother, Parent father) {
        super(firstName, lastName);
        this.mother = mother;
        this.father = father;
    }

    public void setMother(Parent mother){
        this.mother = mother;
    }

    public void setFather(Parent father){
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public Parent getFather() {
        return father;
    }

    @Override
    public int compareTo(Student o) {
        return this.getLastName().compareTo(o.getLastName());
    }

}
