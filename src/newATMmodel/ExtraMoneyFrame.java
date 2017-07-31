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

	private PersonalInform personal = null;// 初始化用户对象变量
	private JTextField inputField = null;
	private String time = null;// 初始化时间变量
	private JButton[] jb = new JButton[16];
	private int c = 0;// 初始化金额变量

	public ExtraMoneyFrame(PersonalInform personal) {
		this.personal = personal;// 的到前一个面板中的用户对象
		init();// 主方法
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		time = dateFormat.format(now);

		JButton ok = new JButton("确定");
		JButton cancel = new JButton("取消");

		JLabel inputLabel = new JLabel("请输入金额");
		inputField = new JTextField(10);
		inputField.setEditable(false);
		inputField.setBackground(Color.WHITE);

		JPanel inputPanel = new JPanel();
		JPanel keyboard = new JPanel();
		JPanel otherPanel = new JPanel();
		JPanel p = new JPanel();

		p.setOpaque(false);
		p.setLayout(null);
		p.setBorder(BorderFactory.createTitledBorder("输入金额"));

		inputPanel.setOpaque(false);
		inputPanel.setLayout(new FlowLayout());

		otherPanel.setOpaque(false);
		otherPanel.setBorder(BorderFactory.createTitledBorder("其他"));
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

		add(p, BorderLayout.CENTER);
		add(otherPanel, BorderLayout.SOUTH);

		ok.addActionListener(new ActionListener() {
			// 确定
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				try {
					c = Integer.parseInt(inputField.getText());// 将文本域中输入的金额转化为整型，捕获字符串为空时的异常
				} catch (Exception el) {

				}
				if (!(inputField.getText().equals(""))) {
					// 输入的金额不为空
					if (c != 0) {
						// 金额不是0元
						if (c % 100 != 0) {
							// 金额不是100的整数倍
							JOptionPane.showMessageDialog(null, "请输入整数金额",
									"警告", 1);
							inputField.setText(null);
						} else if (c > 10000) {
							// 金额超过上限
							JOptionPane.showMessageDialog(null,
									"金额超过上限,请输入小于10000元的金额", "警告", 1);
							inputField.setText(null);
						} else if (personal.getRental() < c) {
							// 用户的余额小于输入的金额
							JOptionPane.showMessageDialog(null, "余额不足，无法取出",
									"警告", 1);
							inputField.setText(null);
						} else {
							// 用户的金额大于输入的金额
							personal.setRental(personal.getRental() - c);// 更新用户的余额信息
							personal.setHistory(time, "卡取", c + "元" + " 余额"
									+ personal.getRental() + "元");// 插入历史记录
							WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
									personal);// 实例化取款成功面板，传入当前用户信息对象
							ATMFrame.c.remove(1);
							ATMtest.frame.repaint();
							ATMFrame.c.add(withdrawalSuccessFrame);
							ATMtest.frame.validate();
						}
					} else {
						// 输入的金额为0
						JOptionPane.showMessageDialog(null, "金额不能为0", "警告", 1);
						inputField.setText("");
					}
				} else {
					// 输入的金额为空
					JOptionPane.showMessageDialog(null, "请输入金额", "警告", 1);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			// 取消
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
			JOptionPane.showMessageDialog(null, "输入无效", "警告", 1);
			// t.setText(t.getText() + ".");
		}
		if (e.getSource() == jb[10]) {
			t.setText(t.getText() + "0");
		}
		if (e.getSource() == jb[11]) {
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
		}
		if (e.getSource() == jb[14]) {
			// 下翻
		}
		if (e.getSource() == jb[15]) {
			// Cancel
			inputField.setText("");
		}
	}
}
