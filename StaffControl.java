import java.util.ArrayList;

//import entries.StaffRecEntry;
public class StaffControl {
	SchoolDatabase sData = new SchoolDatabase();
	Database data = new Database();
	park p = new park();
	//int PARKSIZE = 30;

	/**
	 * To check whether input id is legal.
	 * @param String input ID.
	 * @return int id. -1 for illegal id.
	 * 
	 */
	private int isLegal(String inputID){
		int id;
		try{
			id = Integer.parseInt(inputID);
		}catch(NumberFormatException e){
			return -1;
		}
		if(id <= 0)
			return -2;
		else
		return id;
	}
	
	/**
	 * @return errorMessage there's error or not.
	 * 0 for no error, 
	 * 1 for illegal input, 
	 * 2 for not registered, 
	 * 3 for no free space, 
	 * 4 for one staff can't park more than one car in park.
	 * 
	*/
	public String staffEntering(String inputID, String carNum){
		//boolean isValid =true;
		
		long time = System.currentTimeMillis();
		//boolean alreadyIn = false;
		int id=isLegal(inputID);
		if(inputID==null ||carNum==null)
			return "Please enter valid ID and car number.";
		if(id == -1 )
			return "Please enter valid ID and car number.";
		if(!data.CheckExist("Reg",(long)id))
			return "This ID is not registered yet.";
		
		String[] cars = data.find("Reg", (long)id).getCars();
		boolean carRegistered = false;
		for(int i=0;i<cars.length;i++){
			if(carNum.equals(cars[i]))
				carRegistered = true;
		}
		if(carRegistered == false)
			return "This car has not been registered yet.";
		if(p.checkFull())
			return "Sorry! Park is full.";
		if(data.CheckExist("StaffIn", (long)id))
			return "One staff can't park more than one car in park.";
		StaffInEntry staffIn = new StaffInEntry(id,carNum,time);
		data.add(staffIn);
		
		p.letIn();
		return "Welcome, QM stuff!";
	}
	
	/**
	 * @return errorMessage there's error or not.
	 * 0 for no error, 
	 * 1,2 for illegal input, 
	 * 3 for not in Park.
	 */
	public String staffExit(String inputID){
		int id = isLegal(inputID);
		//System.out.println(id+"");
		if(id == -1||id==-2)
			return "Please enter valid ID.";
		if(!data.CheckExist("StaffIn",(long)id))
			return "This ID's car is not in the park.";

		StaffInEntry staffOut = (StaffInEntry) data.find("StaffIn",(long)id);
		p.staffFee(staffOut);
		p.letOut();
		//data.delete("StaffIn", (long)id);
		return "Bye, QM staff! Drive safe!";
	}
	
	/**
	 * @return errorMessage there's error or not.
	 */
	public String staffRegister (String inputID, String name,String carNum1,String carNum2, String carNum3){
		int id = isLegal(inputID);
		if(id == -1)
			return "Please enter valid staff ID.";
		
		if(name.isEmpty())
			return "Please enter valid staff name.";
		
		String[] names = name.split(" ");
		String newName=null;
		//System.out.println(names.length+"length!!!!!!!!!!!!!!!!!!!!");
		newName=names[0];
		//System.out.println(names[names.length-1]);
		for(int i=1;i<names.length;i++)
			newName=newName+"."+names[i];
		
		
		String[] newCars = new String[4];
		ArrayList<String> carInput = new ArrayList<String>();
		
		if(carNum1.isEmpty() &&carNum2.isEmpty() &&carNum3.isEmpty())
			return "Please enter at least one car number.";
		if(!carNum1.isEmpty())
			carInput.add(carNum1);
		if(!carNum2.isEmpty())
			carInput.add(carNum2);
		if(!carNum3.isEmpty())
			carInput.add(carNum3);
		for(int n=0;n<carInput.size();n++){
			newCars[n]=carInput.get(n);
			System.out.println("car added:"+newCars[n]);
		}
	
		if(data.CheckExist("Reg",(long)id)){
			if(data.CheckExist("StaffIn", (long)id))
				return "Sorry! Information can't be changed when car is in the park.";

				RegisterEntry reg = new RegisterEntry();
				reg.setID(id);
				reg.setName(newName);
				reg.setCars(newCars);
				
				data.delete("Reg", (long)id);
				data.add(reg);
		}
		else{
			if(sData.isValidStaff(id))
				data.add(new RegisterEntry(id,newName,newCars));
			else
				return "Please enter valid staff data.";
		}
		return "You have register your information:\nID:"+id+"\nName: "+newName+"\nCars:\n"+newCars[0]+" "+newCars[1]+" "+newCars[2];
	}

	public String checkBill(String text) {
		System.out.println(text);
		try{
			Long.parseLong(text);
		}catch(NumberFormatException e){
			return "Please enter valid staff ID.";
		}
		if(!data.CheckExist("Reg", Long.parseLong(text)))
		return "Your Account number does not exist!";
		else if(!data.CheckExist("StaffRec", Long.parseLong(text)))
		return "You have not parked here this month.";
		else {
			StaffRecEntry thisentry=  (StaffRecEntry) data.find("StaffRec", Long.parseLong(text));
			return "You have entered the parking area for "+thisentry.getmonthCount()+" days";
		}
	}
}
