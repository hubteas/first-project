package phonebook.service;

import java.util.ArrayList;
import java.util.Scanner;

import phonebook.dao.PhoneBookDao;
import phonebook.dto.PhoneBookDto;

public class PhoneBookService {
	private PhoneBookDto createMember() {
		PhoneBookDto pbDto = new PhoneBookDto();
		PhoneBookDao pbDao = new PhoneBookDao();
			
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("��ȭ��ȣ: ");
			String phonenum = scan.next();
			if (!pbDao.isMemberExists(phonenum)) {
				pbDto.setPhonenum(phonenum);
				System.out.println("���� ��ȭ��ȣ�Դϴ�.");
				break;
			} else {
				System.out.println("�ߺ��� ��ȭ��ȣ�Դϴ�.");
			}
		} while (true);

		// scan.next() ���ϰ��� null �̰ų� "" �� �� ����. üũ �ʿ� ����.
		System.out.print("�̸�: ");
		pbDto.setPplname(scan.next());
		System.out.print("�ּ��Է�: ");
		pbDto.setAddr(scan.next());
		System.out.print("�����Է�: ");
		pbDto.setKindsid(scan.next());
		return pbDto;
	}
	
	public boolean addMember() {
		PhoneBookDao pbDao = new PhoneBookDao();
		return pbDao.addMember(createMember());
	}
	
	public boolean modifyMember() {
		boolean result = false;

//		1. Ű����� �̸��� �Է�
		Scanner scan = new Scanner(System.in);
		System.out.println("������ ȸ���� ������ �Է��ϼ���");
		System.out.print("�̸�: ");
		String name = scan.next();
		
//		2. DBó��
		PhoneBookDao pbDao = new PhoneBookDao();
		ArrayList<PhoneBookDto> pbDtos = pbDao.searchMemberByName(name);
		
		System.out.println("��" + pbDtos.size() + "���� ����� �˻��Ǿ����ϴ�.");
		if(pbDtos.isEmpty() == false){
			System.out.println("�Ʒ� ��� �� ������ ȸ���� ��ȣ�� �Է��ϼ���");
			for(int i = 0; i < pbDtos.size(); ++i) {
				System.out.println(i + " " + pbDtos.get(i));	
			}
			
			int order = scan.nextInt();
			if(order < 0 || order >= pbDtos.size())	{
				return false;
			}

			result = pbDao.modifyMember(pbDtos.get(order).getPhonenum(), createMember());
		}

		return result;
	}
	
	public boolean removeMember() {
		boolean result = false;

//		1. Ű����� �̸��� �Է�
		Scanner scan = new Scanner(System.in);
		System.out.println("������ ȸ���� ������ �Է��ϼ���");
		System.out.print("�̸�: ");
		String name = scan.next();
		
//		2. DBó��
		PhoneBookDao pbDao = new PhoneBookDao();
		ArrayList<PhoneBookDto> pbDtos = pbDao.searchMemberByName(name);
		
		System.out.println("��" + pbDtos.size() + "���� ����� �˻��Ǿ����ϴ�.");
		if(pbDtos.isEmpty() == false){
			System.out.println("�Ʒ� ��� �� ������ ȸ���� ��ȣ�� �Է��ϼ���");
			for(int i = 0; i < pbDtos.size(); ++i) {
				System.out.println(i + " " + pbDtos.get(i));	
			}
			
			int order = scan.nextInt();
			if(order < 0 || order >= pbDtos.size()) {
				return false;
			}

			result = pbDao.removeMember(pbDtos.get(order).getPhonenum());
		}
		
		return result;
	}

//	2.1	��ü�����ȸ
	public ArrayList<PhoneBookDto> searchAll(){
		PhoneBookDao pbDao = new PhoneBookDao();
		return pbDao.searchAll();
	}
}
