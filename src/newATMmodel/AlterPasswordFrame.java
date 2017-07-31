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

	private PersonalInform personal = null;// 初始化用户对象变量
	private JPasswordField oldPasswordField = null;
	private JPasswordField newPasswordField = null;
	private JPasswordField sureNewPasswordField = null;
	private JButton[] jb = new JButton[16];
	private int input = 1;// 初始化文本域指定变量
	private int n = 0;// 初始化新密码相同位数的个数变量

	public AlterPasswordFrame(PersonalInform personal) {
		this.personal = personal;// 获取前一个面板对象中的用户信息对象
		init();// 主方法
	}

	private void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		JButton sureAlter = new JButton("确认修改");
		JButton cancel = new JButton("取消");

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

		JLabel oldPasswordLabel = new JLabel("请输入原密码:");
		JLabel newPasswordLabel = new JLabel("请输入新密码:");
		JLabel surePewPasswordLabel = new JLabel("确认新密码:");

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
		jb[12] = new JButton("←");
		jb[13] = new JButton("上翻");
		jb[14] = new JButton("下翻");
		jb[15] = new JButton("修改");

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
		p.setBorder(BorderFactory.createTitledBorder("修改密码"));

		alterPanel.setOpaque(false);
		alterPanel.setLayout(new GridLayout(3, 2, 3, 3));
		alterPanel.setBounds(80, 20, 350, 80);

		ePanel.setOpaque(false);
		ePanel.setBorder(BorderFactory.createTitledBorder("选择"));
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
					// 当前用户的旧密码与数据库中的相符
					if (newPasswordField.getText().equals("")) {
						// 新密码输入为空
						JOptionPane.showMessageDialog(null, "请输入新密码", "警告", 1);
					} else if (!(newPasswordField.getText()
							.equals(sureNewPasswordField.getText()))
							|| sureNewPasswordField.getText().equals("")) {
						// 新密码和确认密码不匹配或是确认密码为空
						JOptionPane.showMessageDialog(null, "密码不匹配,请重新输入",
								"警告", 1);
						newPasswordField.setText(null);
						sureNewPasswordField.setText(null);
						input = 2;
						oldPasswordField.setOpaque(false);
						newPasswordField.setOpaque(true);
						sureNewPasswordField.setOpaque(false);
						newPasswordField.setBackground(Color.WHITE);
					} else {
						// 新密码和确认密码匹配或是确认密码不为空
						if (newPasswordField.getText().length() != 6) {
							// 密码位数不是6位
							JOptionPane.showMessageDialog(null, "密码不是6位,请重新输入",
									"警告", 1);
							newPasswordField.setText(null);
							sureNewPasswordField.setText(null);
							input = 2;
							oldPasswordField.setOpaque(false);
							newPasswordField.setOpaque(true);
							sureNewPasswordField.setOpaque(false);
							newPasswordField.setBackground(Color.WHITE);
						} else {
							// 密码位数是6位
							String s = newPasswordField.getText();
							for (int i = 0; i < s.length() - 1; i++) {
								// 比较新密码的每一位
								if (s.charAt(i) == s.charAt(i + 1)) {
									// 相等则n+1
									n = n + 1;
									System.out.println(s.charAt(i));
								}
							}
							System.out.println(n);
							if (n < 5) {
								// 新密码所有位数不全相等
								personal.setPassword(newPasswordField.getText());
								MainFrame mainFrame = new MainFrame(personal);
								JOptionPane.showMessageDialog(null, "修改成功",
										"提示", 1);
								ATMFrame.c.remove(1);
								ATMtest.frame.repaint();
								ATMFrame.c.add(mainFrame);
								ATMtest.frame.validate();
								System.out.println("新密码 "
										+ personal.getPassword());
								n = 0;
							} else {
								// 新密码所有位数都相等
								JOptionPane.showMessageDialog(null, "密码不能全相同",
										"警告", 1);
								newPasswordField.setText(null);
								sureNewPasswordField.setText(null);
								n = 0;
							}
						}
					}
				} else {
					// 当前用户的旧密码与数据库中的不符
					JOptionPane.showMessageDialog(null, "原密码错误,请重新输入", "警告", 1);
					oldPasswordField.setText(null);
					newPasswordField.setText(null);
					sureNewPasswordField.setText(null);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// 取消
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
			JOptionPane.showMessageDialog(null, "输入无效", "警告", 1);
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
			// ←
			String s = t.getText();
			if (s.length() > 0) {
				s = s.substring(0, s.length() - 1);
				t.setText(s);
			}
		}
		if (e.getSource() == jb[13]) {
			// 上翻
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
			// 下翻
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
