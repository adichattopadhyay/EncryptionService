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

	public static int editDist(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		// Create a table to store results of subproblems
		int dp[][] = new int[m + 1][n + 1];

		// Fill d[][] in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0)
					dp[i][j] = j;
				else if (j == 0)
					dp[i][j] = i;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];

				else
					dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
			}
		}

		return dp[m][n];
	}

	public static String passwordStregnth(String password) {
		String s = FileUtil.readFile("passwordscommon.txt", "Encryption");
		String[] commonPasswordList = s.split("\n");
		double totalScore = 0;
		int hasLowercase = 0;
		int hasUppercase = 0;
		int hasSpecialChar = 0;
		HashSet<Character> setChar = new HashSet<>();
		for (int i = 0; i < password.length(); i++) {
			char charI = password.charAt(i);
			setChar.add(password.charAt(i));
			if (password.charAt(i) < 65 || (charI > 90 && charI < 97)) {
				hasSpecialChar = 1;
			}
			if (password.charAt(i) > 64 && password.charAt(i) < 91) {
				hasUppercase = 1;
			}
			if (password.charAt(i) > 96 && password.charAt(i) < 123) {
				hasLowercase = 1;
			}
		}
		int numUnique = setChar.size();
		if (numUnique < password.length() / 2) {
			totalScore -= (password.length() - numUnique);
		}
		totalScore += Math.max(0.7, hasSpecialChar + hasUppercase + hasLowercase) * password.length();
		for (String commonp : commonPasswordList) {
			if (editDist(commonp, password) < 4) {
				totalScore -= (30 / (0.5 + editDist(commonp, password)));
				break;
			}
		}
		if (totalScore < 20) {
			return "Weak";
		} else if (totalScore < 50) {
			return "Medium";
		} else {
			return "Strong";
		}
	}

	public static void main(String[] args) {
		String passw = PasswordGenerator.generatePassword(100, "Aditya");
		System.out.println(passw);
		System.out.println(PasswordGenerator.passwordStregnth(""));
	}

}
