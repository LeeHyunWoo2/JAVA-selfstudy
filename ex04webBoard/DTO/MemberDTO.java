package ex04webBoard.DTO;

public class MemberDTO {

	private String id;
	private String pw;
	private String nick;
	private String email;


	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNick() {
		return nick;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
