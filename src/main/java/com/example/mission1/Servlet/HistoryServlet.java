package com.example.mission1.Servlet;

import com.example.mission1.Controllers.HistoryDbController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HistoryServlet", value = "/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete_id");
        try {
            int id = Integer.parseInt(delete);
            HistoryDbController historyDbController = new HistoryDbController();
            historyDbController.delete(id);
            response.sendRedirect("history.jsp");
        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
        }

    }


}
