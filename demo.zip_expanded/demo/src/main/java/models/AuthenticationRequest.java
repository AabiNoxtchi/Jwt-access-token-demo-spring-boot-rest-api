package models;

public class AuthenticationRequest {
	
	private String Name;
	private String Pasword;
	
	
	public AuthenticationRequest() {
		
	}
	
	public AuthenticationRequest(String name, String pasword) {
		
		Name = name;
		Pasword = pasword;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPasword() {
		return Pasword;
	}
	public void setPasword(String pasword) {
		Pasword = pasword;
	}
	

}
