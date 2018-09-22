import java.util.Scanner;

public class Main {
	// Variables
	static int ENCRYPTION = 1;
	static int DECRYPTION = 2;
	static boolean file = false;

	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in); // Used for user input
//		System.out.println("Welcome to my encryption/decryption service!"); // Welcome Message
//		while (true) {
//			int mode = 0; 
//			while (mode != ENCRYPTION && mode != DECRYPTION) { //used to figure out if user wants to encrypt or decrypt, while repeating itself if the user puts incorrect input
//				try {
//					System.out.println("Enter d for Decryption, e for encryption");
//					String a = scan.nextLine(); //used to scan next line
//					if (a.equals("d")) {
//						mode = DECRYPTION; //Things will be decrypted
//					} else if (a.equals("e")) {
//						mode = ENCRYPTION; //Things will be encrypted
//					} else {
//						System.out.println("Invalid Input");
//					}
//
//				} catch (Exception e) {
//					System.out.println("Invalid Input");
//				}
//			}
//			try {
//				System.out.println("Would you like to encrypt/decrypt a file? (y/n)"); //allows user to encrypt a file
//				String c = scan.nextLine();
//				if (c.equals("n")) {
//					continue; //go on, disregard this statement
//				} else if (c.equals("y")) {
//					file = true;
//				} else {
//					System.out.print("Invalid Input");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				if (file == false) {
//					System.out.println("Please type the text here: ");
//					String text = scan.nextLine();
//					System.out.println("Please type the password here: ");
//					String password = scan.nextLine();
//					if (mode == ENCRYPTION) {
//						Encryption e = new Encryption();
//						String encryptedText = e.encrypt(text, password); //uses my encryption function
//						System.out.println(encryptedText);
//					} else if (mode == DECRYPTION) {
//						Decryption d = new Decryption();
//						String decryptedText = d.decrypt(text, password); //uses my decryption function
//						System.out.println(decryptedText);
//					}
//				} else if (file == true) {
//					System.out.println("Please type the input path: ");
//					String inputPath = scan.nextLine();
//					System.out.println("Please type the output path: ");
//					String outputPath = scan.nextLine();
//					System.out.print("Please type the password: ");
//					String password = scan.nextLine();
//					if (mode == ENCRYPTION) {
//						Encryption e = new Encryption();
//						e.encryptFile(inputPath, outputPath, password); //uses file encryption
//						System.out.println("The file has been encrypted.");
//					} else if (mode == DECRYPTION) {
//						Decryption d = new Decryption();
//						d.decryptFile(inputPath, outputPath, password); // uses file decryption
//						System.out.println("The file has been decrypted.");
//					}
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("Thank you for using this service!");
//			System.out.println("To continue using it, press y, to leave, press q."); //allows user to reuse program
//			String b = scan.nextLine();
//			if (b.equals("q")) {
//				break;
//			} else if (b.equals("y")) {
//				continue;
//			} else {
//				System.out.println("Invalid input, so the program is quitting.");
//				break;
//			}
//		}
//		scan.close();
		Gui g = new Gui();
		g.setVisible(true);
	}

}
