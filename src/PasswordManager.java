import java.io.File;
import java.io.IOException;

public class PasswordManager {

	public static void addPassword(String id, String username, String password, String encryptionPassword) { 
		String decryptedFile = null;
		String encryptedFile = null;
		File f = new File("passwords.txt");
		if(!(f.exists() && !f.isDirectory())) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		String s = FileUtil.readFile("passwords.txt", "Decryption"); //reads all the passwords
		Decryption d = new Decryption();
		try {
			decryptedFile = d.decrypt("s", encryptionPassword); //decrypts the passwords
		} catch (Exception e) {
			System.out.println(e);
		}
		decryptedFile += "\n" + id + ":= " + username + ": " + password; //adds new passwords
		Encryption e = new Encryption();
		try {
			encryptedFile = e.encrypt(decryptedFile, encryptionPassword); //encrypts file
		} catch (Exception a) {
			System.out.println(a);
		}
		FileUtil.writeFile(encryptedFile, "passwords.txt"); //rewrite the file
	}

	public static String getPassword(String id, String encryptionPassword) {
		String decryptedFile = null;
		File f = new File("passwords.txt");
		if(!(f.exists() && !f.isDirectory())) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		String s = FileUtil.readFile("passwords.txt", "Decryption");
		Decryption d = new Decryption();
		try {
			decryptedFile = d.decrypt("s", encryptionPassword);
		} catch (Exception e) {
			System.out.println(e);
		}
		String[] entries = decryptedFile.split("\n"); //splits file for finding passwords
		for (String entry : entries) {
			if(entry.substring(0, entry.indexOf(":= ")).equals(id)) { //looks for id
				return entry.substring(entry.indexOf(":=")+ 3); //returns the passwords
			}
		}
		return "That ID does not exist";
	}
}
