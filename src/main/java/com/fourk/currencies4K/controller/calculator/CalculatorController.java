package com.fourk.currencies4K.controller.calculator;

import com.fourk.currencies4K.dto.BasicCurrencyDto;
import com.fourk.currencies4K.dto.CalculateRequest;
import com.fourk.currencies4K.dto.CalculateResult;
import com.fourk.currencies4K.service.CalculatorService;
import com.fourk.currencies4K.service.CurrencyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/calculator")
public class CalculatorController extends HttpServlet {
    private final CalculatorService calculatorService = new CalculatorService();
    private final CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BasicCurrencyDto> basicCurrencies = currencyService.getBasicCurrencies();
        request.setAttribute("currencies", basicCurrencies);
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CalculateRequest calculateRequest = getCalculateRequest(request);
        CalculateResult result = calculatorService.calculate(calculateRequest);
        List<BasicCurrencyDto> basicCurrencies = currencyService.getBasicCurrencies();
        request.setAttribute("currencies", basicCurrencies);
        request.setAttribute("result", result);
        request.getRequestDispatcher("/views/calculator.jsp").forward(request, response);
    }

    private CalculateRequest getCalculateRequest(HttpServletRequest request) {
        String base = request.getParameter("base");
        String amountString = request.getParameter("amount");
        double amount = Double.parseDouble(amountString);
        String target = request.getParameter("target");
        return new CalculateRequest(base, target, amount);
    }
}
