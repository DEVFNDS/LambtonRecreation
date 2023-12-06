package lambtonrecreation.model;

import java.sql.Timestamp;

public class Event {

	int id;
	String name;
	String sportName;
	int sportId;
	Timestamp  dateTime;
	String location;
	String description;
	Timestamp registrationDeadline;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSportId() {
		return sportId;
	}
	public void setSportId(int sportId) {
		this.sportId = sportId;
	}
	public Timestamp  getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp  dateTime) {
		this.dateTime = dateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getRegistrationDeadline() {
		return registrationDeadline;
	}
	public void setRegistrationDeadline(Timestamp registrationDeadline) {
		this.registrationDeadline = registrationDeadline;
	}
	
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return id == event.id;
    }
		
}
