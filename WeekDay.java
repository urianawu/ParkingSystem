

import java.util.Calendar;
import java.util.Date;

public class WeekDay {
	
	public WeekDay() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getWeekDay()
    {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
