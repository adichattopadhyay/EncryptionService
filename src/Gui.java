import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame {
	static int mode = 0;
	static int ENCRYPTION = 1;
	static int DECRYPTION = 2;
	static boolean fileCheck = false;
	static boolean done = false;

	private JButton decrypt = new JButton("Decrypt");
	private JButton encrypt = new JButton("Encrypt");
	private JButton decryptButton = new JButton("Decrypt Text");
	private JButton encryptButton = new JButton("Encrypt Text");
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
		encrypt.setBounds(360, 150, 150, 40);
		decrypt.setBounds(360, 200, 150, 40);
		encryptButton.setBounds(360, 400, 150, 40);
		decryptButton.setBounds(360, 400, 150, 40);
		encryptFile.setBounds(360, 150, 150, 40);
		encryptText.setBounds(360, 200, 150, 40);
		decryptFile.setBounds(360, 150, 150, 40);
		decryptText.setBounds(360, 200, 150, 40);

		txtA.setBounds(100, 10, 700, 20);
		txtB.setBounds(100, 35, 700, 20);
		prompt.setBounds(330, 90, 240, 30);
		txtC.setBounds(100, 10, 700, 20);
		txtD.setBounds(100, 65, 700, 20);
		txtE.setBounds(100, 90, 700, 20);
		error.setBounds(300, 350, 300, 30);

		lblA.setBounds(20, 10, 100, 20);
		lblB.setBounds(20, 35, 100, 20);
		lblD.setBounds(20, 65, 100, 20);
		lblC.setBounds(20, 10, 100, 20);
		lblE.setBounds(20, 90, 100, 20);

		add(encrypt);
		add(decrypt);

		add(prompt);
		prompt.setText("Would you like to Encrypt or Decrypt?");
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
			}});
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
			}});
		decryptText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = false;
				createDecryptButton(e);
			}
		});
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
		prompt.setVisible(false);
		
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
