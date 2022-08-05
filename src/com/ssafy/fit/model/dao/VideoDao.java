package com.ssafy.fit.model.dao;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.ssafy.fit.model.Video;

public interface VideoDao {
	public List<Video> selectVideo() throws IOException, ParseException;

	public Video selectVideoByNo(int no);
}