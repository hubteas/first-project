package phonebook.controller;

import phonebook.service.PhoneBookService;
import phonebook.view.PhoneBookView;

public class PhonebookController {

	public static void main(String[] args) {
		PhoneBookView pbView = new PhoneBookView();
		if(pbView.run(new PhoneBookService()) == false) {
			System.out.println("�������� ���࿡ �����߽��ϴ�.");	
		}
	}

}
