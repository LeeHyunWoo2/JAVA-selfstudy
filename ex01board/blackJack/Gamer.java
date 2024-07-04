package ex01board.blackJack;

import java.util.Stack;

public class Gamer implements Player {

	private Stack<Card> deck;
	private static final String NAME = "플레이어";
	
	public Gamer() {
		deck = new Stack<Card>(); 
	}
	
	public String getName() {
		return NAME;
	}
	
	// 카드 받음
	@Override
	public void getCard(Card card) {
		this.deck.add(card); 
	}
	
	// 카드 오픈
	@Override
	public void printCards() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n플레이어의 카드\n");
		
		for(Card card : deck) {
			sb.append(card.toString());
			sb.append(" ");
		}
		
		System.out.println(sb.toString() + "\n 총합 : " + this.getSum());
		System.out.println();
		
	}
	
	@Override
	public int getSum() {
		int sum = 0; 
		for(Card card : deck) {
			sum += card.getPoint();
		}
		return sum; 
	}

}
