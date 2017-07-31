package newATMmodel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WithdrawalFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019813829258839032L;
	private PersonalInform personal = null;// 初始化用户对象变量
	private String time = null;// 初始化时间变量
	// 取款金额按钮
	private JButton oneHun;
	private JButton threeHun;
	private JButton fiveHun;
	private JButton sevenHun;
	private JButton thousand;
	private JButton twoThousand;
	private JButton extra;
	private JButton cancel;

	public WithdrawalFrame(PersonalInform personal) {
		this.personal = personal;// 得到主服务面板中的用户信息对象
		init();// 主方法
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		time = dateFormat.format(now);
		System.out.println(personal.getPassword());

		oneHun = new JButton("100");
		threeHun = new JButton("300");
		fiveHun = new JButton("500");
		sevenHun = new JButton("700");
		thousand = new JButton("1000");
		twoThousand = new JButton("2000");
		extra = new JButton("其他金额");
		cancel = new JButton("取消");

		JPanel moneyChoosePanel = new JPanel();// 选择金额面板
		JPanel otherChoosePanel = new JPanel();// 其他选择面板

		moneyChoosePanel.setOpaque(false);
		moneyChoosePanel.setBorder(BorderFactory.createTitledBorder("选择金额"));
		moneyChoosePanel.setLayout(new GridLayout(3, 3, 100, 100));

		otherChoosePanel.setOpaque(false);
		otherChoosePanel.setBorder(BorderFactory.createTitledBorder("其他"));
		otherChoosePanel.setLayout(new BorderLayout());

		moneyChoosePanel.add(oneHun);
		moneyChoosePanel.add(threeHun);
		moneyChoosePanel.add(fiveHun);
		moneyChoosePanel.add(sevenHun);
		moneyChoosePanel.add(thousand);
		moneyChoosePanel.add(twoThousand);

		otherChoosePanel.add(cancel, BorderLayout.WEST);
		otherChoosePanel.add(extra, BorderLayout.EAST);

		add(moneyChoosePanel, BorderLayout.CENTER);
		add(otherChoosePanel, BorderLayout.SOUTH);

		oneHun.addActionListener(this);

		threeHun.addActionListener(this);

		fiveHun.addActionListener(this);

		sevenHun.addActionListener(this);

		thousand.addActionListener(this);

		twoThousand.addActionListener(this);

		extra.addActionListener(new ActionListener() {
			// 其他金额
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				ExtraMoneyFrame extraMoneyFrame = new ExtraMoneyFrame(personal);// 实例化其他金额面板对象，传入当前用户对象
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(extraMoneyFrame);
				ATMtest.frame.validate();
			}
		});

		cancel.addActionListener(new ActionListener() {
			// 取消
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				MainFrame mainFrame = new MainFrame(personal);// 实例化主服务面板对象，传入当前用户对象
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
		int i = 0;// 初始化当前选择金额变量F
		if (e.getSource() == oneHun) {
			i = 100;
		} else if (e.getSource() == threeHun) {
			i = 300;
		} else if (e.getSource() == fiveHun) {
			i = 500;
		} else if (e.getSource() == sevenHun) {
			i = 700;
		} else if (e.getSource() == thousand) {
			i = 1000;
		} else if (e.getSource() == twoThousand) {
			i = 2000;
		}
		Toolkit.getDefaultToolkit().beep();
		if (personal.getRental() < i) {
			// 所选金额大于用户的余额
			JOptionPane.showMessageDialog(null, "余额不足,无法取出", "警告", 1);
		} else {
			personal.setRental(personal.getRental() - i);// 修改用户的余额
			System.out.println(personal.getRental());
			personal.setHistory(time, "卡取",
					i + "元" + " 余额 " + personal.getRental() + "元");// 插入当前历史记录到数据库中
			System.out.println(personal.getRental());
			WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
					personal);// 实例化取款成功面板对象，传入当前用户对象
			ATMFrame.c.remove(1);
			ATMtest.frame.repaint();
			ATMFrame.c.add(withdrawalSuccessFrame);
			ATMtest.frame.validate();
		}
	}

}
