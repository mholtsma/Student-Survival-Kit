package hackucsc.darling_christner_holtsman.studentsurvivalkit;

/**
 * Created by march on 3/12/2016.
 */
public class ContactModel {
    private String ID, firstName, lastName;
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
