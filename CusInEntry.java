

public class CusInEntry extends Entry{

	long timein=0;
	String car=null;
	static String file="CusIn";


public void Build(long timein,String car) {
	this.timein=timein;
	this.car=car;
}

public void Build(String arg) {
	String[] argbuff =arg.split(" ");
	timein=Long.parseLong(argbuff[0]);
	car=argbuff[1];
}

public CusInEntry() {
	// TODO Auto-generated constructor stub
}

public CusInEntry(String string) {
	Build(string);
}

public CusInEntry(long timein,String car){
	Build(timein,car);
}

public String toString() {
	return timein + " " + car;
}

public long gettimein(){
	return timein;
}

public String getCar(){
	return car;
}

public void settimein(long timein){
	this.timein=timein;
}

public void setcar(String car){
	this.car=car;
}

public String getfile(){
	return file;
	
}

}
