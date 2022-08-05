package com.ssafy.fit.ui;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.ssafy.fit.model.Video;
import com.ssafy.fit.model.dao.VideoDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoUi {

	public VideoDaoImpl videoDao = VideoDaoImpl.getInstance(); // videoDao 객체 가져오기
	static private VideoUi instance = new VideoUi(); // singleton

	private VideoUi() {
	}

	public static VideoUi getInstance() {
		return instance;
	}

	public void service() throws IOException, ParseException {
		// 영상정보 메인화면
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 영상목록");
		System.out.println("0. 이전으로");
		System.out.println("------------------------------------------------------------");
		System.out.print("메뉴를 선택하세요 : ");
		int input = SsafitUtil.inputInt(SsafitUtil.sc.next()); // 메뉴 입력
		if (input == 1) {
			videoDao.selectVideo(); // JSON 데이터 로딩
			listVideo(); // Video list 출력
		} else if (input == 0) {
			exit(); // 이전으로
		} else {
			service(); // 잘못된 입력
		}

		// 1 누르면 listVideo()
		// 0 누르면 이전으로 -> main으로
	}

	public void listVideo() throws IOException, ParseException {

		System.out.println("------------------------------------------------------------");
		System.out.println("전체:  " + videoDao.list.size() + "개"); // 전체 영상 개수 출력
		System.out.println("------------------------------------------------------------");

		for (int i = 0; i < videoDao.list.size(); i++) { // videoDao list에서 출력
			Video video = videoDao.list.get(i);
			System.out.println(video.getNo() + " " + video.getPart() + " " + video.getTitle());
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 영상상세");
		System.out.println("0. 이전으로");
		System.out.println("------------------------------------------------------------");
		System.out.print("메뉴를 선택하세요: ");
		int input = SsafitUtil.inputInt(SsafitUtil.sc.next()); // 메뉴 선택

		if (input == 1) {
			detailVideo(); // 영상 상세 화면
		} else if (input == 0) {
			service(); // 이전화면
		} else {
			listVideo();
		}

	}

	public void detailVideo() throws IOException, ParseException {
		// 영상 상세 선택화면
		System.out.print("영상번호를 입력하세요.: ");
		int input = SsafitUtil.inputInt(SsafitUtil.sc.next());
		System.out.println("------------------------------------------------------------");
		Video video = videoDao.selectVideoByNo(input); // 정보 출력
		System.out.println("번호 : " + video.getNo());
		System.out.println("제목 : " + video.getTitle());
		System.out.println("운동 : " + video.getPart());
		System.out.println("영상 URL : " + video.getUrl());
		System.out.println("------------------------------------------------------------");

		VideoReviewUi videoReviewUi = VideoReviewUi.getInstance(); // 리뷰 UI로 이동
		videoReviewUi.service(video.getNo());

	}

	public void exit() throws IOException, ParseException {
		MainUi mainUi = MainUi.getInstance();
		mainUi.service(); // 이전화면 = main service창

	}

}
