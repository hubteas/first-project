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
		System.out.println("���� �޴��� �����ϼ���");
		System.out.println("===================");
		System.out.println("1. ȸ�� �߰�");
		System.out.println("2. ȸ�� ��� ����");
		System.out.println("3. ȸ�� ���� �����ϱ�");
		System.out.println("4. ȸ�� ����");
		System.out.println("5. ����");

		Scanner scan = new Scanner(System.in);
		int menuIdx = scan.nextInt();
		switch (menuIdx) {
		case 1:
			if (pbService.addMember() == true) {
				System.out.println("ȸ������ �߰� �Ϸ�");
			} else {
				System.out.println("ȸ������ �߰� ����");
			}
			selectMenu();
			break;
		case 2:
			searchAll();
			selectMenu();
			break;
		case 3:
			if (pbService.modifyMember() == true) {
				System.out.println("ȸ������ ���� �Ϸ�");
			} else {
				System.out.println("ȸ������ ���� ����");
			}
			selectMenu();
			break;
		case 4:
			if (pbService.removeMember() == true) {
				System.out.println("ȸ������ ���� �Ϸ�");
			} else {
				System.out.println("ȸ������ ���� ����");
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
			System.out.println("��ȸ �� ��� ����");
		} else {
			System.out.println(memberCount + "���� ����� ��ȸ");
			for (int i = 0; i < memberCount; i++) {
				System.out.println(pbDtos.get(i));
			}
		}
	}

}
