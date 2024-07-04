package ex01board.blackJack;

public class Rule {

	private static final int BLACKJACK_NUM = 21;
	public static boolean victory = false;
	public static boolean draw= false;

	// 승패 확인
	public void getWinner(int dealerSum, int gamerSum) {

		int dealerMinus = BLACKJACK_NUM - dealerSum;
		int gamerMinus = BLACKJACK_NUM - gamerSum;

		if (dealerMinus > gamerMinus) {
		//	System.out.println("플레이어 승리");
			victory = true;
		} else if (dealerMinus == gamerMinus) {
		//	System.out.println("무승부");
			draw = true;
		} else {
		//	System.out.println("딜러 승리");
			victory = false;
		}
	}

	// 버스트 확인
	public boolean isBust(Player player, int sum) {
		String name = player.getName();
		if (sum > 21) {
			System.out.println(name + " Bust");
			victory = false;
			return true;
		} else {
			return false;
		}
	}
}