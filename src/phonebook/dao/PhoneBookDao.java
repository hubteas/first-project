package phonebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import phonebook.dto.PhoneBookDto;

public class PhoneBookDao extends JDBCTemplate {
//  1. ��ȭ �ߺ� ���� Ȯ�� -> select, phonenum
//  1.1 ��� : ����(boolean), �ش� ��ȭ��ȣ�� ������ �ִ� ���(PhoneBookDto)
	public boolean isMemberExists(String phonenum) {
		boolean result = false;
//		DBó��
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT p.PHONENUM			" +
					 "  FROM PHONEBOOK p		" +
					 " WHERE p.PHONENUM LIKE ?	";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonenum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;
	}

//	2 �߰� -> insert, pplname, phonenum, addr, kindsname
	public boolean addMember(PhoneBookDto pbdto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PHONEBOOK VALUES(?,?,?,?)";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pbdto.getPplname());
			pstmt.setString(2, pbdto.getPhonenum());
			pstmt.setString(3, pbdto.getAddr());
			pstmt.setString(4, pbdto.getKindsid());
			int rowCnt = pstmt.executeUpdate();
			if (rowCnt > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return result;
	}
//	3. ����
	public boolean modifyMember(String phoneNum, PhoneBookDto pbDto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PHONEBOOK								 " + 
					 " 	 SET pplname=?, phonenum=?, addr=?, kindsid=?" +
					 " WHERE phonenum=?								 ";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pbDto.getPplname());
			pstmt.setString(2, pbDto.getPhonenum());
			pstmt.setString(3, pbDto.getAddr());
			pstmt.setString(4, pbDto.getKindsid());
			pstmt.setString(5, phoneNum);
			int rowCnt = pstmt.executeUpdate();
			if (rowCnt > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);

		}

		return result;
	}
//	4. ����
	public boolean removeMember(String phoneNum) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE PHONEBOOK	"
				   + " WHERE phonenum=?	";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			int rowCnt = pstmt.executeUpdate();
			if (rowCnt > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);

		}

		return result;
	}

//  ��ȸ (�̸� �˻�) => ����, ���� ���ȴ�.
	public ArrayList<PhoneBookDto> searchMemberByName(String name) {
//		boolean result = false;
//		DBó��
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PhoneBookDto> pbDtos = new ArrayList<PhoneBookDto>();

		String sql = "SELECT p.pplname						" + 
						"  , p.phonenum						" +
						"  , p.addr							" + 
						"  , k.kindsname					" +
						" FROM PHONEBOOK p					" +
						"    , kinds k						" +
						" WHERE p.pplname LIKE ?			" +
						" 	AND p.kindsid=k.kindsid			";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PhoneBookDto pbDto = new PhoneBookDto();
				pbDto.setPhonenum(rs.getString("phonenum"));
				pbDto.setPplname(rs.getString("pplname"));
				pbDto.setAddr(rs.getString("addr"));
				pbDto.setKindsname(rs.getString("kindsname"));
				pbDtos.add(pbDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return pbDtos;
	}

//  3.1 ��ü��� ��ȸ
	public ArrayList<PhoneBookDto> searchAll() {
		ArrayList<PhoneBookDto> pbDtos = new ArrayList<PhoneBookDto>();
//  	1. DBó��
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT p.PPLNAME			" +
					 "		 , p.PHONENUM		" +
					 "		 , p.ADDR			" +
					 "		 , k.KINDSNAME		" +
					 " FROM phonebook p			" +
					 "		 , KINDS k			" +
					 " WHERE p.KINDSID=k.KINDSID";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

//  	2. ArrayList<[PhoneBookDto> ����
		try {
			while (rs.next()) {
				PhoneBookDto pbDto = new PhoneBookDto();
				pbDto.setPhonenum(rs.getString("phonenum"));
				pbDto.setPplname(rs.getString("pplname"));
				pbDto.setAddr(rs.getString("addr"));
				pbDto.setKindsname(rs.getString("kindsname"));

				pbDtos.add(pbDto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return pbDtos;

	}


}
