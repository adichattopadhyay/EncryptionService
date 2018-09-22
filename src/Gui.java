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
	private JButton yes = new JButton("Yes");
	private JButton no = new JButton("No");

	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	private JTextField prompt = new JTextField();
	private JTextField txtC = new JTextField();
	private JTextField error = new JTextField();
	private JTextField txtD = new JTextField();

	private JLabel lblA = new JLabel("Text :");
	private JLabel lblB = new JLabel("Password :");
	private JLabel lblC = new JLabel("Input Path :");
	private JLabel lblD = new JLabel("Output Path :");

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
		encrypt.setBounds(350, 130, 200, 100);
		decrypt.setBounds(350, 240, 200, 100);
		encryptButton.setBounds(360, 400, 150, 40);
		decryptButton.setBounds(360, 400, 150, 40);
		yes.setBounds(350, 130, 200, 100);
		no.setBounds(350, 240, 200, 100);

		txtA.setBounds(100, 10, 700, 20);
		txtB.setBounds(100, 35, 700, 20);
		prompt.setBounds(330, 90, 240, 30);
		txtC.setBounds(100, 10, 700, 20);
		txtD.setBounds(100, 65, 700, 20);
		error.setBounds(300, 350, 300, 30);

		lblA.setBounds(20, 10, 100, 20);
		lblB.setBounds(20, 35, 100, 20);
		lblD.setBounds(20, 65, 100, 20);
		lblC.setBounds(20, 10, 100, 20);

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
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = true;
				if (mode == 1) {
					createEncryptButton(e);
				} else if (mode == 2) {
					createDecryptButton(e);
				}
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileCheck = false;
				if (mode == 1) {
					createEncryptButton(e);
				} else if (mode == 2) {
					createDecryptButton(e);
				}
			}
		});
	}

	private void createEncryptButton(ActionEvent evt) {
		prompt.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		if (fileCheck == false) {
			add(txtA);
			add(txtB);
			add(lblA);
			add(lblB);
			add(error);
		}
		else {
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
		yes.setVisible(false);
		no.setVisible(false);
		if (fileCheck == false) {
			add(txtA);
			add(txtB);
			add(lblA);
			add(lblB);
			add(error);
		}
		else {
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
//		remove(encrypt);
//		remove(decrypt);
		add(yes);
		add(no);
		if (mode == 1) {
			prompt.setText("Would you like to encrypt a file");
		} else if (mode == 2) {
			prompt.setText("Would you like to decrypt a file?");
		}
		repaint();
	}

	private void performEncryption(ActionEvent evt) {

		while (done = false) {
			if (fileCheck == false) {
				String text = txtA.getText();
				String password = txtB.getText();
				if (text == "" || password == "") {
					error.setText("Type in the inputs, then hit the encrypt button.");
				} else {
					try {
						Encryption e = new Encryption();
						String encryptedText = e.encrypt(text, password);
						System.out.println(encryptedText);
					} catch (Exception e) {
						error.setText("Something went wrong, try again.");
					}
				}
			} else if (fileCheck == true) {
			}

		}
	}
	
	
	private void performDecryption(ActionEvent evt) {
		
		}

	public static void main(String[] args) { // remove eventually
		Gui g = new Gui();
		g.setVisible(true);
	}
}
