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
			System.out.print("전화번호: ");
			String phonenum = scan.next();
			if (!pbDao.isMemberExists(phonenum)) {
				pbDto.setPhonenum(phonenum);
				System.out.println("정상 전화번호입니다.");
				break;
			} else {
				System.out.println("중복된 전화번호입니다.");
			}
		} while (true);

		// scan.next() 리턴값이 null 이거나 "" 일 수 없음. 체크 필요 없음.
		System.out.print("이름: ");
		pbDto.setPplname(scan.next());
		System.out.print("주소입력: ");
		pbDto.setAddr(scan.next());
		System.out.print("구분입력: ");
		pbDto.setKindsid(scan.next());
		return pbDto;
	}
	
	public boolean addMember() {
		PhoneBookDao pbDao = new PhoneBookDao();
		return pbDao.addMember(createMember());
	}
	
	public boolean modifyMember() {
		boolean result = false;

//		1. 키보드로 이름을 입력
		Scanner scan = new Scanner(System.in);
		System.out.println("수정할 회원의 정보를 입력하세요");
		System.out.print("이름: ");
		String name = scan.next();
		
//		2. DB처리
		PhoneBookDao pbDao = new PhoneBookDao();
		ArrayList<PhoneBookDto> pbDtos = pbDao.searchMemberByName(name);
		
		System.out.println("총" + pbDtos.size() + "개의 목록이 검색되었습니다.");
		if(pbDtos.isEmpty() == false){
			System.out.println("아래 목록 중 수정할 회원의 번호를 입력하세요");
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

//		1. 키보드로 이름을 입력
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 회원의 정보를 입력하세요");
		System.out.print("이름: ");
		String name = scan.next();
		
//		2. DB처리
		PhoneBookDao pbDao = new PhoneBookDao();
		ArrayList<PhoneBookDto> pbDtos = pbDao.searchMemberByName(name);
		
		System.out.println("총" + pbDtos.size() + "개의 목록이 검색되었습니다.");
		if(pbDtos.isEmpty() == false){
			System.out.println("아래 목록 중 삭제할 회원의 번호를 입력하세요");
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

//	2.1	전체사원조회
	public ArrayList<PhoneBookDto> searchAll(){
		PhoneBookDao pbDao = new PhoneBookDao();
		return pbDao.searchAll();
	}
}
