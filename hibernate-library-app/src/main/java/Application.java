import model.BookDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 *Hello, and welcome to my first Hibernate app, written by Stefan Novak @StefanNovak96 on Twitter
 *
 * This is a simple library app, covering full CRUD operations with the MySQL database.
 * It utilises the BookDetails POJO to create tables with title, author, genre and location information about your favourite books.
 * Perform your crud operations within the Main method, where I have written some example code,
 * which hopefully is easy and intuitive to follow.
 *
 * 
 *
 * If you have any questions, or are a recruiter interested in contacting me, please do at either my twitter, linkedIn or email, or visit my website:
 * Website:  www.stefanalexnovak.com
 * Twitter:  @StefanNovak96
 * LinkedIn: www.linkedin.com/in/stefan-novak-110ab1114/
 * Email:    stefannovak96@gmail.com
 */

public class Application {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create a sessionFactory object." + ex);
            throw new ExceptionInInitializerError();
        }

        ///////////// Perform CRUD operations here /////////////////

        Application ap = new Application();
        BookDetails b;

        //CREATE
        ap.addBook("To a kill Mockingbird", "Harper Lee", "Fiction", "Bookshelf");
        ap.addBook("This gets deleted", "Null", "Fiction", "Bookshelf");

        //READ
        for(BookDetails c : ap.getAllBooks()) {
            System.out.print(c);
        }
        //For a single book by ID
        System.out.println("This is a single book:");
        ap.findBookById(0);

        //UPDATE
        ap.updateBookTitle(0, "To Kill A Mockingbird");

        //DELETE
        System.out.println("*** DELETING BOOKS ***");

        b = findBookById(1);
        ap.deleteBook(b);



        System.out.println("\n***After everything:***\n");

        for(BookDetails c : ap.getAllBooks()) {
            System.out.print(c);
        }

    } //end of Main




    ///////////////////// CREATE ///////////////////////////
    public Integer addBook(String title, String author, String genre, String location) {
        Integer bookID;
        //open a session
        Session session = sessionFactory.openSession();

        //begin a transaction
        session.beginTransaction();

        //Create a book
        BookDetails book = new BookDetails();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setLocation(location);
        bookID = (Integer) session.save(book);

        //save the book
        session.save(book);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();

        return bookID;
    }

    ///////////////////// READ ///////////////////////////
    private List<BookDetails> getAllBooks() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Create Criteria object
        CriteriaQuery<BookDetails> criteriaQuery = session.getCriteriaBuilder().createQuery(BookDetails.class);
        criteriaQuery.from(BookDetails.class);

        //Get a list of Book objects according to the Criteria object
        List<BookDetails> books = session.createQuery(criteriaQuery).getResultList();

        // Close the session
        session.close();

        return books;
    }

    private static BookDetails findBookById(int id) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Retrieve the book object and turn it into a string
        BookDetails book = session.get(BookDetails.class,id);
        System.out.println(book.toString());

        // Close the session
        session.close();

        // Return the object
        return book;
    }

    ///////////////////// UPDATE ///////////////////////////
    public void updateBookTitle(Integer bookId, String title) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Update the book title
        BookDetails book = session.get(BookDetails.class, bookId);
        System.out.println("Before updating title");
        book.setTitle(title);
        System.out.println("After updating title");
        session.update(book);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

    public void updateBookAuthor(Integer bookId, String author) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Update the book author
        BookDetails book = session.get(BookDetails.class, bookId);
        System.out.println("Before updating author");
        book.setAuthor(author);
        System.out.println("After updating author");
        session.update(book);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

    public void updateBookGenre(Integer bookId, String genre) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Update the book genre
        BookDetails book = session.get(BookDetails.class, bookId);
        System.out.println("Before updating genre");
        book.setGenre(genre);
        System.out.println("After updating genre");
        session.update(book);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

    public void updateBookLocation(Integer bookId, String location) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Update the book location
        BookDetails book = session.get(BookDetails.class, bookId);
        System.out.println("Before updating location");
        book.setLocation(location);
        System.out.println("After updating location");
        session.update(book);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

    ///////////////////// DELETE ///////////////////////////
    public void deleteBook(BookDetails bookId) {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //Delete the book
        session.delete(bookId);

        //commit the transaction
        session.getTransaction().commit();

        //close the session
        session.close();
    }

}