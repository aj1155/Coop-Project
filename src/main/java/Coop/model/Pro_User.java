package Coop.model;

public class Pro_User {
	String userId;
	int proId;
	int cont;

	public String getUserId() {
		return userId;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
}
