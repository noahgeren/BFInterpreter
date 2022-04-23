package com.noahgeren.brainfuck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GUI {
	
	private static Thread runningThread = null;
	private static String input = "";

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Brainfuck Interpreter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		final JTextArea code = new JTextArea(10, 50);
		final Insets textAreaMargin = new Insets(10, 10, 10, 10);
		final Font textAreaFont = new Font("Courier New", Font.PLAIN, 16);
		code.setMargin(textAreaMargin);
		code.setFont(textAreaFont);
		code.setLineWrap(true);
		
		final JButton run = new JButton("Run");
		run.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		final JTextArea output = new JTextArea(5, 50);
		output.setBackground(Color.BLACK);
		output.setForeground(Color.WHITE);
		output.setMargin(textAreaMargin);
		output.setFont(textAreaFont);
		output.setLineWrap(true);
		
		pane.add(code);
		pane.add(run);
		pane.add(output);
		
		final BrainfuckInterpreter bf = new BrainfuckInterpreter(() -> {
			System.out.println("here");
			// Input
			try {
				SwingUtilities.invokeAndWait(() -> {
					input = JOptionPane.showInputDialog(frame, "Input");
				});
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
			return input;
		}, (c) -> {
			// Output
			output.setText(output.getText() + c);
		});
		Runnable stop = () -> {
			if(runningThread != null) {
				runningThread.interrupt();
				runningThread = null;
			}
			run.setText("Run");
		};
		run.addActionListener((event) -> {
			if(runningThread == null) { // Start
				run.setText("Stop");
				output.setText("");
				runningThread = new Thread(() -> {
					bf.execute(code.getText());
					stop.run();
				});
				runningThread.start();
			} else { // Stop
				stop.run();
			}
		});
		
		frame.setContentPane(pane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
