package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Messages;
import service.MessagesService;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/index.jsp")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 全てのメッセージを取得
		List<Messages> postMessages = MessagesService.selectAllMessages();
		request.setAttribute("postMessages", postMessages);
		
		String defaultMessage = "ここに記入";
		request.setAttribute("defaultMessage", defaultMessage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Top.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = request.getParameter("message");
		MessagesService.insertPostMessages(message);
	}
}
