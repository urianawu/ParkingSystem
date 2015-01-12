
public class CustomerControl {
	Database data = new Database();
	park p = new park();

	/**
	 * 
	 * @param carNum
	 * @return long ID or errorMessage 
	 * If no error, return timein as ID for ticket.
	 * 1 for illegal car number,
	 * 2 for already in park,
	 * 3 for not open to customer.
	 */
	private long CusEnter(String carNum){
		
		long timein = System.currentTimeMillis();
		CusInEntry cusIn = new CusInEntry(timein,carNum);
		if(p.forCus()){
			if(carNum.isEmpty())
				return 1;
			if(data.CheckExistByCarNum("CusIn",carNum))
				return 2;
			//String ticket = cusIn.toString();
			data.add(cusIn);
			p.setTicket(p.refreshTicket()-1);
			p.letIn();
		}
		else
			return 3;
		//p.letIn();
		//System.out.println("Now space left:"+((p.getSpace())-(p.getUserCount())));
		return cusIn.gettimein();
	}
	public String CusEntering(String carNum){
		long i = CusEnter(carNum);
		if(i == 1)
			return "Please enter a valid car number.";
		if(i==2)
			return "This car is already in the park.";
		if(i==3)
			return "Not open for public use.";
		else
			return ""+i;
	}
	
	/**
	 * 
	 * @param timein
	 * @return int errorMessage
	 * 0 for no error,
	 * 1 for illegal ID input,
	 * 2 for ID currently not in the park.
	 */
	private int CusExit(long timein){
		//long timeout = System.currentTimeMillis();
		if(timein < 0)
			return 1;
		if(!data.CheckExist("CusIn",timein))
			return 2;
		
		return 0;
	}
	public String CusExiting(String id){
		try{
			Long.parseLong(id);
		}catch(NumberFormatException e){
			return "Please enter a valid id.";
		}
		long i = CusExit(Long.parseLong(id));
		if(i == 1)
			return "Please enter a valid id.";
		if(i==2)
			return "This car is not in the park.";
		else
			return "Your parking fee is "+p.cusFee((CusInEntry)data.find("CusIn",Long.parseLong(id)))+" pounds.";
	}
	public String CusPay(long timein, float paidFee){
		if(p.cusFee((CusInEntry)data.find("CusIn", timein))==paidFee){
			data.delete("CusIn", timein);
			p.setMoney(p.refreshMoney()+paidFee);
			p.letOut();
			return "Bye! Drive safe!";
		}
		else
			return "You need to pay "+p.cusFee((CusInEntry)data.find("CusIn",timein))+" pounds.";
	}
}
