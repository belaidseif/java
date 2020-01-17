package application;

import departement.Departement;

public class OutOfHoraireException extends Exception {
	private String msg;
	public OutOfHoraireException(Departement d) {
		msg = d.getName();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
