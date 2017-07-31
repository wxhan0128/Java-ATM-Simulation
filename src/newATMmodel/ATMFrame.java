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
	private int width = 600;// ���ڿ�
	private int height = 500;// ���ڸ�
	public static Container c;// �������

	public ATMFrame() {
		ImageIcon backgroudPic = new ImageIcon(getClass().getResource(
				"face.jpg"));// ���봰�ڵı���ͼƬ
		JLabel label = new JLabel(backgroudPic);// ����ͼƬ���뵽JLabel��
		label.setBounds(0, 0, backgroudPic.getIconWidth(),
				backgroudPic.getIconHeight());// ����ͼƬ�Ĵ�С��λ��
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();// ��ȡ��ǰ��Ļ�Ĵ�С

		c = (JPanel) this.getContentPane();// ʵ����һ���������
		((JComponent) c).setOpaque(false);
		c.setLayout(null);// ����Ϊ���Բ���

		int screenHeight = screenSize.height;// ���浱ǰ��Ļ�ĸ�
		int screenWidth = screenSize.width;// ���浱ǰ��Ļ�Ŀ�
		this.setTitle("ATMȡ���");// ���ڵ�Ʊ��
		this.setResizable(false);// ��ֹ���ڴ�С�ı�
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// �رմ��ں��˳�����
		this.pack();
		this.setBounds(screenWidth / 2 - width / 2, screenHeight / 2 - height
				/ 2, width, height);// ��������Ļ������ʾ

		JLabel titleMessage = new JLabel("��ӭʹ��ģ��ATM��");// ��ӭ����

		JPanel messagePanel = new JPanel();// ��ӭ��壬������ʾ��ӭ����
		messagePanel.setOpaque(false);// �������Ϊ͸��
		messagePanel.setBounds(0, 0, 580, 60);// ���λ�ü���С����
		messagePanel.setBorder(BorderFactory.createTitledBorder("��Ϣ"));

		messagePanel.add(titleMessage, BorderLayout.CENTER);// ��������ʾ�ڱ߿򲼾ֵ�����
		WelcomeFrame welcomeFrame = new WelcomeFrame();// ʵ������ӭ����

		c.add(messagePanel);
		c.add(welcomeFrame);
	}
}
