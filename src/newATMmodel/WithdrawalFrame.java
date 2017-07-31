package newATMmodel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WithdrawalFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8019813829258839032L;
	private PersonalInform personal = null;// ��ʼ���û��������
	private String time = null;// ��ʼ��ʱ�����
	// ȡ���ť
	private JButton oneHun;
	private JButton threeHun;
	private JButton fiveHun;
	private JButton sevenHun;
	private JButton thousand;
	private JButton twoThousand;
	private JButton extra;
	private JButton cancel;

	public WithdrawalFrame(PersonalInform personal) {
		this.personal = personal;// �õ�����������е��û���Ϣ����
		init();// ������
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		time = dateFormat.format(now);
		System.out.println(personal.getPassword());

		oneHun = new JButton("100");
		threeHun = new JButton("300");
		fiveHun = new JButton("500");
		sevenHun = new JButton("700");
		thousand = new JButton("1000");
		twoThousand = new JButton("2000");
		extra = new JButton("�������");
		cancel = new JButton("ȡ��");

		JPanel moneyChoosePanel = new JPanel();// ѡ�������
		JPanel otherChoosePanel = new JPanel();// ����ѡ�����

		moneyChoosePanel.setOpaque(false);
		moneyChoosePanel.setBorder(BorderFactory.createTitledBorder("ѡ����"));
		moneyChoosePanel.setLayout(new GridLayout(3, 3, 100, 100));

		otherChoosePanel.setOpaque(false);
		otherChoosePanel.setBorder(BorderFactory.createTitledBorder("����"));
		otherChoosePanel.setLayout(new BorderLayout());

		moneyChoosePanel.add(oneHun);
		moneyChoosePanel.add(threeHun);
		moneyChoosePanel.add(fiveHun);
		moneyChoosePanel.add(sevenHun);
		moneyChoosePanel.add(thousand);
		moneyChoosePanel.add(twoThousand);

		otherChoosePanel.add(cancel, BorderLayout.WEST);
		otherChoosePanel.add(extra, BorderLayout.EAST);

		add(moneyChoosePanel, BorderLayout.CENTER);
		add(otherChoosePanel, BorderLayout.SOUTH);

		oneHun.addActionListener(this);

		threeHun.addActionListener(this);

		fiveHun.addActionListener(this);

		sevenHun.addActionListener(this);

		thousand.addActionListener(this);

		twoThousand.addActionListener(this);

		extra.addActionListener(new ActionListener() {
			// �������
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				ExtraMoneyFrame extraMoneyFrame = new ExtraMoneyFrame(personal);// ʵ����������������󣬴��뵱ǰ�û�����
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(extraMoneyFrame);
				ATMtest.frame.validate();
			}
		});

		cancel.addActionListener(new ActionListener() {
			// ȡ��
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i = 0;// ��ʼ����ǰѡ�������F
		if (e.getSource() == oneHun) {
			i = 100;
		} else if (e.getSource() == threeHun) {
			i = 300;
		} else if (e.getSource() == fiveHun) {
			i = 500;
		} else if (e.getSource() == sevenHun) {
			i = 700;
		} else if (e.getSource() == thousand) {
			i = 1000;
		} else if (e.getSource() == twoThousand) {
			i = 2000;
		}
		Toolkit.getDefaultToolkit().beep();
		if (personal.getRental() < i) {
			// ��ѡ�������û������
			JOptionPane.showMessageDialog(null, "����,�޷�ȡ��", "����", 1);
		} else {
			personal.setRental(personal.getRental() - i);// �޸��û������
			System.out.println(personal.getRental());
			personal.setHistory(time, "��ȡ",
					i + "Ԫ" + " ��� " + personal.getRental() + "Ԫ");// ���뵱ǰ��ʷ��¼�����ݿ���
			System.out.println(personal.getRental());
			WithdrawalSuccessFrame withdrawalSuccessFrame = new WithdrawalSuccessFrame(
					personal);// ʵ����ȡ��ɹ������󣬴��뵱ǰ�û�����
			ATMFrame.c.remove(1);
			ATMtest.frame.repaint();
			ATMFrame.c.add(withdrawalSuccessFrame);
			ATMtest.frame.validate();
		}
	}

}
