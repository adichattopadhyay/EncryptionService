import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Test {
	private static final String ALGO = "AES";
	private static byte[] keyValue;

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

	/**
	 * Encrypt a string with AES algorithm.
	 *
	 * @param data
	 *            is a string
	 * @return the encrypted string
	 */
	public static String encrypt(String data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encVal);
	}

	/**
	 * Decrypt a string with AES algorithm.
	 *
	 * @param encryptedData
	 *            is a string
	 * @return the decrypted string
	 */
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);
	}

	/**
	 * Generate a new encryption key.
	 */
	private static Key generateKey() throws Exception {
		getKey("Aditya");
		return new SecretKeySpec(keyValue, ALGO);
	}
	
	public static void main(String[] args) {
		String text = "My name is the lord when I lay my vengeance upon thee.";
		try {
			String encrypted = encrypt(text);
			String decrypted = decrypt(encrypted);
			System.out.println(encrypted);
			System.out.println(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
