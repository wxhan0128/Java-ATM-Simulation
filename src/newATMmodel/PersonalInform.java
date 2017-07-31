package newATMmodel;

public interface PersonalInform {
	public String getID();

	public void setPassword(String newPassword);

	public String getPassword();

	public void setRental(double r);

	public double getRental();

	public void setError();

	public void setReError();

	public int getError();

	public void setHistory(String time, String type, String event);

	public String Display();

	public int getEnable();

}