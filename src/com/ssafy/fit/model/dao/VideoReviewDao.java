package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.VideoReview;

public interface VideoReviewDao {

	void insertReview(VideoReview videoreview, int movieNo);

	public List<VideoReview> selectReview(int videoNo);

}
