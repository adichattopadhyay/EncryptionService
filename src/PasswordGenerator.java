import java.util.HashSet;
import java.util.Random;

public class PasswordGenerator {

	public static String generatePassword(int passwordLength, String seed) {
		String salt = "123";
		Random rand = new Random();
		int seed2 = rand.nextInt(Integer.MAX_VALUE);
		String data = seed + seed2 + System.currentTimeMillis();
		try {
			Encryption e = new Encryption();
			String encryptedText = e.encrypt(salt + data, seed);
			return encryptedText.substring(0, Math.min(passwordLength, encryptedText.length()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return data;
	}

	public static int passwordStregnth(String password) {
		int totalScore = 0;
		boolean hasLowercase = false;
		boolean hasUppercase = false;
		boolean hasSpecialChar = false;
		HashSet<Character> setChar = new HashSet<>();
		for (int i = 0; i < password.length(); i++) {
			char charI = password.charAt(i);
			setChar.add(password.charAt(i));
			if (password.charAt(i) < 65 || (charI > 90 && charI < 97)) {
				hasSpecialChar = true;
			}
			if (password.charAt(i) > 64 && password.charAt(i) < 91) {
				hasUppercase = true;
			}
			if (password.charAt(i) > 96 && password.charAt(i) < 123) {
				hasLowercase = true;
			}
		}
		int numUnique = setChar.size();
		if (numUnique < password.length() / 2) {
			totalScore -= (password.length() - numUnique);
		}
		totalScore += password.length() * 2;

		return 0;
	}

	public static void main(String[] args) {
		System.out.print(PasswordGenerator.generatePassword(23, "Aditya"));
	}

}
