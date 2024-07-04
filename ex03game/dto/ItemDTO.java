package ex03game.dto;

public class ItemDTO {
	
	public String itemName;     //아이템 이름
	public String description;  //아이템 설명
	public int itemPrice;       //아이템 가격
	public int stock;           //아이템 재고
	
	public ItemDTO(String itemName, String description, int itemPrice, int stock) {
		super();
		this.itemName = itemName;
		this.description = description;
		this.itemPrice = itemPrice;
		this.stock = stock;
	}
}
