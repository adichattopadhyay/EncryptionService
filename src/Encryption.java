import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encryption {
	private static byte[] keyValue;
	private static final String ALGO = "AES";

	public static byte[] getKey(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] inputBytes = password.getBytes();
			byte[] key = digest.digest(inputBytes);
			keyValue = Arrays.copyOfRange(key, 0, 16);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
	}

	private static Key generateKey(String password) throws Exception {
		getKey(password);
		return new SecretKeySpec(keyValue, ALGO);
	}

	public static String encrypt(String data, String password) throws Exception {
		Key key = generateKey(password);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encVal);
	}

	public void encryptFile(String inputPath, String outputPath, String password) {
		try {
			String text = FileUtil.readFile(inputPath, "Encryption");
			String encryptedText = encrypt(text, password);
			FileUtil.writeFile(encryptedText, outputPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
