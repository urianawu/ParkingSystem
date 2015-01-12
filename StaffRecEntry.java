

public class StaffRecEntry extends Entry{
	private int ID;
	private boolean dayCount;
	private int monthCount;
	static String file="StaffRec";

	public void Build(int iD, boolean dayCount, int monthCount) {
		ID = iD;
		this.dayCount = dayCount;
		this.monthCount = monthCount;
	}

	public void Build(String arg) {
		String[] argbuff =arg.split(" ");
		ID=Integer.parseInt(argbuff[0]);
		dayCount=Boolean.parseBoolean(argbuff[1]);
		monthCount=Integer.parseInt(argbuff[2]);
	}

	public StaffRecEntry(String arg){
		Build(arg);
	}

	public StaffRecEntry(int iD, boolean dayCount, int monthCount){
		Build(iD, dayCount, monthCount);
	}

	public StaffRecEntry() {
		Build(0,false,0);
	}

	public String toString() {
		return ID + " " + dayCount+ " " + monthCount;
	}

	public int getID(){
		return ID;
	}

	public boolean getdayCount(){
		return dayCount;
	}

	public int getmonthCount(){
		return monthCount;
	}

	public void setID(int ID){
		this.ID=ID;
	}

	public void setdayCount(boolean dayCount){
		this.dayCount=dayCount;
	}

	public void setmonthCount(int monthCount){
		this.monthCount=monthCount;
	}

	public String getfile(){
		return file;
	}
}
