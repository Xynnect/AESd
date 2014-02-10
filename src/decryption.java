import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.org.jvnet.staxex.Base64Data;

public class decryption {
	
	private static String input;
	private static String inputString = "";
	private static String decryptedString;
	static String IV = "AAAAAAAAAAAAAAAA";
	static String encryptionKey = "1234123412341234";

	public static void main(String[] args) throws Exception {
		
		decryptedString = decrypt(readFromFile(), encryptionKey);
		writingToFile();

	}
	
	private static void writingToFile() {
		try {

			String content = decryptedString;
			File file = new File("C:/Users/Xelnect/Desktop/decrypted.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String readFromFile() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:/Users/Xelnect/Desktop/encrypted.txt"));
			while ((input = br.readLine()) != null) {
				inputString += input;
				System.out.println(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return inputString;
	}

	  public static String decrypt(String cipherText, String encryptionKey) throws Exception{
		    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		    return new String(cipher.doFinal(Base64.decode(cipherText)));
		  }
}
