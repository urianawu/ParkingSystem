

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Holiday {
	ArrayList<Float> holidayStart=new ArrayList<Float>();
	ArrayList<Float> holidayEnd=new ArrayList<Float>();
	ArrayList<Date> holidayStartDate=new ArrayList<Date>();
	ArrayList<Date> holidayEndDate=new ArrayList<Date>();
	boolean result=true;
	
	@SuppressWarnings("deprecation")
	public boolean noHoliday(ArrayList holidays) {

		for(int i=0;i<holidays.size();){
			holidayStart.add((Float) holidays.get(i));
			i++;
			holidayEnd.add((Float) holidays.get(i));
			i++;
		}
		for(int i=0;i<holidayStart.size()&&i<holidayEnd.size();i++){
			Date date=assignDate(holidayStart.get(i));
			holidayStartDate.add(date);
			date=assignDate(holidayEnd.get(i));
			holidayEndDate.add(date);
			holidayEnd.add((Float) holidays.get(i));
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			//System.out.println("Start: "+df.format((holidayStartDate.get(i))));// new Date()为获取当前系统时间
			//System.out.println("END: "+df.format((holidayEndDate.get(i))));// new Date()为获取当前系统时间
			result=result&&(holidayEndDate.get(i).before(Calendar.getInstance().getTime())||holidayStartDate.get(i).after(Calendar.getInstance().getTime()));
		}
		
		return result;
		
	}

	@SuppressWarnings("deprecation")
	private Date assignDate(Float floatDate) {
		int day=(int) ((floatDate-Math.floor(floatDate))*100.01);
		System.out.println("Day:"+day);
		int month=Math.round(floatDate);
		System.out.println("Month:"+month);
		Date date=new Date();
		date.setYear(new Date(System.currentTimeMillis()).getYear());
		date.setMonth(month-1);
		date.setDate(day);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);

		return date;
	}

	public int[] explain(float holiday){
		int[] result={0,0};
		result[0]=(int)holiday;
		result[1]=(int) ((holiday-(int)holiday)*101);
		return result;
	}
}
