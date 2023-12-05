package lambtonrecreation.model;

import java.util.Date;

public class User {
	int id;
    String firstName;
    String lastName;
    String username;
    String password;
    Date dob;
    private String gender;
    private boolean agreement;
    private String email;
    int role;

    public User() {
    }

    public User(int id, String firstName, String lastName, String username, String password, Date dob, String gender,
    		boolean agreement, String email, int role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.agreement = agreement;
        this.role = role;
    }

    public User(String firstName, String lastName, String username, String password, Date dob, String gender,
    		boolean agreement, String email,int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.agreement = agreement;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setDob(Date dob) {
    	this.dob = dob;
    }
    
    public Date getDob() {
    	return dob;
    }
    
    public void setRole(int role) {
    	this.role = role;
    }
    
    public int getRole() {
    	return role;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }
}
