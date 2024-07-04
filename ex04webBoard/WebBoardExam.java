package ex04webBoard;

import java.util.Scanner;

import ex04webBoard.DTO.*;
import ex04webBoard.DTO.child.*;
import ex04webBoard.services.MemberServices;
import ex04webBoard.services.child.FreeBoardServices;
import ex04webBoard.services.child.MarketBoardServices;
import ex04webBoard.services.child.MemberBoardServices;
import ex04webBoard.services.child.MovieBoardServices;

public class WebBoardExam {
	
	// 주요 포인트
	// BoardServices.java , BoardServices.java , FreeBoardServices.java, MemberServices.java
	// ↑ 이 파일들 위주로 봐주세요
	
	// BoardMenuServices 에서 메뉴에 대한 템플릿 생성
	// 각 자식 클래스 (free, member, movie 등 메뉴) 에서 부모 클래스의 메뉴 형식을 그대로 갖다씀
	// ( 글메뉴, 글쓰기, 글수정 등등 기능을 그냥 불러와서 그대로 갖다쓴다는 소리 )
	
	// 각 자식마다 특징적인 기능은 @Override를 통해서 재정의하거나, 메서드 추가하는식으로 업데이트함
	// 그리고 각 자식 객체는 자신만의 배열에 저장함. <<< 핵심
	
	// 부모클래스에서 부모클래스용 배열에 저장한다는 코드가 있고, 그게 그대로 자식한테 넘어감
	// 그럼 자식들은 전부 부모 배열에 저장을 해버리면서 결국 각 게시판 분리가 안된 상태가됨
	// 근데 그렇다고 오버라이드 하면서 코드내용만 freeBoardDTOs 로 바꾸면
	// 바로 오류가 떠버림. 뒤에 배열이름을 바꾼다고 한들, 배열의 타입이 다르기 때문임. ex) BoardDTO[] vs FreeBoardDTO[]
	// 이 타입까지 바꿔버리려면 강제타입변환이 필요함. 왜냐면 부모타입으로 들어온 배열을 자식타입 배열로 바꿔야하기 때문(큰거 -> 작은거)
	// 그리고 강제타입 변환하기 전에 부모배열을 씻어서 보내야함
	// BoardServices 88번째 줄 참조 (이 과정을 담음)
	
	

	public static Scanner scanner = new Scanner(System.in);
	public static BoardDTO[] boardDTOs = new BoardDTO[1000];
	public static FreeBoardDTO[] freeBoardDTOs = new FreeBoardDTO[1000];
	public static MemberBoardDTO[] memberBoardDTOs = new MemberBoardDTO[1000];
	public static MovieBoardDTO[] movieBoardDTOs = new MovieBoardDTO[1000];
	public static MarketBoardDTO[] itemBoardDTOs = new MarketBoardDTO[1000];
//	public static ReportDTO[] reportDTOs = new ReportDTO[500];
	public static MemberDTO[] memberDTOs = new MemberDTO[100];
	private static MemberDTO loginStatus;

	static {

		MemberDTO admin = new MemberDTO();
		admin.setId("1");
		admin.setPw("2");
		admin.setNick("관리자");
		admin.setEmail("admin@mbc.co.kr");
		memberDTOs[0] = admin;

	} // DB를 아직 안배웠기 때문에, 관리자 정보는 여기에 추가함. (스태틱블럭)

	public static void main(String[] args) {

		System.out.println("대충 홈페이지 이름");

		boolean run = true;
		while (run) {
			System.out.println("1회원 2게시판 3신고하기 4종료");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원 정보 메뉴로 이동합니다.");
				MemberServices memberServices = new MemberServices();
				loginStatus = memberServices.memberServicesMenu(scanner, memberDTOs, loginStatus);
				break;

			case 2:
				boolean run1 = true;
				while (run1) {
					System.out.println("원하는 게시판을 입력해주세요.");
					System.out.println("1.자유게시판 2. 회원게시판 3.영상게시판 4.거래게시판 5.나가기");
					System.out.print(">>>");
					int select1 = scanner.nextInt();
					switch (select1) {
					case 1:
						System.out.println("자유 게시판으로 이동합니다.");
						FreeBoardServices freeBoardServices = new FreeBoardServices(freeBoardDTOs);
						freeBoardServices.BoardTemplate(scanner, memberDTOs, loginStatus, "자유 게시판");
						break;
					case 2:
						if (loginStatus == null) {
							System.out.println("이곳은 회원용 게시판입니다. 로그인 해주세요");
						} else {
							System.out.println("회원 게시판으로 이동합니다.");
							MemberBoardServices memberBoardServices = new MemberBoardServices(memberBoardDTOs);
							memberBoardServices.BoardTemplate(scanner, memberDTOs, loginStatus, "회원 게시판");
						}
						break;
					case 3:
						// 이건 귀찮아서 그냥 일반 게시판 복붙했으니 다른분꺼 보세요
						// 결국 그냥 부모객체 불러서 원하는거 오버라이드에 추가하는 방식임
						System.out.println("동영상 게시판으로 이동합니다.");
						MovieBoardServices movieBoardServices = new MovieBoardServices(movieBoardDTOs);
						movieBoardServices.BoardTemplate(scanner, memberDTOs, loginStatus, "동영상 게시판");
						break;
					case 4:
						System.out.println("중고거래 게시판으로 이동합니다.");
						MarketBoardServices marketBoardServices = new MarketBoardServices(itemBoardDTOs);
						marketBoardServices.BoardTemplate(scanner, memberDTOs, loginStatus, "중고거래 게시판");
						break;
					case 5:
						run1 = false;
						break;
					default:
						System.out.println("1~5");

					}// 스위치

				} // while
				break;
//			case 3:
//				if (loginStatus == null) {
//					System.out.println("허위 신고 방지를 위해 로그인이 필요합니다.");
//				} else {
//					System.out.println("불량회원 신고 메뉴로 이동합니다.");
//					ReportServices reportServices = new ReportServices();
//					reportServices.reportMenu(scanner, memberDTOs, loginStatus);
//				}
//				break;
			// 신고 게시판을 만들고 각종 게시판과 연동해서 관리자가 강퇴하는 기능 추가하려고 했으나
			// 진짜 너무 복잡해져서 시간만 버릴거같아서 캔슬함
			case 3:
				run1 = false;
				break;
//			case 1234:
//				break;
			default:
				System.out.println("1~3");
			}// switch

		} // while

	}// 메인메서드

	public static MemberDTO getLoginStatus() {
		return loginStatus;
	}

	public static void setLoginStatus(MemberDTO loginStatus) {
		WebBoardExam.loginStatus = loginStatus;
	}

}// 클래스