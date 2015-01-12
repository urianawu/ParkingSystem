

/**
 * @author wanghaoxuan
 *
 */
public class RegisterEntry extends Entry{
	private int ID=0;
	private String Name=null;
	private String[] Cars=null;
	static String file="Reg";

	public RegisterEntry(int iD, String name, String[] cars) {
	Build(iD,name,cars);
	}

	public RegisterEntry(String arg) {
		Build(arg);
	}

	public void Build(int iD, String name, String[] cars) {
		ID = iD;
		Name = name;
		Cars = cars;
	}

	public void Build(String arg) {
		String[] argbuff =arg.split(" ");
		String[] carbuff = {null,null,null};
		int i=2;
		setID(Integer.parseInt(argbuff[0]));
		setName(argbuff[1]);
		while(i<argbuff.length){
			carbuff[i-2]=argbuff[i];
			i++;
		}
		setCars(carbuff);
	}
	
	public RegisterEntry() {
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String str=ID+" "+Name;
		int i=0;
		while(Cars[i]!=null){
			str+=(" "+Cars[i]);
			i++;
		}
		return str;
	}

	public int getID(){
		return ID;
	}

	public String getName(){
		return Name;
	}

	public String[] getCars(){
		return Cars;	
	}

	public void setID(int ID){
		this.ID=ID;
	}

	public void setName(String Name){
		this.Name=Name;	
	}

	public void setCars(String[] Cars){
		this.Cars=Cars;
	}

	public void addCar(String car){
		Cars[Cars.length]=car;
	}

	public String getfile(){
		return file;
		
	}
}
