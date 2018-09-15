public class PasswordManager {

	public void addPassword(String id, String username, String password, String encryptionPassword) {
		String decryptedFile = null;
		String encryptedFile = null;
		String s = FileUtil.readFile("passwords.txt", "Decryption");
		Decryption d = new Decryption();
		try {
			decryptedFile = d.decrypt("s", encryptionPassword);
		} catch (Exception e) {
			System.out.println(e);
		}
		decryptedFile += "\n" + id + ":= " + username + ": " + password;
		Encryption e = new Encryption();
		try {
			encryptedFile = e.encrypt(decryptedFile, encryptionPassword);
		} catch (Exception a) {
			System.out.println(a);
		}
		FileUtil.writeFile(encryptedFile, "passwords.txt");
	}

	public String getPassword(String id, String encryptionPassword) {
		String decryptedFile = null;
		String s = FileUtil.readFile("passwords.txt", "Decryption");
		Decryption d = new Decryption();
		try {
			decryptedFile = d.decrypt("s", encryptionPassword);
		} catch (Exception e) {
			System.out.println(e);
		}
		String[] entries = decryptedFile.split("\n");
		for (String entry : entries) {
			if(entry.substring(0, entry.indexOf(":= ")).equals(id)) {
				return entry.substring(entry.indexOf(":=")+ 3);
			}
		}
		return "That ID does not exist";
	}

	public String[] getAllIds() {

		return null;
	}
}
