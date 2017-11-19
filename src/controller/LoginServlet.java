package controller;
//①お決まりのインポート文（Eclipseでサーブレットクラスを作成すると自動で書いてくれる）
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Users;
import service.UsersService;

@WebServlet("/login")
//②サーブレットクラスを作るにはサーブレットクラスの元になるHttpServletクラスを継承する必要がある。
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	
    //③サーブレットクラスがGETで呼ばれた場合のdoGetメソッドをオーバーライドする。書き方は基本このお決まりの書き方になる。
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            //処理を書く
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
        
    	
    }
    @Override
    //④サーブレットクラスがPOSTで呼ばれた場合のdoPostメソッドをオーバーライドする。書き方は基本このお決まりの書き方になる。
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            //処理を書く
    	String loginId = null;
    	String password = null;
    	
    	loginId = request.getParameter("loginId");
    	password = request.getParameter("password");
    	
		try {
			Users users = UsersService.selectLoginUsers(loginId, password);
			
			if(users != null){
			//ログイン成功の処理
		    	//getServletConfig().getServletContext().
		    	response.sendRedirect("./");
		  //      request.getRequestDispatcher("/Top.jsp").forward(request, response);
	
			} else {
			//ログイン失敗の処理
				//postの時のアドレス指定
		    	response.sendRedirect("./");
		    	//getの時のアドレス指定
		    	//request.getRequestDispatcher("/Top.jsp").forward(request, response);
		    	
			}
	    	
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }
}