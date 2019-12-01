package com.fuful.service;



import com.fuful.dao.BookDao;
import com.fuful.domain.Book;
import com.fuful.domain.BookLove;
import com.fuful.domain.BookSuperType;
import com.fuful.domain.Mark;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookDao bookDao;

    //	获得热门影票
    public List<Book> getHot() {
        // TODO Auto-generated method stub
        List<Book> plist =new ArrayList<>();
        plist= bookDao.findHotBook();
        return plist;
    }
//  获得最新商品
	public List<Book> getNew() {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
		List<Book> plist =bookDao.findAllNewBook();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book  order by INTime desc limit 0,9");
//			ResultSet rs = ps.executeQuery();
////			private int ID;		  //影票ID
////			private int typeBID;  //大类
////			private int typeID;   //小类别
////			private String bookName; //影票名称
////			private String introduce;  //影票简介
////			private Double price;	//定价
////			private Double nowPrice; //现价
////			private String picture; //影票文件
////			private Date   INTime; //录入时间
////			private int newBook;  //是否是新书，1是，0不是
////			private int sale;    //是否是打折，1是，0不是
////			private int hit;    //浏览次数
//			while(rs.next()){
//				Book p = new Book();
//				p.setID(rs.getInt("ID"));
//				p.setTypeBID(rs.getInt("typeBID"));
//				p.setTypeID(rs.getInt("typeID"));
//				p.setBookName(rs.getString("bookName"));
//				p.setIntroduce(rs.getString("introduce"));
//				p.setPrice(rs.getDouble("price"));
//				p.setNowPrice(rs.getDouble("nowPrice"));
//				p.setPicture(rs.getString("picture"));
//				p.setINTime(rs.getString("INTime"));
//				p.setNewBook(rs.getInt("newBook"));
//				p.setSale(rs.getInt("sale"));
//				p.setHit(rs.getInt("hit"));
//				plist.add(p);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return plist;
	}


    //	获得所有的栏目superType
    public List<BookSuperType> findAllSuperType() {
        // TODO Auto-generated method stub
        List<BookSuperType> plist = bookDao.findSuperType();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_superType ");
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				BookSuperType p=new BookSuperType();
//
//				p.setID(rs.getInt("ID"));
//				p.setTname(rs.getString("tname"));
//				p.setAname(rs.getString("aname"));
//				p.setKeyword(rs.getString("keyword"));
//				p.setSupdesc(rs.getString("supdesc"));
//				plist.add(p);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return plist;
    }

    public PageInfo<Book> getProductList(int pageNum, int pageSize,int cid){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> bookList = bookDao.findProductByCid(cid);
        PageInfo<Book> data = new PageInfo<>(bookList);
        return data;
    }


//	获得所有的影票信息
//	public List<Book> findAllBookInfo() {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		List<Book> plist = new ArrayList();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book ");
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				Book p = new Book();
//				p.setID(rs.getInt("ID"));
//				p.setTypeBID(rs.getInt("typeBID"));
//				p.setTypeID(rs.getInt("typeID"));
//				p.setBookName(rs.getString("bookName"));
//				p.setIntroduce(rs.getString("introduce"));
//				p.setPrice(rs.getDouble("price"));
//				p.setNowPrice(rs.getDouble("nowPrice"));
//				p.setPicture(rs.getString("picture"));
//				p.setINTime(rs.getString("INTime"));
//				p.setNewBook(rs.getInt("newBook"));
//				p.setSale(rs.getInt("sale"));
//				p.setAuthor(rs.getString("author"));
//				p.setPublish(rs.getString("publish"));
//
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
//	public boolean saveBookInfo(Book p) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		try {
//			PreparedStatement ps = conn.prepareStatement("insert into tb_book(typeBID,typeID,bookName,introduce,price,nowPrice,picture,INTime,newBook,sale,author,publish) values(?,?,?,?,?,?,?,?,?,?,?,?)");
//
//			ps.setInt(1, p.getTypeBID());
//			ps.setInt(2, p.getTypeID());
//			ps.setString(3, p.getBookName());
//			ps.setString(4, p.getIntroduce());
//			ps.setDouble(5, p.getPrice());
//			ps.setDouble(6, p.getNowPrice());
//			ps.setString(7,p.getPicture());
//			ps.setString(8, p.getINTime());
//			ps.setInt(9, p.getNewBook());
//			ps.setInt(10, p.getSale());
//			ps.setString(11, p.getAuthor());
//			ps.setString(12, p.getPublish());
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
//	查询是否存在已经有的影票信息
//	public boolean findBookInfoExist(String bookName) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("select count(*) from tb_book where bookName=?");
//			ps.setString(1, bookName);
//			ResultSet rs = ps.executeQuery();
//
//			rs.next();
//
//			if(rs.getInt(1)>0)
//			{
//				return true; //存在相同的数据
//			}
//
//
//			JNDISessionFactory.close(ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//





//	根据影票的ID删除信息
//	public boolean deleteBookInfoByID(int id) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//				PreparedStatement ps = conn.prepareStatement("delete from tb_book where ID=?  ");
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

//	根据影票的ID查询的信息
	public Book findBookInfoById(int id) {
//		// TODO Auto-generated method stub
		Book p = bookDao.getBookInfoById(id);
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book where ID=? ");
		return p;
	}

//	更新影票信息
//	public boolean updateBookInfo(Book p) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//
//		try {
//			PreparedStatement ps = conn.prepareStatement("update  tb_book set typeBID=?,typeID=?,bookName=?,introduce=?,price=?,nowPrice=?,picture=?,INTime=?,newBook=?,sale=?,author=?,publish=? where ID=?");
//
//			ps.setInt(1, p.getTypeBID());
//			ps.setInt(2, p.getTypeID());
//			ps.setString(3, p.getBookName());
//			ps.setString(4, p.getIntroduce());
//			ps.setDouble(5, p.getPrice());
//			ps.setDouble(6, p.getNowPrice());
//			ps.setString(7,p.getPicture());
//			ps.setString(8, p.getINTime());
//			ps.setInt(9, p.getNewBook());
//			ps.setInt(10, p.getSale());
//			ps.setString(11, p.getAuthor());
//			ps.setString(12, p.getPublish());
//			ps.setInt(13, p.getID());
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
//	畅销打折影票
	public List<Book> getDiscount() {
//		// TODO Auto-generated method stub
		List<Book> plist = bookDao.findAllDiscountBook();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book   where sale=? order by hit desc limit 0,9");
//			ps.setInt(1, 1);
//			ResultSet rs = ps.executeQuery();
////			private int ID;		  //图书ID
////			private int typeBID;  //大类
////			private int typeID;   //小类别
////			private String bookName; //图书名称
////			private String introduce;  //图书简介
////			private Double price;	//定价
////			private Double nowPrice; //现价
////			private String picture; //图片文件
////			private Date   INTime; //录入时间
////			private int newBook;  //是否是新书，1是，0不是
////			private int sale;    //是否是打折，1是，0不是
////			private int hit;    //浏览次数
//			while(rs.next()){
//				Book p = new Book();
//				p.setID(rs.getInt("ID"));
//				p.setTypeBID(rs.getInt("typeBID"));
//				p.setTypeID(rs.getInt("typeID"));
//				p.setBookName(rs.getString("bookName"));
//				p.setIntroduce(rs.getString("introduce"));
//				p.setPrice(rs.getDouble("price"));
//				p.setNowPrice(rs.getDouble("nowPrice"));
//				p.setPicture(rs.getString("picture"));
//				p.setINTime(rs.getString("INTime"));
//				p.setNewBook(rs.getInt("newBook"));
//				p.setSale(rs.getInt("sale"));
//				p.setHit(rs.getInt("hit"));
//				plist.add(p);
//			}
//
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return plist;
	}

	public void addBookInfoHit(int hit,int id) {
//		// TODO Auto-generated method stub
        int res=bookDao.addBookHit(hit,id);
//		try {
//			PreparedStatement ps = conn.prepareStatement("update  tb_book set hit=? where ID=?");
	}
//	public Book findBookInfoByName(String bookName) {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
//		Book p = new Book();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book where bookName=?");
//			ps.setString(1, bookName);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()){
//				p.setID(rs.getInt("ID"));
//				p.setTypeBID(rs.getInt("typeBID"));
//				p.setTypeID(rs.getInt("typeID"));
//				p.setBookName(rs.getString("bookName"));
//				p.setIntroduce(rs.getString("introduce"));
//				p.setPrice(rs.getDouble("price"));
//				p.setNowPrice(rs.getDouble("nowPrice"));
//				p.setPicture(rs.getString("picture"));
//				p.setINTime(rs.getString("INTime"));
//				p.setNewBook(rs.getInt("newBook"));
//				p.setSale(rs.getInt("sale"));
//				p.setAuthor(rs.getString("author"));
//				p.setPublish(rs.getString("publish"));
//
//
//			}
////
//		JNDISessionFactory.close(rs, ps, conn);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return p;
//	}




    public String findSuperTypeName(int superType) {
        // TODO Auto-generated method stub
        String  p =bookDao.getSuperTypeName(superType);
//        try {
//            PreparedStatement ps = conn.prepareStatement("select tname from tb_superType where ID=? ");
        return p;
    }


    public PageInfo<Mark> getMarkList(int pageNum,int pageSize,int pid){
        PageHelper.startPage(pageNum,pageSize);
        List<Mark> markList = bookDao.findMarkList(pid);
        PageInfo<Mark> data = new PageInfo<>(markList);
        return data;
    }

    public boolean deletemark(String mid){
        boolean res=bookDao.DeleteMark(mid);
        return res;
    }
    public PageInfo<Mark> getMyMarkList(int pageNum,int pageSize,String  username){
        PageHelper.startPage(pageNum,pageSize);
        List<Mark> markList = bookDao.findMyMarkList(username);
        PageInfo<Mark> data = new PageInfo<>(markList);
        return data;
    }


//    public BookLove queryState(String uid, String pid){
//        BookLove p = bookDao.getBookLove(uid,Integer.parseInt(pid));
//        return p;
//    }
}
