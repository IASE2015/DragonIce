package jp.ac.tsuda.kyoryuIce;

import java.io.IOException;


import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import jp.ac.tsuda.kyoryuIce.Ice;
import jp.ac.tsuda.kyoryuIce.PMF;*/

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("no url...");
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String i = request.getParameter("id");
        String name = request.getParameter("name");
        String p = request.getParameter("price");
        
        int id = Integer.parseInt(i);
        int price = Integer.parseInt(p);
        
        
        Ice data = new Ice(id,name,price);
        
        PersistenceManagerFactory factory = PMF.get();
        PersistenceManager manager = factory.getPersistenceManager();
        try {
            manager.makePersistent(data);
        } finally {
            manager.close();
        }
        //response.sendRedirect("/index.html");
    }
}

