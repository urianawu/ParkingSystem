
import java.io.*;

public class ReaderBuffer {
	FileReader reader;
	BufferedReader Buffer;
	public ReaderBuffer(String filename) {
		java.io.File Reg= new java.io.File(filename);
		if(Reg.exists());
		else
			try {
				Reg.createNewFile();
			} catch (IOException e){}
		try {
			reader=new FileReader(Reg);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Buffer=new BufferedReader(reader);
	}

}
