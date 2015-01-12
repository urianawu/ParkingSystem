
  
	  
import java.security.MessageDigest;  
	  
/**  
 * Class that encrypt the password and check the password 
 */  
public class Encryption {
      
    //map the number to characters in hex  
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",  
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};  
      
    /** * Encrypt inputString     */  
    public static String generatePassword(String inputString){  
        return encodeByMD5(inputString);  
    }  
      
    /** 
     * check whether the input password is right
     * @param password    the encrypted pass word 
     * @param inputString    the input string 
     * @return    the result of checking
     */  
    public static boolean validatePassword(String password, String inputString){  
        if(password.equals(encodeByMD5(inputString))){  

            return true;  
        } else{  
            return false;  
        }  
    }  

    /**  Encrypt the string with MD5     */  
    private static String encodeByMD5(String originString){  
        if (originString != null){  
            try{  
                //create a message digest  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                //refresh the digest with the byte vector
                byte[] results = md.digest(originString.getBytes());  
                //return the string transformed from byte vector
                String resultString = byteArrayToHexString(results);  
                return resultString.toUpperCase();  
            } catch(Exception ex){  
                ex.printStackTrace();  
            }  
        }  
        return null;  
    }  
      
    /**  
     * parse a byte vector to a hex string 
     * @param   b  byte  vector
     * @return  a hex string 
     */  
    private static String byteArrayToHexString(byte[] b){  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++){  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    }  
      
    /** parse a normal string to a string in hex     */  
    private static String byteToHexString(byte b){  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
}  

