import java.util.*;
public class Group extends TreeSet<Student>{
    private Assistant assistant;
    private String ID;

    public Group(String ID, Assistant assistant, Comparator<Student> comp){
        super(comp);
        this.ID = ID;
        this.assistant = assistant;
    }

    public Group(String ID, Assistant assistant){
        this.ID = ID;
        this.assistant = assistant;
    }

    public Group(String ID){
        this.ID = ID;
        this.assistant = null;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
