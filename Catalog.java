import java.util.ArrayList;
public class Catalog implements Subject{
    private ArrayList<Course> courses;
    private ArrayList<Observer> observers;
    private static Catalog instance = null;

    private Catalog(){
        courses = new ArrayList<Course>();
        observers = new ArrayList<Observer>();
    }

    public static void setInstance(Catalog instance) {
        Catalog.instance = instance;
    }

    public static Catalog getInstance(){
        if(instance == null){
            instance = new Catalog();
        }
        return instance;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public void addCourse (Course course){
        courses.add(course);
    }

    public void removeCourse(Course course){
        if (courses.contains(course))
            courses.remove(course);
    }

    @Override
    public void addObserver(Observer observer) {
        for (Observer o : observers)
            if (o == observer)
                return;
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer))
            observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for (Observer observer : observers){
            if ((grade.getStudent()).getMother() == observer || (grade.getStudent()).getFather() == observer ){
                Notification notification = new Notification(grade);
                observer.update(notification);
            }
        }
    }

    public Student findStudent(String curs, String student) {
        for (Course course : Catalog.getInstance().getCourses()) {
            if (course.getName().equals(curs)) {
                for (Group g : course.getGroups().values()) {
                    for (Student s : g) {
                        if (s.toString().equals(student)) {
                            return s;
                        }
                    }
                }
            }
        }
        return null;
    }
}
