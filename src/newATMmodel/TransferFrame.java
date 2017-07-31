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
	private JTextField otherIdField = null;// 卡号域
	private JTextField transferField = null;// 金额域
	private PersonalInform personal = null;// 声明用户对象
	private String time = null;// 用于保存当前时间
	private JButton[] jb = new JButton[16];// 模拟键盘按钮
	private double c = 0;// 初始化转账金额变量
	private double charge = 0;// 初始化手续费变量
	private int input = 0;// 初始化文本域指定变量

	public TransferFrame(PersonalInform personal) {
		this.personal = personal;// 得到服务选择面板的用户对象
		init();// 转账面板主方法
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();// 实例化日期对象
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 实例化日期格式对象
		time = dateFormat.format(now);// 获取当前时间

		JButton ok = new JButton("确定");
		JButton cancel = new JButton("取消");

		// 卡号域和金额域的属性设置
		otherIdField = new JTextField(10);
		transferField = new JTextField(10);
		otherIdField.setEditable(false);
		transferField.setEditable(false);
		otherIdField.setBackground(Color.WHITE);
		transferField.setBackground(null);
		transferField.setOpaque(false);
		otherIdField.addMouseListener(this);// 卡号域的鼠标单击事件
		transferField.addMouseListener(this);// 金额域的鼠标单击事件

		JLabel otherIdLabel = new JLabel("请输入要转账的卡号:");
		JLabel transferLabel = new JLabel("请输入金额:");

		JPanel transferPanel = new JPanel();// 转账面板
		JPanel enterPanel = new JPanel();
		JPanel keyboard = new JPanel();// 模拟键盘面板
		JPanel p = new JPanel();

		p.setOpaque(false);
		p.setLayout(null);
		p.setBorder(BorderFactory.createTitledBorder("转账"));

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

		transferPanel.setOpaque(false);
		transferPanel.setLayout(new GridLayout(2, 2, 10, 10));

		enterPanel.setOpaque(false);
		enterPanel.setBorder(BorderFactory.createTitledBorder("选择"));
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
			// 确定键
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AccessConnect conn = new AccessConnect();// 建立数据库连接
				conn.selectCardNum(otherIdField.getText());// 查找新的卡号
				try {
					c = Double.parseDouble(transferField.getText());// 将金额域中的字符串转化为双精度，捕字符串为空是的异常
				} catch (Exception el) {

				}
				if (!(conn.getCardNum().equals("") || (conn.getCardNum()
						.equals(personal.getID())))) {
					// 新卡号存在与数据库中或新卡号与当前用户的卡号不等时
					PersonalInform otherPersonal = new CustomerInform(
							otherIdField.getText());// 实例化新的用户对象
					if (otherPersonal.getEnable() != 0) {
						// 新用户卡的状态为可使用
						if (!(transferField.getText().equals(""))) {
							// 输入卡号的情况
							if (c > 50000) {
								// 金额超过上限50000
								JOptionPane.showMessageDialog(null,
										"金额超过上限,请输入小于50000元的金额", "警告", 1);
								transferField.setText(null);
							} else if (c <= personal.getRental()) {
								// 输入的金额小于用户的余额
								if (c > 100) {
									// 金额大于100时
									charge = (c / 100) * 1.0;// 计算手续费
								} else {
									charge = 0;
								}
								System.out.println(charge);
								personal.setRental(personal.getRental() - c
										- charge);// 当前用户余额扣除
								otherPersonal.setRental(otherPersonal
										.getRental() + c);// 新用户余额增加
								personal.setHistory(time, "转账", c + "元" + " 余额"
										+ personal.getRental() + "元" + " 手续费"
										+ charge);// 插入当前记录到当前用户的历史事件表中
								otherPersonal.setHistory(time, "汇兑", c + "元"
										+ " 余额" + otherPersonal.getRental()
										+ "元");// 插入当前记录到新用户的历史事件表中
								WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
										personal);// 实例化取款成功面板
								ATMFrame.c.remove(1);
								ATMtest.frame.repaint();
								ATMFrame.c.add(withdrawalSuccessFrame);
								ATMtest.frame.validate();
							} else {
								// 余额小于所输金额
								JOptionPane.showMessageDialog(null,
										"余额不足,无法取出", "警告", 1);
								transferField.setText(null);
							}
						} else {
							// 未输入金额
							JOptionPane.showMessageDialog(null, "请输入金额", "警告",
									1);
						}
					} else {
						// 卡不可用
						JOptionPane.showMessageDialog(null, "该账户已被吞没,无法进行转账",
								"警告", 1);
						otherIdField.setText(null);
						transferField.setText(null);
					}
				} else {
					// 卡号输入错误
					JOptionPane.showMessageDialog(null, "账户错误,请重新输入", "警告", 1);
					otherIdField.setText(null);
					transferField.setText(null);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// 取消
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
			// ←
			String s = t.getText();
			if (s.length() > 0) {
				s = s.substring(0, s.length() - 1);
				t.setText(s);
			}
		}
		if (e.getSource() == jb[13]) {
			// 上翻
			otherIdField.setBackground(null);
			transferField.setBackground(null);
			input = 0;
			otherIdField.setOpaque(true);
			transferField.setOpaque(false);
			otherIdField.setBackground(Color.WHITE);
		}
		if (e.getSource() == jb[14]) {
			// 下翻
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
