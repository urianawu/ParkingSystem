

import java.util.ArrayList;


import java.util.ArrayList;
  
/**
 * @author wanghaoxuan
 *
 */
public class park {
	private int space;// total space of user in park
	private int userCount;//total number of user in park
	private StaffInEntry[] StaffIn;//buffer of staffs in park
	private CusInEntry[] CusIn;//buffer of customers in park
	public String password;//encripted password of operator
	private float[] tariff={0,0,0,0,0};
	private boolean[] openDays={true,true,true,true,true,false,false};//
	private String message;
	private Database data=new Database();
	private  ArrayList<Float> holidays=new ArrayList();
	private int ticket;
	private float money;

	
	public int refreshTicket() {
		reset();
		return ticket;
	}

	private void reset() {
		Database d= new Database();
		d.reset();
		String profile=d.readterm("profile");
		String[] buffer = profile.split(" ");
		space=Integer.parseInt(buffer[0]);
		holidays.clear();
		for(int i=1;i<=5;i++){
			tariff[i-1]=Float.parseFloat(buffer[i]);
		}
		for(int i=6;i<=12;i++){
			openDays[i-6]=Boolean.parseBoolean(buffer[i]);
		}
		password=buffer[13];
		int i=14;
		//System.out.println(buffer[buffer.length-1]);
		//System.out.println("holidays size "+holidays.size());
		holidays.ensureCapacity(buffer.length-15);
		while(!buffer[i].equals("#")){
			//System.out.println("buffersize "+buffer.length);
			holidays.add(Float.parseFloat(buffer[i]));
			i++;
		}
		//System.out.println("in holidays: "+holidays);
		money=Float.parseFloat(buffer[++i]);
		ticket=Integer.parseInt(buffer[++i]);
		
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
		save();
	}

	public float refreshMoney() {
		reset();
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
		save();
	}

	public boolean forCus(){
		WeekDay week=new WeekDay(); 
		Holiday hol=new Holiday();
		return (openDays[Math.abs((week.getWeekDay()-2))]||!hol.noHoliday(holidays));
		}

	private int deltaHour(long timeIn){
		long delta=System.currentTimeMillis()-timeIn;
		int deltaHour;
		return deltaHour=(int)Math.ceil((delta/3600000));
	}

	public void staffFee(StaffInEntry b){
		StaffRecEntry a =new StaffRecEntry();
		//System.out.println("in staff fee: "+ ((StaffRecEntry)data.find("StaffRec", (long)b.getID())).toString());
		
		if(data.find("StaffRec", (long)b.getID()).getID() == 0){
			a.setID(b.getID());
			a.setmonthCount(0);
			a.setdayCount(false);
		}
		else
			a = (StaffRecEntry) data.find("StaffRec", (long)b.getID());
		
		staffFee(deltaHour(b.gettimein()),a);
	}

	private StaffRecEntry staffFee(int deltaHour,StaffRecEntry a){
		if(deltaHour>24) {
			a.setmonthCount(a.getmonthCount()+1);
			a=staffFee(deltaHour-24,a);
			a.setdayCount(false);
		}
		else{
			if(a.getdayCount()==false){
			a.setmonthCount(a.getmonthCount()+1);
			a.setdayCount(true);
			}
		}
		data.delete("StaffRec", a.getID());
		////System.out.println("this is a: "+a);
		data.add(a);
		data.delete("StaffIn", a.getID());
		return a;
	}

	public float cusFee(CusInEntry a){
		int delta=deltaHour(a.gettimein());
		//System.out.println(delta+" Hours in total");
		float fee=0;
		fee+=((int)(delta/24))*getTariff()[4];
		//System.out.println(fee/getTariff()[4]+"days in park");
		delta=delta%24;
		if(delta<2)fee+=getTariff()[0];
		else if(delta<4)fee+=getTariff()[1];
		else if(delta<8)fee+=getTariff()[2];
		else if(delta<12)fee+=getTariff()[3];
		else fee+=getTariff()[4];
		//System.out.println(delta+ " hours during the day");
		return fee;
		}
	
	public boolean checkFull(){
		if(data.countusers()==space)
		return true;
		else return false;
	}

	public park() {
	reset();
	
	}
	
	/**
	 *open the gate and add userCount by 1, then close the gate 
	 */
	public void letIn(){
	userCount++;
	}
	
	/**
	 *open the gate and minus userCount by 1, then close the gate 
	 */
	public void letOut(){
	userCount--;
	}

	/**
	 *return the space of the park 
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * generate the space of the park
	 * @param space the space of the park
	 */
	public void setSpace(int space) {
		if (this.space<space)
			this.space=space;
		if (this.space>space&&getUserCount()<space)
			this.space = space;
		save();
	}

	/**
	 * the total number of users in the park
	 * @return the total number of users in the park
	 */
	private int getUserCount() {
		return userCount;
	}

	/**
	 * refresh the userCount of the park
	 */
	public int refreshUserCount() {
		this.userCount = data.countusers();
		return getUserCount();
		
	}

	/**
	 * return the list of staffs in the park
	 * @return the list of staffs in the park
	 */
	public StaffInEntry[] getStaffIn() {
		return StaffIn;
	}

	/**
	 * read the staffs in the park from file
	 */
	public void refreshStaffIn() {
		int i=0;
		StaffInEntry temp;
		while((temp=new StaffInEntry(data.readterm("StaffIn")))!=null)
		{
			StaffIn[i] =temp;
			i++;
			}
	}

	/**
	 *return the list of customers in the park
	 * @return the list of customers in the park
	 */
	public CusInEntry[] getCusIn() {
		return CusIn;
	}

	/**
	 * refresh the list of customers in the park from file
	 */
	public void refreshCusIn() {
		int i=0;
		CusInEntry temp;
		while((temp=new CusInEntry(data.readterm("CusIn")))!=null)
		{
			CusIn[i] =temp;
			i++;
			}
	}

	public int getTicket() {
		return ticket;
	}

	/**
	 * check the password of the operator
	 * @param inputpassword the codeword that is fed into the system by a user
	 * @return whether the input password is valid
	 */
	public Boolean checkPassword(String inputpassword) {
    	//System.out.println("Password stored "+ password);
    	//System.out.println("Password fed "+ inputpassword);
        return Encryption.validatePassword(password,inputpassword); 
	}

	/**
	 * set a new password by the operator
	 * @param passwordStr the new password
	 */
	private void setPassword(String passwordStr) {
	        String pwd2="";  
	        Encryption cipher = new Encryption();  
	        password = cipher.generatePassword(passwordStr); 
	        save();
	}

	public boolean resetPassword(String inputpassword,String passwordStr){
		if(checkPassword(inputpassword)){
			setPassword(passwordStr);
			save();
			return true;

		}
		else return false;
		
	}

	/**
	 * get the tariff of the park
	 * @return
	 */
	public float[] getTariff() {
		return tariff;
	}

	public void setTariff(float[] tariff) {
		this.tariff = tariff;
		save();
	}

	public boolean[] getOpenDays() {
		return openDays;
	}

	public void setOpenDays(boolean[] openDays) {
		this.openDays = openDays;
		save();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private void Save() {
		// TODO Auto-generated method stub
		
	}

	public String toProfile(){
		String str="";
		str+=(space+" ");
		for(int i=0;i<5;i++)str+=(tariff[i]+" ");
		for(int i=0;i<7;i++)str+=(openDays[i]+" ");
		str+=(password+" ");
		int i=0;
		while(i<holidays.size()){
			str+=(holidays.get(i)+" ");
			i++;
		}
		str+="# ";
		str+=money+" ";
		str+=ticket+" ";
		return str;
	}

	public void save(){
		data.addterm("Profile", toProfile());
	}

    public void deleteholiday(float[] holidayDelete){
    	holidays.remove(holidayDelete[0]);
    	holidays.remove(holidayDelete[1]);
    	save();
    }

    public void addholiday(float[] holidayAdd){
    	holidays.add(holidayAdd[0]);
    	holidays.add(holidayAdd[1]);
    	save();
    	}

    public ArrayList<Float>  getholidays(){
		return holidays;
	}

    public String getParkInfo(){
    	String holidays = "";
    	for(int i=0;i<getholidays().size();i+=2){
    		holidays+=("From ");
    		holidays+=(getholidays().get(i));
    		holidays+=(" to ");
    		holidays+=(getholidays().get(i+1));
    		holidays+=("\n");
    	}
    	
    	String staffReg = "";
    	int length = data.countreg();
    	data.reset();
    	for(int i=0;i<length;i++){
    		staffReg+=(data.readterm("Reg"));
    		staffReg+="\n";
    	}
    	
    	//System.out.println("in park:test usercount"+new park().getUserCount());
    	String info = "Total park space is "+getSpace()+", "+refreshUserCount()+" cars are in the park."+
    	"\nCurrent tariff:\nUp to 2 hours: "+getTariff()[0]+
    	"\n2 to 4 hours: "+getTariff()[1]+
    	"\n4 to 8 hours: "+getTariff()[2]+
    	"\n8 to 12 hours: "+getTariff()[3]+
    	"\n12 to 24 hours: "+getTariff()[4]+
    	"\nHolidays:\n"+holidays+
    	"\nRegistered Staff information: \nstaff ID--staff name--cars\n"+staffReg;

    	return info;
    }
 
   public ArrayList<String> monthlySettlement(){
    	ArrayList<StaffRecEntry> monthBill=new ArrayList();
    	String tempS;
    	StaffRecEntry temp;
    	ArrayList<String> finalBill = new ArrayList();
    	String billEntry="";
    	data.reset();
    	while((tempS=data.readterm("StaffRec"))!=null){
    		temp=new StaffRecEntry(tempS);
    		monthBill.add(temp);
    		billEntry=temp.getID()+" "+temp.getmonthCount()+"";
    		finalBill.add(billEntry);
    		//System.out.println(temp);
    	}
    	data.reset();
    	data.cleanFile("StaffRec");
    	new SchoolDatabase().MonthBill(finalBill);
    	//System.out.println("File cleared");
		return finalBill;
    }

    public void dailySettlement(){
    	ArrayList<StaffRecEntry> op=new ArrayList();
    	data.reset();
    	String temp;
    	int i=0;
    	int loop=data.countRec();
    	while(i<loop){
    	data.reset();
    	temp=data.readterm("StaffRec");
    	op.add(new StaffRecEntry(temp));
    	StaffRecEntry opi= op.get(i);
    	opi.setdayCount(false);
    	data.delete("StaffRec", opi.getID());
    	data.add(opi);
    	i++;
    	}
    }

}
   
