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
	private int i = 0;// 记录密码输错的次数
	private JTextField idField = null;// 卡号文本域
	private JPasswordField passwordField = null;// 密码域声明
	private PersonalInform personal = null;// 声明用户对象变量
	private JButton[] jb = new JButton[16];// 模拟键盘按钮声明
	private int input = 1;// 用于切换的文本域

	public LoginFrame() {
		init();// 登录面板主方法
	}

	public void init() {
		this.setOpaque(false);// 主面板设置为透明
		this.setLayout(new BorderLayout());// 主面板布局为边框式
		this.setBounds(0, 60, 580, 390);// 主面板大小和位置的设置

		idField = new JTextField(10);// 实例化卡号文本域，长度为10
		idField.setEditable(false);// 文本域设置为不可编辑
		passwordField = new JPasswordField(10);// 实例化密码域，长度为10
		passwordField.setEditable(false);// 密码域设置为不可编辑

		JLabel idLabel = new JLabel("请输入卡号:");
		JLabel passwordLabel = new JLabel("请输入密码:");

		idField.setBackground(Color.WHITE);// 卡号域背景色设置为白色
		idField.setOpaque(true);// 初始状态的卡号域为不透明
		passwordField.setOpaque(false);// 初始状态的密码域为透明

		idLabel.setBounds(200, 20, 100, 20);
		passwordLabel.setBounds(200, 60, 100, 20);
		idField.setBounds(300, 20, 100, 20);// 卡号域的位置和大小设置
		idField.addMouseListener(this);// 卡号域的鼠标事件
		passwordField.setBounds(300, 60, 100, 20);// 密码域的大小和位置设置
		passwordField.addMouseListener(this);// 密码域的鼠标事件

		JPanel loginPanel = new JPanel();// 登录面板
		JPanel enterPanel = new JPanel();// 进入面板
		JPanel p = new JPanel();
		JPanel keyboard = new JPanel();// 模拟键盘面板

		p.setOpaque(false);
		loginPanel.setOpaque(false);// 登录面板设置为透明
		loginPanel.setBorder(BorderFactory.createTitledBorder("登录"));
		loginPanel.setBounds(0, 0, 600, 330);// 登录面板的大小和位置设置
		loginPanel.setLayout(null);// 登录面板设置为绝对布局

		enterPanel.setOpaque(false);// 进入面板设置为透明
		enterPanel.setBorder(BorderFactory.createTitledBorder("选择"));
		enterPanel.setLayout(new BorderLayout());// 进入面板设置为边框式布局

		keyboard.setOpaque(false);// 键盘面板设置为透明
		keyboard.setBorder(BorderFactory.createTitledBorder(""));
		keyboard.setBounds(170, 120, 250, 200);
		keyboard.setLayout(new GridLayout(4, 4));// 键盘面板设置为网格布局

		for (int i = 0; i < 9; i++) {
			jb[i] = new JButton("" + (i + 1));// 实例化9个数字键
		}

		jb[9] = new JButton(".");// 小数点键
		jb[10] = new JButton("0");// 0键
		jb[11] = new JButton("00");// 00键
		jb[12] = new JButton("←");// 退格键
		jb[13] = new JButton("上翻");// 上翻键
		jb[14] = new JButton("下翻");// 下翻键
		jb[15] = new JButton("修改");// 修改键

		JButton login = new JButton("确认");// 登录确认键
		JButton cancel = new JButton("取消");// 取消操作键

		// 键盘面板添加按键
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
			jb[i].addActionListener(this);// 单击事件设置
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
				AccessConnect conn = new AccessConnect();// 建立数据库的连接
				conn.selectCardNum(idField.getText());// 调用查找卡号的方法
				conn.getEnable();// 得到当前卡的状态
				if (!(conn.getCardNum().equals(""))) {
					// 卡号正确时，执行以下操作
					if (conn.getEnable() != 0) {
						// 当卡未被吞没时执行以下操作
						personal = new CustomerInform(idField.getText());// 实例化一个用户，传入当前卡号作为参数
						// 输入的密码和用户密码不相同时，执行以下操作
						if (!(passwordField.getText().equals(personal
								.getPassword()))) {
							JOptionPane.showMessageDialog(null,
									"密码和账户不匹配,请重新输入", "警告", 1);// 弹出对话框
							passwordField.setText(null);// 清空密码域
							personal.setError();// 数据库中卡的错误次数加一
						} else {
							// 密码正确后，执行以下操作
							MainFrame mainFrame = new MainFrame(personal);// 实例化服务选择面板
							ATMFrame.c.remove(1);
							ATMtest.frame.repaint();
							ATMFrame.c.add(mainFrame);
							ATMtest.frame.validate();
							personal.setReError();// 更新数据库中密码输错次数为0次
						}
					} else {
						// 卡被吞没后，执行以下操作
						JOptionPane.showMessageDialog(null, "卡已吞没，无效", "警告", 1);// 弹出对话框
						idField.setText(null);// 卡号域清空
						passwordField.setText(null);// 密码域清空
					}
				} else {
					// 卡号输错的情况下，执行以下操作
					JOptionPane.showMessageDialog(null, "账户不存在,请重新输入", "警告", 1);// 弹出对话框
					idField.setText(null);
					passwordField.setText(null);
				}
				try {
					try {
						i = personal.getError();// 得到当前数据库中用户输错密码的次数
					} catch (Exception el) {

					}
					if (i >= 3) {
						// 输错次数超过3次后，执行以下操作
						System.out.println("enable=0");
						System.out.println(idField.getText());
						conn.updateEnable(idField.getText());// 更新数据库中的卡状态为被吞没
						idField.setText(null);
						passwordField.setText(null);
						input = 1;// 当文本域为卡号域
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

		// 取消按钮事件
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				WelcomeFrame welcomFrame = new WelcomeFrame();// 实例化欢迎面板
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
		JTextField t = null;// 指定当前的文本域
		if (input == 1) {
			t = idField;// input=1时，指向卡号域
		} else if (input == 0) {
			t = passwordField;// input=0时，指向密码域
		}
		// 0~9及小数点，0,00键事件设置，按下后在当前文本域内容后面继续添加内容
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
			// 退格键
			String s = t.getText();// 得到当前文本域的内容
			if (s.length() > 0) {
				// 内容不为空
				s = s.substring(0, s.length() - 1);// 内容字符串的长度减一
				t.setText(s);// 将字符串重新添加到文本域中
			}
		}
		if (e.getSource() == jb[13]) {
			// 上翻
			idField.setBackground(null);// 卡号域背景为空
			passwordField.setBackground(null);// 密码域背景为空
			input = 1;// 设置当前文本域为卡号域
			idField.setOpaque(true);
			passwordField.setOpaque(false);
			idField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[14]) {
			// 下翻
			idField.setBackground(null);
			passwordField.setBackground(null);
			input = 0;// 设置当前的文本域为密码域
			passwordField.setOpaque(true);
			idField.setOpaque(false);
			passwordField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[15]) {
			// Cancel
			if (input == 1) {
				idField.setText("");// 清空卡号域
			} else if (input == 0) {
				passwordField.setText("");// 清空密码域
			}
		}
	}

	// 文本域鼠标单击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		idField.setBackground(null);
		passwordField.setBackground(null);
		if (e.getSource() == idField) {
			input = 1;// 设置当前文本域为卡号域
			idField.setOpaque(true);
			passwordField.setOpaque(false);
			idField.setBackground(Color.WHITE);
		} else if (e.getSource() == passwordField) {
			input = 0;// 设置当前文本域为密码域
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
