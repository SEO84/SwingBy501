package dto;

public class Lsh1208_dto {
	String job;
	int sal;

	public Lsh1208_dto(String job, int sal) {
		super();
		this.job = job;
		this.sal = sal;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public Lsh1208_dto() {

	}
	
	public Object[] getRow() {
		return new Object[] { job, sal};
	}
}
