package com.ssafy.fit.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fit.model.VideoReview;

public class VideoReviewDaoImpl implements VideoReviewDao {
	private int reviewNo;
	// Map<VideoNo, List<VideoReview>>
	private Map<Integer, List<VideoReview>> videoReviewDb = new HashMap<Integer, List<VideoReview>>();
	private static VideoReviewDaoImpl instance = new VideoReviewDaoImpl();

	public VideoReviewDaoImpl() {
	}

	// 싱글톤 패턴
	public static VideoReviewDaoImpl getInstance() {
		return instance;
	}

	// videoReviewDb에 입력
	@Override
	public void insertReview(VideoReview videoreview, int videoNo) {
		// reviewList에 추가
		if (videoReviewDb.containsKey(videoNo)) {
			List<VideoReview> list = videoReviewDb.get(videoNo);
			list.add(videoreview);
			videoreview.setReviewNo(list.size());
		}
		// review가 하나도 없을 경우 List 생성
		else {
			List<VideoReview> reviewList = new ArrayList<>();
			reviewList.add(videoreview);
			videoreview.setReviewNo(reviewList.size());
			videoReviewDb.put(videoNo, reviewList);
		}

	};

	// videoNo에 일치하는 VideoReview return
	@Override
	public List<VideoReview> selectReview(int videoNo) {
		// Map에서 key값으로 value출력
		if (videoReviewDb.containsKey(videoNo)) {
			return videoReviewDb.get(videoNo);

		}
		return null;

	};
}