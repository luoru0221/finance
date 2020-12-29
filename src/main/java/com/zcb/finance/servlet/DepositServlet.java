package com.zcb.finance.servlet;

import com.zcb.finance.dao.TradeDao;
import com.zcb.finance.model.TradeInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		double money = Double.parseDouble(request.getParameter("money"));
		
		TradeInfo tr =new TradeInfo();
		tr.setMoney(money);
		
		TradeDao tdao = new TradeDao();
		boolean result = false ;
		result = tdao.deposit(tr,uname);
		if(result){
			request.setAttribute("msg", "存款成功！");
		}else{
			request.setAttribute("msg", "存款失败！");
		}
		request.getRequestDispatcher("deposit.jsp").forward(request, response);
		
        
	}

}
