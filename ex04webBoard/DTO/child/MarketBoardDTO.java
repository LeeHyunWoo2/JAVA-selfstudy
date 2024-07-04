package ex04webBoard.DTO.child;

import ex04webBoard.DTO.BoardDTO;

public class MarketBoardDTO extends BoardDTO {

	private String item; // 이미지 기능은 잘 모르니까 대충 이걸로 된다치고
	private int price;
	private boolean nego;

	public String getItem() {
		return item;
	}

	public int getPrice() {
		return price;
	}

	public boolean isNego() {
		return nego;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setNego(boolean nego) {
		this.nego = nego;
	}

}
