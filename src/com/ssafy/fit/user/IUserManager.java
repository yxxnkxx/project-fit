package com.ssafy.fit.user;

public interface IUserManager {

	void add(User user);

	User[] getUsers();

	// 데이터 저장 기능 추가
	void saveData();

	// 데이터 로드 기능 추가
	void loadData();
}
