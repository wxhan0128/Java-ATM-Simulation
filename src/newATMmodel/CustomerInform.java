package newATMmodel;

public class CustomerInform implements PersonalInform {
	private AccessConnect conn = new AccessConnect();
	private String no = null;

	public CustomerInform(String no) {
		this.no = no;
		conn.selectCardNum(no);
	}

	public String getID() {
		// TODO Auto-generated method stub
		return conn.getCardNum();
	}

	public void setPassword(String newPassword) {
		conn.updatePassword(no, newPassword);
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		conn.selectCardNum(no);
		return conn.getPassword();
	}

	public void setRental(double newRental) {
		conn.updateRental(no, newRental);
	}

	public double getRental() {
		conn.selectCardNum(no);
		return conn.getRental();
	}

	public void setError() {
		conn.updateError(no);
	}

	public void setReError() {
		conn.updateReError(no);
	}

	public int getError() {
		conn.selectCardNum(no);
		return conn.getError();
	}

	public void setHistory(String historyTime, String type, String event) {
		conn.insertHistory(no, historyTime, type, event);
	}

	public String Display() {
		conn.displayHistory(no);
		return conn.getEvent();
	}

	public int getEnable() {
		conn.selectCardNum(no);
		return conn.getEnable();
	}
}
