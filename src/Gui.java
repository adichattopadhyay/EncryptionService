import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame {
	static int mode = 0;
	static int ENCRYPTION = 1;
	static int DECRYPTION = 2;
	static boolean fileCheck = false;
	static boolean done = false;
	static boolean continueOn = true;

	private JButton decrypt = new JButton("Decrypt");
	private JButton encrypt = new JButton("Encrypt");
	private JButton decryptButton = new JButton("Decrypt Text");
	private JButton encryptButton = new JButton("Encrypt Text");
	private JButton passwordGenerator = new JButton("Password Generator");
	private JButton strengthButton = new JButton("Find Strength");
	private JButton generateButton = new JButton("Generate");
	private JButton passwordManager = new JButton("Password Manager");
	private JButton passwordNew = new JButton("Generate New Password");
	private JButton passwordStrength = new JButton("Password Strength");
	private JButton encryptFile = new JButton("Encrypt File");
	private JButton encryptText = new JButton("Encrypt Text");
	private JButton decryptFile = new JButton("Decrypt File");
	private JButton decryptText = new JButton("Decrypt Text");

	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JTextField prompt = new JTextField();
	private JTextField txtC = new JTextField();
	private JTextField error = new JTextField();
	private JTextField txtD = new JTextField();
	private JTextField txtE = new JTextField();

	private JLabel lblA = new JLabel("Text :");
	private JLabel lblB = new JLabel("Password :");
	private JLabel lblC = new JLabel("Input Path :");
	private JLabel lblD = new JLabel("Output Path :");
	private JLabel lblE = new JLabel("ID: ");
	private JLabel lblF = new JLabel("Password Len:");
	private JLabel lblG = new JLabel("Seed: ");

	public Gui() {
		setTitle("Encryption Service");
		setSize(900, 500);
		setLocation(new Point(300, 200));
		setLayout(null);
		setResizable(false);

		initComponent();
		initEvent();
	}

	private void initComponent() {
		encrypt.setBounds(360, 150, 200, 40);
		decrypt.setBounds(360, 200, 200, 40);
		passwordStrength.setBounds(360, 150, 200, 40);
		passwordNew.setBounds(360, 200, 200, 40);
		passwordGenerator.setBounds(360, 250, 200, 40);
		passwordManager.setBounds(360, 300, 200, 40);
		encryptButton.setBounds(360, 400, 200, 40);
		decryptButton.setBounds(360, 400, 200, 40);
		strengthButton.setBounds(360, 400, 200, 40);
		encryptFile.setBounds(360, 150, 200, 40);
		encryptText.setBounds(360, 200, 200, 40);
		decryptFile.setBounds(360, 150, 200, 40);
		decryptText.setBounds(360, 200, 200, 40);
		generateButton.setBounds(360, 400, 200, 40);

		txtA.setBounds(110, 10, 700, 20);
		txtB.setBounds(110, 35, 700, 20);
		prompt.setBounds(330, 90, 240, 30);
		txtC.setBounds(110, 10, 700, 20);
		txtD.setBounds(110, 65, 700, 20);
		txtE.setBounds(110, 90, 700, 20);
		error.setBounds(300, 350, 300, 30);

		lblA.setBounds(20, 10, 100, 20);
		lblB.setBounds(20, 35, 100, 20);
		lblD.setBounds(20, 65, 100, 20);
		lblC.setBounds(20, 10, 100, 20);
		lblE.setBounds(20, 90, 100, 20);
		lblF.setBounds(20, 10, 100, 20);
		lblG.setBounds(20, 35, 100, 20);

		add(encrypt);
		add(decrypt);
		add(passwordManager);
		add(passwordGenerator);

		prompt.setEditable(false);
		error.setEditable(false);

		txtA.setVisible(true);
		txtB.setVisible(true);
		txtC.setVisible(true);
		txtD.setVisible(true);
		lblA.setVisible(true);
		lblB.setVisible(true);
		lblC.setVisible(true);
		lblD.setVisible(true);
	}

	private void initEvent() {

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		passwordGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordGen(e);
			}
		});
		
		passwordNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makePasswordGen(e);
			}
		});
		passwordStrength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordStrengthButton(e);
			}
		});

		passwordManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordMan(e);
			}
		});

		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performDecryption(e);
			}
		});

		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performEncryption(e);
			}
		});

		encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = ENCRYPTION;
				askIfFile(e);
			}
		});

		decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = DECRYPTION;
				askIfFile(e);
			}
		});
		encryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = true;
				createEncryptButton(e);
			}
		});
		encryptText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = false;
				createEncryptButton(e);
			}
		});
		decryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = true;
				createDecryptButton(e);
			}
		});
		decryptText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = false;
				createDecryptButton(e);
			}
		});
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performGeneration(e);
			}
		});
		strengthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordStrengthCheck(e);
			}
		});
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
	
	private void passwordStrengthButton(ActionEvent evt) {
		add(error);
		error.setText("Type in the password.");
		add(lblA);
		lblA.setText("Password:");
		passwordStrength.setVisible(false);
		passwordNew.setVisible(false);
		add(txtA);
		add(strengthButton);
		repaint();
	}
	
	private void makePasswordGen(ActionEvent evt) {
		add(error);
		error.setText("Type in the password length(number) and seed.");
		add(lblF);
		add(lblG);
		passwordStrength.setVisible(false);
		passwordNew.setVisible(false);
		add(txtA);
		add(txtB);
		add(generateButton);
		repaint();
	}
	
	private void performGeneration(ActionEvent evt) {
		continueOn = true;
		int lenPassword = 0;
		String passwordLen = txtA.getText();
		String seed = txtB.getText();
		if (seed == "" || passwordLen == "") {
			error.setText("You something wrong");
		}
		else {
			try {
				lenPassword = Integer.parseInt(passwordLen);
			}
			catch(Exception e) {
				error.setText("You did something wrong");
				continueOn = false;
			}
			if (continueOn == true) {
				String newPassword = PasswordGenerator.generatePassword(lenPassword, seed);
				error.setText(newPassword);
			}
		}
		repaint();
	}
	
	private void passwordStrengthCheck(ActionEvent evt) {
		String password = txtA.getText();
		String score = PasswordGenerator.passwordStregnth(password);
		error.setText(score);
		repaint();
	}

	private void passwordGen(ActionEvent evt) {
		encrypt.setVisible(false);
		decrypt.setVisible(false);
		passwordGenerator.setVisible(false);
		passwordManager.setVisible(false);
		add(passwordStrength);
		add(passwordNew);
		repaint();
	}

	private void passwordMan(ActionEvent evt) {
		encrypt.setVisible(false);
		decrypt.setVisible(false);
		passwordGenerator.setVisible(false);
		passwordManager.setVisible(false);
	}

	private void createEncryptButton(ActionEvent evt) {
		prompt.setVisible(false);
		encryptFile.setVisible(false);
		encryptText.setVisible(false);
		if (fileCheck == false) {
			add(txtA);
			add(txtB);
			add(lblA);
			add(lblB);
			add(error);
		} else {
			add(txtC);
			add(txtB);
			add(txtD);
			add(lblD);
			add(lblB);
			add(lblC);
			add(error);

		}

		error.setText("Type in the inputs, then hit the encrypt button.");
		add(encryptButton);
		repaint();
	}

	private void createDecryptButton(ActionEvent evt) {
		prompt.setVisible(false);
		decryptFile.setVisible(false);
		decryptText.setVisible(false);
		if (fileCheck == false) {
			add(txtA);
			add(txtB);
			add(lblA);
			add(lblB);
			add(error);
		} else {
			add(txtC);
			add(txtB);
			add(txtD);
			add(lblD);
			add(lblB);
			add(lblC);
			add(error);
		}
		error.setText("Type in the inputs, then hit the decrypt button.");
		add(decryptButton);
		repaint();
	}

	private void askIfFile(ActionEvent evt) {
		encrypt.setVisible(false);
		decrypt.setVisible(false);
		passwordGenerator.setVisible(false);
		passwordManager.setVisible(false);
		if (mode == 1) {
			add(encryptFile);
			add(encryptText);
		} else if (mode == 2) {
			add(decryptFile);
			add(decryptText);
		}
		repaint();
	}

	private void performEncryption(ActionEvent evt) {

		while (done == false) {
			if (fileCheck == false) {
				String text = txtA.getText();
				String password = txtB.getText();
				if (text == "" || password == "") {
					error.setText("Type in the inputs, then hit the encrypt button.");
				} else {
					try {
						Encryption e = new Encryption();
						String encryptedText = e.encrypt(text, password);
						error.setText(encryptedText);
						done = true;
					} catch (Exception e) {
						error.setText("Something went wrong, try again.");
						done = false;
					}
				}
			} else if (fileCheck == true) {
				String inputPath = txtC.getText();
				String outputPath = txtD.getText();
				String password = txtB.getText();
				try {
					Encryption e = new Encryption();
					e.encryptFile(inputPath, outputPath, password);
					error.setText("The file has been encrypted.");
					done = true;
				} catch (Exception e) {
					error.setText("Something went wrong, try again.");
					done = false;
				}

			}
			repaint();
		}
	}

	private void performDecryption(ActionEvent evt) {

		while (done == false) {
			if (fileCheck == false) {
				String text = txtA.getText();
				String password = txtB.getText();
				if (text == "" || password == "") {
					error.setText("Type in the inputs, then hit the encrypt button.");
				} else {
					try {
						Decryption d = new Decryption();
						String decryptedText = d.decrypt(text, password);
						error.setText(decryptedText);
						done = true;
					} catch (Exception e) {
						error.setText("Something went wrong, try again.");
						done = false;
					}
				}
			} else if (fileCheck == true) {
				String inputPath = txtC.getText();
				String outputPath = txtD.getText();
				String password = txtB.getText();
				try {
					Decryption d = new Decryption();
					d.decryptFile(inputPath, outputPath, password);
					error.setText("The file has been decrypted");
					done = true;
				} catch (Exception e) {
					error.setText("Something went wrong, try again.");
					done = false;
				}
			}
			repaint();
		}
	}

}
