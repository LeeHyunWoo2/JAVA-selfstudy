package ex04webBoard.DTO;

public class BoardDTO {

	private String title;
	private String content;
	private String nick;
	private String regDate;
	private int num;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getNick() {
		return nick;
	}

	public String getRegDate() {
		return regDate;
	}

	public int getNum() {
		return num;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
