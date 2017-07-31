package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ATMFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3885378445666567851L;
	private int width = 600;// 窗口宽
	private int height = 500;// 窗口高
	public static Container c;// 内容面板

	public ATMFrame() {
		ImageIcon backgroudPic = new ImageIcon(getClass().getResource(
				"face.jpg"));// 导入窗口的背景图片
		JLabel label = new JLabel(backgroudPic);// 背景图片加入到JLabel中
		label.setBounds(0, 0, backgroudPic.getIconWidth(),
				backgroudPic.getIconHeight());// 设置图片的大小和位置
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();// 获取当前屏幕的大小

		c = (JPanel) this.getContentPane();// 实例化一个内容面板
		((JComponent) c).setOpaque(false);
		c.setLayout(null);// 设置为绝对布局

		int screenHeight = screenSize.height;// 保存当前屏幕的高
		int screenWidth = screenSize.width;// 保存当前屏幕的宽
		this.setTitle("ATM取款机");// 窗口的票提
		this.setResizable(false);// 禁止窗口大小改变
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 关闭窗口后即退出程序
		this.pack();
		this.setBounds(screenWidth / 2 - width / 2, screenHeight / 2 - height
				/ 2, width, height);// 窗口在屏幕中央显示

		JLabel titleMessage = new JLabel("欢迎使用模拟ATM机");// 欢迎文字

		JPanel messagePanel = new JPanel();// 欢迎面板，用于显示欢迎文字
		messagePanel.setOpaque(false);// 面板设置为透明
		messagePanel.setBounds(0, 0, 580, 60);// 面板位置及大小设置
		messagePanel.setBorder(BorderFactory.createTitledBorder("信息"));

		messagePanel.add(titleMessage, BorderLayout.CENTER);// 将文字显示在边框布局的中央
		WelcomeFrame welcomeFrame = new WelcomeFrame();// 实例化欢迎界面

		c.add(messagePanel);
		c.add(welcomeFrame);
	}
}
