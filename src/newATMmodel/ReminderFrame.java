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
		init();// 阅读提示主方法
	}

	public void init() {
		this.setOpaque(false);// 主面板设置为透明以显示背景
		this.setLayout(new BorderLayout());// 主面板布局为边框式布局
		this.setBounds(0, 60, 580, 390);// 主面板大小和位置设置
		JButton ok = new JButton("确定");// 确定按钮
		JPanel surePanel = new JPanel();// 确定面板
		JTextArea jta = new JTextArea();// 声明文本区组件,用来书写提示信息
		jta.setOpaque(false);// 文本区组件设置为透明
		jta.setBackground(null);// 文本区组件背景为空
		jta.setEditable(false);// 文本区组件设置为不可编辑
		jta.setLineWrap(true);// 文本区组件自动换行设置
		jta.setFont(new Font("Serif", Font.PLAIN, 14));
		jta.setText("     一、输入密码时，尽量用身体挡住键盘，以防他人窥视。\n"
				+ "     二、不要向他人透露密码；也不要设定简单数字排列（如888888）或本人生日日期作为密码。 \n"
				+ "     三、在银行自助服务厅的入口，通常需要刷银行卡才可进门。但持卡人无需在此输入自己的银行卡密码。   \n"
				+ "     四、打印的取款单，请不要随手丢弃，应妥善保管或及时销毁。    \n"
				+ "     五、操作ATM时，如果出现机器吞卡或不吐钞故障，不要轻易离开，可在原地及时拨打ATM屏幕上显示的银行服务电话或直接拨打银行的客户服务热线进行求助。   \n"
				+ "     六、认真识别银行公告，不要相信要求客户将钱转到指定账户的公告，发现此类公告应尽快向银行和公安机关举报。     \n"
				+ "     七、警惕虚假银行卡短信。收到可疑手机短信时，应谨慎确认，如有疑问应直接拨打发卡银行客户服务热线进行查询，不要拨打短信中的联系电话。    \n"
				+ "     八、在ATM上查询、取款时，要留意ATM上是否有多余的装置或摄像头；输入密码时应尽量快速并用身体遮挡操作手势，以防遭到窥视。    \n"
				+ "     九、取款单据应该妥善保管或者销毁，不要随手丢弃，以防被他人捡到，制作伪卡冒领存款。");// 文本区组件内容

		surePanel.setOpaque(false);// 确认面板设置为透明
		surePanel.setBorder(BorderFactory.createTitledBorder("请阅读提示"));
		surePanel.setLayout(new BorderLayout());// 确认面板设置为边框式布局F

		surePanel.add(ok, BorderLayout.EAST);// 向确认面板添加确定按钮
		add(jta, BorderLayout.CENTER);// 添加文本区与组件至主面板
		add(surePanel, BorderLayout.SOUTH);// 添加确认面板至主面板

		// 单击确认按钮的事件
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Toolkit.getDefaultToolkit().beep();// 确认按钮被按下的声音设置
				LoginFrame loginFrame = new LoginFrame();// 实例化登录面板
				ATMFrame.c.remove(1);// 移除当前面板
				ATMtest.frame.repaint();// 重绘组件
				ATMFrame.c.add(loginFrame);// 添加登录面板
				ATMtest.frame.validate();
			}
		});

	}
}
