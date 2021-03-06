package com.tuana9a.learn.hibernate.servlets;

import com.tuana9a.learn.hibernate.dao.BookDao;
import com.tuana9a.learn.hibernate.models.Book;
import com.tuana9a.learn.hibernate.models.ResponseEntity;
import com.tuana9a.learn.hibernate.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

@WebServlet("/api/book")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        BookDao dao = BookDao.getInstance();
        JsonUtils jsonUtils = JsonUtils.getInstance();

        Object result = new LinkedList<>();
        if (type.equals("all")) {
            result = dao.findAll();
        } else if (type.equals("name")) {
            result = dao.findByName(name);
        }
        writer.print(jsonUtils.toJson(ResponseEntity.builder()
                .code(1)
                .message("success")
                .data(result)
                .build()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = JsonUtils.getInstance().fromJson(req.getReader(), Book.class);
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        BookDao dao = BookDao.getInstance();
        JsonUtils jsonUtils = JsonUtils.getInstance();

        dao.update(book);
        writer.print(jsonUtils.toJson(ResponseEntity.builder()
                .code(1)
                .message("success")
                .data(book)
                .build()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        BookDao dao = BookDao.getInstance();
        JsonUtils jsonUtils = JsonUtils.getInstance();

        dao.delete(Integer.parseInt(id));
        writer.print(jsonUtils.toJson(ResponseEntity.builder()
                .code(1)
                .message("success")
                .data(id)
                .build()));
    }
}
