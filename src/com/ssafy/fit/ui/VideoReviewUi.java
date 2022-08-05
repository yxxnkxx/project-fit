package com.ssafy.fit.ui;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.ssafy.fit.model.VideoReview;
import com.ssafy.fit.model.dao.VideoReviewDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoReviewUi {
	VideoReviewDaoImpl videoReviewDao = VideoReviewDaoImpl.getInstance(); // reviewDao 객체

	int videoNo;
	private static VideoReviewUi instance = new VideoReviewUi();

	private VideoReviewUi() {

	}

	public static VideoReviewUi getInstance() {
		return instance;
	}

	public void service(int videoNo) throws IOException, ParseException {
		this.videoNo = videoNo; // videoNo 설정 - 각 video의 리뷰를 출력할 key

		listReview();
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 리뷰등록");
		System.out.println("0. 이전으로");
		System.out.println("------------------------------------------------------------");
		System.out.print("메뉴를 선택하세요 : ");
		int input = SsafitUtil.inputInt(SsafitUtil.sc.next());
		if (input == 1) {
			registReview(); // 리뷰 등록
		} else if (input == 0) {
			VideoUi videoUi = VideoUi.getInstance();
			videoUi.listVideo(); // 이전 화면: 영상 목록
		} else {
			service(this.videoNo);
		}

	}

	public void listReview() {
		System.out.println("------------------------------------------------------------");
		List<VideoReview> list = videoReviewDao.selectReview(this.videoNo); // 리뷰 리스트

		if (list == null) {
			System.out.println("영상리뷰 : 0개"); // 리뷰가 0개이면 리스트 출력x
		} else {

			System.out.println("영상리뷰 : " + list.size() + "개");
			System.out.println("------------------------------------------------------------");
			for (VideoReview review : list) {
				System.out.println(review.getReviewNo() + " " + review.getNickName() + " " + review.getContent());
			}
		}

	}

	public void registReview() throws IOException, ParseException {

		System.out.println("닉네임을 입력하세요. : ");
		SsafitUtil.sc.nextLine();
		String nickName = SsafitUtil.input(SsafitUtil.sc.nextLine()); // 닉네임
		System.out.println("내용을 입력하세요. : ");
		String content = SsafitUtil.input(SsafitUtil.sc.nextLine()); // 리뷰 내용
		VideoReview videoReview = new VideoReview(); // 리뷰 객체 생성
		videoReview.setNickName(nickName);
		videoReview.setContent(content);
		videoReview.setVideoNo(this.videoNo);
		videoReviewDao.insertReview(videoReview, this.videoNo);

		service(this.videoNo);

	}

}
