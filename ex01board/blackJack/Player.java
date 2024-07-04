package ex01board.blackJack;

public interface Player {
	
	void getCard(Card card);
	void printCards();
	int getSum();
	String getName(); 

}
