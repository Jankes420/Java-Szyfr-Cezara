import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public String encode(String message, int keyVal) {
		String output = "";
		char key = (char) keyVal;
		for (int x = 0; x < message.length(); x++) 
		{
			char input = message.charAt(x);
			if (input >= 'A' && input <= 'Z')
			{
				input += key;
				if (input >'Z')
					input -=26;
				if (input < 'A')
					input += 26;
			}
			else if (input >= 'a' && input <= 'z')
			{
				input += key;
				if (input >'z')
					input -=26;
				if (input < 'a')
					input += 26;
			}
			else if (input >= '0' && input <= '9') 
			{
				input += (keyVal % 10);
				if (input >'9')
					input -=10;
				if (input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}
	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(112, 128, 144));
		setTitle("Szyfr Cezara");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
		getContentPane().add(scrollPane);

		txtIn = new JTextArea();
		scrollPane.setViewportView(txtIn);
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 564, 140);
		getContentPane().add(scrollPane_1);

		txtOut = new JTextArea();
		scrollPane_1.setViewportView(txtOut);
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));

		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int key = Integer.parseInt( txtKey.getText() );
				slider.setValue(key);
			}
		});
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(280, 162, 44, 20);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);

		JLabel lblKlucz = new JLabel("Klucz:");
		lblKlucz.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		lblKlucz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKlucz.setBounds(224, 165, 46, 14);
		getContentPane().add(lblKlucz);

		JButton btnSzyfrujodszyfruj = new JButton("Szyfruj/Odszyfruj");
		btnSzyfrujodszyfruj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSzyfrujodszyfruj.setBackground(new Color(211, 211, 211));
		btnSzyfrujodszyfruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = txtIn.getText();
					int key = Integer.parseInt( txtKey.getText() );
					String output = encode( message, key );
					txtOut.setText( output );
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Podaj liczbê ca³kowit¹ jako klucz.");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnSzyfrujodszyfruj.setBounds(334, 162, 146, 23);
		getContentPane().add(btnSzyfrujodszyfruj);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtKey.setText(""+slider.getValue());
				String message = txtIn.getText();
				int key = slider.getValue();
				String output = encode( message, key );
				txtOut.setText( output );
			}
		});
		slider.setMinorTickSpacing(1);
		slider.setValue(0);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setPaintLabels(true);
		slider.setBackground(new Color(112, 128, 144));
		slider.setBounds(20, 162, 200, 37);
		getContentPane().add(slider);
		
		JButton btnDoGry = new JButton("Do g\u00F3ry ^");
		btnDoGry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = txtOut.getText();
				txtIn.setText(output);
				int key = slider.getValue();
				slider.setValue(-key);
			}
		});
		btnDoGry.setBackground(new Color(211, 211, 211));
		btnDoGry.setBounds(490, 162, 89, 23);
		getContentPane().add(btnDoGry);
	}

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Apka nie obs³uguje szyfrowania polskich znaków jakby co :c");
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);

	}
}
