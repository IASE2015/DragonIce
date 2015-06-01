package jp.ac.tsuda.kyoryuIce;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
//import javax.servlet.ServletContext;
import javax.servlet.http.*;

import jp.ac.tsuda.kyoryuIce.Ice;
import jp.ac.tsuda.kyoryuIce.PMF;

@SuppressWarnings("serial")
public class KyoryuIceServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
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
            	Ice data = (Ice)manager.getObjectById(Ice.class,Integer.parseInt(param1));
                list = new ArrayList();
                list.add(data);
            } catch(JDOObjectNotFoundException e){}
        }
        String res = "[";
        if (list != null){
            for(Ice d : list){
                res += "{id:" + d.getId() + ",number:'"+ d.getNumber() +",name:'" + d.getName() + "',price:'" +
                    d.getPrice()  + "'},";
            }
        }
        res += "]";
        out.println(res);
        manager.close();
    }
}
