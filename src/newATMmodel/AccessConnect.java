package newATMmodel;

import java.sql.*;

public class AccessConnect {
	private Connection con;// ��ʼ�����ݿ����Ӷ������
	private Statement stm;// ��ʼ�����������
	private ResultSet rs;// ��ʼ�������
	private String cardNum = "";// ��ʼ�����ű���
	private String password = "";// ��ʼ���������
	private int enable = 0;// ��ʼ����״̬����
	private StringBuffer event = new StringBuffer();// ʵ����һ���¼��ַ�������
	private double rental = 0;// ��ʼ��������
	private int error = 0;// ��ʼ�������������

	public void selectCardNum(String i) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			rs = stm.executeQuery("select * from member where cardNum='" + i
					+ "'");
			while (rs.next()) {
				cardNum = rs.getString(2);
				password = rs.getString(3);
				rental = rs.getDouble(4);
				enable = rs.getInt(5);
				error = rs.getInt(6);
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void updatePassword(String i, String newPassword) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			String sql = "update member set password='" + newPassword
					+ "' where cardNum='" + i + "'";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				cardNum = rs.getString(2);
				password = rs.getString(3);
				System.out.println("���³ɹ�!");
				System.out.println("qwqwe" + password);
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void updateRental(String i, double newRental) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			System.out.println("qwqwe");
			String sql = "update member set rental='" + newRental
					+ "' where cardNum='" + i + "'";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				cardNum = rs.getString(2);
				rental = rs.getDouble(4);
			}
			System.out.println("���³ɹ�!");
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void updateEnable(String i) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			String sql = "update member set enable=0 where cardNum='" + i + "'";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				enable = rs.getInt(5);
				System.out.println("���³ɹ�!");
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void updateError(String i) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			String sql = "update member set errNum='" + ((getError()) + 1)
					+ "' where cardNum='" + i + "'";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				cardNum = rs.getString(2);
				System.out.println("���³ɹ�!");
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void updateReError(String i) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			String sql = "update member set errNum=0 where cardNum='" + i + "'";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				cardNum = rs.getString(2);
				System.out.println("���³ɹ�!");
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void insertHistory(String no, String time, String type,
			String history) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			String sql = "insert into historyevent(cardNum,eventTime,type,event) values ('"
					+ no
					+ "'"
					+ ","
					+ "'"
					+ time
					+ "'"
					+ ","
					+ "'"
					+ type
					+ "'" + "," + "'" + history + "')";
			boolean isSuccess = stm.execute(sql);
			while (isSuccess) {
				System.out.println("����ɹ�!");
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public void displayHistory(String no) {
		event.delete(0, event.length());
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("abc" + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:test1", "", "");
			stm = con.createStatement();
			rs = stm.executeQuery("select * from historyevent where cardNum='"
					+ no + "'");
			while (rs.next()) {
				event.append(rs.getString(3));
				event.append(" " + rs.getString(4));
				event.append(" " + rs.getString(5));
				event.append("\n");
				System.out.println(event);
			}
			con.close();
		} catch (SQLException el) {
			System.out.println(" " + el);
		}
	}

	public String getCardNum() {
		return cardNum;
	}

	public String getPassword() {
		return password;
	}

	public double getRental() {
		return rental;
	}

	public int getEnable() {
		return enable;
	}

	public String getEvent() {
		return event.toString();
	}

	public int getError() {
		return error;
	}
}