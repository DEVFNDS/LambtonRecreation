package lambtonrecreation.model;

public class Role {
	int id;
    String role;
    String description;
    
    public Role() {
    	
    }
    
    public Role(int id, String role, String description){
    	this.id = id;
    	this.role = role;
    	this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
