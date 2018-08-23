package com.lib.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

import com.lib.user.User;

@WebServlet("/Database")
public class Database{
	 
	private DataSource ds ;
	public Database(DataSource ds){
		this.ds=ds;
	}

	
	/*public static Connection getConnection() {
	Connection con = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/lib_magn", "root", "root");
	} catch (ClassNotFoundException | SQLException | NoClassDefFoundError e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}*/
	
	public User checkLogin(String userId, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.prepareStatement("select * from login where login_id=? and login_password=?");
			st.setString(1, userId);
			st.setString(2, password);
			rs = st.executeQuery();
			if (rs.next()) {
				if(rs.getString("login_status").equalsIgnoreCase("approved")){
					user = new User();
					user.setId(userId);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User roleAssign(User user){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("select ra_role_id from role_assign where ra_login_id=?");
			 st.setString(1,user.getId());
			 rs = st.executeQuery();
			 while(rs.next())
			 {
				 user.setRole(rs.getInt("ra_role_id"));
			 }
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return user;
		}
	
	
	public User bookDetails(User user){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("Select lib_magn.transaction.tra_doi, lib_magn.transaction.tra_dor, lib_magn.book_info.b_title From lib_magn.transaction Inner Join lib_magn.book_info"+
					 	" On lib_magn.transaction.tra_b_id = lib_magn.book_info.b_id where tra_login_id=?");
			 st.setString(1,user.getId());
			 rs = st.executeQuery();
			 int i=0;
			 while(rs.next())
			 {
				 user.setRecords(true);
				 user.setTitle(rs.getString("b_title"));
				 user.setDoi(rs.getString("tra_doi"));
				 user.setDor(rs.getString("tra_dor"));
				 i++;
			 }
			 user.setLength(i);
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}
	
	public User userDetails(User user) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			if (user.getRole()==3) {
				st = con.prepareStatement("Select lib_magn.dept_info.dept_name,lib_magn.student_info.stu_name,lib_magn.student_info.stu_course, "+
									 "lib_magn.student_info.stu_address,lib_magn.student_info.stu_mobile From "+
									 "lib_magn.dept_info Inner Join lib_magn.student_info On lib_magn.student_info.stu_dept_id = lib_magn.dept_info.dept_id where stu_id=?");
				st.setString(1, user.getId());
				rs = st.executeQuery();
				if (rs.next()) {
					user.setName(rs.getString("stu_name"));
					/*user.setCourse(rs.getString("stu_course"));*/
					user.setDept(rs.getString("dept_name"));
					user.setAddress(rs.getString("stu_address"));
					user.setMobile(rs.getString("stu_mobile"));
				}
			} else{
				st = con.prepareStatement("Select lib_magn.dept_info.dept_name,lib_magn.staff_info.sta_name, "+
									 	"lib_magn.staff_info.sta_address,lib_magn.staff_info.sta_mobile "+
									 	"From lib_magn.dept_info Inner Join lib_magn.staff_info "+
									 	"On lib_magn.staff_info.sta_dept_id = lib_magn.dept_info.dept_id where sta_id=?");
				st.setString(1, user.getId());
				rs = st.executeQuery();
				if (rs.next()) {
					user.setName(rs.getString("sta_name"));
					/*user.setCourse(rs.getString("sta_course"));*/
					user.setDept(rs.getString("dept_name"));
					user.setAddress(rs.getString("sta_address"));
					user.setMobile(rs.getString("sta_mobile"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User booking(String ttl){
		User user=null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("Select lib_magn.publisher_info.pub_name,lib_magn.book_info.b_availaibility,lib_magn.author_info.au_name,"+
  "lib_magn.book_info.b_title,lib_magn.book_info.b_id,lib_magn.book_info.b_edition From lib_magn.author_info Inner Join lib_magn.book_info"+
   " On lib_magn.book_info.author_id = lib_magn.author_info.au_id Inner Join lib_magn.publisher_info On lib_magn.book_info.publisher_id = lib_magn.publisher_info.pub_id where lib_magn.book_info.b_title=?");
			 st.setString(1,ttl);
			 rs = st.executeQuery();
			 int i=0;
			 user = new User();
			 while(rs.next())
			 {
				 user.setBrecords(true);
				 user.setBid(rs.getString("b_id"));
				 user.setBtitle(rs.getString("b_title"));
				 user.setBauthor(rs.getString("au_name"));
				 user.setBpublisher(rs.getString("pub_name"));;
				 user.setBedition(rs.getString("b_edition"));
				 user.setAvail(rs.getString("b_availaibility"));
				 i++;
			 }
			 user.setBlength(i);
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}
	
	public User updateBooking(User user,String bid){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int avail=0;
		try {
			con = ds.getConnection();
			st =con.prepareStatement("select  b_availaibility from book_info where b_id=?");
			st.setString(1,bid);
			rs=st.executeQuery();
			if(rs.next()){
				
				avail=rs.getInt("b_availaibility");
			}
			avail-=1;
			 st =con.prepareStatement("update book_info set b_availaibility=? where b_id=?");
			 st.setString(2,bid);
			 st.setInt(1,avail);
			 int n=st.executeUpdate();
			 if(n>0) {
				 st =con.prepareStatement("insert into booking_book(bb_login_id,bb_b_id) values(?,?)");
				 st.setString(1,user.getId());
				 st.setString(2,bid);
				 int nn=0;
				 nn=st.executeUpdate();
				 if(nn==0) {
					 throw new SQLException();
				 }
			 }
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}
	
	
	
	public User search(String kwd,String ttl,String aut,String isbn,String pub){
		User user=null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int i=0;
		 user = new User();
		try {
			con = ds.getConnection();
			if(ttl!=""){
				System.out.println("title meh");
			 st =con.prepareStatement("Select lib_magn.b.b_id,lib_magn.b.b_title, lib_magn.b.b_edition, lib_magn.b.b_availaibility, lib_magn.a.au_name, lib_magn.p.pub_name From lib_magn.book_info as b Inner Join lib_magn.author_info as a On lib_magn.b.author_id = lib_magn.a.au_id Inner Join lib_magn.publisher_info as p On lib_magn.b.publisher_id = lib_magn.p.pub_id where b.b_title=?");
			 st.setString(1,ttl);
			i= abcd(st,user,i);
			}
			 if(aut!=""){
				 System.out.println("author meh");
				st =con.prepareStatement("Select lib_magn.b.b_id,lib_magn.b.b_title, lib_magn.b.b_edition, lib_magn.b.b_availaibility, lib_magn.a.au_name, lib_magn.p.pub_name From lib_magn.book_info as b Inner Join lib_magn.author_info as a On lib_magn.b.author_id = lib_magn.a.au_id Inner Join lib_magn.publisher_info as p On lib_magn.b.publisher_id = lib_magn.p.pub_id where a.au_name like ?");
			st.setString(1,"%"+aut+"%");
			i= abcd(st,user,i);
			}
			 if(isbn!=""){
					st =con.prepareStatement("Select lib_magn.b.b_id,lib_magn.b.b_title,lib_magn.a.au_name,lib_magn.p.pub_name,lib_magn.b.b_edition,"+
  "lib_magn.b.b_availaibility,lib_magn.bt.isbn From lib_magn.book_info as b Inner Join lib_magn.author_info as a On lib_magn.b.author_id = lib_magn.a.au_id Inner Join "+
  "lib_magn.publisher_info as p On lib_magn.b.publisher_id = lib_magn.p.pub_id Inner Join lib_magn.book_table as bt "+
    "On lib_magn.bt.b_info_id = lib_magn.b.b_id where bt.isbn like ?");
				st.setString(1,"%"+isbn+"%");
				i= abcd(st,user,i);
			 }
			 if(pub!=""){
				 st =con.prepareStatement("Select lib_magn.b.b_id,lib_magn.b.b_title, lib_magn.b.b_edition, lib_magn.b.b_availaibility, lib_magn.a.au_name, lib_magn.p.pub_name From lib_magn.book_info as b Inner Join lib_magn.author_info as a On lib_magn.b.author_id = lib_magn.a.au_id Inner Join lib_magn.publisher_info as p On lib_magn.b.publisher_id = lib_magn.p.pub_id where p.pub_name like ?");
				st.setString(1,"%"+pub+"%");
				i= abcd(st,user,i);
			 }
			  if(kwd!=""){
				 st =con.prepareStatement("Select lib_magn.b.b_id,lib_magn.b.b_title, lib_magn.b.b_edition, lib_magn.b.b_availaibility, lib_magn.a.au_name, lib_magn.p.pub_name From lib_magn.book_info as b Inner Join lib_magn.author_info as a On lib_magn.b.author_id = lib_magn.a.au_id Inner Join lib_magn.publisher_info as p On lib_magn.b.publisher_id = lib_magn.p.pub_id where p.pub_name like ? or b.b_title like ? or a.au_name like ?");
				st.setString(1,"%"+kwd+"%");
				st.setString(2,"%"+kwd+"%");
				st.setString(3,"%"+kwd+"%");
		
				i= abcd(st,user,i);
			 }
			 
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
	}
	
	public int abcd(PreparedStatement st,User user,int i){
		ResultSet rs=null;
		try {
		 rs = st.executeQuery();
		 while(rs.next())
		 {
			 user.setBrecords(true);
			 user.setBid(rs.getString("b_id"));
			 user.setBtitle(rs.getString("b_title"));
			 user.setBauthor(rs.getString("au_name"));
			 user.setBpublisher(rs.getString("pub_name"));
			 user.setBedition(rs.getString("b_edition"));
			 user.setAvail(rs.getString("b_availaibility"));
			 i++;
		 }
		 user.setBlength(i);
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}

			return i;
	}
	
	
	public User reg(String usrname , String name,int dept_id,int role,String course,String addr,String mobile,String psw) {
		User user = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			if(role==3)
			{
				st=con.prepareStatement("insert into login (login_id,login_password,login_status) values(?,?,?)");
				st.setString(1,usrname);
				st.setString(2,psw);
				st.setString(3,"not approved");
				int r1 = st.executeUpdate();
			st = con.prepareStatement("insert into student_info values(?,?,?,?,?,?,?)");
			st.setString(1,usrname);
			st.setString(2,name);
			st.setInt(3,dept_id);
			st.setInt(4,3);
			st.setString(5,course);
			st.setString(6,addr);
			st.setString(7,mobile);
			int r = st.executeUpdate();
			}
			else
			{		st=con.prepareStatement("insert into login (login_id,login_password,login_status,login_reason) values(?,?,?,?)");
				st.setString(1,usrname);
				st.setString(2,psw);
				st.setString(3,"not approve");
				st.setString(4," Approval not done");
				int r1 = st.executeUpdate();
				st = con.prepareStatement("insert into staff_info values(?,?,?,?,?,?)");
				st.setString(1,usrname); 
				st.setString(2,name);
				st.setInt(3,dept_id);
				st.setInt(4,role);
				System.out.print("Rolesss"+role);
				st.setString(5,addr);
				st.setString(6,mobile);
				int r = st.executeUpdate();
				if(r>0)
					System.out.println("stafffffff");
				st=con.prepareStatement("insert into role_assign (ra_login_id,ra_role_id) values(?,?)");
				st.setString(1,usrname);
				st.setInt(2,role);
				int r2 = st.executeUpdate();
			}
			
			}
			
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User roles(String role) {
		User user = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int r =0;int roleid=0;
		try {
			con = ds.getConnection();
			st = con.prepareStatement("select * from role where role_name=?");
			st.setString(1, role);
			rs = st.executeQuery();
			if (rs.next()) {
				roleid=rs.getInt("role_id");
				user=new User();
				user.setRole(roleid);
			}
			
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User addBook(String title,int price,String edition,String author,String isbn,String publisher,String publisher_place,int quantity) 
	{
		String kk=null;
		User user=null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null,rs2=null,rs1=null,rs3=null,rs4=null;
		int m=0,w=0,k=0,sk=0;
		boolean flag=false,flag1=false;
		
		try {
			con = ds.getConnection();
			st = con.prepareStatement("insert into book_table(isbn) values(?)");
			
			st.setString(1,isbn);
		
			int r = st.executeUpdate();
			st=con.prepareStatement("insert into book_info(b_title,b_price,b_edition,b_availaibility,b_quantity,book_isbn) values(?,?,?,?,?,?)");
			st.setString(1, title);
			st.setInt(2,price);
			st.setString(3,edition);
			st.setInt(4,quantity);
			st.setInt(5,quantity);
			st.setString(6,isbn);
			int r1=st.executeUpdate();
			st=con.prepareStatement("select * from publisher_info");
			rs=st.executeQuery();		 
	        while(rs.next())
	        {
	        	if(rs.getString(2).equals(publisher) && rs.getString(3).equals(publisher_place))
	        	{ 
	        		 w=rs.getInt(1); 
	        		flag=true;
	            }
	        	
	        }
	        
	        if(flag==false)
	        	
	        {    
	        	
	        	st=con.prepareStatement("insert into publisher_info (pub_name,pub_place) values(?,?)");
	        	st.setString(1,publisher);
	        	st.setString(2,publisher_place);
	  
	        	st.executeUpdate();
	        	 st=con.prepareStatement("select pub_id from publisher_info where pub_name=?");
	        	 st.setString(1,publisher);
	        	 rs2=st.executeQuery();
				 while(rs2.next())
				 {
					 m=rs2.getInt(1);
				 }
				 st=con.prepareStatement("update book_info set  publisher_id=? where book_isbn=?");
				 st.setInt(1,m);
				 st.setString(2,isbn);
				st.executeUpdate();
				        	
	        }
	        if(flag==true)
	        {
	        	st=con.prepareStatement("update book_info set publisher_id=? where book_isbn=?") ;
	        	st.setInt(1,w);
	        	st.setString(2, isbn);
	    		st.executeUpdate();
	        }
	        st=con.prepareStatement("select * from author_info");
	         rs1=st.executeQuery();
	        while(rs1.next())
	        {
	        	kk=rs1.getString(2);
	        	if(kk.equals(author))
	        	{  
	        		 flag1=true;
	        	     w=rs1.getInt(1);
	        		
	        	}
	        	
	        }
	        if(flag1==false)
	        	
	        {     st=con.prepareStatement("insert into author_info" + "(au_name)" + "values(?)");
	              st.setString(1,author);
	              int r3=st.executeUpdate();
	        	st=con.prepareStatement("select au_id from author_info where au_name=?");
	        	st.setString(1,author);
				 rs3=st.executeQuery();
				 while(rs3.next())
				 {
					 k=rs3.getInt(1);
				 }
				 st=con.prepareStatement("update book_info set  author_id=? where book_isbn=?");
				 st.setInt(1,k);
				 st.setString(2,isbn);
			st.executeUpdate();
	        
	        }
	        if(flag1==true)
	        {
	        	st=con.prepareStatement("update book_info set author_id=? where book_isbn=?");
	        	st.setInt(1,w);
	        	st.setString(2,isbn);
	       st.executeUpdate();
	        }
	        
	       st=con.prepareStatement("select b_id from book_info where book_isbn=?");
	       st.setString(1, isbn);
			rs4=st.executeQuery();
			while(rs4.next())
			{
				 sk=rs4.getInt(1);
				
				
			}
			st=con.prepareStatement("update book_table set b_info_id=? where isbn=?");
			st.setInt(1,sk);
			st.setString(2,isbn);
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	public User removeBook(String isbn) 
	{
		int r=0;
		User user = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.prepareStatement("update book_info set b_availaibility=?,b_quantity=? where book_isbn=?");
			st.setInt(1,0);
			st.setInt(2, 0);
			st.setString(3, isbn);
			
			st = con.prepareStatement("delete from book_table where isbn=?");
			st.setString(1, isbn);
			
			r = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User approveMembership(User user, String reg_no) 
	{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String role_name=null;
		user.setMem(true);
		user.setSflag(true);
		
		try {
			con = ds.getConnection();
		
			st = con.prepareStatement("SELECT role_assign.ra_login_id, role.role_name FROM role INNER JOIN role_assign ON role_assign.ra_role_id = role.role_id WHERE ra_login_id =?");
			st.setString(1, reg_no);
			rs = st.executeQuery();
				while(rs.next())
					
				{ 
					System.out.print("vj whie mwh");
					role_name=rs.getString("role_name");
					System.out.println(role_name);
					if(role_name.equals("student"))
					{
						st=con.prepareStatement("select * from student_info where stu_id=?");
								st.setString(1, reg_no);
								rs=st.executeQuery();
						while(rs.next())
						{
							//System.out.println(arg0);
					user.setTempName(rs.getString("stu_name"));
					user.setTempId(rs.getString("stu_id"));;
					user.setTempMobile(rs.getString("stu_mobile"));
				        }
					}
					else
					{
						st=con.prepareStatement("select * from staff_info where sta_id=?");
						st.setString(1, reg_no);
					rs=	st.executeQuery();
						while(rs.next())
						{
					user.setTempName(rs.getString("sta_name"));
					user.setTempId(rs.getString("sta_id"));;
					user.setTempMobile(rs.getString("sta_mobile"));
				        }
					}
					
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	public User finalApproval(User user,String reg_no) 
	{
		//int r=0;
		//User user = null;
		Connection con = null;
		String a="approved";
		PreparedStatement st = null;
		ResultSet rs = null;
		 user.setMem(false);
		try {
			con = ds.getConnection();
			st = con.prepareStatement("update login set login_status=? where login_id=?");
			st.setString(1,a);
			st.setString(2,reg_no);
			
			st.executeUpdate();
			
			 
			//System.out.println(r);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	public User tralogin(String regd_no){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User user=null;
		try {
			con=ds.getConnection();
       st=con.prepareStatement("select * from login where login_id=?");
       st.setString(1,regd_no);
       rs=st.executeQuery();
       user=new User();
       while(rs.next())
       {
       String k= rs.getString("login_id");
       if(k.equals(regd_no))
       {
    	   user.setSflag(true);
    	   user.setRegid(regd_no);
       }
       }
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}

	
	
	
	public User issue(User user,String ttl){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("Select lib_magn.publisher_info.pub_name,lib_magn.book_info.b_availaibility,lib_magn.author_info.au_name,"+
  "lib_magn.book_info.b_title,lib_magn.book_info.b_id,lib_magn.book_info.b_edition From lib_magn.author_info Inner Join lib_magn.book_info"+
   " On lib_magn.book_info.author_id = lib_magn.author_info.au_id Inner Join lib_magn.publisher_info On lib_magn.book_info.publisher_id = lib_magn.publisher_info.pub_id where book_info.b_title=?");
			 st.setString(1,ttl);
			 rs = st.executeQuery();
			 user.setLength(user.getBlength());
			 int i=user.getLength();
			 while(rs.next())
			 {
				 user.setBrecords(true);
				 user.setSflag(true);
				 user.setBid(rs.getString("b_id"));
				 user.setBtitle(rs.getString("b_title"));
				 user.setBauthor(rs.getString("au_name"));
				 user.setBpublisher(rs.getString("pub_name"));
				 user.setBedition(rs.getString("b_edition"));
				 user.setAvail(rs.getString("b_availaibility"));
				 i++;
			 }

			 user.setTflag(1);
			 user.setBlength(i);
			 user.setIssue(1);
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}
	public User finalIssue(User user,String bid)
	{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int avail=0;
	    	Date date=new Date();
		 SimpleDateFormat ft=new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		try {
			con = ds.getConnection();
			st =con.prepareStatement("select tra_b_id from transaction where tra_b_id=? and tra_login_id=? and  tra_dor is null");
			st.setString(1,bid);
			st.setString(2,user.getRegid());
			rs=st.executeQuery();
			if(rs.next()!=true){
			st =con.prepareStatement("select  b_availaibility from book_info where b_id=?");
			st.setString(1,bid);
			rs=st.executeQuery();
			if(rs.next()){
				avail=rs.getInt("b_availaibility");
			}
			avail-=1;
			if(avail>0){
			 st =con.prepareStatement("update book_info set b_availaibility=? where b_id=?");
			 st.setString(2,bid);
			 st.setInt(1,avail);
			 int n=st.executeUpdate();
			 if(n>0) {
				 st =con.prepareStatement("insert into transaction(tra_b_id,tra_login_id,tra_doi) values(?,?,?)");
				 st.setString(1,bid);
				 st.setString(2,user.getRegid());
				 st.setString(3, ft.format(date));
				 int nn=0;
				 nn=st.executeUpdate();
				 if(nn==0) {
					 throw new SQLException();
				 }
				 user.setSflag(true);
				user.setTflag(0);
			 }
		}
			user.setIssue(0);
			} else{
				
				user.setTflag(2);
			}
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			return user;
	}
	
	public User returnbook(User user,String ttl){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			user.setTflag(0);
			user.setIssue(0);
			con = ds.getConnection();
			 st =con.prepareStatement("Select lib_magn.transaction.tra_b_id,lib_magn.transaction.tra_login_id,lib_magn.book_info.b_title,lib_magn.book_info.b_edition,lib_magn.author_info.au_name,"
		+"lib_magn.publisher_info.pub_name,lib_magn.book_info.b_availaibility From lib_magn.transaction Inner Join lib_magn.book_info On lib_magn.transaction.tra_b_id = lib_magn.book_info.b_id Inner Join "
					 +"lib_magn.author_info On lib_magn.book_info.author_id = lib_magn.author_info.au_id Inner Join lib_magn.publisher_info On lib_magn.book_info.publisher_id = lib_magn.publisher_info.pub_id where lib_magn.book_info.b_title=? and transaction.tra_login_id=? and transaction.tra_dor is null");
			 st.setString(1,ttl);
			 st.setString(2,user.getRegid());
			 rs = st.executeQuery();
			 if(rs!=null){
				 System.out.println("andar");
			 user.setLength(user.getBlength());
			 int i=user.getLength();
			 String k=null;
			 while(rs.next())
			 {
				 System.out.println("andar!");
				 user.setBrecords(true);
				 user.setSflag(true);
				 k=rs.getString("tra_b_id");
				 user.setBid(k);
				 user.setBtitle(rs.getString("b_title"));
				 user.setBauthor(rs.getString("au_name"));
				 user.setBpublisher(rs.getString("pub_name"));
				 user.setBedition(rs.getString("b_edition"));
			     user.setAvail(rs.getString("b_availaibility"));
				 i++;
			 }			
			 System.out.println("andar!!");
			 user.setTflag(1);
			 user.setBlength(i);
			 user.setRtn(1);
			 } else {
				user.setRtn(2);
			 }
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}

	
	public User finalReturn(User user,String bid)
	{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int avail=0;
		int id=0;
	    	Date date=new Date();
		 SimpleDateFormat ft=new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		try {
			user.setTflag(0);
			con = ds.getConnection();
			st =con.prepareStatement("select  b_availaibility from book_info where b_id=?");
			st.setString(1,bid);
			rs=st.executeQuery();
			if(rs.next()){
				avail=rs.getInt("b_availaibility");
			}
			avail+=1;
			 st =con.prepareStatement("update book_info set b_availaibility=? where b_id=?");
			 st.setInt(1,avail);
			 st.setString(2,bid);
			 int n=st.executeUpdate();
			 if(n>0) {
				 st =con.prepareStatement("select tra_id from transaction where tra_b_id=? and tra_login_id=? and tra_dor is null");
				 st.setString(1,bid);
			     st.setString(2,user.getRegid());
			     rs=st.executeQuery();
			     if(rs.next())
			    	  id = rs.getInt("tra_id");
			     user.setFlength(id);
				 st =con.prepareStatement("update transaction set tra_dor=? where tra_b_id=? and tra_login_id=? and tra_dor is null");
			     st.setString(1, ft.format(date));
			     st.setString(2,bid);
			     st.setString(3,user.getRegid());
				 int nn=0;
				 nn=st.executeUpdate();
				 user.setSflag(true);
				user.setTflag(0);
				user.setRtn(0);
			 }
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return user;
	}
	
	public User fine(User user,String bid){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int n=0,p=0,famt=0;
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("select DATE(tra_doi),DATE(tra_dor) from transaction where tra_b_id=? and tra_login_id=? and tra_doi is not null and tra_dor is not null and tra_id=?");
			 st.setString(1,bid);
			 st.setString(2,user.getRegid());
			 st.setInt(3,user.getFlength());
			 System.out.print("fine ka pehla");
			 rs = st.executeQuery();
			 java.sql.Date doi=null,dor=null;
			 if(rs.next()){
			  doi=rs.getDate("DATE(tra_doi)");
			  dor=rs.getDate("DATE(tra_dor)");
				System.out.println(doi);
				System.out.println(dor);
			 }
			 st =con.prepareStatement("SELECT DATEDIFF(?,?) AS DiffDate");
			 st.setDate(2,doi);
			 st.setDate(1,dor);
			 rs = st.executeQuery();
			 if(rs.next()){
				 n=rs.getInt("DiffDate");
				 System.out.print(n);
			 }
			 if(n>7){
				 p=n-7;
				 famt=p*5;
			 }
			 st =con.prepareStatement("insert into fine(f_tra_id,f_days,f_amt,f_reason) values(?,?,?,?)");
			 st.setInt(1,user.getFlength());
			 st.setInt(2,n);
			 st.setInt(3,famt);
			 st.setString(4,"Late");
			 st.executeUpdate();
			/** int i=user.getLength();
			 while(rs.next())
			 {
				 user.setRecords(true);
				 user.setTitle(rs.getString("b_title"));
				 user.setDoi(rs.getString("tra_doi"));
				 user.setDor(rs.getString("tra_dor"));
				 user.setAmt(rs.getString("f_amt"));
				 i++;
			 }
			 user.setFlength(i);**/
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return user;
		}
	
	public User finepro(User user){
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			 st =con.prepareStatement("Select lib_magn.book_info.b_title, lib_magn.book_info.b_edition, lib_magn.author_info.au_name, lib_magn.publisher_info.pub_name, lib_magn.fine.f_days,"+
					 "lib_magn.fine.f_amt,lib_magn.transaction.tra_doi,lib_magn.transaction.tra_dor From lib_magn.fine Inner Join lib_magn.transaction  On lib_magn.fine.f_tra_id = lib_magn.transaction.tra_id Inner Join"+
					 " lib_magn.book_info  On lib_magn.transaction.tra_b_id = lib_magn.book_info.b_id Inner Join lib_magn.author_info  On lib_magn.book_info.author_id = lib_magn.author_info.au_id Inner Join lib_magn.publisher_info"+
					 " On lib_magn.book_info.publisher_id = lib_magn.publisher_info.pub_id where tra_login_id=?");
			 st.setString(1,user.getId());
			 rs = st.executeQuery();
			 int i=0;
			 while(rs.next())
			 {
				 user.setRecords(true);
				 user.setTitle(rs.getString("b_title"));
				 user.setBauthor(rs.getString("au_name"));
				 user.setBpublisher(rs.getString("pub_name"));
				 user.setBedition(rs.getString("b_edition"));
				 user.setDoi(rs.getString("tra_doi"));
				 user.setDor(rs.getString("tra_dor"));
				 user.setAmt(rs.getString("f_days"));
				 user.setFamt(rs.getString("f_amt"));
				 i++;
			 }
			 user.setBlength(i);
		}
		 catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			return user;
		}
	public User account(String id) {
		User user = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		user = new User();
		try {
			user.setBrecords(true);
			con = ds.getConnection();
			System.out.println("i am setting tid as "+id);
			user.setRegid(id);
			st = con.prepareStatement("SELECT book_info.b_title,book_info.b_id,fine.f_amt,transaction.tra_login_id FROM book_info INNER JOIN .transaction ON transaction.tra_b_id = book_info.b_id INNER JOIN fine ON fine.f_tra_id = transaction.tra_id WHERE tra_login_id =?");
			st.setString(1,id);
			rs =st.executeQuery();
		
			int i=0;
			while(rs.next()) {
				user.setBid(rs.getString("b_id"));
				user.setBtitle(rs.getString("b_title"));
				user.setFamt(rs.getString("f_amt"));
				i++;
			}
			user.setBlength(i);
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User amount(User user,String bid) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int tra=0,fid=0;
		try {
			System.out.println("b_id"+bid);
			con = ds.getConnection();
			st =con.prepareStatement("Select fine.f_amt, transaction.tra_id From fine Inner Join transaction On fine.f_tra_id = transaction.tra_id where tra_b_id=? and tra_login_id=?");
			st.setString(1,bid); 
		
			System.out.println("my tid is+"+user.getRegid());
			st.setString(2,user.getRegid());
			 rs=st.executeQuery();
			 if(rs.next())
			 {
				 tra=rs.getInt("tra_id");
				 System.out.println("intra_id"+tra);
			 }
			 System.out.println("tra_id"+tra);
			 st=con.prepareStatement("select f_id from fine where f_tra_id=?");
			 st.setInt(1,tra);
			 rs=st.executeQuery();
			if(rs.next())
			 {
				fid=rs.getInt("f_id");
			 }
			Date date=new Date();
			 SimpleDateFormat ft=new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
			 st =con.prepareStatement("insert into fine_collect(fi_f_id,fi_date,fi_login_id) values(?,?,?)");
			 
			 st.setInt(1,fid);
			 st.setString(2, ft.format(date));
			// System.out.println("Fine is " +user.getFamount());
			 //st.setString(3,user.getFamount());
			 st.setString(3,user.getRegid());
			int a=st.executeUpdate();
			if(a>0)
			{
				 System.out.println("amt"+a);
				
			}
			else{
				System.out.println("noamt"+a);
			 
			} }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User updateamount(User user,String amt) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int fid=0;
		try {
			user.setBrecords(false);
			con = ds.getConnection();
			 st =con.prepareStatement("select fi_f_id from fine_collect where fi_login_id=? ");
			 st.setString(1,user.getRegid());
			rs= st.executeQuery();
			 if(rs.next())
			 { 
				 fid=rs.getInt("fi_f_id");
				 System.out.println("andar1");
			 }
			 st=con.prepareStatement("update fine_collect set fi_amt=? where fi_f_id=? and fi_login_id=?");
			 st.setString(1,amt);	
			 st.setInt(2, fid);
			 st.setString(3,user.getRegid());
			 int x=st.executeUpdate();
			 if(x>0)
				 System.out.println("to");
		}
			
		 catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
}
