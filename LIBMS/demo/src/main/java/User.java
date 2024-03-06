/**
 * The User class represents a user in the system.
 * It contains information about the user's name and contact details.
 */
public class User {
    private String name;
    private String contact;

    /**
     * Constructs a User object with the specified name and contact details.
     * @param name the name of the user
     * @param contact the contact details of the user
     */
    public User(String name, String contact){
        this.name = name;
        this.contact = contact;
    }

    /**
     * Returns the name of the user.
     * @return the name of the user
     */
    public String get_name(){
        return name;
    }

    /**
     * Returns the contact details of the user.
     * @return the contact details of the user
     */
    public String get_contact(){
        return contact;
    }

    /**
     * Sets the name of the user.
     * @param new_name the new name of the user
     */
    public void set_name(String new_name){
        name = new_name;
    }

    /**
     * Sets the contact details of the user.
     * @param new_contact the new contact details of the user
     */
    public void set_contact(String new_contact){
        contact = new_contact;
    }
}