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
		init();// ����ѡ�����������
	}

	public void init() {
		this.setOpaque(false);// ���������Ϊ͸��
		this.setLayout(new BorderLayout());// �����Ĳ���Ϊ�߿�ʽ����
		this.setBounds(0, 60, 580, 390);// ������λ�úʹ�С����
		JButton Chinese = new JButton("����");// ���İ�ť
		JButton English = new JButton("English");// Ӣ�İ�ť
		JPanel languagePanel = new JPanel();// �������

		languagePanel.setOpaque(false);// �����������Ϊ͸��
		languagePanel.setBorder(BorderFactory.createTitledBorder("����ѡ��"));
		languagePanel.setLayout(new BorderLayout());// �����������Ϊ�߿�ʽ����

		languagePanel.add(Chinese, BorderLayout.EAST);// ���İ�ť���
		languagePanel.add(English, BorderLayout.WEST);// Ӣ�İ�ť���

		add(languagePanel, BorderLayout.SOUTH);// �������������������

		// ���İ�ť�¼�
		Chinese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// ��ť���µ���������
				ReminderFrame reminderFrame = new ReminderFrame();// ʵ�����Ķ���ʾ���
				ATMFrame.c.remove(1);// �Ƴ��������
				ATMtest.frame.repaint();// �ػ����
				ATMFrame.c.add(reminderFrame);// ����Ķ���ʾ���
				ATMtest.frame.validate();
			}
		});

		// Ӣ�İ�ť�¼�
		English.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// ��ť������������
				ReminderFrame reminderFrame = new ReminderFrame();// ʵ�����Ķ���ʾ���
				ATMFrame.c.remove(1);// �Ƴ��������
				ATMtest.frame.repaint();// �ػ����
				ATMFrame.c.add(reminderFrame);// ����Ķ���ʾ���
				ATMtest.frame.validate();
			}
		});
	}
}
