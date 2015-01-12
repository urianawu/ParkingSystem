
public class OperationControl {
	park p=new park();
	
	public boolean setTariff(String[] tariff){
		float[] tariffInt = {0,0,0,0,0};
		for(int i=0;i<tariff.length;i++){
			if(tariff[i]==null)
				return false;
			try {
				tariffInt[i]=Float.parseFloat(tariff[i]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		
		return setTariff(tariffInt);
	}

	private boolean setTariff(float[] tariff){
		if(tariff.length==5){
			p.setTariff(tariff);
			return true;
		}
		else return false;
	}
	
	public float collectMoney(){
		float money=p.refreshMoney();
		p.setMoney(0);
		return money;
	}

	public boolean feedTicket(String num){	
		int number;
		try {
			number=Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return false;
		}
		if(number<=0)
			return false;
		else return feedTicket(number);
	}

	private boolean feedTicket(int num){
		if((num+p.refreshTicket())>1000||(num+p.refreshTicket())<=0)
			return false;
		else
			p.setTicket(num+p.refreshTicket());
			return true;
	}

	public boolean setPassword(String oldPassword,String newPassword){
		return p.resetPassword(oldPassword, newPassword);
	}

	public boolean setWeekDay(String[] openWeek){
		int i=0;
		boolean[] openWeekBole = {true,true,true,true,true};
		while(i<5){
			try {
				openWeekBole[i]=Boolean.parseBoolean(openWeek[i]);
			} catch (Exception e) {
				return false;
			}
		i++;
		}
		setWeekDay(openWeekBole);
		return true;
	}

	private void setWeekDay(boolean[] openWeek){
		p.setOpenDays(openWeek);
	}

	public boolean addHoliday(String[] month, String[] day){
		int[] monthInt={0,0};
		int[] dayInt={0,0};
		
		try {
			monthInt[0]=Integer.parseInt(month[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			monthInt[1]=Integer.parseInt(month[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			dayInt[0]=Integer.parseInt(day[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			dayInt[1]=Integer.parseInt(day[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		return addHoliday(monthInt, dayInt);
	}

	private boolean addHoliday(int[] month,int[] day){
		int i=0;
		float[] date={0,0};
		while(i<2){
		int monthes=month[i];
		int days=day[i];
		if(monthes==1&&days<31||monthes==2&&days<29||monthes==3&&days<31||monthes==4&&days<30||monthes==5&&days<31||monthes==6&&days<30||monthes==7&&days<31||monthes==8&&days<31||monthes==9&&days<30||monthes==10&&days<31||monthes==11&&days<30||monthes==12&&days<31){
			date[i]=monthes+(float)days/100;
			i++;
		}else return false;
		}
		p.addholiday(date);
		return true;
	
	}

	public boolean deleteHoliday(String[] month, String[] day){
		int[] monthInt={0,0};
		int[] dayInt={0,0};
		
		try {
			monthInt[0]=Integer.parseInt(month[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			monthInt[1]=Integer.parseInt(month[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			dayInt[0]=Integer.parseInt(day[0]);
		} catch (NumberFormatException e) {
			return false;
		}
		try {
			dayInt[1]=Integer.parseInt(day[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		return deleteHoliday(monthInt, dayInt);
	}
	
	private boolean deleteHoliday(int[]month,int[]day){
		float[] holiday={(float)0.0,(float) 0.0};
		holiday[0]=month[0]+(float)day[0]/100;
		holiday[1]=month[1]+(float)day[1]/100;
		p.deleteholiday(holiday);
		return true;
	}

	public String parkInfo(){
		return p.getParkInfo();
	}
	
}
