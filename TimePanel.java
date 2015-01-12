import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

public class TimePanel extends JPanel{
	private Calendar calendar = new GregorianCalendar();
	private String week = "";
	private MessagePanel dateMessage = null;
	private MessagePanel timeMessage = null;
	private int x1 = 40;
	private int y1 = 40;
	private int x2 = 60;
	private int y2 = 20;
	
	public void setX1(int x1){
		this.x1 = x1;
	}
	public void setX2(int x2){
		this.x2 = x2;
	}
	public void setY1(int y1){
		this.y1 = y1;
	}
	public void setY2(int y2){
		this.y2 = y2;
	}
	
	public TimePanel(){
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case 1: week = "SUN"; break;
			case 2: week = "MON"; break;
			case 3: week = "TUE"; break;
			case 4: week = "WED"; break;
 			case 5: week = "THU"; break;
			case 6: week = "FRI"; break;
			case 7: week = "SAT"; break;
		}
		dateMessage = new MessagePanel(calendar.get(Calendar.DAY_OF_MONTH)+"/"
				+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR)+","
				+week);
		dateMessage.setFont(new Font("Times", Font.PLAIN, 22));
		dateMessage.setXCoordinate(40);
		dateMessage.setYCoordinate(40);
		timeMessage = new MessagePanel(calendar.get(Calendar.HOUR_OF_DAY)+":"
				+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
		timeMessage.setFont(new Font("Times", Font.PLAIN, 20));
		timeMessage.setXCoordinate(60);
		timeMessage.setYCoordinate(20);

		this.setLayout(new GridLayout(2,1,0,10));
		add(dateMessage);
		add(timeMessage);
		
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calendar = new GregorianCalendar();
			switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case 1: week = "SUN"; break;
			case 2: week = "MON"; break;
			case 3: week = "TUE"; break;
			case 4: week = "WED"; break;
 			case 5: week = "THU"; break;
			case 6: week = "FRI"; break;
			case 7: week = "SAT"; break;
			}
			dateMessage.setMessage(calendar.get(Calendar.DAY_OF_MONTH)+"/"
					+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR)+","
					+week);
			timeMessage.setMessage(calendar.get(Calendar.HOUR_OF_DAY)+":"
					+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
			repaint();
		}
	}
}
