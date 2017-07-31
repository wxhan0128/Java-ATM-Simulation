package newATMmodel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReminderFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6554670975812053439L;

	public ReminderFrame() {
		init();// �Ķ���ʾ������
	}

	public void init() {
		this.setOpaque(false);// ���������Ϊ͸������ʾ����
		this.setLayout(new BorderLayout());// ����岼��Ϊ�߿�ʽ����
		this.setBounds(0, 60, 580, 390);// ������С��λ������
		JButton ok = new JButton("ȷ��");// ȷ����ť
		JPanel surePanel = new JPanel();// ȷ�����
		JTextArea jta = new JTextArea();// �����ı������,������д��ʾ��Ϣ
		jta.setOpaque(false);// �ı����������Ϊ͸��
		jta.setBackground(null);// �ı����������Ϊ��
		jta.setEditable(false);// �ı����������Ϊ���ɱ༭
		jta.setLineWrap(true);// �ı�������Զ���������
		jta.setFont(new Font("Serif", Font.PLAIN, 14));
		jta.setText("     һ����������ʱ�����������嵲ס���̣��Է����˿��ӡ�\n"
				+ "     ������Ҫ������͸¶���룻Ҳ��Ҫ�趨���������У���888888����������������Ϊ���롣 \n"
				+ "     ������������������������ڣ�ͨ����Ҫˢ���п��ſɽ��š����ֿ��������ڴ������Լ������п����롣   \n"
				+ "     �ġ���ӡ��ȡ����벻Ҫ���ֶ�����Ӧ���Ʊ��ܻ�ʱ���١�    \n"
				+ "     �塢����ATMʱ��������ֻ����̿����³����ϣ���Ҫ�����뿪������ԭ�ؼ�ʱ����ATM��Ļ����ʾ�����з���绰��ֱ�Ӳ������еĿͻ��������߽���������   \n"
				+ "     ��������ʶ�����й��棬��Ҫ����Ҫ��ͻ���Ǯת��ָ���˻��Ĺ��棬���ִ��๫��Ӧ���������к͹������ؾٱ���     \n"
				+ "     �ߡ�����������п����š��յ������ֻ�����ʱ��Ӧ����ȷ�ϣ���������Ӧֱ�Ӳ��򷢿����пͻ��������߽��в�ѯ����Ҫ��������е���ϵ�绰��    \n"
				+ "     �ˡ���ATM�ϲ�ѯ��ȡ��ʱ��Ҫ����ATM���Ƿ��ж����װ�û�����ͷ����������ʱӦ�������ٲ��������ڵ��������ƣ��Է��⵽���ӡ�    \n"
				+ "     �š�ȡ���Ӧ�����Ʊ��ܻ������٣���Ҫ���ֶ������Է������˼񵽣�����α��ð���");// �ı����������

		surePanel.setOpaque(false);// ȷ���������Ϊ͸��
		surePanel.setBorder(BorderFactory.createTitledBorder("���Ķ���ʾ"));
		surePanel.setLayout(new BorderLayout());// ȷ���������Ϊ�߿�ʽ����F

		surePanel.add(ok, BorderLayout.EAST);// ��ȷ��������ȷ����ť
		add(jta, BorderLayout.CENTER);// ����ı���������������
		add(surePanel, BorderLayout.SOUTH);// ���ȷ������������

		// ����ȷ�ϰ�ť���¼�
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// ȷ�ϰ�ť�����µ���������
				LoginFrame loginFrame = new LoginFrame();// ʵ������¼���
				ATMFrame.c.remove(1);// �Ƴ���ǰ���
				ATMtest.frame.repaint();// �ػ����
				ATMFrame.c.add(loginFrame);// ��ӵ�¼���
				ATMtest.frame.validate();
			}
		});

	}
}
