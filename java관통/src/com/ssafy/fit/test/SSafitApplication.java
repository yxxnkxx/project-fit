
package com.ssafy.fit.test;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.ssafy.fit.ui.MainUi;

public class SSafitApplication {
	public static void main(String[] args) throws IOException, ParseException {
		MainUi mainUi = MainUi.getInstance();
//		mainUi.service();
		mainUi.init();
	}

}