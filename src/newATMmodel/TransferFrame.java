package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransferFrame extends JPanel implements ActionListener,
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6877037446482458612L;
	private JTextField otherIdField = null;// ������
	private JTextField transferField = null;// �����
	private PersonalInform personal = null;// �����û�����
	private String time = null;// ���ڱ��浱ǰʱ��
	private JButton[] jb = new JButton[16];// ģ����̰�ť
	private double c = 0;// ��ʼ��ת�˽�����
	private double charge = 0;// ��ʼ�������ѱ���
	private int input = 0;// ��ʼ���ı���ָ������

	public TransferFrame(PersonalInform personal) {
		this.personal = personal;// �õ�����ѡ�������û�����
		init();// ת�����������
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();// ʵ�������ڶ���
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// ʵ�������ڸ�ʽ����
		time = dateFormat.format(now);// ��ȡ��ǰʱ��

		JButton ok = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");

		// ������ͽ�������������
		otherIdField = new JTextField(10);
		transferField = new JTextField(10);
		otherIdField.setEditable(false);
		transferField.setEditable(false);
		otherIdField.setBackground(Color.WHITE);
		transferField.setBackground(null);
		transferField.setOpaque(false);
		otherIdField.addMouseListener(this);// ���������굥���¼�
		transferField.addMouseListener(this);// ��������굥���¼�

		JLabel otherIdLabel = new JLabel("������Ҫת�˵Ŀ���:");
		JLabel transferLabel = new JLabel("��������:");

		JPanel transferPanel = new JPanel();// ת�����
		JPanel enterPanel = new JPanel();
		JPanel keyboard = new JPanel();// ģ��������
		JPanel p = new JPanel();

		p.setOpaque(false);
		p.setLayout(null);
		p.setBorder(BorderFactory.createTitledBorder("ת��"));

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

		transferPanel.setOpaque(false);
		transferPanel.setLayout(new GridLayout(2, 2, 10, 10));

		enterPanel.setOpaque(false);
		enterPanel.setBorder(BorderFactory.createTitledBorder("ѡ��"));
		enterPanel.setLayout(new BorderLayout());

		transferPanel.add(otherIdLabel);
		transferPanel.add(otherIdField);
		transferPanel.add(transferLabel);
		transferPanel.add(transferField);
		transferPanel.setBounds(120, 30, 320, 60);
		enterPanel.add(cancel, BorderLayout.WEST);
		enterPanel.add(ok, BorderLayout.EAST);

		p.add(keyboard);
		p.add(transferPanel);

		add(p, BorderLayout.CENTER);
		add(enterPanel, BorderLayout.SOUTH);

		ok.addActionListener(new ActionListener() {
			// ȷ����
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AccessConnect conn = new AccessConnect();// �������ݿ�����
				conn.selectCardNum(otherIdField.getText());// �����µĿ���
				try {
					c = Double.parseDouble(transferField.getText());// ��������е��ַ���ת��Ϊ˫���ȣ����ַ���Ϊ���ǵ��쳣
				} catch (Exception el) {

				}
				if (!(conn.getCardNum().equals("") || (conn.getCardNum()
						.equals(personal.getID())))) {
					// �¿��Ŵ��������ݿ��л��¿����뵱ǰ�û��Ŀ��Ų���ʱ
					PersonalInform otherPersonal = new CustomerInform(
							otherIdField.getText());// ʵ�����µ��û�����
					if (otherPersonal.getEnable() != 0) {
						// ���û�����״̬Ϊ��ʹ��
						if (!(transferField.getText().equals(""))) {
							// ���뿨�ŵ����
							if (c > 50000) {
								// ��������50000
								JOptionPane.showMessageDialog(null,
										"��������,������С��50000Ԫ�Ľ��", "����", 1);
								transferField.setText(null);
							} else if (c <= personal.getRental()) {
								// ����Ľ��С���û������
								if (c > 100) {
									// ������100ʱ
									charge = (c / 100) * 1.0;// ����������
								} else {
									charge = 0;
								}
								System.out.println(charge);
								personal.setRental(personal.getRental() - c
										- charge);// ��ǰ�û����۳�
								otherPersonal.setRental(otherPersonal
										.getRental() + c);// ���û��������
								personal.setHistory(time, "ת��", c + "Ԫ" + " ���"
										+ personal.getRental() + "Ԫ" + " ������"
										+ charge);// ���뵱ǰ��¼����ǰ�û�����ʷ�¼�����
								otherPersonal.setHistory(time, "���", c + "Ԫ"
										+ " ���" + otherPersonal.getRental()
										+ "Ԫ");// ���뵱ǰ��¼�����û�����ʷ�¼�����
								WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
										personal);// ʵ����ȡ��ɹ����
								ATMFrame.c.remove(1);
								ATMtest.frame.repaint();
								ATMFrame.c.add(withdrawalSuccessFrame);
								ATMtest.frame.validate();
							} else {
								// ���С��������
								JOptionPane.showMessageDialog(null,
										"����,�޷�ȡ��", "����", 1);
								transferField.setText(null);
							}
						} else {
							// δ������
							JOptionPane.showMessageDialog(null, "��������", "����",
									1);
						}
					} else {
						// ��������
						JOptionPane.showMessageDialog(null, "���˻��ѱ���û,�޷�����ת��",
								"����", 1);
						otherIdField.setText(null);
						transferField.setText(null);
					}
				} else {
					// �����������
					JOptionPane.showMessageDialog(null, "�˻�����,����������", "����", 1);
					otherIdField.setText(null);
					transferField.setText(null);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// ȡ��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame mainFrame = new MainFrame(personal);
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(mainFrame);
				ATMtest.frame.validate();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JTextField t = null;
		if (input == 0) {
			t = otherIdField;
		} else if (input == 1) {
			t = transferField;
		}
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
			t.setText(t.getText() + ".");
		}
		if (e.getSource() == jb[10]) {
			t.setText(t.getText() + "0");
		}
		if (e.getSource() == jb[11]) {
			// 00
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
			otherIdField.setBackground(null);
			transferField.setBackground(null);
			input = 0;
			otherIdField.setOpaque(true);
			transferField.setOpaque(false);
			otherIdField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[14]) {
			// �·�
			otherIdField.setBackground(null);
			transferField.setBackground(null);
			input = 1;
			transferField.setOpaque(true);
			otherIdField.setOpaque(false);
			transferField.setBackground(Color.WHITE);
		}

		if (e.getSource() == jb[15]) {
			// Cancel
			if (input == 1) {
				t.setText("");
			} else if (input == 0) {
				t.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		otherIdField.setBackground(null);
		otherIdField.setOpaque(false);
		transferField.setBackground(null);
		transferField.setOpaque(false);
		if (e.getSource() == otherIdField) {
			input = 0;
			otherIdField.setBackground(Color.WHITE);
			otherIdField.setOpaque(true);
		} else if (e.getSource() == transferField) {
			input = 1;
			transferField.setBackground(Color.WHITE);
			transferField.setOpaque(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
