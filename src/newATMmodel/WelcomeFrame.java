package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WelcomeFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8060209887587252915L;

	public WelcomeFrame() {
		init();// 面板的主方法
	}

	public void init() {
		this.setOpaque(false);// 主面板透明，以显示背景图片
		this.setLayout(new BorderLayout());// 主面板布局为边框布局
		this.setBounds(0, 60, 580, 390);// 面板的位置
		JButton ok = new JButton("确认");// 确认按钮
		JPanel surePanel = new JPanel();// 确认面板，显示确认按钮

		surePanel.setOpaque(false);// 确认面板透明化
		surePanel.setBorder(BorderFactory.createTitledBorder("确认进入"));
		surePanel.setLayout(new BorderLayout());// 确认面板为边框是布局

		surePanel.add(ok, BorderLayout.EAST);// 确认面板添加按钮

		this.add(surePanel, BorderLayout.SOUTH);// 确认面板加入到主面板中

		// 确认按钮单击事件
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// 按下按键的声音设置
				LanguageChooseFrame languageFrame = new LanguageChooseFrame();// 实例化语言选择面板

				ATMFrame.c.remove(1);// 一出当前的欢迎面板
				ATMtest.frame.repaint();// 重绘组件
				ATMFrame.c.add(languageFrame);// 添加语言选择面板
				ATMtest.frame.validate();
			}
		});
	}

}
