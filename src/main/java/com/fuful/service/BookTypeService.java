package com.fuful.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeService {
//保存父栏目
//	public boolean saveCategorySuperType(BookSuperType p) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		try {
//
//
//
//
//
//			PreparedStatement ps = conn.prepareStatement("insert into tb_superType(tname,aname,keyword,supdesc) values(?,?,?,?)");
//
//			ps.setString(1, p.getTname());
//			ps.setString(2, p.getAname());
//			ps.setString(3, p.getKeyword());
//			ps.setString(4, p.getSupdesc());
//			int i = ps.executeUpdate();
//			if(i==1){
//				JNDISessionFactory.close(ps, conn);
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	保存子栏目
//	public boolean saveCategorySubType(BookSubType p) {
//		Connection conn = JNDISessionFactory.getConnection();
//		try {
//			PreparedStatement ps = conn.prepareStatement("insert into tb_subType(superType,tname,otherName,keyword,subdesc) values(?,?,?,?,?)");
//
//			ps.setInt(1, p.getSuperType());
//			ps.setString(2, p.getTname());
//			ps.setString(3,p.getOtherName());
//			ps.setString(4, p.getKeyword());
//			ps.setString(5, p.getSubdesc());
//			int i = ps.executeUpdate();
//			if(i==1){
//				JNDISessionFactory.close(ps, conn);
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//
//	}
//  根据父栏目ID寻找详细信息
//	public BookSuperType findSuperTypeDetailByid(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		BookSuperType plist = new BookSuperType();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_superType where ID=? ");
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//
//				plist.setTname(rs.getString("tname"));
//				plist.setAname(rs.getString("aname"));
//				plist.setKeyword(rs.getString("keyword"));
//				plist.setSupdesc(rs.getString("supdesc"));
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return plist;
//
//
//	}

//	根据父栏目的ID找它下面的所有的子栏目
//	public List<BookSubType> findAllSubTypeByid(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		List<BookSubType> plist = new ArrayList();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_subType where superType=? ");
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				BookSubType p = new BookSubType();
//				p.setID(rs.getInt("ID"));
//				p.setSuperType(rs.getInt("superType"));
//				p.setTname(rs.getString("tname"));
//				p.setOtherName(rs.getString("otherName"));
//				p.setKeyword(rs.getString("keyword"));
//				p.setSubdesc(rs.getString("subdesc"));
//				plist.add(p);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return plist;
//
//
//	}

//	public boolean updateSuperType(BookSuperType p) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		BookSuperType plist = new BookSuperType();
//		try {
//			PreparedStatement ps = conn.prepareStatement("update tb_superType set tname=?,aname=?,keyword=?,supdesc=? where ID=? ");
//			ps.setString(1, p.getTname());
//			ps.setString(2, p.getAname());
//			ps.setString(3, p.getKeyword());
//			ps.setString(4, p.getSupdesc());
//			ps.setInt(5, p.getID());
//			int i=ps.executeUpdate();
//
//			if(i>0)
//			{
//				return true;
//			}
//
//			JNDISessionFactory.close( ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

	
//	根据父栏目名称判断是否已经存在数据了
//	public boolean querySuperTypeExistByName(String tname) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("select count(*) from tb_superType where tname=? ");
//			ps.setString(1, tname);
//			ResultSet rs = ps.executeQuery();
//
//			rs.next();
//
//			if(rs.getInt(1)!=0)
//			{
//				return true;
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
// 查询子栏目中是否存在有某个相同数据，根据同父栏目同名称判断
//	public boolean querySubTypeExistByName(BookSubType p) {
//			Connection conn = JNDISessionFactory.getConnection();
//			try {
//					PreparedStatement ps = conn.prepareStatement("select count(*) from tb_subType where tname=? and superType=? ");
//					ps.setString(1, p.getTname());
//					ps.setInt(2, p.getSuperType());
//					ResultSet rs = ps.executeQuery();
//
//					rs.next();
//
//					if(rs.getInt(1)!=0)
//					{
//						return true;
//					}
//
//					JNDISessionFactory.close(rs, ps, conn);
//
//				} catch (SQLException e) {
//			// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			return false;
//	}
// 栏目管理里主栏目删除，并且附带的子栏目和书籍全部删除,返回true删除成功  //在全部都有数据时使用
//	public boolean deleteSuperTypeAttachall(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete tb_book,tb_subType,tb_superType from tb_book left join tb_subType on tb_book.typeBID=tb_subType.superType left join tb_superType on tb_superType.ID=tb_book.typeBID where ((tb_subType.superType=tb_book.typeBID) or (tb_superType.ID=tb_book.typeBID)) and  tb_superType.ID=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//
//
//	}
// 根据ID删除主栏目
//	public boolean deleteSuperTypeById(int id) {
//		// TODO Auto-generated method stub
//				Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete from tb_superType where ID=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//
//	}
// 根据主栏目ID删除子栏目
//	public boolean deleteSupTypeBysuperType(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete from tb_subType where superType=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//	}
//根据ID删除书籍
//	public boolean deleteBookBytypeBID(int id) {
//		// TODO Auto-generated method stub
//	Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete from tb_book where typeBID=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//	}
//根据子栏目的ID找到子栏目的信息
//	public BookSubType findSubTypeDetailByid(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		BookSubType plist = new BookSubType();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_subType where ID=? ");
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				BookSubType p = new BookSubType();
//				plist.setID(rs.getInt("ID"));
//				plist.setSuperType(rs.getInt("superType"));
//				plist.setTname(rs.getString("tname"));
//				plist.setOtherName(rs.getString("otherName"));
//				plist.setKeyword(rs.getString("keyword"));
//				plist.setSubdesc(rs.getString("subdesc"));
//
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return plist;
//	}
//根据子栏目的ID更新信息
//	public boolean updateSubType(BookSubType p) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		BookSuperType plist = new BookSuperType();
//		try {
//			PreparedStatement ps = conn.prepareStatement("update tb_subType set superType=?,tname=?,otherName=?,keyword=?,subdesc=? where ID=? ");
//			ps.setInt(1, p.getSuperType());
//			ps.setString(2, p.getTname());
//			ps.setString(3, p.getOtherName());
//			ps.setString(4, p.getKeyword());
//			ps.setString(5, p.getSubdesc());
//
//			ps.setInt(6, p.getID());
//			int i=ps.executeUpdate();
//
//			if(i>0)
//			{
//				return true;
//			}
//
//			JNDISessionFactory.close( ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	
	
	
//	根据主栏目的ID查询他的名称
//	public String findSuperTypeName(int superType) {
//		// TODO Auto-generated method stub
//		String  p =
//		try {
//			PreparedStatement ps = conn.prepareStatement("select tname from tb_superType where ID=? ");
//			ps.setInt(1, superType);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//			{
//				p=rs.getString(1);
//			}
//
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return p;
//	}
//根据子栏目的ID删除子栏目
//	public boolean deleteSubTypeByID(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete from tb_subType where ID=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//	}
//根据子栏目ID删除图书
//	public boolean deleteBookBytypeID(int id) {
//		// TODO Auto-generated method stub
//			Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("delete from tb_book where typeID=?  ");
//
//				ps.setInt(1,id);
//
//				int i=ps.executeUpdate();
//				if(i!=0)
//				{
//					return true;
//				}
//
//
//				JNDISessionFactory.close( ps, conn);
//
//			} catch (SQLException e) {
//		// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return false;
//	}

	
//	根据图书的子栏目ID查询子栏目的名称
//	public String findSubTypeName(int typeID) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		String  p = null;
//		try {
//			PreparedStatement ps = conn.prepareStatement("select tname from tb_subType where ID=? ");
//			ps.setInt(1, typeID);
//			ResultSet rs = ps.executeQuery();
//
//			if(rs.next())
//			{
//				p=rs.getString(1);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return p;
//	}

//	根据主栏目ID查询所子栏目的信息
//	public List<BookSubType> findSubTypeDetailBySuperType(int superType) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		List<BookSubType> plist=new ArrayList();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_subType where superType=? ");
//			ps.setInt(1, superType);
//
//			ResultSet rs = ps.executeQuery();
//
//
//			while(rs.next())
//			{
//				BookSubType p=new BookSubType();
//				p.setID(rs.getInt("ID"));
//				p.setSuperType(rs.getInt("superType"));
//				p.setTname(rs.getString("tname"));
//				p.setKeyword(rs.getString("keyword"));
//				p.setOtherName(rs.getString("otherName"));
//				p.setSubdesc(rs.getString("subdesc"));
//				plist.add(p);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return plist;
//	}



}
