package com.sbs.java.ssg;

import java.util.Scanner;

import com.sbs.java.ssg.controller.Controller;
import com.sbs.java.ssg.controller.ArticleController;
import com.sbs.java.ssg.controller.MemberController;

public class App {

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTastData();

		while(true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();
			command = command.trim();

			if(command.length() == 0) {
				continue;
			}
			if(command.equals("system exit")) {
				break;
			} 
			
			String[] commandBits = command.split(" "); // article ~~
			if(commandBits.length==1) {
				System.out.println("존재하지 않는 명령어");
				continue;
			}
			String controllerName  = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // member
			
			Controller controller = null;
			
			if(controllerName .equals("article")) {
				controller = articleController;
			}
			else if(controllerName .equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어");
				continue;
			}
			
			controller.doAction(command, actionMethodName);
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}