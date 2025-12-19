package com.project.code.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    // Save book
    public void saveBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(book);

        transaction.commit();
    }

    // Get book by id
    public Book getBookById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class, id);

        transaction.commit();

        return book;
    }

    // Get all books
    public List<Book> getAllBooks() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Book> books = session.createQuery("FROM Book", Book.class).getResultList();

        transaction.commit();

        return books;
    }

    // Update book
    public void updateBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.update(book);

        transaction.commit();
    }

    // Delete book
    public void deleteBook(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class, id);

        if (book != null) {
            session.delete(book);
        }

        transaction.commit();
    }
}