package ex04webBoard.services.child;

import java.util.Scanner;

import ex04webBoard.DTO.MemberDTO;
import ex04webBoard.DTO.child.MarketBoardDTO;
import ex04webBoard.services.BoardServices;


public class MarketBoardServices extends BoardServices {

	public MarketBoardServices(MarketBoardDTO[] marketBoardDTOs) {
		super(marketBoardDTOs);
	}

	@Override
	protected MarketBoardDTO createBoardDTO() {
		MarketBoardDTO marketBoardDTO = new MarketBoardDTO();
		return marketBoardDTO;
	}

	@Override
	public void list(Scanner scanner, MemberDTO[] memberDTOs) {

		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				System.out.println("[" + (i + 1) + "] : " + ((MarketBoardDTO) boardDTOs[i]).getTitle() + "\n");
			}
		}

		System.out.println("열람할 게시물 번호를 입력해주세요.");
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == post - 1) {
					System.out.println("\n=================================\n");
					System.out.println("[" + (boardDTOs[i].getNum() + 1) + "]\n" + "제목 : "
							+ ((MarketBoardDTO) boardDTOs[i]).getTitle());
					System.out.println("작성자 : " + boardDTOs[i].getNick());
					System.out.println(boardDTOs[i].getRegDate());
					System.out.println("\n============== 내용 ==============\n");
					System.out.println(boardDTOs[i].getContent());
					System.out.println("\n=================================\n");
					break;

				} // if

			} // if

		} // for

	}// 리스트 메서드

}