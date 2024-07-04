package ex01board.board;

public class Item {

	int stock; // 재고
	int itemNum; // 물품 번호로 쓸 변수
	String nick; // 닉
	String title; // 글 제목
	String content; // 글 내용
	String regDate; // 글쓴 시간
	int price; // 글의 가격

	public void itemList(Item[] items) {

		Item item1 = new Item();
		item1.itemNum = 1;
		item1.title = "Hello, world!";
		item1.content = "Hello, world!";
		item1.nick = "데니스 리치";
		item1.price = 2100000000;
		item1.stock = 1;
		items[0] = item1;

		Item item2 = new Item();
		item2.itemNum = 2;
		item2.title = "Don't panic!";
		item2.content = "Don't panic!";
		item2.nick = "일론 머스크";
		item2.price = 1000000000;
		item2.stock = 1;
		items[1] = item2;

	}// 상품목록

}// 클래스
