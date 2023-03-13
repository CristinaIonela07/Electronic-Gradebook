public class UserFactory {
    public User getUser(String type, String firstName, String lastName) {
        if (type.equalsIgnoreCase("student")) {
            return new Student(firstName, lastName);
        } else if (type.equalsIgnoreCase("parent")) {
            return new Parent(firstName, lastName);
        } else if (type.equalsIgnoreCase("assistant")) {
            return new Assistant(firstName, lastName);
        } else if (type.equalsIgnoreCase("teacher")) {
            return new Teacher(firstName, lastName);
        }

        return null;
    }
}
