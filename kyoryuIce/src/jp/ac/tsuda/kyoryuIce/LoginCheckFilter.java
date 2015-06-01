package jp.ac.tsuda.kyoryuIce;

import java.io.IOException;
import java.security.Principal;
 
import javax.servlet.*;
import javax.servlet.http.*;
 
import com.google.appengine.api.users.*;
 

public class LoginCheckFilter implements Filter {
	@Override
    public void init(FilterConfig conf) throws ServletException {}
 
    @Override 
    public void doFilter(ServletRequest req,
            ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        UserService service = UserServiceFactory.getUserService();
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        if (service.isUserLoggedIn()){
            session.setAttribute("user", service.getCurrentUser());
            chain.doFilter(request, response);
        } else {
            session.removeAttribute("user");
            String url = request.getRequestURI();
            String loginurl = service.createLoginURL(url);
            response.sendRedirect(loginurl);
        }

    }
     
    @Override
    public void destroy() {}
 
}
