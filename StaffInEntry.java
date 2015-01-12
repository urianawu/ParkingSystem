

public class StaffInEntry extends Entry {
	int ID;
	String car;
	long timein;
	static String file="StaffIn";

	public void Build (int iD, String car, long timein) {
		ID = iD;
		this.car = car;
		this.timein = timein;
	}

	public void Build (String arg) {
		String[] argbuff =arg.split(" ");
		ID=Integer.parseInt(argbuff[0]);
		car=argbuff[1];
		timein=Long.parseLong(argbuff[2]);
	}

	public StaffInEntry(int iD, String car, long timein){
		Build(iD, car,timein);
	}

	public StaffInEntry (String arg) {
		Build(arg);
	}

	public StaffInEntry() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return  ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public long gettimein() {
		return timein;
	}

	public void settimein(long timein) {
		this.timein = timein;
	}

	@Override
	public String toString() {
		return ID + " " + car + " " + timein;
	}

	public String getfile(){
		return file;
		
	}
}
