package newATMmodel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -269722733031031079L;
	private PersonalInform personal = null;// 声明用户对象变量

	public MainFrame(PersonalInform personal) {
		this.personal = personal;// 得到登录面板的用户对象
		init();// 服务选择面板主方法
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		System.out.println(personal.getPassword());

		JButton exit = new JButton("退卡");// 退卡按钮
		JButton search = new JButton("查询");// 查询按钮
		JButton withdrawal = new JButton("取款");// 取款按钮
		JButton transfer = new JButton("转账");// 转账按钮
		JButton alterPassword = new JButton("修改密码");// 修改密码按钮
		// alterPassword.setContentAreaFilled(false);

		JPanel mainPanel = new JPanel();// 主面板
		JPanel expendPanel = new JPanel();// 扩展面板

		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createTitledBorder("主要功能"));
		mainPanel.setLayout(new GridLayout(2, 2, 200, 100));

		expendPanel.setOpaque(false);
		expendPanel.setBorder(BorderFactory.createTitledBorder("其它选项"));
		expendPanel.setLayout(new BorderLayout());

		mainPanel.add(transfer);
		mainPanel.add(search);
		mainPanel.add(alterPassword);
		mainPanel.add(withdrawal);

		expendPanel.add(exit, BorderLayout.WEST);

		add(mainPanel, BorderLayout.CENTER);
		add(expendPanel, BorderLayout.SOUTH);

		transfer.addActionListener(new ActionListener() {
			// 转账
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				TransferFrame transferFrame = new TransferFrame(personal);// 实例化转账面板，转入当前用户对象
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(transferFrame);
				ATMtest.frame.validate();
			}
		});

		withdrawal.addActionListener(new ActionListener() {
			// 取款
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				WithdrawalFrame withdrawalFrame = new WithdrawalFrame(personal);// 实例化取款面板，传入当前用户对象
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(withdrawalFrame);
				ATMtest.frame.validate();
			}
		});

		alterPassword.addActionListener(new ActionListener() {
			// 修改密码
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				AlterPasswordFrame alterPasswordFrame = new AlterPasswordFrame(
						personal);// 实例化修改密码面板，传入当前用户对象
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(alterPasswordFrame);
				ATMtest.frame.validate();
			}
		});

		search.addActionListener(new ActionListener() {
			// 查询
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				SearchDetailsFrame searchDetailsFrame = new SearchDetailsFrame(
						personal);// 实例化查询面板，传入用户对象
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(searchDetailsFrame);
				ATMtest.frame.validate();
			}
		});

		exit.addActionListener(new ActionListener() {
			// 退卡
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "请在30秒内取走您的卡片", "提示", 1);
				WelcomeFrame welcomeFrame = new WelcomeFrame();// 实例化欢迎面板
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(welcomeFrame);
				ATMtest.frame.validate();
			}
		});
	}
}
