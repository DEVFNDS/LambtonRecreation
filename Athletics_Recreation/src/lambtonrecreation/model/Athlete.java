package lambtonrecreation.model;

public class Athlete {

	int id;
	int user_id;
	String bio;
	String interests;
	String achievements;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getInterests () {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public String getAchievements () {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	
}

