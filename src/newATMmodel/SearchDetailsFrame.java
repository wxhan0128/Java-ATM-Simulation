package newATMmodel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SearchDetailsFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1446104742428934372L;

	private JTextArea historyShowArea;
	private PersonalInform personal;
	private int i = 0;

	public SearchDetailsFrame(PersonalInform personal) {
		this.personal = personal;
		init();
	}

	public void init() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 60, 580, 390);
		// Toolkit kit = Toolkit.getDefaultToolkit();

		JLabel historyLabel = new JLabel("历史记录查询");

		JButton searchRental = new JButton("查询余额");
		JButton searchDetail = new JButton("查询明细");
		JButton exit = new JButton("退卡");
		JButton continueTo = new JButton("继续");

		JPanel searchChoosePanel = new JPanel();
		JPanel historyShowPanel = new JPanel();
		JPanel cancelPanel = new JPanel();

		searchChoosePanel.setOpaque(false);
		searchChoosePanel.setBorder(BorderFactory.createTitledBorder("选择"));
		searchChoosePanel.setLayout(new FlowLayout());

		historyShowPanel.setOpaque(false);
		historyShowPanel.setBorder(BorderFactory.createTitledBorder("历史记录查询"));
		historyShowPanel.setLayout(new BorderLayout());

		cancelPanel.setOpaque(false);
		cancelPanel.setBorder(BorderFactory.createTitledBorder("其它选项"));
		cancelPanel.setLayout(new BorderLayout());

		historyShowArea = new JTextArea(50, 20);
		historyShowArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(historyShowArea);

		searchChoosePanel.add(searchRental);
		searchChoosePanel.add(searchDetail);

		historyShowPanel.add(searchChoosePanel, BorderLayout.NORTH);
		historyShowPanel.add(historyLabel, BorderLayout.WEST);
		historyShowPanel.add(scrollPane, BorderLayout.CENTER);

		cancelPanel.add(exit, BorderLayout.WEST);
		cancelPanel.add(continueTo, BorderLayout.EAST);

		add(historyShowPanel, BorderLayout.CENTER);
		add(cancelPanel, BorderLayout.SOUTH);

		searchRental.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"余额: " + personal.getRental() + "元", "显示余额", 1);
			}
		});

		searchDetail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				if (i == 0) {
					String display = personal.Display();
					historyShowArea.append(display);
					i = 1;
				} else {
					historyShowArea.append(null);
				}

			}
		});

		continueTo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				historyShowArea.setText(null);
				MainFrame mainFrame = new MainFrame(personal);
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(mainFrame);
				ATMtest.frame.validate();
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();
				historyShowArea.setText(null);
				JOptionPane.showMessageDialog(null, "请在30秒内取走您的卡片", "提示", 1);
				WelcomeFrame welcomeFrame = new WelcomeFrame();
				ATMFrame.c.remove(1);
				ATMtest.frame.repaint();
				ATMFrame.c.add(welcomeFrame);
				ATMtest.frame.validate();
			}
		});

	}
}
