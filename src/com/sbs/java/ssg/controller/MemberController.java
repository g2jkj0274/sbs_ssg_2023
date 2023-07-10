package com.sbs.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<Member>();
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch ( actionMethodName ) {
		case "join":
			doJoin();
			break;
		}
	}
	private int getMemberIndexByLoginID(String loginID) {
		int i = 0;
		for(Member member : members) {
			if(member.loginId.equals(loginID)) {
				return i;
			}
		}
		return -1;
	}
	private boolean isJoinableLoginID(String loginID) {
		int index = getMemberIndexByLoginID(loginID);
		if(index == -1) {
			return true;
		}
		return false;
	}
	public void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		
		String loginId = null;
		
		while(true) {
			System.out.printf("로그인 ID : ");
			loginId = sc.nextLine();
			
			if(isJoinableLoginID(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 ID\n", loginId);
				continue;
			}
			break;
		}
		
		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("로그인 PW : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 PW 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원 생성.\n", id);
	}
}
