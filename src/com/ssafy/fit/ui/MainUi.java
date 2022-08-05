package com.ssafy.fit.ui;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.ssafy.fit.user.User;
import com.ssafy.fit.user.UserManagerImpl;
import com.ssafy.fit.util.SsafitUtil;

public class MainUi {

	UserManagerImpl manager = UserManagerImpl.getInstance();
	private static MainUi instance = new MainUi(); // singleton

	private MainUi() {

	}

	public static MainUi getInstance() {
		return instance;
	}

	public void init() throws IOException, ParseException {
		manager.loadData();
		System.out.println("------------------------------------------------------------");
		System.out.println("자바로 구현하는 SSAFIT");
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("0. 종료");
		int input = SsafitUtil.inputInt(SsafitUtil.sc.next()); // 메뉴 입력받기

		if (input == 1) {
			login();
		} else if (input == 0) {
			exit(); // 종료
		} else if (input == 2) {
			register();
		}

	}

	// 최초 메인화면
	public void service() throws IOException, ParseException {
		// 시작할 때 사용자 정보 로드
		System.out.println("------------------------------------------------------------");
		System.out.println("자바로 구현하는 SSAFIT");
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.println("1. 영상정보");
		System.out.println("2. 회원목록");
		System.out.println("3. 로그아웃");

		System.out.println("0. 종료");
		System.out.println("------------------------------------------------------------");
		System.out.print("메뉴를 선택하세요: ");

		int input = SsafitUtil.inputInt(SsafitUtil.sc.next()); // 메뉴 입력받기

		if (input == 1) {
			VideoUi videoUi = VideoUi.getInstance(); // 영상정보 창으로 이동
			videoUi.service();
		} else if (input == 0) {
			exit(); // 종료
		} else if (input == 2) {
			getUserList();
		} else if (input == 3) {
			init();
		} else {
			service(); // 잘못된 입력-다시 로딩
		}
	}

	public void exit() {
		manager.saveData();
		return;

	}

	public void register() throws IOException, ParseException {
		System.out.println("------------------------------------------------------------");
		System.out.println("아이디를 입력하세요. ");
		SsafitUtil.sc.nextLine();
		String id = SsafitUtil.input(SsafitUtil.sc.nextLine());
		System.out.println("비밀번호를 입력하세요. ");
		String password = SsafitUtil.input(SsafitUtil.sc.nextLine());
		System.out.println("이름을 입력하세요. ");
		String name = SsafitUtil.input(SsafitUtil.sc.nextLine());
		System.out.println("이메일을 입력하세요. ");
		String email = SsafitUtil.input(SsafitUtil.sc.nextLine());
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		manager.add(user);
		manager.saveData();
		init();

	}

	public void getUserList() throws IOException, ParseException {

		User[] userList = manager.getUsers();
		for (User user : userList) {
			System.out.println("id: " + user.getId() + ", name:  " + user.getName() + ", email: " + user.getEmail());
		}

		service();
	}

	public void login() throws IOException, ParseException {
		System.out.println("------------------------------------------------------------");
		System.out.println("아이디를 입력하세요. ");

		SsafitUtil.sc.nextLine();
		String id = SsafitUtil.input(SsafitUtil.sc.nextLine());
		System.out.println("비밀번호를 입력하세요. ");
		String password = SsafitUtil.input(SsafitUtil.sc.nextLine());

		User[] userList = manager.getUsers();
		for (User user : userList) {
			if (id.equals(user.getId()) && password.equals(user.getPassword())) {
				System.out.println("로그인 성공!");
				service();
				return;
			}
		}
		System.out.println("사용자 정보가 없습니다.");
		init();

	}
}
