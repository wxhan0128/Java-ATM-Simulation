package newATMmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WithdrawalSuccessFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4085187485255219484L;

	private PersonalInform personal = null;// 初始化用户信息对象变量

	public WithdrawalSuccessFrame(PersonalInform personal) {
		this.personal = personal;// 得到上个面板的对象
		init();// 主方法
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		JButton showBalance = new JButton("显示余额");
		JButton continueTo = new JButton("继续");
		JButton exit = new JButton("退卡");

		JPanel choosePanel = new JPanel();
		JPanel exitPanel = new JPanel();

		choosePanel.setOpaque(false);
		choosePanel.setBorder(BorderFactory.createTitledBorder("选项"));
		choosePanel.setLayout(new FlowLayout());

		exitPanel.setOpaque(false);
		exitPanel.setBorder(BorderFactory.createTitledBorder("退出"));
		exitPanel.setLayout(new BorderLayout());

		choosePanel.add(showBalance);
		choosePanel.add(continueTo);
		exitPanel.add(exit, BorderLayout.WEST);

		add(choosePanel, BorderLayout.CENTER);
		add(exitPanel, BorderLayout.SOUTH);

		showBalance.addActionListener(new ActionListener() {
			// 显示余额
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"余额: " + personal.getRental() + "元", "显示余额", 1);// 弹出对话框，获取当前用户在数据库中的余额信息
			}
		});

		continueTo.addActionListener(new ActionListener() {
			// 继续
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

		exit.addActionListener(new ActionListener() {
			// 退卡
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "请在30秒内取走您的卡片", "提示", 1); // test1
				WelcomeFrame chooseCardFrame = new WelcomeFrame();
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(chooseCardFrame);
				ATMtest.frame.validate();
			}
		});
	}
}
