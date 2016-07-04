/**
 * 
 */
package classes;

import java.util.ArrayList;



/**
 * @author Marcus Vinicius G. Pestana
 *
 */
public class Resource {

	/**
	 * States: 						Types:
	 * 			1 - Allocated				1 - Class Room
	 * 			2 - Allocating				2 - Conference Room
	 * 			3 - Available				3 - Lab
	 * 			4 - Completed				4 - Projector
	 * 			5 - In Progress
	 */
	private int type;  
	private int state;
	//private ArrayList<Locate> locateList = new ArrayList<>();
	
	public Resource(int type, int state) {
		this.type = type;
		this.state = state;
		//this.locateList = locateList;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	/**
	public ArrayList<Locate> getLocateList() {
		return locateList;
	}

	public void setLocateList(ArrayList<Locate> locateList) {
		this.locateList = locateList;
	}
	**/
	
	
	
	
	
	

}
