package com.calc.ipen;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.*;

public class Calculator extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField displayArea = new JTextField("0");
	private double result = 0;
	private String operator = "=";
	private boolean calculation = true;

	public Calculator() {
		setLayout(new BorderLayout());
		
		displayArea.setEditable(false);
		displayArea.setPreferredSize(new Dimension(70, 70));
		add(displayArea, "North");
		Font bigFont = displayArea.getFont().deriveFont(Font.PLAIN, 25f);
		displayArea.setFont(bigFont);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		String buttonLabels = "789/456*123-0.=+";
		for (int i = 0; i < buttonLabels.length(); i++) {
			JButton b = new JButton(buttonLabels.substring(i, i + 1));
			b.setFont(bigFont);
			panel.add(b);
			b.addActionListener(this);
		}
		add(panel, "Center");
	}

	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		if ('0' <= command.charAt(0) && command.charAt(0) <= '9' || command.equals(".")) {
			if (calculation)
				displayArea.setText(command);
			else
				displayArea.setText(displayArea.getText() + command);
			calculation = false;
		} else {
			if (calculation) {
				if (command.equals("-")) {
					displayArea.setText(command);
					calculation = false;
				} else
					operator = command;
			} else {
				double x = Double.parseDouble(displayArea.getText());
				calculate(x);
				operator = command;
				calculation = true;
			}
		}
	}

	private void calculate(double n) {
		if (operator.equals("+"))
			result += n;
		else if (operator.equals("-"))
			result -= n;
		else if (operator.equals("*"))
			result *= n;
		else if (operator.equals("/"))
			result /= n;
		else if (operator.equals("="))
			result = n;
		displayArea.setText("" + result);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new JFrame();
		frame.setTitle("Simple Calculator");
		frame.setSize(300, 300);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = frame.getContentPane();
		contentPane.add(new Calculator());
		frame.show();
	}
}
