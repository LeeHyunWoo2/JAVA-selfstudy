package ex04webBoard.DTO.child;

import ex04webBoard.DTO.BoardDTO;

public class MemberBoardDTO extends BoardDTO {

	private boolean member;
	private String email;

	public boolean isMember() {
		return member;
	}

	public String getEmail() {
		return email;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
