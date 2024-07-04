package ex04webBoard.services.child;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ex04webBoard.DTO.MemberDTO;
import ex04webBoard.DTO.child.MovieBoardDTO;
import ex04webBoard.services.BoardServices;


public class MovieBoardServices extends BoardServices {

	public MovieBoardServices(MovieBoardDTO[] movieBoardDTOs) {
		super(movieBoardDTOs);
	}

	@Override
	protected MovieBoardDTO createBoardDTO() {
		MovieBoardDTO movieBoardDTO = new MovieBoardDTO();
		return movieBoardDTO;
	}

	@Override
	public void write(Scanner scanner, MemberDTO[] memberDTOs, MemberDTO loginStatus) {

		MovieBoardDTO writeBoard = (MovieBoardDTO) createBoardDTO();

		System.out.println("동영상 제목");
		writeBoard.setMovie(scanner.next());
		
		System.out.println(" 유튜브 URL ");
		writeBoard.setYoutubeLink(scanner.next());
		
		writeBoard.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));


	}

	@Override
	public void list(Scanner scanner, MemberDTO[] memberDTOs) {

		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				System.out.println("[" + (i + 1) + "] : " + ((MovieBoardDTO) boardDTOs[i]).getTitle() + "\n");
			} // if
		} // for

		System.out.println("열람할 게시물 번호를 입력해주세요.");
		int post = scanner.nextInt();
		for (int i = 0; i < boardDTOs.length; i++) {
			if (boardDTOs[i] != null) {
				if (boardDTOs[i].getNum() == post - 1) {
					System.out.println("\n=================================\n");
					System.out.println("[" + (boardDTOs[i].getNum() + 1) + "]\n" + "제목 : "
							+ ((MovieBoardDTO) boardDTOs[i]).getTitle());
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