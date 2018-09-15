
public class Temp {
	public static void main(String args[]) {
		Encryption e = new Encryption();
		e.encryptFile("Text/Test.txt", "Text/EncryptedHarryPotter3.txt", "Aditya");
//		System.out.println(readFile);
//		FileUtil.writeFile(readFile, "Text/Encry[");

		Decryption d = new Decryption();
		d.decryptFile("Text/EncryptedHarryPotter3.txt", "Text/DecryptedHarryPotter3.txt", "Aditya");

	}
}
