package com.fourk.currencies4K.controller.home;

import com.fourk.currencies4K.dto.BasicCurrencyDto;
import com.fourk.currencies4K.service.CurrencyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/average"})
public class HomeController extends HttpServlet {
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BasicCurrencyDto> basicCurrencyList = currencyService.getBasicCurrencies();
        request.setAttribute("rates", basicCurrencyList);
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }
}
