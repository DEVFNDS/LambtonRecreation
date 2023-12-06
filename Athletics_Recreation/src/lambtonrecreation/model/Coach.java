package lambtonrecreation.model;

public class Coach {
    private int id;  // Added coach ID
    private String userId;
    private String sportsSpecializedIn;
    private String coachingExperience;
    private String certifications;
    private String availability;

    public Coach() {
    }

    public Coach(int id, String userId, String sportsSpecializedIn, String coachingExperience, String certifications, String availability) {
        this.id = id;
        this.userId = userId;
        this.sportsSpecializedIn = sportsSpecializedIn;
        this.coachingExperience = coachingExperience;
        this.certifications = certifications;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
 // Overloaded method
    public void setUserId(int userId) {
        this.userId = String.valueOf(userId);
    }

    public String getSportsSpecializedIn() {
        return sportsSpecializedIn;
    }

    public void setSportsSpecializedIn(String sportsSpecializedIn) {
        this.sportsSpecializedIn = sportsSpecializedIn;
    }

    public String getCoachingExperience() {
        return coachingExperience;
    }

    public void setCoachingExperience(String coachingExperience) {
        this.coachingExperience = coachingExperience;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
