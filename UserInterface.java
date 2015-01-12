import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class UserInterface extends JFrame implements ActionListener{
	private JPanel panelWelcome = new JPanel();
	private JPanel panelStaff = new JPanel();
	private JPanel panelCustomer = new JPanel();
	private JPanel panelOperator = new JPanel();
	private JPanel panelPassword = new JPanel();
	private String[] titles = {"QM Staff", "Customer", "System Operator"};
	private JComboBox jcbo = new JComboBox(titles);
	private int selection = 0;
	private JTabbedPane tappedStaff = new JTabbedPane();
	private JTabbedPane tappedCustomer = new JTabbedPane();
	private JTabbedPane tappedOperator = new JTabbedPane();
	private JPanel pnlS1 = new JPanel();
	private JPanel pnlS2 = new JPanel();
	private JPanel pnlS3 = new JPanel();
	private JPanel pnlS4 = new JPanel();
	private JPanel pnlC1 = new JPanel();
	private JPanel pnlC2 = new JPanel();
	private JPanel pnlC3 = new JPanel();
	private JPanel pnlC4 = new JPanel();
	private JPanel pnlC5 = new JPanel();
	private JPanel pnl1 = new JPanel();
	private JPanel pnl1_2 = new JPanel();
	private JPanel pnlO1 = new JPanel();
	private JPanel pnlO2 = new JPanel();
	private JPanel pnlO3 = new JPanel();
	private JPanel pnlO4 = new JPanel();
	private JPanel pnlP1 = new JPanel();
	private JPanel P1_pnl1 = new JPanel();
	private JPanel C1_pnl2 = new JPanel();
	private JPanel C1_pnl2_1 = new JPanel();
	private JPanel C1_pnl2_2 = new JPanel();
	private JPanel C2_pnl1 = new JPanel();
	private JPanel C2_pnl1_2 = new JPanel();
	private JPanel C2_pnl2 = new JPanel();
	private JPanel C2_pnl2_1 = new JPanel();
	private JPanel C2_pnl2_2 = new JPanel();
	private JPanel C3_pnl1 = new JPanel();
	private JPanel C3_pnl1_1 = new JPanel();
	private JPanel C3_pnl1_2 = new JPanel();
	private JPanel C3_pnl2 = new JPanel();
	private JPanel C4_pnl0 = new JPanel();
	private JPanel C4_pnl1 = new JPanel();
	private JPanel C4_pnl2 = new JPanel();
	private JPanel O1_pnl0 = new JPanel();
	private JPanel O1_pnl1 = new JPanel();
	private JTextField O3_costtxf1 = new JTextField();
	private JTextField O3_costtxf2 = new JTextField();
	private JTextField O3_costtxf3 = new JTextField();
	private JTextField O3_costtxf4 = new JTextField();
	private JTextField O3_costtxf5 = new JTextField();
	private JSlider sld1 = new CoinSlider();
	private JSlider sld2 = new CoinSlider();
	private JSlider sld3 = new CoinSlider();
	private JSlider sld4 = new CoinSlider();
	private JSlider sld5 = new CoinSlider();
	private JTextField C2_customertxf = new JTextField();
	private JTextField S1_jtf1 = new JTextField();
	private JTextField S1_jtf2 = new JTextField();
	private JTextField S2_jtf1 = new JTextField();
	private JTextField S2_jtf2 = new JTextField();
	private JPanel S1_pnl2 = new JPanel();
	private JPanel S1_pnl2_1 = new JPanel();
	private JPanel S1_pnl2_2 = new JPanel();
	private JPanel S2_pnl2 = new JPanel();
	private JPanel S2_pnl2_1 = new JPanel();
	private JPanel S2_pnl2_2 = new JPanel();
	private JTextField S3_jtf1 = new JTextField();
	private JTextField S3_jtf2 = new JTextField();
	private JTextField S3_jtf3 = new JTextField();
	private JTextField S3_jtf4 = new JTextField();
	private JTextField S3_jtf5 = new JTextField();
	private JPanel S4_pnl1 = new JPanel();
	private JPanel S4_pnl2 = new JPanel();
	private JTextField S4_jtf = new JTextField();
	private JPanel O2_pnl1_1 = new JPanel();
	private JPasswordField txf = new JPasswordField();
	private JPasswordField O4_pwtxf1 = new JPasswordField();	//child of JTextField
	private JPasswordField O4_pwtxf2 = new JPasswordField();	//child of JTextField
	private JPasswordField O4_pwtxf3 = new JPasswordField();	//child of JTextField

	/*private double cost2 = 0.5;				// these message
	private double cost4 = 1.0;				// need to be
	private double cost8 = 2.0;				// storted
	private double cost12 = 3.0;			// in a
	private double cost24 = 5.0;			// file
	*/
	private float amountOfMoney = 0;		//~
	private int amountOfTicket = 100;		//~

	private Calendar calendar = new GregorianCalendar();
	
	/**
	 * This is the constructor of class UserInterface.
	 * This constructor generate a frame which contains a welcome interface.
	 */
	public UserInterface(){
		super("QM Parking System");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		setSize(470, 500); // set the window size
		setLocationRelativeTo(null);  // center the window
		setVisible(true); // show the window
			
		welcomeSetting();	//setting 
		StaffSetting(); 	//five
		customerSetting();	//interface
		operatorSetting();	//respectively
		passwordSetting();	//~
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(panelWelcome);  //insert the welcome interface.
		
		Timer timer1 = new Timer(3600000, new TimerListener1());		//Time triggered event. Check every hour.
		timer1.start();
		Timer timer2 = new Timer(86400000, new TimerListener2());		//Time triggered event. Check every day.
		timer2.start();
	}

	/**
	 * This method configurates the welcome interface
	 */
	public void welcomeSetting(){
		MessagePanel messagePanel1 = new MessagePanel("Welcome to QM Parking System!");
		messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		messagePanel1.setBackground(new Color(222,222,222));
		messagePanel1.setCentered(true);

		TimePanel timePanel = new TimePanel();
		MessagePanel messagePanel3 = new MessagePanel("Parking spaces:");
		messagePanel3.setFont(new Font("Times", Font.PLAIN, 16));
		messagePanel3.setXCoordinate(30);
		messagePanel3.setYCoordinate(40);
		MessagePanel messagePanel4 = new MessagePanel(""+(new park().getSpace()-new park().refreshUserCount()));
		messagePanel4.setFont(new Font("Times", Font.BOLD, 24));
		messagePanel4.setXCoordinate(80);
		messagePanel4.setYCoordinate(20);
		pnl1.setLayout(new GridLayout(1,2,0,0));
		pnl1_2.setLayout(new GridLayout(2,1,0,10));
		pnl1_2.add(messagePanel3);
		pnl1_2.add(messagePanel4);
		pnl1.add(timePanel);
		pnl1.add(pnl1_2);
		JPanel pnl2 = new JPanel();
		JPanel pnl2_1 = new JPanel();
		JButton enterbtn = new JButton("Enter System");
		enterbtn.addActionListener(this);
		enterbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////TBD/////////////////////
				//pnlP1.remove(txf);
				//pnlP1.add(txf);
				O2_pnl1_1.removeAll();
				O2_pnl1_1.add(ticketSet());
			}
			
		});
		jcbo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent arg0) {
				selection = jcbo.getSelectedIndex();
			}
		});
		MessagePanel messagePanel2 = new MessagePanel("Please verify your identity.");
		messagePanel2.setFont(new Font("Times", Font.BOLD, 17));
		messagePanel2.setXCoordinate(20);
		messagePanel2.setYCoordinate(20);
		pnl2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 40,0));
		pnl2_1.add(jcbo);
		pnl2_1.add(enterbtn);
		pnl2.setLayout(new GridLayout(2,1,0,0));
		pnl2.add(messagePanel2);
		pnl2.add(pnl2_1);
		
		panelWelcome.setLayout(new GridLayout(3,1,10,10));
		panelWelcome.add(messagePanel1);
		panelWelcome.add(pnl1);
		panelWelcome.add(pnl2);
		
	}
	
	/**
	 * This method configurates the stuff interface
	 */
	public void StaffSetting(){
		MessagePanel S1_messagePanel1 = new MessagePanel("Welcome to QM Parking System!");//panS1, panel1
		S1_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		S1_messagePanel1.setCentered(true);
		JPanel S1_pnl1 = new JPanel();                               					//panS1, panel2
		JPanel S1_pnl1_2 = new JPanel();
		TimePanel S1_timePanel = new TimePanel();
		JLabel S1_lbl1 = new JLabel("Campus Card ID:");
		JLabel S1_lbl2 = new JLabel("Car number:");
		S1_jtf1.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S1_jtf1.setFont(new Font("Times", Font.PLAIN, 18));	//set character size
		S1_jtf2.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S1_jtf2.setFont(new Font("Times", Font.PLAIN, 18));	//set character size
		
		MessagePanel S1_messagePanel3 = new MessagePanel("Parking spaces:");			//panS1, panel3
		S1_messagePanel3.setFont(new Font("Times", Font.PLAIN, 16));
		S1_messagePanel3.setXCoordinate(30);
		S1_messagePanel3.setYCoordinate(40);
		MessagePanel S1_messagePanel4 = spaceDisplay();
		JButton S1_donebtn = new JButton("DONE");
		S1_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println(S1_jtf1.getText()+"it's enter ID");
				//System.out.println(S1_jtf2.getText()+"it's enter car");
				String s = new StaffControl().staffEntering(S1_jtf1.getText(),S1_jtf2.getText());
				JOptionPane.showMessageDialog(null, s);
				reset();
			}
		});
		JButton S1_backbtn = new JButton("BACK");
		S1_backbtn.addActionListener(new BackButtonListener());
		S1_pnl1.setLayout(new GridLayout(1,2,0,0));										//panS1, layout
		S1_pnl1_2.setLayout(new GridLayout(2,2,0,10));
		S1_pnl1_2.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		S1_pnl1_2.setLayout(new GridLayout(2,1,0,20));
		S1_pnl1_2.add(S1_lbl1);
		S1_pnl1_2.add(S1_jtf1);
		S1_pnl1_2.add(S1_lbl2);
		S1_pnl1_2.add(S1_jtf2);
		S1_pnl1.add(S1_timePanel);
		S1_pnl1.add(S1_pnl1_2);
		S1_pnl2_1.setLayout(new GridLayout(2,1,0,0));
		S1_pnl2_1.add(S1_messagePanel3);
		S1_pnl2_1.add(S1_messagePanel4);
		S1_pnl2_2.setBorder(BorderFactory.createEmptyBorder(100,45,17,25));
		S1_pnl2_2.setLayout(new GridLayout(1,2,20,0));
		S1_pnl2_2.add(S1_donebtn);
		S1_pnl2_2.add(S1_backbtn);
		S1_pnl2.setLayout(new GridLayout(1,2,0,0));
		S1_pnl2.add(S1_pnl2_1);
		S1_pnl2.add(S1_pnl2_2);
		pnlS1.setLayout(new GridLayout(3,1,0,0));
		pnlS1.add(S1_messagePanel1);
		pnlS1.add(S1_pnl1);
		pnlS1.add(S1_pnl2);
		
		
		MessagePanel S2_messagePanel1 = new MessagePanel("Goodbye, Drive Safe!");//panS2, panel1
		S2_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		S2_messagePanel1.setCentered(true);
		JPanel S2_pnl1 = new JPanel();                               					//panS2, panel2
		JPanel S2_pnl1_2 = new JPanel();
		TimePanel S2_timePanel = new TimePanel();
		JLabel S2_lbl1 = new JLabel("Campus Card ID:");
		S2_jtf1.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S2_jtf1.setFont(new Font("Times", Font.PLAIN, 18));	//set character size
		S2_jtf2.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S2_jtf2.setFont(new Font("Times", Font.PLAIN, 18));	//set character size
		MessagePanel S2_messagePanel3 = new MessagePanel("Parking spaces:");			//panS2, panel3
		S2_messagePanel3.setFont(new Font("Times", Font.PLAIN, 16));
		S2_messagePanel3.setXCoordinate(30);
		S2_messagePanel3.setYCoordinate(40);
		MessagePanel S2_messagePanel4 = spaceDisplay();
		JButton S2_donebtn = new JButton("DONE");
		S2_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println(S2_jtf1.getText());
				String s = new StaffControl().staffExit(S2_jtf1.getText());
				JOptionPane.showMessageDialog(null, s);
				reset();
			}
		});
		JButton S2_backbtn = new JButton("BACK");
		S2_backbtn.addActionListener(new BackButtonListener());
		S2_pnl1.setLayout(new GridLayout(1,2,0,0));										//panS2, layout
		S2_pnl1_2.setLayout(new GridLayout(2,2,0,10));
		S2_pnl1_2.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		S2_pnl1_2.setLayout(new GridLayout(2,1,0,20));
		S2_pnl1_2.add(S2_lbl1);
		S2_pnl1_2.add(S2_jtf1);
		S2_pnl1.add(S2_timePanel);
		S2_pnl1.add(S2_pnl1_2);
		S2_pnl2_1.setLayout(new GridLayout(2,1,0,0));
		S2_pnl2_1.add(S2_messagePanel3);
		S2_pnl2_1.add(S2_messagePanel4);
		S2_pnl2_2.setBorder(BorderFactory.createEmptyBorder(100,45,17,25));
		S2_pnl2_2.setLayout(new GridLayout(1,2,20,0));
		S2_pnl2_2.add(S2_donebtn);
		S2_pnl2_2.add(S2_backbtn);
		S2_pnl2.setLayout(new GridLayout(1,2,0,0));
		S2_pnl2.add(S2_pnl2_1);
		S2_pnl2.add(S2_pnl2_2);
		pnlS2.setLayout(new GridLayout(3,1,0,0));
		pnlS2.add(S2_messagePanel1);
		pnlS2.add(S2_pnl1);
		pnlS2.add(S2_pnl2);
		
		
		MessagePanel S3_messagePanel1 = new MessagePanel("Please Input new Staff Info!");//panS3, panel1
		S3_messagePanel1.setFont(new Font("Times", Font.BOLD, 26));
		S3_messagePanel1.setCentered(true);
		JPanel S3_pnl1 = new JPanel();                               					//panS3, panel2
		JLabel S3_lbl1 = new JLabel("Staff ID:");
		JLabel S3_lbl2 = new JLabel("Name: ");
		JLabel S3_lbl3 = new JLabel("Car number: ");
		JLabel S3_lbl4 = new JLabel("Car ID(optional):");
		JLabel S3_lbl5 = new JLabel("Car ID(optional):");
		
		S3_jtf1.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S3_jtf1.setFont(new Font("Times", Font.PLAIN, 22));	//set character size
		S3_jtf2.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S3_jtf2.setFont(new Font("Times", Font.PLAIN, 22));	//set character size
		S3_jtf3.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S3_jtf3.setFont(new Font("Times", Font.PLAIN, 22));	//set character size
		S3_jtf4.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S3_jtf4.setFont(new Font("Times", Font.PLAIN, 22));	//set character size
		S3_jtf5.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		S3_jtf5.setFont(new Font("Times", Font.PLAIN, 22));	//set character size
		JPanel S3_pnl2 = new JPanel();
		JButton S3_donebtn = new JButton("DONE");
		S3_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = new StaffControl().staffRegister(S3_jtf1.getText(),S3_jtf2.getText(),S3_jtf3.getText(),S3_jtf4.getText(),S3_jtf5.getText());
				JOptionPane.showMessageDialog(null, s);
				reset();
			}
		});
		JButton S3_backbtn = new JButton("BACK");
		S3_backbtn.addActionListener(new BackButtonListener());
		S3_pnl1.setLayout(new GridLayout(5,2,0,0));										//panS3, layout
		S3_pnl1.setBorder(BorderFactory.createEmptyBorder(65,40,50,40));
		S3_pnl1.add(S3_lbl1);
		S3_pnl1.add(S3_jtf1);
		S3_pnl1.add(S3_lbl2);
		S3_pnl1.add(S3_jtf2);
		S3_pnl1.add(S3_lbl3);
		S3_pnl1.add(S3_jtf3);
		S3_pnl1.add(S3_lbl4);
		S3_pnl1.add(S3_jtf4);
		S3_pnl1.add(S3_lbl5);
		S3_pnl1.add(S3_jtf5);
		S3_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20,20));
		S3_pnl2.add(S3_donebtn);
		S3_pnl2.add(S3_backbtn);
		pnlS3.setLayout(new BorderLayout());
		pnlS3.add(S3_messagePanel1,BorderLayout.NORTH);
		pnlS3.add(S3_pnl1);
		pnlS3.add(S3_pnl2,BorderLayout.SOUTH);
		
		




		S2_pnl1_2.setLayout(new GridLayout(2,2,0,10));
		S2_pnl1_2.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		S2_pnl1_2.setLayout(new GridLayout(2,1,0,20));
		S2_pnl1_2.add(S2_lbl1);
		S2_pnl1_2.add(S2_jtf1);



		
		MessagePanel S4_messagePanel1 = new MessagePanel("Input your ID to check monthly bill!");
		S4_messagePanel1.setFont(new Font("Times", Font.BOLD, 24));
		S4_messagePanel1.setCentered(true);
		MessagePanel S4_messagePanel2 = new MessagePanel("Your staff ID:");
		S4_messagePanel2.setFont(new Font("Times", Font.PLAIN, 16));
		S4_messagePanel2.setXCoordinate(0);
		S4_messagePanel2.setYCoordinate(20);
		S4_jtf.setHorizontalAlignment(JTextField.RIGHT);
		S4_jtf.setFont(new Font("Times", Font.PLAIN, 18));
		
		JButton S4_donebtn = new JButton("DONE");
		S4_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = new StaffControl().checkBill(S4_jtf.getText());
				JOptionPane.showMessageDialog(null, s);
			}
		});
		JButton S4_backbtn = new JButton("BACK");
		S4_backbtn.addActionListener(new BackButtonListener());
		S4_pnl1.setLayout(new GridLayout(2,1,10,10));
		S4_pnl1.setBorder(BorderFactory.createEmptyBorder(30,130,30,130));
		S4_pnl1.add(S4_messagePanel2);
		S4_pnl1.add(S4_jtf);
		S4_pnl2.setBorder(BorderFactory.createEmptyBorder(75,0,30,0));
		S4_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		S4_pnl2.add(S4_donebtn);
		S4_pnl2.add(S4_backbtn);
		pnlS4.setLayout(new GridLayout(3,1,0,0));
		pnlS4.add(S4_messagePanel1);
		pnlS4.add(S4_pnl1);
		pnlS4.add(S4_pnl2);
		
		
		tappedStaff.addTab("Entrance", pnlS1);
		tappedStaff.addTab("Exit", pnlS2);
		tappedStaff.addTab("Registry", pnlS3);
		tappedStaff.addTab("Check Bill", pnlS4);
		panelStaff.setLayout(new BorderLayout());
		panelStaff.add(tappedStaff, BorderLayout.CENTER);
	}

	/**
	 * This method configurates the customer interface
	 */
	public void customerSetting(){
		MessagePanel C1_messagePanel1 = new MessagePanel("Welcome to QM Parking System!");//panS1, panel1
		C1_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		C1_messagePanel1.setCentered(true);
		JPanel C1_pnl1 = new JPanel();                               					//panS1, panel2
		JPanel C1_pnl1_2 = new JPanel();
		TimePanel C1_timePanel = new TimePanel();
		CircleButton C1_getbtn = new CircleButton("get ticket", new Color(140,140,140));
		C1_getbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(new park().getTicket()==0){
					JOptionPane.showMessageDialog(null, "No ticket available!");
					return;}
				String carNum = JOptionPane.showInputDialog(null, "Please enter your car number:",null);
				if(carNum!=null){
					String message = new CustomerControl().CusEntering(carNum);
					if(message.contains("1")){
						JOptionPane.showMessageDialog(null, "Your ticket ID is:\n"+message);
						reset();
					}
					else
						JOptionPane.showMessageDialog(null, message);

				}
			}
		});
		MessagePanel C1_messagePanel3 = new MessagePanel("Parking spaces:");			//panS1, panel3
		C1_messagePanel3.setFont(new Font("Times", Font.PLAIN, 16));
		C1_messagePanel3.setXCoordinate(30);
		C1_messagePanel3.setYCoordinate(40);
		MessagePanel C1_messagePanel4 = spaceDisplay();
		JButton C1_backbtn = new JButton("BACK");
		C1_backbtn.addActionListener(new BackButtonListener());
		C1_pnl1.setLayout(new GridLayout(1,2,0,0));										//panS1, layout
		C1_pnl1_2.setBorder(BorderFactory.createEmptyBorder(20,55,20,55));
		C1_pnl1_2.setLayout(new GridLayout(1,1,0,0));
		C1_pnl1_2.add(C1_getbtn);
		C1_pnl1.add(C1_timePanel);
		C1_pnl1.add(C1_pnl1_2);
		C1_pnl2_1.setLayout(new GridLayout(2,1,0,0));
		C1_pnl2_1.add(C1_messagePanel3);
		C1_pnl2_1.add(C1_messagePanel4);
		C1_pnl2_2.setBorder(BorderFactory.createEmptyBorder(100,140,20,70));
		C1_pnl2_2.add(C1_backbtn);
		C1_pnl2.setLayout(new GridLayout(1,2,0,0));
		C1_pnl2.add(C1_pnl2_1);
		C1_pnl2.add(C1_pnl2_2);
		pnlC1.setLayout(new GridLayout(3,1,0,0));
		pnlC1.add(C1_messagePanel1);
		pnlC1.add(C1_pnl1);
		pnlC1.add(C1_pnl2);
		
		
		MessagePanel C2_messagePanel1 = new MessagePanel("Goodbye, Drive Safe!");//panS1, panel1
		C2_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		C2_messagePanel1.setCentered(true);
		TimePanel C2_timePanel = new TimePanel();
		MessagePanel C2_messagePanel5 = new MessagePanel("Input the ID of your card:");			//panS1, panel3
		C2_messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		C2_messagePanel5.setCentered(true);
		C2_customertxf.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		C2_customertxf.setFont(new Font("Times", Font.PLAIN, 18));//set character size
	
		MessagePanel C2_messagePanel3 = new MessagePanel("Parking spaces:");			//panS1, panel3
		C2_messagePanel3.setFont(new Font("Times", Font.PLAIN, 16));
		C2_messagePanel3.setXCoordinate(30);
		C2_messagePanel3.setYCoordinate(40);
		MessagePanel C2_messagePanel4 = spaceDisplay();
		JButton C2_donebtn = new JButton("DONE");
		C2_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String message =  new CustomerControl().CusExiting(C2_customertxf.getText());
				if(message.charAt(0) == 'Y'){
					pnlC2.setVisible(false);
					pnlC4.setVisible(true);
					pnlC5.remove(pnlC2);
					pnlC5.add(pnlC4);
					reset();
				}
				JOptionPane.showMessageDialog(null,message);
			}
		});
		JButton C2_backbtn = new JButton("BACK");
		C2_backbtn.addActionListener(new BackButtonListener());
		C2_pnl1.setLayout(new GridLayout(1,2,0,0));										//panS1, layout
		C2_pnl1_2.setBorder(BorderFactory.createEmptyBorder(20,30,40,30));
		C2_pnl1_2.setLayout(new GridLayout(2,1,0,20));
		C2_pnl1_2.add(C2_messagePanel5);
		C2_pnl1_2.add(C2_customertxf);
		C2_pnl1.add(C2_timePanel);
		C2_pnl1.add(C2_pnl1_2);
		C2_pnl2_1.setLayout(new GridLayout(2,1,0,0));
		C2_pnl2_1.add(C2_messagePanel3);
		C2_pnl2_1.add(C2_messagePanel4);
		C2_pnl2_2.setBorder(BorderFactory.createEmptyBorder(100,43,10,25));
		C2_pnl2_2.setLayout(new GridLayout(1,2,20,0));
		C2_pnl2_2.add(C2_donebtn);
		C2_pnl2_2.add(C2_backbtn);
		C2_pnl2.setLayout(new GridLayout(1,2,0,0));
		C2_pnl2.add(C2_pnl2_1);
		C2_pnl2.add(C2_pnl2_2);
		pnlC2.setLayout(new GridLayout(3,1,0,0));
		pnlC2.add(C2_messagePanel1);
		pnlC2.add(C2_pnl1);
		pnlC2.add(C2_pnl2);
		pnlC5.setLayout(new BorderLayout());
		pnlC5.add(pnlC2);
		
		
		MessagePanel C3_messagePanel1 = new MessagePanel("Open Time & Parking Fee");//panC3, panel1
		C3_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		C3_messagePanel1.setCentered(true);
		MessagePanel C3_messagePanel3 = new MessagePanel("The car park is open to");			//panC3, panel3
		C3_messagePanel3.setFont(new Font("Times", Font.PLAIN, 14));
		C3_messagePanel3.setCentered(true);
		MessagePanel C3_messagePanel4 = new MessagePanel("the public at weekends,");
		C3_messagePanel4.setFont(new Font("Times", Font.PLAIN, 14));
		C3_messagePanel4.setCentered(true);
		MessagePanel C3_messagePanel5 = new MessagePanel("public holidays and during");
		C3_messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		C3_messagePanel5.setCentered(true);
		MessagePanel C3_messagePanel6 = new MessagePanel("summer time only");
		C3_messagePanel6.setFont(new Font("Times", Font.PLAIN, 14));
		C3_messagePanel6.setCentered(true);
		JButton C3_backbtn = new JButton("BACK");
		C3_backbtn.addActionListener(new BackButtonListener());
		C3_pnl1_1.setBorder(BorderFactory.createEmptyBorder(100,0,100,0));
		C3_pnl1_1.setLayout(new GridLayout(4,1,0,0));
		C3_pnl1_1.add(C3_messagePanel3);
		C3_pnl1_1.add(C3_messagePanel4);
		C3_pnl1_1.add(C3_messagePanel5);
		C3_pnl1_1.add(C3_messagePanel6);
		C3_pnl1_2 = paymentDisplay();
		C3_pnl1.setLayout(new GridLayout(1,2,0,0));	
		C3_pnl1.add(C3_pnl1_1);
		C3_pnl1.add(C3_pnl1_2);
		C3_pnl2.setBorder(BorderFactory.createEmptyBorder(40,335,6,42));
		C3_pnl2.setLayout(new FlowLayout());
		C3_pnl2.add(C3_backbtn);
		pnlC3.setLayout(new BorderLayout());
		pnlC3.add(C3_messagePanel1,BorderLayout.NORTH);
		pnlC3.add(C3_pnl1);
		pnlC3.add(C3_pnl2,BorderLayout.SOUTH);
		
		
		MessagePanel C4_messagePanel0 = new MessagePanel("PAY");
		C4_messagePanel0.setFont(new Font("Times", Font.BOLD, 27));
		C4_messagePanel0.setCentered(true);
		MessagePanel C4_messagePanel1 = new MessagePanel("Insert 10p(s)");
		C4_messagePanel1.setFont(new Font("Times", Font.PLAIN, 14));
		C4_messagePanel1.setCentered(true);
		MessagePanel C4_messagePanel2 = new MessagePanel("Insert 20p(s)");
		C4_messagePanel2.setFont(new Font("Times", Font.PLAIN, 14));
		C4_messagePanel2.setCentered(true);
		MessagePanel C4_messagePanel3 = new MessagePanel("Insert 50p(s)");
		C4_messagePanel3.setFont(new Font("Times", Font.PLAIN, 14));
		C4_messagePanel3.setCentered(true);
		MessagePanel C4_messagePanel4 = new MessagePanel("Insert 1pound(s)");
		C4_messagePanel4.setFont(new Font("Times", Font.PLAIN, 14));
		C4_messagePanel4.setCentered(true);
		MessagePanel C4_messagePanel5 = new MessagePanel("Insert 2pound(s)");
		C4_messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		C4_messagePanel5.setCentered(true);
		JButton C4_donebtn = new JButton("DONE");
		C4_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				float paidFee = (float) (0.1*((float)sld1.getValue())+0.2*sld2.getValue()+0.5*sld3.getValue()+sld4.getValue()+2*sld5.getValue());
				String message = new CustomerControl().CusPay(Long.parseLong(C2_customertxf.getText()),paidFee);
				if(message.charAt(0)=='B'){
					JOptionPane.showMessageDialog(null, message);
					pnlC2.setVisible(true);
					pnlC4.setVisible(false);
					pnlC5.remove(pnlC4);
					pnlC5.add(pnlC2);
					O2_pnl1_1.removeAll();
					O2_pnl1_1.add(ticketSet());
					O2_pnl1_1.removeAll();
					O2_pnl1_1.add(ticketSet());
				}
				else{
					JOptionPane.showMessageDialog(null, message);
				}
				//System.out.println(sld1.getValue()+" "+sld2.getValue()+" "+sld3.getValue()+" "+sld4.getValue()+" "+sld5.getValue()+" ");
			}
		});
		C4_pnl0.setLayout(new FlowLayout());
		C4_pnl0.setBorder(BorderFactory.createEmptyBorder(20,20,30,20));
		C4_pnl0.add(C4_messagePanel0);
		C4_pnl1.setLayout(new GridLayout(5,2,20,20));
		C4_pnl1.setBorder(BorderFactory.createEmptyBorder(0,0,0,80));
		C4_pnl1.add(C4_messagePanel1);
		C4_pnl1.add(sld1);
		C4_pnl1.add(C4_messagePanel2);
		C4_pnl1.add(sld2);
		C4_pnl1.add(C4_messagePanel3);
		C4_pnl1.add(sld3);
		C4_pnl1.add(C4_messagePanel4);
		C4_pnl1.add(sld4);
		C4_pnl1.add(C4_messagePanel5);
		C4_pnl1.add(sld5);
		C4_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		C4_pnl2.setBorder(BorderFactory.createEmptyBorder(15,15,25,15));
		C4_pnl2.add(C4_donebtn);
		pnlC4.setLayout(new BorderLayout());
		pnlC4.add(C4_pnl0, BorderLayout.NORTH);
		pnlC4.add(C4_pnl1);
		pnlC4.add(C4_pnl2, BorderLayout.SOUTH);
		
		tappedCustomer.addTab("Entrance", pnlC1);
		tappedCustomer.addTab("Exit", pnlC5);
		tappedCustomer.addTab("Park info", pnlC3);
		panelCustomer.setLayout(new BorderLayout());
		panelCustomer.add(tappedCustomer, BorderLayout.CENTER);
	}
	
	/**
	 * This method resets the parking space of park.
	 * 
	 */
	public void reset(){
		pnl1_2.remove(1);
		pnl1_2.add(spaceDisplay());
		S1_pnl2_1.remove(1);
		S1_pnl2_1.add(spaceDisplay());
		S2_pnl2_1.remove(1);
		S2_pnl2_1.add(spaceDisplay());
		C1_pnl2_1.remove(1);
		C1_pnl2_1.add(spaceDisplay());
		C2_pnl2_1.remove(1);
		C2_pnl2_1.add(spaceDisplay());
		O1_pnl1.remove(1);
		O1_pnl1.add(parkInfoSet());
		O2_pnl1_1.removeAll();
		O2_pnl1_1.add(ticketSet());
		C3_pnl1.remove(1);
		C3_pnl1.add(paymentDisplay());
	}

	/**
	 * This method create a MessagePanel display the number of spaces of the park
	 * @return msgpnl      the created messagePanel
	 */
	public MessagePanel spaceDisplay(){
		MessagePanel msgpnl = new MessagePanel(""+(new park().getSpace()-new park().refreshUserCount()));
		msgpnl.setFont(new Font("Times", Font.BOLD, 24));
		msgpnl.setXCoordinate(80);
		msgpnl.setYCoordinate(20);
		return msgpnl;
	}

	/**
	 * This method makes a MessagePanel contains the payment message
	 * @return pnl      the created messagePanel
	 */
	public JPanel paymentDisplay(){
		JPanel pnl = new JPanel();
		MessagePanel messagePanel1 = new MessagePanel("Up to 2 hours"+parseCost(new park().getTariff()[0]));
		messagePanel1.setFont(new Font("Times", Font.PLAIN, 14));
		messagePanel1.setCentered(true);
		MessagePanel messagePanel2 = new MessagePanel("2 to 4 hours"+parseCost(new park().getTariff()[1]));
		messagePanel2.setFont(new Font("Times", Font.PLAIN, 14));
		messagePanel2.setCentered(true);
		MessagePanel messagePanel3 = new MessagePanel("4 to 8 hours"+parseCost(new park().getTariff()[2]));
		messagePanel3.setFont(new Font("Times", Font.PLAIN, 14));
		messagePanel3.setCentered(true);
		MessagePanel messagePanel4 = new MessagePanel("8 to 12 hours"+parseCost(new park().getTariff()[3]));
		messagePanel4.setFont(new Font("Times", Font.PLAIN, 14));
		messagePanel4.setCentered(true);
		MessagePanel messagePanel5 = new MessagePanel("12 to 24 hours"+parseCost(new park().getTariff()[4]));
		messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		messagePanel5.setCentered(true);
		pnl.setBorder(BorderFactory.createEmptyBorder(90,0,90,0));
		pnl.setLayout(new GridLayout(5,1,0,10));
		pnl.add(messagePanel1);
		pnl.add(messagePanel2);
		pnl.add(messagePanel3);
		pnl.add(messagePanel4);
		pnl.add(messagePanel5);
		return pnl;
	}
	
	/**
	 * This method configurates the operator interface
	 */
	public void operatorSetting(){
		MessagePanel O1_messagePanel1 = new MessagePanel("QM Parking System ");//panO1, panel1
		O1_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		O1_messagePanel1.setCentered(true);
		MessagePanel O1_messagePanel2 = new MessagePanel("(Operator) ");
		O1_messagePanel2.setFont(new Font("Times", Font.BOLD, 24));
		O1_messagePanel2.setCentered(true);
		MessagePanel O1_messagePanel3 = new MessagePanel("Park information:");
		O1_messagePanel3.setFont(new Font("Times", Font.BOLD, 18));
		JScrollPane scrollPane = parkInfoSet();
		O1_pnl1.setLayout(new BorderLayout());
		O1_pnl1.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		O1_pnl1.add(O1_messagePanel3, BorderLayout.NORTH);
		O1_pnl1.add(scrollPane);
		JPanel O1_pnl2 = new JPanel();
		O1_pnl0.setLayout(new GridLayout(2,1,0,0));
		O1_pnl0.add(O1_messagePanel1);
		O1_pnl0.add(O1_messagePanel2);
		JButton O1_backbtn = new JButton("BACK");
		O1_backbtn.addActionListener(new BackButtonListener());
		O1_pnl2.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		O1_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		O1_pnl2.add(O1_backbtn);
		pnlO1.setLayout(new BorderLayout());
		pnlO1.add(O1_pnl0,BorderLayout.NORTH);
		pnlO1.add(O1_pnl1);
		pnlO1.add(O1_pnl2,BorderLayout.SOUTH);
		
		
		MessagePanel O2_messagePanel1 = new MessagePanel("QM Parking System ");//panO2, panel1
		O2_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		O2_messagePanel1.setCentered(true);
		MessagePanel O2_messagePanel2 = new MessagePanel("(Operator) ");
		O2_messagePanel2.setFont(new Font("Times", Font.BOLD, 24));
		O2_messagePanel2.setCentered(true);
		amountOfMoney = new park().refreshMoney();
		amountOfTicket = new park().refreshTicket();
		
		JPanel O2_pnl0 = new JPanel();
		JPanel O2_pnl1 = new JPanel();
		JPanel O2_pnl1_2 = new JPanel();
		JPanel O2_pnl2 = new JPanel();
		JButton O2_backbtn = new JButton("BACK");
		O2_backbtn.addActionListener(new BackButtonListener());
		
		/* need two listener respectively */
		
		JButton O2_moneyCollect = new JButton("Money Collecting");
		O2_moneyCollect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,"You have collect "+new OperationControl().collectMoney()+" pounds.");
				//System.out.println(amountOfMoney+"111111111111111111");
				reset();
			}
		});
		//amountOfMoney = new park().refreshMoney();
		//System.out.println(amountOfMoney);
		JButton O2_ticketPad = new JButton("Ticket Padding");
		O2_ticketPad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//CAN ADD ONE SELECT PANEL TO SELECT HOW MANY TICKETS ARE ADDED.
				if(new OperationControl().feedTicket("100")){
					JOptionPane.showMessageDialog(null,"Tickets fed successfully.");
					reset();
				}else
					JOptionPane.showMessageDialog(null,"There are still plenty of tickets, \n      no need to insert!");
				
				//System.out.println(amountOfTicket+"111111111111111111");
			}
		});

		O2_pnl1_2.setBorder(BorderFactory.createEmptyBorder(100,30,100,30));
		O2_pnl1_2.setLayout(new GridLayout(2,1,20,30));
		O2_pnl1_2.add(O2_moneyCollect);
		O2_pnl1_2.add(O2_ticketPad);
		O2_pnl1.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		O2_pnl1.setLayout(new GridLayout(1,2,20,0));
		O2_pnl1_1.add(ticketSet());
		O2_pnl1.add(O2_pnl1_1);
		O2_pnl1.add(O2_pnl1_2);
		O2_pnl2.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		O2_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		O2_pnl2.add(O2_backbtn);
		O2_pnl0.setLayout(new GridLayout(2,1,0,0));
		O2_pnl0.add(O2_messagePanel1);
		O2_pnl0.add(O2_messagePanel2);
		pnlO2.setLayout(new BorderLayout());
		pnlO2.add(O2_pnl0,BorderLayout.NORTH);
		pnlO2.add(O2_pnl1);
		pnlO2.add(O2_pnl2,BorderLayout.SOUTH);
		
		
		MessagePanel O3_messagePanel1 = new MessagePanel("QM Parking System ");//panO3, panel1
 		O3_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
 		O3_messagePanel1.setCentered(true);
 		MessagePanel O3_messagePanel2 = new MessagePanel("(Operator) ");
		O3_messagePanel2.setFont(new Font("Times", Font.BOLD, 24));
		O3_messagePanel2.setCentered(true);
		MessagePanel O3_messagePanel3 = new MessagePanel("Up to 2 hours");
		O3_messagePanel3.setFont(new Font("Times", Font.PLAIN, 14));
		O3_messagePanel3.setCentered(true);
		MessagePanel O3_messagePanel4 = new MessagePanel("2 to 4 hours");
		O3_messagePanel4.setFont(new Font("Times", Font.PLAIN, 14));
		O3_messagePanel4.setCentered(true);
		MessagePanel O3_messagePanel5 = new MessagePanel("4 to 8 hours");
		O3_messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		O3_messagePanel5.setCentered(true);
		MessagePanel O3_messagePanel6 = new MessagePanel("8 to 12 hours");
		O3_messagePanel6.setFont(new Font("Times", Font.PLAIN, 14));
		O3_messagePanel6.setCentered(true);
		MessagePanel O3_messagePanel7 = new MessagePanel("12 to 24 hours");
		O3_messagePanel7.setFont(new Font("Times", Font.PLAIN, 14));
		O3_messagePanel7.setCentered(true);
		JPanel O3_pnl0 = new JPanel();
		JPanel O3_pnl1 = new JPanel();
		JPanel O3_pnl2 = new JPanel();
		JButton O3_donebtn = new JButton("DONE");
		O3_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OperationControl op = new OperationControl();
				String[] tariffs = new String[5]; 
				//if(O3_costtxf1.getText()!=null)
				tariffs[0] = (O3_costtxf1.getText());
				tariffs[1] = (O3_costtxf2.getText());
				tariffs[2] = (O3_costtxf3.getText());
				tariffs[3] = (O3_costtxf4.getText());
				tariffs[4] = (O3_costtxf5.getText());
				if(op.setTariff(tariffs)){
					reset();
					JOptionPane.showMessageDialog(null,"Tariff changed successfully.");	
				}
				else
					JOptionPane.showMessageDialog(null,"Sorry! Please enter all spaces with valid tariff(s).");	
					
				/*valid value: successfully changed
				invalid value: illegal input, double only*/
				
			}
		});
		JButton O3_backbtn = new JButton("BACK");
		O3_backbtn.addActionListener(new BackButtonListener());
		O3_pnl1.setBorder(BorderFactory.createEmptyBorder(60,30,60,30));
		O3_pnl1.setLayout(new GridLayout(5,2,0,0));
		O3_costtxf1.setHorizontalAlignment(JTextField.RIGHT);
		O3_costtxf2.setHorizontalAlignment(JTextField.RIGHT);
		O3_costtxf3.setHorizontalAlignment(JTextField.RIGHT);
		O3_costtxf4.setHorizontalAlignment(JTextField.RIGHT);
		O3_costtxf5.setHorizontalAlignment(JTextField.RIGHT);
		O3_costtxf1.setFont(new Font("Times", Font.PLAIN, 20));
		O3_costtxf2.setFont(new Font("Times", Font.PLAIN, 20));
		O3_costtxf3.setFont(new Font("Times", Font.PLAIN, 20));
		O3_costtxf4.setFont(new Font("Times", Font.PLAIN, 20));
		O3_costtxf5.setFont(new Font("Times", Font.PLAIN, 20));
		O3_pnl1.add(O3_messagePanel3);							
		O3_pnl1.add(O3_costtxf1);
		O3_pnl1.add(O3_messagePanel4);
		O3_pnl1.add(O3_costtxf2);
		O3_pnl1.add(O3_messagePanel5);
		O3_pnl1.add(O3_costtxf3);
		O3_pnl1.add(O3_messagePanel6);
		O3_pnl1.add(O3_costtxf4);
		O3_pnl1.add(O3_messagePanel7);
		O3_pnl1.add(O3_costtxf5);
		O3_pnl2.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		O3_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		O3_pnl2.add(O3_donebtn);
		O3_pnl2.add(O3_backbtn);
		O3_pnl0.setLayout(new GridLayout(2,1,0,0));
		O3_pnl0.add(O3_messagePanel1);
		O3_pnl0.add(O3_messagePanel2);
		pnlO3.setLayout(new BorderLayout());
		pnlO3.add(O3_pnl0,BorderLayout.NORTH);
		pnlO3.add(O3_pnl1);
		pnlO3.add(O3_pnl2,BorderLayout.SOUTH);
		
		
		MessagePanel O4_messagePanel1 = new MessagePanel("QM Parking System ");//panO4, panel1
		O4_messagePanel1.setFont(new Font("Times", Font.BOLD, 27));
		O4_messagePanel1.setCentered(true);
		MessagePanel O4_messagePanel2 = new MessagePanel("(Operator) ");
		O4_messagePanel2.setFont(new Font("Times", Font.BOLD, 24));
		O4_messagePanel2.setCentered(true);
		JPanel O4_pnl0 = new JPanel();
		JPanel O4_pnl1 = new JPanel();
		JPanel O4_pnl2 = new JPanel();
		JButton O4_donebtn = new JButton("DONE");
		JButton O4_backbtn = new JButton("BACK");
		O4_backbtn.addActionListener(new BackButtonListener());
		MessagePanel O4_messagePanel3 = new MessagePanel("Your Password:");
		O4_messagePanel3.setFont(new Font("Times", Font.PLAIN, 14));
		O4_messagePanel3.setCentered(true);
		MessagePanel O4_messagePanel4 = new MessagePanel("New Password:");
		O4_messagePanel4.setFont(new Font("Times", Font.PLAIN, 14));
		O4_messagePanel4.setCentered(true);
		MessagePanel O4_messagePanel5 = new MessagePanel("Re-enter New Password:");
		O4_messagePanel5.setFont(new Font("Times", Font.PLAIN, 14));
		O4_messagePanel5.setCentered(true);
		O4_pwtxf1.setEchoChar('*');							//cover the password 
		O4_pwtxf1.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		O4_pwtxf1.setFont(new Font("Times", Font.PLAIN, 20));	//set character size
		//JPasswordField O4_pwtxf2 = new JPasswordField();
		O4_pwtxf2.setEchoChar('*');
		O4_pwtxf2.setHorizontalAlignment(JTextField.RIGHT);
		O4_pwtxf2.setFont(new Font("Times", Font.PLAIN, 20));
		//JPasswordField O4_pwtxf3 = new JPasswordField();
		O4_pwtxf3.setEchoChar('*');
		O4_pwtxf3.setHorizontalAlignment(JTextField.RIGHT);
		O4_pwtxf3.setFont(new Font("Times", Font.PLAIN, 20));
		O4_donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(new String(O4_pwtxf2.getPassword()).equals(new String(O4_pwtxf3.getPassword()))){
					if(new OperationControl().setPassword(new String(O4_pwtxf1.getPassword()), new String(O4_pwtxf2.getPassword())))
						JOptionPane.showMessageDialog(null,"Password has been changed.");
					else
						JOptionPane.showMessageDialog(null,"Sorry! Wrong password.");
				}
				else
					JOptionPane.showMessageDialog(null,"Sorry! New passwords are different.");
			}
		});
		O4_backbtn.addActionListener(new BackButtonListener());
		O4_pnl1.setBorder(BorderFactory.createEmptyBorder(90,30,60,30));
		O4_pnl1.setLayout(new GridLayout(5,2,0,0));
		O4_pnl1.add(O4_messagePanel3);
		O4_pnl1.add(O4_pwtxf1);
		O4_pnl1.add(O4_messagePanel4);
		O4_pnl1.add(O4_pwtxf2);
		O4_pnl1.add(O4_messagePanel5);
		O4_pnl1.add(O4_pwtxf3);
		O4_pnl2.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		O4_pnl2.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		
		/* need a password varify system */
		
		O4_pnl2.add(O4_donebtn);
		O4_pnl2.add(O4_backbtn);
		O4_pnl0.setLayout(new GridLayout(2,1,0,0));
		O4_pnl0.add(O4_messagePanel1);
		O4_pnl0.add(O4_messagePanel2);
		pnlO4.setLayout(new BorderLayout());
		pnlO4.add(O4_pnl0,BorderLayout.NORTH);
		pnlO4.add(O4_pnl1);
		pnlO4.add(O4_pnl2,BorderLayout.SOUTH);
		
		
		tappedOperator.addTab("Park info", pnlO1);
		tappedOperator.addTab("Money/Ticket Manage", pnlO2);
		tappedOperator.addTab("Payment", pnlO3);
		tappedOperator.addTab("Password Setting", pnlO4);
		panelOperator.setLayout(new BorderLayout());
		panelOperator.add(tappedOperator, BorderLayout.CENTER);
	}
	
	/**
	 * genarate a JTextbox with scroll bar.
	 * @return scrollPane     The panel contains park information.
	 */
	public JScrollPane parkInfoSet(){
		JTextArea staffInfo = new JTextArea();
		staffInfo.setFont(new Font("Times", Font.PLAIN, 18));
		staffInfo.setLineWrap(true);
		staffInfo.setWrapStyleWord(true);
		staffInfo.setEditable(false);
		staffInfo.setText(new OperationControl().parkInfo());
		JScrollPane scrollPane = new JScrollPane(staffInfo);
		return scrollPane;
	}
	
	/**
	 * genarate a JPanel.
	 * @return panel     The panel contains ticket and money information.
	 */
	public JPanel ticketSet(){
		JPanel panel = new JPanel();
		amountOfMoney = new park().refreshMoney();
		amountOfTicket = new park().refreshTicket();
		MessagePanel messagePanel1 = new MessagePanel("The amount of money in");
		messagePanel1.setFont(new Font("Times", Font.PLAIN, 18));
		MessagePanel messagePanel2 = new MessagePanel("the system: "+amountOfMoney);
		messagePanel2.setFont(new Font("Times", Font.PLAIN, 20));
		MessagePanel messagePanel3 = new MessagePanel("The amount of tickets in");
		messagePanel3.setFont(new Font("Times", Font.PLAIN, 18));
		MessagePanel messagePanel4 = new MessagePanel("the system: "+amountOfTicket);
		messagePanel4.setFont(new Font("Times", Font.PLAIN, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(100,0,100,0));
		panel.setLayout(new GridLayout(4,1,20,0));
		panel.add(messagePanel1);
		panel.add(messagePanel2);
		panel.add(messagePanel3);
		panel.add(messagePanel4);
		return panel;
	}
	
	/**
	 * This method configurates the password interface for operator
	 */
	public void passwordSetting(){
		pnlP1.setBorder(BorderFactory.createEmptyBorder(160,100,150,100));
		MessagePanel msgpnl = new MessagePanel("Please enter your password:");
		msgpnl.setFont(new Font("Times", Font.PLAIN, 14));
		msgpnl.setXCoordinate(0);
		txf.setEchoChar('*');							//cover the password 
		txf.setHorizontalAlignment(JTextField.RIGHT);	//start at the right side
		txf.setFont(new Font("Times", Font.PLAIN, 20));	//set character size
		JButton donebtn = new JButton("DONE");
		JButton cancelbtn = new JButton("CANCEL");
		donebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(new park().checkPassword(new String(txf.getPassword()))){
					panelPassword.setVisible(false);
					panelOperator.setVisible(true);
					remove(panelPassword);
					add(panelOperator);
				}
				else{
					JOptionPane.showMessageDialog(null,"Sorry! Wrong password!\nPlease try again!");
				}
			}
		});
		cancelbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				panelPassword.setVisible(false);
				panelWelcome.setVisible(true);
				remove(panelPassword);
				add(panelWelcome);
			}
		});
		P1_pnl1.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		P1_pnl1.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		P1_pnl1.add(donebtn);
		P1_pnl1.add(cancelbtn);
		pnlP1.setLayout(new GridLayout(3,1,20,20));
		pnlP1.add(msgpnl);
		pnlP1.add(txf);
		pnlP1.add(P1_pnl1);
		
		panelPassword.setLayout(new BorderLayout());
		panelPassword.add(pnlP1, BorderLayout.CENTER);
	}
	
	/**
	 * Convert the input cost number to a string
	 * @param d    The set cost.
	 */
	public String parseCost(float d){
		if(d>1.0){
			return " "+d+" pounds";  // If cost>1, pounds.
		}
		else{
			return " "+d+" pound";
		}
	}
	
	/**
	 * This method ensures when "Enter System" button is clicked,
	 * the welcome panel will removed from the window
	 * and the corresponding panel will added.
	 * 
	 * "selection" signify which panel is selected.
	 */
	public void actionPerformed(ActionEvent arg0) {
		switch(selection){
		case 0:
			panelWelcome.setVisible(false);
			panelStaff.setVisible(true);
			remove(panelWelcome);
			add(panelStaff);
			break;
		case 1:
			panelWelcome.setVisible(false);
			panelCustomer.setVisible(true);
			remove(panelWelcome);
			add(panelCustomer);
			break;
		case 2:
			panelWelcome.setVisible(false);
			panelPassword.setVisible(true);
			remove(panelWelcome);
			add(panelPassword);
			break;
		}
		
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String args[]){
		JFrame f =new UserInterface();
		f.validate();
	}
	
	/**
	 * Inner class
	 * Listener of backbutton.
	 */
	private class BackButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			panelStaff.setVisible(false);
			panelCustomer.setVisible(false);
			panelOperator.setVisible(false);
			panelWelcome.setVisible(true);
			remove(panelStaff);
			remove(panelCustomer);
			remove(panelOperator);
			add(panelWelcome);
			txf.setText("");
		}
	}

	/**
	 * Inner class
	 * Time triggered
	 * Once an hour
	 * @author Zhe Zhao
	 */
	private class TimerListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calendar = new GregorianCalendar();
			if(calendar.get(Calendar.HOUR_OF_DAY) == 0){
				new park().dailySettlement();
			}
		}
	}
	
	/**
	 * Inner class
	 * Time triggered
	 * Once a day
	 * @author Zhe Zhao
	 */
	private class TimerListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calendar = new GregorianCalendar();
			if(calendar.get(Calendar.DAY_OF_MONTH) == 1){
				new park().monthlySettlement();
			}
		}
	}
}
