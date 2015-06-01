package jp.ac.tsuda.kyoryuIce;

//import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
// java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import jp.ac.tsuda.kyoryuIce.Ice;
import jp.ac.tsuda.kyoryuIce.PMF;

/**
 *
 * @author 
 */
@SuppressWarnings("serial")
public class DatabaseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        String param1 = request.getParameter("id");
        PrintWriter out = response.getWriter();
        List<Ice> list = null;
        if (param1 == null || param1 ==""){
            String query = "select from " + Ice.class.getName();
            try {
                list = (List<Ice>)manager.newQuery(query).execute();
            } catch(JDOObjectNotFoundException e){}
        } else {
            try {
            	Ice data = (Ice)manager.getObjectById(Ice.class,Long.parseLong(param1));
                list = new ArrayList();
                list.add(data);
            } catch(JDOObjectNotFoundException e){}
        }
        String res = "[";
        if (list != null){
            for(Ice data:list){
                res += "{id:" +data.getId()+",number:'"+ data.getNumber() + "',name:'" + data.getName() + "',price:'" +
                    data.getPrice() + "'},";
            }
        }
        res += "]";
        out.println(res);
        manager.close();
    }
}
