package newATMmodel;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ATMtest {

	protected static ATMFrame frame = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				frame = new ATMFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
