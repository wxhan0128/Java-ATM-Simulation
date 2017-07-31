package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LanguageChooseFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8683605907707167844L;

	public LanguageChooseFrame() {
		init();// 语言选择面板主方法
	}

	public void init() {
		this.setOpaque(false);// 主面板设置为透明
		this.setLayout(new BorderLayout());// 主面板的布局为边框式布局
		this.setBounds(0, 60, 580, 390);// 主面板的位置和大小设置
		JButton Chinese = new JButton("中文");// 中文按钮
		JButton English = new JButton("English");// 英文按钮
		JPanel languagePanel = new JPanel();// 语言面板

		languagePanel.setOpaque(false);// 语言面板设置为透明
		languagePanel.setBorder(BorderFactory.createTitledBorder("语言选择"));
		languagePanel.setLayout(new BorderLayout());// 语言面板设置为边框式布局

		languagePanel.add(Chinese, BorderLayout.EAST);// 中文按钮添加
		languagePanel.add(English, BorderLayout.WEST);// 英文按钮添加

		add(languagePanel, BorderLayout.SOUTH);// 添加语言面板至主面板中

		// 中文按钮事件
		Chinese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// 按钮按下的声音设置
				ReminderFrame reminderFrame = new ReminderFrame();// 实例化阅读提示面板
				ATMFrame.c.remove(1);// 移除语言面板
				ATMtest.frame.repaint();// 重绘组件
				ATMFrame.c.add(reminderFrame);// 添加阅读提示面板
				ATMtest.frame.validate();
			}
		});

		// 英文按钮事件
		English.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// 按钮按下声音设置
				ReminderFrame reminderFrame = new ReminderFrame();// 实例化阅读提示面板
				ATMFrame.c.remove(1);// 移除语言面板
				ATMtest.frame.repaint();// 重绘组件
				ATMFrame.c.add(reminderFrame);// 添加阅读提示面板
				ATMtest.frame.validate();
			}
		});
	}
}
