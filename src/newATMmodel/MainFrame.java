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
	private PersonalInform personal = null;// �����û��������

	public MainFrame(PersonalInform personal) {
		this.personal = personal;// �õ���¼�����û�����
		init();// ����ѡ�����������
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		System.out.println(personal.getPassword());

		JButton exit = new JButton("�˿�");// �˿���ť
		JButton search = new JButton("��ѯ");// ��ѯ��ť
		JButton withdrawal = new JButton("ȡ��");// ȡ�ť
		JButton transfer = new JButton("ת��");// ת�˰�ť
		JButton alterPassword = new JButton("�޸�����");// �޸����밴ť
		// alterPassword.setContentAreaFilled(false);

		JPanel mainPanel = new JPanel();// �����
		JPanel expendPanel = new JPanel();// ��չ���

		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createTitledBorder("��Ҫ����"));
		mainPanel.setLayout(new GridLayout(2, 2, 200, 100));

		expendPanel.setOpaque(false);
		expendPanel.setBorder(BorderFactory.createTitledBorder("����ѡ��"));
		expendPanel.setLayout(new BorderLayout());

		mainPanel.add(transfer);
		mainPanel.add(search);
		mainPanel.add(alterPassword);
		mainPanel.add(withdrawal);

		expendPanel.add(exit, BorderLayout.WEST);

		add(mainPanel, BorderLayout.CENTER);
		add(expendPanel, BorderLayout.SOUTH);

		transfer.addActionListener(new ActionListener() {
			// ת��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				TransferFrame transferFrame = new TransferFrame(personal);// ʵ����ת����壬ת�뵱ǰ�û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(transferFrame);
				ATMtest.frame.validate();
			}
		});

		withdrawal.addActionListener(new ActionListener() {
			// ȡ��
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				WithdrawalFrame withdrawalFrame = new WithdrawalFrame(personal);// ʵ����ȡ����壬���뵱ǰ�û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(withdrawalFrame);
				ATMtest.frame.validate();
			}
		});

		alterPassword.addActionListener(new ActionListener() {
			// �޸�����
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				AlterPasswordFrame alterPasswordFrame = new AlterPasswordFrame(
						personal);// ʵ�����޸�������壬���뵱ǰ�û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(alterPasswordFrame);
				ATMtest.frame.validate();
			}
		});

		search.addActionListener(new ActionListener() {
			// ��ѯ
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				SearchDetailsFrame searchDetailsFrame = new SearchDetailsFrame(
						personal);// ʵ������ѯ��壬�����û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(searchDetailsFrame);
				ATMtest.frame.validate();
			}
		});

		exit.addActionListener(new ActionListener() {
			// �˿�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "����30����ȡ�����Ŀ�Ƭ", "��ʾ", 1);
				WelcomeFrame welcomeFrame = new WelcomeFrame();// ʵ������ӭ���
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(welcomeFrame);
				ATMtest.frame.validate();
			}
		});
	}
}
