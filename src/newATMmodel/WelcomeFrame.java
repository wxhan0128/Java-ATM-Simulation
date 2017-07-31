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
		init();// ����������
	}

	public void init() {
		this.setOpaque(false);// �����͸��������ʾ����ͼƬ
		this.setLayout(new BorderLayout());// ����岼��Ϊ�߿򲼾�
		this.setBounds(0, 60, 580, 390);// ����λ��
		JButton ok = new JButton("ȷ��");// ȷ�ϰ�ť
		JPanel surePanel = new JPanel();// ȷ����壬��ʾȷ�ϰ�ť

		surePanel.setOpaque(false);// ȷ�����͸����
		surePanel.setBorder(BorderFactory.createTitledBorder("ȷ�Ͻ���"));
		surePanel.setLayout(new BorderLayout());// ȷ�����Ϊ�߿��ǲ���

		surePanel.add(ok, BorderLayout.EAST);// ȷ�������Ӱ�ť

		this.add(surePanel, BorderLayout.SOUTH);// ȷ�������뵽�������

		// ȷ�ϰ�ť�����¼�
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// ���°�������������
				LanguageChooseFrame languageFrame = new LanguageChooseFrame();// ʵ��������ѡ�����

				ATMFrame.c.remove(1);// һ����ǰ�Ļ�ӭ���
				ATMtest.frame.repaint();// �ػ����
				ATMFrame.c.add(languageFrame);// �������ѡ�����
				ATMtest.frame.validate();
			}
		});
	}

}
