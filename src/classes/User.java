/**
 * 
 */
package classes;

/**
 * @author Marcus Vinicius G. Pestana
 *
 */
public class User {
	/**
	 * Permission Level Values:
	 * 					
	 * 					1 - Student
	 * 					2 - Teacher
	 * 					3 - Researcher
	 * 					4 - Administrator
	 */
	private String id;
	private String password;
	private String name;
	private String email;
	private int permissionLevel;
	private boolean allocating;

	public User(String id, String password, String name, String email, int permissionLevel) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.permissionLevel = permissionLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public boolean isAllocating() {
		return allocating;
	}

	public void setAllocating(boolean allocating) {
		this.allocating = allocating;
	}
	
	public boolean isApt(boolean apt){
		if (this.allocating == false){
			if (this.permissionLevel>=2){
			apt = true;
			}
		else
			apt = false;
		}
		return apt;
	}
}
