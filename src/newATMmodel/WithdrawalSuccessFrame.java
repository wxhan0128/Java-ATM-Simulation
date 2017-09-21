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

	private PersonalInform personal = null;// ��ʼ���û���Ϣ�������

	public WithdrawalSuccessFrame(PersonalInform personal) {
		this.personal = personal;// �õ��ϸ����Ķ���
		init();// ������
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);

		JButton showBalance = new JButton("��ʾ���");
		JButton continueTo = new JButton("����");
		JButton exit = new JButton("�˿�");

		JPanel choosePanel = new JPanel();
		JPanel exitPanel = new JPanel();

		choosePanel.setOpaque(false);
		choosePanel.setBorder(BorderFactory.createTitledBorder("ѡ��"));
		choosePanel.setLayout(new FlowLayout());

		exitPanel.setOpaque(false);
		exitPanel.setBorder(BorderFactory.createTitledBorder("�˳�"));
		exitPanel.setLayout(new BorderLayout());

		choosePanel.add(showBalance);
		choosePanel.add(continueTo);
		exitPanel.add(exit, BorderLayout.WEST);

		add(choosePanel, BorderLayout.CENTER);
		add(exitPanel, BorderLayout.SOUTH);

		showBalance.addActionListener(new ActionListener() {
			// ��ʾ���
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"���: " + personal.getRental() + "Ԫ", "��ʾ���", 1);// �����Ի��򣬻�ȡ��ǰ�û������ݿ��е������Ϣ
			}
		});

		continueTo.addActionListener(new ActionListener() {
			// ����
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				MainFrame mainFrame = new MainFrame(personal);// ʵ���������������󣬴��뵱ǰ�û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(mainFrame);
				ATMtest.frame.validate();
			}
		});

		exit.addActionListener(new ActionListener() {
			// �˿�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "����30����ȡ�����Ŀ�Ƭ", "��ʾ", 1); // test1
				WelcomeFrame chooseCardFrame = new WelcomeFrame();
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(chooseCardFrame);
				ATMtest.frame.validate();
			}
		});
	}
}
