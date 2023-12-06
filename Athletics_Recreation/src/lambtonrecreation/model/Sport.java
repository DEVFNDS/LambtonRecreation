package lambtonrecreation.model;

/**
 * @author Jagraj-kaur
 *
 */
public class Sport {

	private int id;
    private String name;
    private String description;
    private String rules;
    private String equipmentNeeded;
    private boolean isFavourite; /* used to verify if user selects the sport as favourite*/
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public String getEquipmentNeeded() {
		return equipmentNeeded;
	}
	public void setEquipmentNeeded(String equipmentNeeded) {
		this.equipmentNeeded = equipmentNeeded;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
}
