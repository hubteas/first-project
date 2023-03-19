package phonebook.dto;

public class PhoneBookDto {
	private String pplname;
	private String phonenum;
	private String addr;
	private String kindsid;
	private String kindsname;
	
	public String getPplname() {
		return pplname;
	}
	public void setPplname(String pplname) {
		this.pplname = pplname;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getKindsid() {
		return kindsid;
	}
	public void setKindsid(String kindsid) {
		this.kindsid = kindsid;
	}
	public String getKindsname() {
		return kindsname;
	}
	public void setKindsname(String kindsname) {
		this.kindsname = kindsname;
	}
	
	@Override
	public String toString() {
		return "이름= " + pplname + ", 전화번호= " + phonenum + ", 주소= " + addr + ", 종류= " + kindsname;
	}
	
}
