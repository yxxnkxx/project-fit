package com.ssafy.fit.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ssafy.fit.model.Video;

public class VideoDaoImpl implements VideoDao {
	public List<Video> list = new ArrayList<>();

	private static VideoDaoImpl instance = new VideoDaoImpl();

	private VideoDaoImpl() {
	}

	// 싱글톤 패턴
	public static VideoDaoImpl getInstance() {
		return instance;
	}

	// 비디오 목록 return
	@Override
	public List<Video> selectVideo() throws IOException, ParseException {
		JSONParser parser = new JSONParser();

		// JSON 파일 파싱(경로 확인!)
		Reader reader = new FileReader("./data/video.json");
		JSONArray jArray = (JSONArray) parser.parse(reader);

		// 파싱한 데이터 배열에서 꺼내와서 Video 인스턴스 생성 후 List에 정리
		for (int i = 0; i < jArray.size(); i++) {
			Video video = new Video();
			JSONObject video1 = (JSONObject) jArray.get(i);
			video.setNo((int) (long) video1.get("no"));
			video.setTitle((String) video1.get("title"));
			video.setPart((String) video1.get("part"));
			video.setUrl((String) video1.get("url"));

			list.add(video);
		}

		return list;
	}

	// videoNo가 일치하는 비디오 찾아서 return
	@Override
	public Video selectVideoByNo(int no) {
		for (Video video : list) {
			if (video.getNo() == no) {
				return video;
			}
		}
		System.out.println("찾으시는 영상이 없습니다");

		return null;
	}
}