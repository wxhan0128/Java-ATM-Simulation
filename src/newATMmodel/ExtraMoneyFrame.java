package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExtraMoneyFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1285717819739792536L;

	private PersonalInform personal = null;// ��ʼ���û��������
	private JTextField inputField = null;
	private String time = null;// ��ʼ��ʱ�����
	private JButton[] jb = new JButton[16];
	private int c = 0;// ��ʼ��������

	public ExtraMoneyFrame(PersonalInform personal) {
		this.personal = personal;// �ĵ�ǰһ������е��û�����
		init();// ������
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		time = dateFormat.format(now);

		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");

		JLabel inputLabel = new JLabel("��������");
		inputField = new JTextField(10);
		inputField.setEditable(false);
		inputField.setBackground(Color.WHITE);

		JPanel inputPanel = new JPanel();
		JPanel keyboard = new JPanel();
		JPanel otherPanel = new JPanel();
		JPanel p = new JPanel();

		p.setOpaque(false);
		p.setLayout(null);
		p.setBorder(BorderFactory.createTitledBorder("������"));

		inputPanel.setOpaque(false);
		inputPanel.setLayout(new FlowLayout());

		otherPanel.setOpaque(false);
		otherPanel.setBorder(BorderFactory.createTitledBorder("����"));
		otherPanel.setLayout(new BorderLayout());

		inputPanel.add(inputLabel);
		inputPanel.add(inputField);
		inputPanel.setBounds(120, 20, 350, 80);
		otherPanel.add(cancel, BorderLayout.WEST);
		otherPanel.add(ok, BorderLayout.EAST);

		p.add(keyboard);
		p.add(inputPanel);

		keyboard.setOpaque(false);
		keyboard.setBorder(BorderFactory.createTitledBorder(""));
		keyboard.setBounds(170, 120, 250, 200);
		keyboard.setLayout(new GridLayout(4, 4));

		for (int i = 0; i < 9; i++) {
			jb[i] = new JButton("" + (i + 1));
		}

		jb[9] = new JButton(".");
		jb[10] = new JButton("0");
		jb[11] = new JButton("00");
		jb[12] = new JButton("��");
		jb[13] = new JButton("�Ϸ�");
		jb[14] = new JButton("�·�");
		jb[15] = new JButton("�޸�");

		keyboard.add(jb[0]);
		keyboard.add(jb[1]);
		keyboard.add(jb[2]);
		keyboard.add(jb[12]);
		keyboard.add(jb[3]);
		keyboard.add(jb[4]);
		keyboard.add(jb[5]);
		keyboard.add(jb[13]);
		keyboard.add(jb[6]);
		keyboard.add(jb[7]);
		keyboard.add(jb[8]);
		keyboard.add(jb[14]);
		keyboard.add(jb[9]);
		keyboard.add(jb[10]);
		keyboard.add(jb[11]);
		keyboard.add(jb[15]);
		for (int i = 0; i < 16; i++) {
			jb[i].addActionListener(this);
		}

		add(p, BorderLayout.CENTER);
		add(otherPanel, BorderLayout.SOUTH);

		ok.addActionListener(new ActionListener() {
			// ȷ��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				try {
					c = Integer.parseInt(inputField.getText());// ���ı���������Ľ��ת��Ϊ���ͣ������ַ���Ϊ��ʱ���쳣
				} catch (Exception el) {

				}
				if (!(inputField.getText().equals(""))) {
					// ����Ľ�Ϊ��
					if (c != 0) {
						// ����0Ԫ
						if (c % 100 != 0) {
							// ����100��������
							JOptionPane.showMessageDialog(null, "�������������",
									"����", 1);
							inputField.setText(null);
						} else if (c > 10000) {
							// ��������
							JOptionPane.showMessageDialog(null,
									"��������,������С��10000Ԫ�Ľ��", "����", 1);
							inputField.setText(null);
						} else if (personal.getRental() < c) {
							// �û������С������Ľ��
							JOptionPane.showMessageDialog(null, "���㣬�޷�ȡ��",
									"����", 1);
							inputField.setText(null);
						} else {
							// �û��Ľ���������Ľ��
							personal.setRental(personal.getRental() - c);// �����û��������Ϣ
							personal.setHistory(time, "��ȡ", c + "Ԫ" + " ���"
									+ personal.getRental() + "Ԫ");// ������ʷ��¼
							WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
									personal);// ʵ����ȡ��ɹ���壬���뵱ǰ�û���Ϣ����
							ATMFrame.c.remove(1);
							ATMtest.frame.repaint();
							ATMFrame.c.add(withdrawalSuccessFrame);
							ATMtest.frame.validate();
						}
					} else {
						// ����Ľ��Ϊ0
						JOptionPane.showMessageDialog(null, "����Ϊ0", "����", 1);
						inputField.setText("");
					}
				} else {
					// ����Ľ��Ϊ��
					JOptionPane.showMessageDialog(null, "��������", "����", 1);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// ȡ��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WithdrawalFrame withdrawalFrame = new WithdrawalFrame(personal);
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(withdrawalFrame);
				ATMtest.frame.validate();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JTextField t = inputField;

		if (e.getSource() == jb[0]) {
			t.setText(t.getText() + "1");
		}
		if (e.getSource() == jb[1]) {
			t.setText(t.getText() + "2");
		}
		if (e.getSource() == jb[2]) {
			t.setText(t.getText() + "3");
		}
		if (e.getSource() == jb[3]) {
			t.setText(t.getText() + "4");
		}
		if (e.getSource() == jb[4]) {
			t.setText(t.getText() + "5");
		}
		if (e.getSource() == jb[5]) {
			t.setText(t.getText() + "6");
		}
		if (e.getSource() == jb[6]) {
			t.setText(t.getText() + "7");
		}
		if (e.getSource() == jb[7]) {
			t.setText(t.getText() + "8");
		}
		if (e.getSource() == jb[8]) {
			t.setText(t.getText() + "9");
		}
		if (e.getSource() == jb[9]) {
			// .
			JOptionPane.showMessageDialog(null, "������Ч", "����", 1);
			// t.setText(t.getText() + ".");
		}
		if (e.getSource() == jb[10]) {
			t.setText(t.getText() + "0");
		}
		if (e.getSource() == jb[11]) {
			t.setText(t.getText() + "00");
		}
		if (e.getSource() == jb[12]) {
			// ��
			String s = t.getText();
			if (s.length() > 0) {
				s = s.substring(0, s.length() - 1);
				t.setText(s);
			}
		}
		if (e.getSource() == jb[13]) {
			// �Ϸ�
		}
		if (e.getSource() == jb[14]) {
			// �·�
		}
		if (e.getSource() == jb[15]) {
			// Cancel
			inputField.setText("");
		}
	}
}
