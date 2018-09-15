import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame {

	private JButton hello = new JButton("Hello");
	private JButton encrypt = new JButton("Encrypt");
	
	private JTextField txtA = new JTextField();
	private JTextField txtB = new JTextField();
	// private JTextField txtC = new JTextField();

	private JLabel lblA = new JLabel("Text :");
	private JLabel lblB = new JLabel("Password :");
	// private JLabel lblC = new JLabel("C :");

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
		encrypt.setBounds(300, 130, 80, 25);
		hello.setBounds(300, 100, 80, 25);

		txtA.setBounds(100, 10, 700, 20);
		txtB.setBounds(100, 35, 700, 20);
		// txtC.setBounds(100,65,100,20);

		lblA.setBounds(20, 10, 100, 20);
		lblB.setBounds(20, 35, 100, 20);
		// lblC.setBounds(20,65,100,20);

		add(encrypt);
		add(hello);

		add(lblA);
		add(lblB);
		// add(lblC);

		add(txtA);
		add(txtB);
		// add(txtC);
	}

	private void initEvent() {

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performEncryption(e);
			}
		});

		hello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTambahClick(e);
			}
		});
	}

	private void performEncryption(ActionEvent evt) {
		String text = txtA.getText();
		String password = txtB.getText();
		try {
			Encryption e = new Encryption();
			String encryptedText = e.encrypt(text, password);
			System.out.println(encryptedText);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	private void btnTambahClick(ActionEvent evt) {
		Integer x, y, z;
		try {
			x = Integer.parseInt(txtA.getText());
			y = Integer.parseInt(txtB.getText());
			z = x + y;
			// txtC.setText(z.toString());

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) { //remove eventually
		Gui g = new Gui();
		g.setVisible(true);
	}
}


