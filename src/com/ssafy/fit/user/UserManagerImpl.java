package com.ssafy.fit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserManagerImpl implements IUserManager {
	private List<User> userList = new ArrayList<>();
	private static UserManagerImpl um = new UserManagerImpl();
	private final int MAX_SIZE = 100;

	private UserManagerImpl() {
	};

	public static UserManagerImpl getInstance() {
		return um;
	}

	@Override
	public void add(User user) {
		if (userList.size() < MAX_SIZE) {
			userList.add(user);
		} else {
			System.out.println("유저의 수가 100을 넘었습니다. 등록 불가.");
		}

	}

	@Override
	public User[] getUsers() {
		User[] res = new User[userList.size()];

		return this.userList.toArray(res);
	}

	@Override
	public void saveData() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
			oos.writeObject(this.userList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void loadData() {
		File file = new File("user.dat");

		// 파일이 없을 경우 고려
		if (file.exists()) {
			// try with resources
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				this.userList = (List<User>) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
