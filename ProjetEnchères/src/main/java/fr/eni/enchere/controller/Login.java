package fr.eni.enchere.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.Cookie;

import fr.eni.enchere.bll.BLLException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.MD5;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String cookieValue = getCookieValue(request, "saveEmail");
			if(cookieValue != null) {
				request.setAttribute("cookieValue", cookieValue);
			}
		
			if(request.getParameter("connexionNecessaire") != null) {
				request.setAttribute("connexionNecessaire", (String) request.getParameter("connexionNecessaire"));
			}
			
			if(request.getParameter("lienEnchere") != null && request.getParameter("nomVendeur") != null) {
				request.setAttribute("lienEnchere", (String) request.getParameter("lienEnchere"));
				request.setAttribute("nomVendeur", (String) request.getParameter("nomVendeur"));
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cookieValue = getCookieValue(request, "saveEmail");

		if(request.getParameter("saveMail") != null && request.getParameter("saveMail").equals("saveMail") ) {
			Cookie cookie = new Cookie("saveEmail", request.getParameter("email"));
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("saveEmail","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		String email2 = (String) request.getAttribute("email");
		String mdp2 = (String) request.getAttribute("motDePasse");
		String succes_creation = (String) request.getAttribute("succes_creation");
		
		if(email2 != null && mdp2 != null && succes_creation != null) {
			
			Utilisateur user;
			HttpSession ses;
			ses= request.getSession();
			try {
				user =UtilisateurManager.getInstance().login(email2,MD5.getMd5(mdp2));
				if( user!=null )
				{
					ses.setAttribute("userConnected", user );
					request.setAttribute("succes_creation", request.getAttribute("succes_creation"));
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					}
				} catch (BLLException e) {
				e.printStackTrace();
				}
			} else {
				
				
			Utilisateur user;
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			try {
				user =UtilisateurManager.getInstance().login(email,MD5.getMd5(mdp));
				HttpSession ses;
				ses= request.getSession();
				if(user!=null) {
					ses.setAttribute("userConnected", user);
					if(request.getParameter("lienEnchere") != null && request.getParameter("nomVendeur") != null) {
						request.setAttribute("lienEnchere", (String) request.getParameter("lienEnchere"));
						request.setAttribute("nomVendeur", (String) request.getParameter("nomVendeur"));
						RequestDispatcher rd = request.getRequestDispatcher((String)request.getParameter("lienEnchere")+"&"+(String)request.getParameter("nomVendeur"));
						rd.forward(request, response);
					
					}else {
						
						
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
				}else{	
					
					if(request.getAttribute("lienEnchere") != null && request.getAttribute("nomVendeur") != null) {
						request.setAttribute("lienEnchere", request.getAttribute("lienEnchere"));
						request.setAttribute("nomVendeur", request.getAttribute("nomVendeur"));
					}
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					request.setAttribute("erreur", "Email ou mot de passe non valide.");
					rd.forward(request, response);
				
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
       
    }
}

