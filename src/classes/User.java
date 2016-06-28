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
	 * 					3 - Administrator
	 */

	String[] name;
	String[] email;
	int permissionLevel;

	public User(String[] name,
			String[] email, int permissionLevel) {
		super();
		this.name = name;
		this.email = email;
		this.permissionLevel = permissionLevel;
	}

}
