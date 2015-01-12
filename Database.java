
import java.io.*;
import java.util.ArrayList;

/**
 * @author You Wu & Haoxuan Wang
 * @version 1.0
 */
public class Database {
	static String[] failed={"@","@","@"};
	//////file pointers//////
	private ReaderBuffer readReg;
	private ReaderBuffer readStaffRec;
	private ReaderBuffer readStaffIn;
	private ReaderBuffer readCusIn;
	private ReaderBuffer readProfile;
	//read buffers/////
	//private BufferedReader readRegBf;
	//private BufferedReader readStaffRecBf;
	//private BufferedReader readStaffInBf;
	//private BufferedReader readCusInBf;
	//private BufferedReader readProfileBf;
	//private BufferedReader buffReg;
	private BufferedWriter writeReg;
	private BufferedWriter writeStaffRec;
	private BufferedWriter writeStaffIn;
	private BufferedWriter writeCusIn;
	private BufferedWriter writeProfile;
	///////construct an empty database/////////
	
	
	/**
	 * This is the constructor of the class database with no parameters,it initialize all the file writers and file readers.
	 */
	public Database(){
		reset();
	}

	/**
	 * Check if all relevant files are existing.
	 * @param Filename the name of a database file
	 * @return the readerbuffer that holds the file reader and the buffered reader of the file operation
	 */
	private ReaderBuffer initialize(String Filename){
		ReaderBuffer temp = new ReaderBuffer(Filename);
		return temp;
	}
	
	/**
	 * This method generated a profile document with default value
	 */
	private void profile(){
		
	}

	/**
	 * count lines in a general text file
	 * @param filein the file being checked
	 * @return the number of lines in the file
	 */
	private int countln(ReaderBuffer filein){
		//System.out.println(""+filein.toString());
		BufferedReader fileinbuf= new BufferedReader(filein.Buffer);
		//System.out.println(""+filein.toString()+"||"+fileinbuf.toString());
		int count=0;
		try {
			while(fileinbuf.readLine()!=null){
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
		
	}

	public int countRec(){
		return countln(readStaffRec);
	}

	/**
	 * Count the total number of staffs in the register chart.
	 * @return the total number of staffs.
	 */
	public int countreg(){
		return (countln(readReg));
	}
	
	/**
	 * Count the total number of users in the park
	 * @return the total number of users including both staff& customers.
	 */
	public int countusers(){
		//System.out.println(readCusIn.toString());
		return (countln(readCusIn)+countln(readStaffIn));
	}

	/**
	 * Read one line from a text file
	 * @param filein the file read
	 * @return a line of text read from the file
	 */
	private String readterm(BufferedReader filein){
		try {
			return filein.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	///read a entry from database/////
	/**
	 * Read a term from a database file
	 * @param file the database file read
	 * @return the string of text that is read from the file
	 */
	public String readterm(String file){
		String str=null;
		int fileno=0;
		fileno=file.length();
		switch(fileno){
		case 3:	
		str= readterm(readReg.Buffer);
		break;	
		case 8:
		str= readterm(readStaffRec.Buffer);
		break;
		case 7:
		if (file=="StaffIn")
		str= readterm(readStaffIn.Buffer);
		else
		str= readterm(readProfile.Buffer);
		break;
		case 5:
		str= readterm(readCusIn.Buffer);
		break;
		default:
		str=null;
		}
		return str;
	}

	/**
	 * find an entry from a file with its key field
	 * @param chart the file being checked
	 * @param field the key field that is used to identify an entry
	 * @return
	 */
	public Entry find(String chart,long field){
		Entry found = null;
		ReaderBuffer opFile;
		switch(chart.length()){
		case 3:
			opFile=readReg;
			readReg=initialize(chart);
			found=new RegisterEntry();
			break;
		case 8:
			readStaffRec=initialize(chart);
			opFile=readStaffRec;
			found=new StaffRecEntry();
			break;
		case 7:
			readStaffIn=initialize(chart);
			opFile=readStaffIn;
			found=new StaffInEntry();
			break;
		case 5:
			readCusIn=initialize(chart);
			opFile=readCusIn;
			found=new CusInEntry();
			break;
		default:
			opFile=null;
		break;	
		}
		opFile=initialize(chart);
		int length=countln(opFile);
	//	System.out.println("File depth "+length);
		opFile=initialize(chart);				
		boolean flag=false;
		int count=1;
		while(count<=length)
		{
			found.Build(readterm(chart));
			count++;

			if(chart=="CusIn"){
				//System.out.println("Field="+field+"; current="+found.gettimein());
				if(found.gettimein()==field){
					flag=true;
					break;}
			}
			else
				//System.out.println("Field="+field+"; current="+found.getID());
			if(Long.parseLong(found.getID()+"")==field){

				flag=true;
				break;}
		}
		if(flag==false)return new Entry();
		else return found;
	}
	
	/**
	 * Check whether an entry with its key field assigned with a specific value is already in the database chart.
	 * @param chart the chart being checked
	 * @param field the key field that can identify the entries
	 * @return
	 */
	public boolean CheckExist(String chart,long field){
		this.reset();
		boolean ret=false;
		if(Long.parseLong((find (chart,field).getID()+""))==field)
			ret=true;
		if(find (chart,field).gettimein()==field)
			ret=true;
		return ret;
	}

	public boolean CheckExistByCarNum(String chart,String CarNum){
		//System.out.println("Start checking exist by car number..."+CarNum);
		reset();
		ReaderBuffer temp;
		if(chart=="CusIn"){
		String str=null;
		do{
		str=readterm(readCusIn.Buffer);
		if(str==null)
			break;
		else
		{
			if(CarNum.equals(new CusInEntry(str).getCar()))
				return true;
		}
		}
		while(str!=null);
		return false;
		}

		if(chart=="StaffIn"){
		String str=null;
		do{
		str=readterm(readStaffIn.Buffer);
		if(str==null)
			break;
		else
		{
			if(CarNum.equals(new StaffInEntry(str).getCar()))
				return true;
		}
		}
		while(str!=null);
		return false;
		}
		
		return false;
	}
	
	/**
	 * add a line to a text file
	 * @param filewrite is the file being written
	 * @param info the string that will be written to the file
	 */
	public void writeterm(BufferedWriter filewrite, String info){
	try {
		//filewrite.newLine();
		//filewrite.newLine();
		//System.out.println("infor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+info);
		filewrite.write(info);	
		filewrite.newLine();
		filewrite.flush();
		
		//filewrite.newLine();
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
	
	//add a entry to database//
	/**
	 * add a new term to a database file
	 * @param file the database file that is written to
	 * @param term the string of entry that will be added to the database file
	 */
	public void addterm(String file,String term){
/*	try{
		FileWriter write=new FileWriter(file,true);
	}catch(IOException e){
		e.printStackTrace();
	}*/
	int fileno=0;//file name length
	fileno=file.length();
	BufferedWriter tempProfile = null;
	switch(fileno){
	case 3:	
	writeterm(writeReg,term);
	break;	
	case 8:
	writeterm(writeStaffRec,term);
	break;
	case 7:
	if(file.equals("StaffIn"))
	writeterm(writeStaffIn,term);
	else{
		try {
			tempProfile=new BufferedWriter(new FileWriter("Profile",false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	writeterm(tempProfile,term);	
	}
	break;
	case 5:
	writeterm(writeCusIn,term);
	break;
	default:
	break;
	}
}

	/**
	 * Add a new entry of data into the database. If it already exists then add will return false an quit.
	 * @param newEntry the entry of data to be add to database
	 * @return the boolean value whether the entry is already in the database
	 */
	public boolean add(Entry newEntry){

		if(newEntry.getfile()!="CusIn"){
			if(!CheckExist(newEntry.getfile(),newEntry.getID()))
				{
				//System.out.println(newEntry.getfile()+" add:file");
				//System.out.println(newEntry.toString()+" add:info");

				addterm(newEntry.getfile(),newEntry.toString());
				return true;
				}else return false;
			}
		else{
			if(!CheckExist(newEntry.getfile(),newEntry.gettimein()))
			{
				addterm(newEntry.getfile(),newEntry.toString());
				return true;
				}else return false;
			}
	
}

	//remove one line in file//
	/**
	 * delete a line of string from a text file
	 * @param the file that the string is delete from
	 * @param the target string that will be deleted
	 */
	private void deleteterm(String filename,String delTerm){
	//File file=new File(filename);
	//ReaderBuffer reader=initialize(filename);
	ReaderBuffer readerBuf=initialize(filename);
	//BufferedWriter writerBuf=init(filename);

	int lines = countln(readerBuf);
	readerBuf = initialize(filename);
	ArrayList<String> buffer = new ArrayList<String>();
	int count=0;
	//System.out.println("In deleteterm function:\n"+readterm(filedelR));
	for(count=0;count<lines;count++)
		buffer.add(readterm(readerBuf.Buffer));
	/*System.out.println("Try to delete 100....");
	System.out.println("In deleteterm function:\n"+buffer.isEmpty());
	System.out.println("Data are:\n");
	for(count=0;count<buffer.size();count++)
		System.out.println(buffer.get(count));
	System.out.println("buffer size is "+buffer.size());*/
	if(buffer.remove(delTerm)==true)
		////System.out.println("Removed\n");
	for(count=0;count<buffer.size();count++)
		////System.out.println(buffer.get(count));
		
	try{
	FileWriter writer=new FileWriter(filename,false);
	BufferedWriter writerBuf=new BufferedWriter(writer); 
		for( count=0;count<buffer.size();count++){
				writeterm(writerBuf,buffer.get(count));
				//writerBuf.write(buffer.get(count));
				//writerBuf.flush();
				//writerBuf.newLine();
				
		}
		
	}catch(IOException e){}
		
	//readerBuf=initialize(filename);
	this.reset();
}

	//delete a entery from database//
	/**
	 * delete an entry of data from database
	 * @param file the database file that is being deleted from
	 * @param term the entry that will be deleted from the database
	 */
	private void delterm(String file, Entry term){
	
	int fileno=0;//file name length
	fileno=file.length();
	switch(fileno){
	case 3:	
	deleteterm("Reg",term.toString());
	break;
	case 5:
		deleteterm("CusIn",term.toString());
		break;
	case 8:
		deleteterm("StaffRec",term.toString());
		break;
	case 7:
		deleteterm("StaffIn",term.toString());
		break;

	default:
	break;
	}
}	

	/**
	 * delete an entry from the database with the key filed of the entry, this will automatically find the entry with its key field
	 * @param chart the database file that will be deleted from
	 * @param field the key field that will be used to identify the right entry
	 */
	public void delete(String chart, long field){
	Entry del=find(chart,field);
	delterm(chart,del);
}

	/**
	 * Reset all the file readers writers buffers to their initial values
	 */
	public void reset(){
	readReg=initialize("Reg");
	readStaffRec=initialize("StaffRec");
	readStaffIn=initialize("StaffIn");
	readCusIn=initialize("CusIn");
	readProfile=initialize("Profile");
	try{
		writeReg=new BufferedWriter(new FileWriter("Reg",true));
		writeStaffRec=new BufferedWriter(new FileWriter("StaffRec",true));
		writeStaffIn=new BufferedWriter(new FileWriter("StaffIn",true));
		writeCusIn=new BufferedWriter(new FileWriter("CusIn",true));
		writeProfile=new BufferedWriter(new FileWriter("Profile",true));
	}catch(IOException e){
		e.printStackTrace();
	}
	profile();/////initialize profile with default value
}

	public void cleanFile(String file) 
    {     
		String chart="StaffRec";
		int count =this.countln(readStaffRec);
		this.reset();
		while(count>-1){
		////System.out.println("at "+count+"");
		long mark=new StaffRecEntry(this.readterm(file)).getID();
		this.delete(chart,mark);
		count--;
		}
    }

}

