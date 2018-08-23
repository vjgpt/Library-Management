package com.lib.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.lib.db.Database;

@WebListener
public class DBCPoolingListener implements ServletContextListener {

    public DBCPoolingListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	try {
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			Database dao=new Database(ds);
			arg0.getServletContext().setAttribute("DBCPool", dao);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
}
