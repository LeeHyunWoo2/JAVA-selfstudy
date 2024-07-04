package ex04webBoard.DTO.child;

import ex04webBoard.DTO.BoardDTO;

public class MovieBoardDTO extends BoardDTO {

	private String movie;
	private String youtubeLink;
	private boolean danger; // 법적으로 문제되는 영상인가

	public String getMovie() {
		return movie;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

}
