package com.example.mission1;

import org.sqlite.util.StringUtils;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String command = request.getParameter("command");

        if (command.equals("save")) {
            WifiDbController wifiDbController = new WifiDbController();
            wifiDbController.createTable();
            ApiExplorer apiexplorer = new ApiExplorer();
            apiexplorer.saveDB();
            response.sendRedirect("savePage.jsp");
        }
    }

}
