/**
 * 
 */
package classes;

import java.util.ArrayList;


/**
 * @author Marcus Vinicius G. Pestana
 *
 */
public class Activity {
	/**
	 * Types:
	 * 		1 - Class
	 * 		2 - Presentation
	 * 		3 - Laboratory
	 */
	private String name;
	private ArrayList<User> participants = new ArrayList<>();
	private int type;
	private String description;
	private String supportMaterial;
	
	public Activity(String name, int type, ArrayList<User> partipants, String description,
			String supportMaterial) {
		this.name = name;
		this.type = type;
		this.participants = partipants;
		this.description = description;
		this.supportMaterial = supportMaterial;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<User> getParticipant() {
		return participants;
	}

	public void setParticipant(ArrayList<User> partipant) {
		this.participants = partipant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupportMaterial() {
		return supportMaterial;
	}

	public void setSupportMaterial(String supportMaterial) {
		this.supportMaterial = supportMaterial;
	}
	

}
