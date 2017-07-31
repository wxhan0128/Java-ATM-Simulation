package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AlterPasswordFrame extends JPanel implements ActionListener,
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7875215815764741556L;

	private PersonalInform personal = null;// ��ʼ���û��������
	private JPasswordField oldPasswordField = null;
	private JPasswordField newPasswordField = null;
	private JPasswordField sureNewPasswordField = null;
	private JButton[] jb = new JButton[16];
	private int input = 1;// ��ʼ���ı���ָ������
	private int n = 0;// ��ʼ����������ͬλ���ĸ�������

	public AlterPasswordFrame(PersonalInform personal) {
		this.personal = personal;// ��ȡǰһ���������е��û���Ϣ����
		init();// ������
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		JButton sureAlter = new JButton("ȷ���޸�");
		JButton cancel = new JButton("ȡ��");

		oldPasswordField = new JPasswordField(10);
		newPasswordField = new JPasswordField(10);
		sureNewPasswordField = new JPasswordField(10);

		oldPasswordField.addMouseListener(this);
		newPasswordField.addMouseListener(this);
		sureNewPasswordField.addMouseListener(this);

		oldPasswordField.setEditable(false);
		newPasswordField.setEditable(false);
		sureNewPasswordField.setEditable(false);
		oldPasswordField.setOpaque(true);
		newPasswordField.setOpaque(false);
		sureNewPasswordField.setOpaque(false);

		oldPasswordField.setBackground(Color.WHITE);

		JLabel oldPasswordLabel = new JLabel("������ԭ����:");
		JLabel newPasswordLabel = new JLabel("������������:");
		JLabel surePewPasswordLabel = new JLabel("ȷ��������:");

		JPanel alterPanel = new JPanel();
		JPanel ePanel = new JPanel();
		JPanel keyboard = new JPanel();
		JPanel p = new JPanel();

		p.setLayout(null);

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

		p.setOpaque(false);
		p.setBorder(BorderFactory.createTitledBorder("�޸�����"));

		alterPanel.setOpaque(false);
		alterPanel.setLayout(new GridLayout(3, 2, 3, 3));
		alterPanel.setBounds(80, 20, 350, 80);

		ePanel.setOpaque(false);
		ePanel.setBorder(BorderFactory.createTitledBorder("ѡ��"));
		ePanel.setLayout(new BorderLayout());

		alterPanel.add(oldPasswordLabel);
		alterPanel.add(oldPasswordField);
		alterPanel.add(newPasswordLabel);
		alterPanel.add(newPasswordField);
		alterPanel.add(surePewPasswordLabel);
		alterPanel.add(sureNewPasswordField);

		ePanel.add(cancel, BorderLayout.WEST);
		ePanel.add(sureAlter, BorderLayout.EAST);

		p.add(alterPanel);
		p.add(keyboard);
		add(ePanel, BorderLayout.SOUTH);
		add(p, BorderLayout.CENTER);

		sureAlter.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				if (oldPasswordField.getText().equals(personal.getPassword())) {
					// ��ǰ�û��ľ����������ݿ��е����
					if (newPasswordField.getText().equals("")) {
						// ����������Ϊ��
						JOptionPane.showMessageDialog(null, "������������", "����", 1);
					} else if (!(newPasswordField.getText()
							.equals(sureNewPasswordField.getText()))
							|| sureNewPasswordField.getText().equals("")) {
						// �������ȷ�����벻ƥ�����ȷ������Ϊ��
						JOptionPane.showMessageDialog(null, "���벻ƥ��,����������",
								"����", 1);
						newPasswordField.setText(null);
						sureNewPasswordField.setText(null);
						input = 2;
						oldPasswordField.setOpaque(false);
						newPasswordField.setOpaque(true);
						sureNewPasswordField.setOpaque(false);
						newPasswordField.setBackground(Color.WHITE);
					} else {
						// �������ȷ������ƥ�����ȷ�����벻Ϊ��
						if (newPasswordField.getText().length() != 6) {
							// ����λ������6λ
							JOptionPane.showMessageDialog(null, "���벻��6λ,����������",
									"����", 1);
							newPasswordField.setText(null);
							sureNewPasswordField.setText(null);
							input = 2;
							oldPasswordField.setOpaque(false);
							newPasswordField.setOpaque(true);
							sureNewPasswordField.setOpaque(false);
							newPasswordField.setBackground(Color.WHITE);
						} else {
							// ����λ����6λ
							String s = newPasswordField.getText();
							for (int i = 0; i < s.length() - 1; i++) {
								// �Ƚ��������ÿһλ
								if (s.charAt(i) == s.charAt(i + 1)) {
									// �����n+1
									n = n + 1;
									System.out.println(s.charAt(i));
								}
							}
							System.out.println(n);
							if (n < 5) {
								// ����������λ����ȫ���
								personal.setPassword(newPasswordField.getText());
								MainFrame mainFrame = new MainFrame(personal);
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�",
										"��ʾ", 1);
								ATMFrame.c.remove(1);
								ATMtest.frame.repaint();
								ATMFrame.c.add(mainFrame);
								ATMtest.frame.validate();
								System.out.println("������ "
										+ personal.getPassword());
								n = 0;
							} else {
								// ����������λ�������
								JOptionPane.showMessageDialog(null, "���벻��ȫ��ͬ",
										"����", 1);
								newPasswordField.setText(null);
								sureNewPasswordField.setText(null);
								n = 0;
							}
						}
					}
				} else {
					// ��ǰ�û��ľ����������ݿ��еĲ���
					JOptionPane.showMessageDialog(null, "ԭ�������,����������", "����", 1);
					oldPasswordField.setText(null);
					newPasswordField.setText(null);
					sureNewPasswordField.setText(null);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// ȡ��
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
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
		if (input == 1) {
			t = oldPasswordField;

		} else if (input == 2) {
			t = newPasswordField;

		} else if (input == 3) {
			t = sureNewPasswordField;

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
			JOptionPane.showMessageDialog(null, "������Ч", "����", 1);
			// t.setText(t.getText() + ".");
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
			oldPasswordField.setBackground(null);
			newPasswordField.setBackground(null);
			sureNewPasswordField.setBackground(null);
			if (input == 1) {
				input = 1;
				oldPasswordField.setOpaque(true);
				newPasswordField.setOpaque(false);
				sureNewPasswordField.setOpaque(false);
				oldPasswordField.setBackground(Color.WHITE);
			} else if (input == 2) {
				input = 1;
				oldPasswordField.setOpaque(true);
				newPasswordField.setOpaque(false);
				sureNewPasswordField.setOpaque(false);
				newPasswordField.setBackground(Color.WHITE);
			} else if (input == 3) {
				input = 2;
				oldPasswordField.setOpaque(false);
				newPasswordField.setOpaque(true);
				sureNewPasswordField.setOpaque(false);
				sureNewPasswordField.setBackground(Color.WHITE);
			}
			System.out.println(input);
		}
		if (e.getSource() == jb[14]) {
			// �·�
			oldPasswordField.setBackground(null);
			newPasswordField.setBackground(null);
			sureNewPasswordField.setBackground(null);
			if (input == 1) {
				input = 2;
				oldPasswordField.setOpaque(false);
				newPasswordField.setOpaque(true);
				sureNewPasswordField.setOpaque(false);
				newPasswordField.setBackground(Color.WHITE);
			} else if (input == 2) {
				input = 3;
				oldPasswordField.setOpaque(false);
				newPasswordField.setOpaque(false);
				sureNewPasswordField.setOpaque(true);
				sureNewPasswordField.setBackground(Color.WHITE);
			} else if (input == 3) {
				input = 3;
				oldPasswordField.setOpaque(false);
				newPasswordField.setOpaque(false);
				sureNewPasswordField.setOpaque(true);
				sureNewPasswordField.setBackground(Color.WHITE);
			}
			System.out.println(input);
		}
		if (e.getSource() == jb[15]) {
			// Cancel
			if (input == 1) {
				t.setText("");
			} else if (input == 2) {
				t.setText("");
			} else if (input == 3) {
				t.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		oldPasswordField.setBackground(null);
		newPasswordField.setBackground(null);
		sureNewPasswordField.setBackground(null);
		oldPasswordField.setOpaque(false);
		newPasswordField.setOpaque(false);
		sureNewPasswordField.setOpaque(false);
		if (e.getSource() == oldPasswordField) {
			input = 1;
			oldPasswordField.setBackground(Color.WHITE);
			oldPasswordField.setOpaque(true);
		} else if (e.getSource() == newPasswordField) {
			input = 2;
			newPasswordField.setBackground(Color.WHITE);
			newPasswordField.setOpaque(true);
		} else if (e.getSource() == sureNewPasswordField) {
			input = 3;
			sureNewPasswordField.setBackground(Color.WHITE);
			sureNewPasswordField.setOpaque(true);
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
