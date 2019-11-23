package com.fuful.service;



import com.fuful.dao.TicketDao;
import com.fuful.domain.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {


    @Autowired
    TicketDao ticketDao;

    public List<Price> getPriceByTid(String tid){
        List<Price> priceList=ticketDao.findAllPriceByTid(tid);
        return priceList;
    }
    public List<Round> getRoundByPlace(String tid,String city){
        List<Round> roundList=ticketDao.findRoundByPlace(tid,city);
        return roundList;
    }

    public List<City> getPlaceByTid(String tid){
        List<City> cityList=ticketDao.findAllPlaceByTid(tid);
        return cityList;
    }

    public List<Round> getPriceByLRT(String tid,String city,String round){
        List<Round> priceList=ticketDao.findAllPriceByLRT(tid,city,round);
        return priceList;
    }


    //	获得热门图书
    public List<Book> getHot() {
        // TODO Auto-generated method stub
        List<Book> plist =new ArrayList<>();
        plist= ticketDao.findHotBook();
        return plist;
    }
//  获得最新商品
	public List<Book> getNew() {
//		// TODO Auto-generated method stub
//		Connection conn = JNDISessionFactory.getConnection();
		List<Book> plist = ticketDao.findAllNewBook();
//		try {
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book  order by INTime desc limit 0,9");
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


    //	获得所有的栏目superType
    public List<BookSuperType> findAllSuperType() {
        // TODO Auto-generated method stub
        List<BookSuperType> plist = ticketDao.findSuperType();
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

    public PageInfo<Ticket> getProductList(int pageNum, int pageSize, int cid){
        PageHelper.startPage(pageNum,pageSize);
        List<Ticket> ticketList = ticketDao.findProductByCid(cid);
        PageInfo<Ticket> data = new PageInfo<>(ticketList);
        return data;
    }


//	获得所有的图书信息
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
//	查询是否存在已经有的图书信息
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





//	根据图书的ID删除信息
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

//
	public Ticket findTicketInfoById(int id) {
//		// TODO Auto-generated method stub
        Ticket p = ticketDao.getTicketInfoById(id);
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book where ID=? ");
        return p;
    }

//	更新图书信息
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
//	畅销打折图书
	public List<Book> getDiscount() {
//		// TODO Auto-generated method stub
		List<Book> plist = ticketDao.findAllDiscountBook();
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

	public void addTicketInfoHit(int hit,int id) {
//		// TODO Auto-generated method stub
        int res= ticketDao.addTicketHit(hit,id);

	}

    public String findSuperTypeName(int superType) {
        // TODO Auto-generated method stub
        String  p = ticketDao.getSuperTypeName(superType);
//        try {
//            PreparedStatement ps = conn.prepareStatement("select tname from tb_superType where ID=? ");
        return p;
    }


    public PageInfo<Mark> getMarkList(int pageNum,int pageSize,int pid){
        PageHelper.startPage(pageNum,pageSize);
        List<Mark> markList = ticketDao.findMarkList(pid);
        PageInfo<Mark> data = new PageInfo<>(markList);
        return data;
    }

    public boolean deletemark(String mid){
        boolean res= ticketDao.DeleteMark(mid);
        return res;
    }
    public PageInfo<Mark> getMyMarkList(int pageNum,int pageSize,String  username){
        PageHelper.startPage(pageNum,pageSize);
        List<Mark> markList = ticketDao.findMyMarkList(username);
        PageInfo<Mark> data = new PageInfo<>(markList);
        return data;
    }


//    public BookLove queryState(String uid, String pid){
//        BookLove p = bookDao.getBookLove(uid,Integer.parseInt(pid));
//        return p;
//    }
}
