/**
 * 
 */
package classes;

import java.util.Date;

/**
 * @author Marcus Vinicius G. Pestana
 *
 */
public class Locate {
	/**
	 * 	States: 
	 * 			1 - Allocated
	 * 			2 - Allocating
	 * 			3 - Available
	 * 			4 - Completed
	 * 			5 - In Progress
	 */
	private Resource resource;
	private Date beginDate;
	private Date endDate;
	private int state;
	private User responsable;
	private Activity activity;
	
	public Locate(Resource resource, Date beginDate, Date endDate, int state,
			User responsable, Activity activity) {
		this.resource = resource;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.state = state;
		this.responsable = responsable;
		this.activity = activity;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
	
}
