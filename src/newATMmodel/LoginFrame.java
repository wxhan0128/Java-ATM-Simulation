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

public class LoginFrame extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 155978017268629068L;
	private int i = 0;// ��¼�������Ĵ���
	private JTextField idField = null;// �����ı���
	private JPasswordField passwordField = null;// ����������
	private PersonalInform personal = null;// �����û��������
	private JButton[] jb = new JButton[16];// ģ����̰�ť����
	private int input = 1;// �����л����ı���

	public LoginFrame() {
		init();// ��¼���������
	}

	public void init() {
		this.setOpaque(false);// ���������Ϊ͸��
		this.setLayout(new BorderLayout());// ����岼��Ϊ�߿�ʽ
		this.setBounds(0, 60, 580, 390);// ������С��λ�õ�����

		idField = new JTextField(10);// ʵ���������ı��򣬳���Ϊ10
		idField.setEditable(false);// �ı�������Ϊ���ɱ༭
		passwordField = new JPasswordField(10);// ʵ���������򣬳���Ϊ10
		passwordField.setEditable(false);// ����������Ϊ���ɱ༭

		JLabel idLabel = new JLabel("�����뿨��:");
		JLabel passwordLabel = new JLabel("����������:");

		idField.setBackground(Color.WHITE);// �����򱳾�ɫ����Ϊ��ɫ
		idField.setOpaque(true);// ��ʼ״̬�Ŀ�����Ϊ��͸��
		passwordField.setOpaque(false);// ��ʼ״̬��������Ϊ͸��

		idLabel.setBounds(200, 20, 100, 20);
		passwordLabel.setBounds(200, 60, 100, 20);
		idField.setBounds(300, 20, 100, 20);// �������λ�úʹ�С����
		idField.addMouseListener(this);// �����������¼�
		passwordField.setBounds(300, 60, 100, 20);// ������Ĵ�С��λ������
		passwordField.addMouseListener(this);// �����������¼�

		JPanel loginPanel = new JPanel();// ��¼���
		JPanel enterPanel = new JPanel();// �������
		JPanel p = new JPanel();
		JPanel keyboard = new JPanel();// ģ��������

		p.setOpaque(false);
		loginPanel.setOpaque(false);// ��¼�������Ϊ͸��
		loginPanel.setBorder(BorderFactory.createTitledBorder("��¼"));
		loginPanel.setBounds(0, 0, 600, 330);// ��¼���Ĵ�С��λ������
		loginPanel.setLayout(null);// ��¼�������Ϊ���Բ���

		enterPanel.setOpaque(false);// �����������Ϊ͸��
		enterPanel.setBorder(BorderFactory.createTitledBorder("ѡ��"));
		enterPanel.setLayout(new BorderLayout());// �����������Ϊ�߿�ʽ����

		keyboard.setOpaque(false);// �����������Ϊ͸��
		keyboard.setBorder(BorderFactory.createTitledBorder(""));
		keyboard.setBounds(170, 120, 250, 200);
		keyboard.setLayout(new GridLayout(4, 4));// �����������Ϊ���񲼾�

		for (int i = 0; i < 9; i++) {
			jb[i] = new JButton("" + (i + 1));// ʵ����9�����ּ�
		}

		jb[9] = new JButton(".");// С�����
		jb[10] = new JButton("0");// 0��
		jb[11] = new JButton("00");// 00��
		jb[12] = new JButton("��");// �˸��
		jb[13] = new JButton("�Ϸ�");// �Ϸ���
		jb[14] = new JButton("�·�");// �·���
		jb[15] = new JButton("�޸�");// �޸ļ�

		JButton login = new JButton("ȷ��");// ��¼ȷ�ϼ�
		JButton cancel = new JButton("ȡ��");// ȡ��������

		// ���������Ӱ���
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
			jb[i].addActionListener(this);// �����¼�����
		}

		p.add(keyboard);
		p.add(loginPanel);
		p.setLayout(null);
		loginPanel.add(idLabel);
		loginPanel.add(idField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);

		enterPanel.add(cancel, BorderLayout.WEST);
		enterPanel.add(login, BorderLayout.EAST);

		// add(loginPanel, BorderLayout.CENTER);
		this.add(p, BorderLayout.CENTER);
		add(enterPanel, BorderLayout.SOUTH);

		login.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				AccessConnect conn = new AccessConnect();// �������ݿ������
				conn.selectCardNum(idField.getText());// ���ò��ҿ��ŵķ���
				conn.getEnable();// �õ���ǰ����״̬
				if (!(conn.getCardNum().equals(""))) {
					// ������ȷʱ��ִ�����²���
					if (conn.getEnable() != 0) {
						// ����δ����ûʱִ�����²���
						personal = new CustomerInform(idField.getText());// ʵ����һ���û������뵱ǰ������Ϊ����
						// �����������û����벻��ͬʱ��ִ�����²���
						if (!(passwordField.getText().equals(personal
								.getPassword()))) {
							JOptionPane.showMessageDialog(null,
									"������˻���ƥ��,����������", "����", 1);// �����Ի���
							passwordField.setText(null);// ���������
							personal.setError();// ���ݿ��п��Ĵ��������һ
						} else {
							// ������ȷ��ִ�����²���
							MainFrame mainFrame = new MainFrame(personal);// ʵ��������ѡ�����
							ATMFrame.c.remove(1);
							ATMtest.frame.repaint();
							ATMFrame.c.add(mainFrame);
							ATMtest.frame.validate();
							personal.setReError();// �������ݿ�������������Ϊ0��
						}
					} else {
						// ������û��ִ�����²���
						JOptionPane.showMessageDialog(null, "������û����Ч", "����", 1);// �����Ի���
						idField.setText(null);// ���������
						passwordField.setText(null);// ���������
					}
				} else {
					// ������������£�ִ�����²���
					JOptionPane.showMessageDialog(null, "�˻�������,����������", "����", 1);// �����Ի���
					idField.setText(null);
					passwordField.setText(null);
				}
				try {
					try {
						i = personal.getError();// �õ���ǰ���ݿ����û��������Ĵ���
					} catch (Exception el) {

					}
					if (i >= 3) {
						// ����������3�κ�ִ�����²���
						System.out.println("enable=0");
						System.out.println(idField.getText());
						conn.updateEnable(idField.getText());// �������ݿ��еĿ�״̬Ϊ����û
						idField.setText(null);
						passwordField.setText(null);
						input = 1;// ���ı���Ϊ������
						idField.setOpaque(true);
						passwordField.setOpaque(false);
						idField.setBackground(Color.WHITE);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// ȡ����ť�¼�
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				WelcomeFrame welcomFrame = new WelcomeFrame();// ʵ������ӭ���
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(welcomFrame);
				ATMtest.frame.validate();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JTextField t = null;// ָ����ǰ���ı���
		if (input == 1) {
			t = idField;// input=1ʱ��ָ�򿨺���
		} else if (input == 0) {
			t = passwordField;// input=0ʱ��ָ��������
		}
		// 0~9��С���㣬0,00���¼����ã����º��ڵ�ǰ�ı������ݺ�������������
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
			//t.setText(t.getText() + "00");
		}

		if (e.getSource() == jb[12]) {
			// �˸��
			String s = t.getText();// �õ���ǰ�ı��������
			if (s.length() > 0) {
				// ���ݲ�Ϊ��
				s = s.substring(0, s.length() - 1);// �����ַ����ĳ��ȼ�һ
				t.setText(s);// ���ַ���������ӵ��ı�����
			}
		}
		if (e.getSource() == jb[13]) {
			// �Ϸ�
			idField.setBackground(null);// �����򱳾�Ϊ��
			passwordField.setBackground(null);// �����򱳾�Ϊ��
			input = 1;// ���õ�ǰ�ı���Ϊ������
			idField.setOpaque(true);
			passwordField.setOpaque(false);
			idField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[14]) {
			// �·�
			idField.setBackground(null);
			passwordField.setBackground(null);
			input = 0;// ���õ�ǰ���ı���Ϊ������
			passwordField.setOpaque(true);
			idField.setOpaque(false);
			passwordField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[15]) {
			// Cancel
			if (input == 1) {
				idField.setText("");// ��տ�����
			} else if (input == 0) {
				passwordField.setText("");// ���������
			}
		}
	}

	// �ı�����굥���¼�
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		idField.setBackground(null);
		passwordField.setBackground(null);
		if (e.getSource() == idField) {
			input = 1;// ���õ�ǰ�ı���Ϊ������
			idField.setOpaque(true);
			passwordField.setOpaque(false);
			idField.setBackground(Color.WHITE);
		} else if (e.getSource() == passwordField) {
			input = 0;// ���õ�ǰ�ı���Ϊ������
			passwordField.setOpaque(true);
			idField.setOpaque(false);
			passwordField.setBackground(Color.WHITE);
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
