package com.tuana9a.learn.hibernate.dao;

import com.tuana9a.learn.hibernate.database.HibernateMySQLClient;
import com.tuana9a.learn.hibernate.models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {
    private static final BookDao instance = new BookDao();

    public static BookDao getInstance() {
        return instance;
    }

    private BookDao() {

    }

    public void insertAll(List<Book> books) {

    }

    public List<Book> findAll() {
        HibernateMySQLClient databaseClient = HibernateMySQLClient.getInstance();
        SessionFactory factory = databaseClient.getSessionFactory();
        Session session = factory.openSession();

        List<Book> books = session.createQuery("SELECT b FROM Book b", Book.class).list();
        session.close();

        return books;
    }

    public List<Book> findByName(String name) {
        HibernateMySQLClient databaseClient = HibernateMySQLClient.getInstance();
        SessionFactory factory = databaseClient.getSessionFactory();
        Session session = factory.openSession();

        String sql = "SELECT b FROM Book b WHERE b.name='" + name + "'";
        List<Book> books = session.createQuery(sql, Book.class).list();
        session.close();

        return books;
    }

    public Book findById(long id) {
        HibernateMySQLClient databaseClient = HibernateMySQLClient.getInstance();
        SessionFactory factory = databaseClient.getSessionFactory();
        Session session = factory.openSession();

        String sql = "SELECT b FROM Book b WHERE b.id='" + id + "'";
        Book book = session.createQuery(sql, Book.class).getSingleResult();
        session.close();

        return book;
    }

    public void update(Book newBook) {
        HibernateMySQLClient databaseClient = HibernateMySQLClient.getInstance();
        SessionFactory factory = databaseClient.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book existBook = session.get(Book.class, newBook.getId());
            existBook.setName(newBook.getName());
            existBook.setPublisher(newBook.getPublisher());
            session.update(existBook);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Integer id) {
        HibernateMySQLClient databaseClient = HibernateMySQLClient.getInstance();
        SessionFactory factory = databaseClient.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book existBook = session.get(Book.class, id);
            if (existBook != null) {
                session.delete(existBook);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
