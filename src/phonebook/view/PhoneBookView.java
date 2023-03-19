package phonebook.view;

import java.util.ArrayList;
import java.util.Scanner;

import phonebook.dto.PhoneBookDto;
import phonebook.service.PhoneBookService;

public class PhoneBookView {
	private PhoneBookService pbService = null;
	
	public boolean run(PhoneBookService pbService) {
		if(pbService == null) {
			return false;
		}
		this.pbService = pbService;
		
		selectMenu();
		
		return true;
	}

	private void selectMenu() {
		System.out.println();
		System.out.println("===================");
		System.out.println("다음 메뉴를 선택하세요");
		System.out.println("===================");
		System.out.println("1. 회원 추가");
		System.out.println("2. 회원 목록 보기");
		System.out.println("3. 회원 정보 수정하기");
		System.out.println("4. 회원 삭제");
		System.out.println("5. 종료");

		Scanner scan = new Scanner(System.in);
		int menuIdx = scan.nextInt();
		switch (menuIdx) {
		case 1:
			if (pbService.addMember() == true) {
				System.out.println("회원정보 추가 완료");
			} else {
				System.out.println("회원정보 추가 실패");
			}
			selectMenu();
			break;
		case 2:
			searchAll();
			selectMenu();
			break;
		case 3:
			if (pbService.modifyMember() == true) {
				System.out.println("회원정보 수정 완료");
			} else {
				System.out.println("회원정보 수정 실패");
			}
			selectMenu();
			break;
		case 4:
			if (pbService.removeMember() == true) {
				System.out.println("회원정보 삭제 완료");
			} else {
				System.out.println("회원정보 삭제 실패");
			}
			selectMenu();
			break;
		case 5:
			System.exit(0);
			break;
		}

	}

	private void searchAll() {
		ArrayList<PhoneBookDto> pbDtos = pbService.searchAll();
		int memberCount = pbDtos.size();
		if (memberCount == 0) {
			System.out.println("조회 된 사람 없다");
		} else {
			System.out.println(memberCount + "명의 사람이 조회");
			for (int i = 0; i < memberCount; i++) {
				System.out.println(pbDtos.get(i));
			}
		}
	}

}
