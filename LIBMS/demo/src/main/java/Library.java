import java.sql.*;
import java.util.ArrayList;

/**
 * The Library class represents a library system.
 * It provides methods to connect to a database, perform CRUD operations on books and users,
 * and retrieve information about books and users.
 */
public class Library {
    private static Connection connection;

    public Library(){
        connect_to_database();
    }

    /**
     * Connects to the database using JDBC driver and establishes a connection with the specified credentials.
     */
    private void connect_to_database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "your_new_password");
            System.out.println("Connected");
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and returns a list of all books in the library.
     *
     * @return An ArrayList of strings representing the titles of all books.
     */
    public ArrayList<String> display_all_books(){
        ArrayList<String> books = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Books");
            while (result.next()){
                books.add(result.getString("title"));
            } 
        }
        catch (SQLException e){
            books.add("Unable to complete action. Please try again");
        }
        return books;
    }

    /**
     * Stores a book in the library.
     * 
     * @param book_to_insert The book to be inserted into the library.
     */
    public void store_book(Book book_to_insert){
        try{ 
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Books (title, author, genre, available) VALUES (?,?,?,?)";
            PreparedStatement query_to_run = connection.prepareStatement(query);


            query_to_run.setString(1, book_to_insert.get_title());
            query_to_run.setString(2, book_to_insert.get_author());
            query_to_run.setString(3, book_to_insert.get_genre());
            query_to_run.setString(4, book_to_insert.get_available());


            int affectedrows = query_to_run.executeUpdate();

            System.out.println("Inserted successfully");
            query_to_run.close();
        }
        catch (SQLException e){
            System.out.println("Unable to complete action. Please try again");
        }
    }

    /**
     * Checks the availability of a book in the library.
     * 
     * @param book_name the name of the book to check availability for
     * @return the availability status of the book ("Available" or "Not Available")
     */
    public static String check_availability(String book_name){
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT available FROM Books WHERE title = '" + book_name + '\'');
            while (result.next()){
                return result.getString("available");
            } 
        }
        catch (SQLException e){
            System.out.println("Unable to complete action. Please try again");
            e.printStackTrace();
        }
        
        return "Book not Found.\n Please make sure the name is correct"; // Add this line to return a default value if no result is found
    }

    /**
     * Creates a new user in the library system.
     * 
     * @param user_to_insert the User object representing the user to be inserted
     */
    public void create_new_user(User user_to_insert){
        try{ 
            Statement statement = connection.createStatement();
            String query = "INSERT INTO User (name, contact) VALUES (?,?)";
            PreparedStatement query_to_run = connection.prepareStatement(query);


            query_to_run.setString(1, user_to_insert.get_name());
            query_to_run.setString(2, user_to_insert.get_contact());


            int affectedrows = query_to_run.executeUpdate();

            System.out.println("User created successfully");
            query_to_run.close();
        }
        catch (SQLException e){
            System.out.println("Unable to complete action. Please try again");
        }
    }

    /**
     * Retrieves and returns a list of all users in the library.
     * 
     * @return An ArrayList of strings containing the names of all users.
     */
    public ArrayList<String> display_all_users(){
        ArrayList<String> users = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM User");
            while (result.next()){
                users.add(result.getString("name"));
            } 
        }
        catch (SQLException e){
            users.add("Unable to complete action. Please try again");
        }
        return users;
    }

    /**
     * Deletes a user from the library system.
     * 
     * @param user_name the name of the user to be deleted
     */
    public void delete_user(User user_name){
        try{ 
            Statement statement = connection.createStatement();
            String query = "DELETE FROM User WHERE name = ?";
            PreparedStatement query_to_run = connection.prepareStatement(query);

            query_to_run.setString(1, user_name.get_name());

            int affectedrows = query_to_run.executeUpdate();
            System.out.println("Deleted successfully");
            query_to_run.close();
        }
        catch (SQLException e){
            System.out.println("Unable to complete action. Please try again");
        }
    }

    /**
     * Updates the information of a book in the library.
     * 
     * @param book_to_update The book object containing the updated information.
     */
    public void update_book_info(Book book_to_update){
        try{ 
            Statement statement = connection.createStatement();
            String query = "UPDATE Books SET available = ? WHERE title = ?";
            PreparedStatement query_to_run = connection.prepareStatement(query);

            query_to_run.setString(1, book_to_update.get_available());
            query_to_run.setString(2, book_to_update.get_title());

            int affectedrows = query_to_run.executeUpdate();
            System.out.println("Updated successfully");
            query_to_run.close();
        }
        catch (SQLException e){
            System.out.println("Unable to complete action. Please try again");
        }
    }

    /**
     * Retrieves information about a book from the database based on its title.
     * 
     * @param book_name the title of the book to retrieve
     * @return an ArrayList containing the book's title, author, genre, and availability
     */
    public ArrayList<String> get_book(String book_name){
        ArrayList<String> book = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Books WHERE title = '" + book_name + '\'');
            while (result.next()){
                book.add(result.getString("title"));
                book.add(result.getString("author"));
                book.add(result.getString("genre"));
                book.add(result.getString("available"));
            } 
        }
        catch (SQLException e){
            book.add("Book of the name \'"+ book_name + "\' not found. Please try again");
        }
        if (book.isEmpty() == true){
            book.add("Book of the name \'"+ book_name + "\' not found. Please try again");
        }
        return book;
    }

    /**
     * Checks out a book for a user.
     * 
     * @param book_name the name of the book to be checked out
     * @param user_name the name of the user checking out the book
     * @return a message indicating the status of the checkout process
     */
    public String book_checkout(String book_name, String user_name){
        if (book_name == null || user_name == null){
            return "Book or user not found. Please try again";
        }

        if (check_availability(book_name).equals("No")){
            return "Book not available. Please try again";
        }


        try{ 
            Statement statement = connection.createStatement();
            String query = "UPDATE Books SET available = ? WHERE title = ?";
            PreparedStatement query_to_run = connection.prepareStatement(query);

            query_to_run.setString(1, "No");
            query_to_run.setString(2, book_name);

            int affectedrows = query_to_run.executeUpdate();
            query_to_run.close();
        }
        catch (SQLException e){
            return "Unable to complete action. Please try again";
        }

        try{ 
            Statement statement = connection.createStatement();
            String query = "INSERT INTO borrows (idBooks, idUser) VALUES ((SELECT idBooks FROM Books WHERE title = ?),(SELECT idUser FROM User WHERE name = ?))";
            PreparedStatement query_to_run = connection.prepareStatement(query);

            query_to_run.setString(1, book_name);
            query_to_run.setString(2, user_name);

            int affectedrows = query_to_run.executeUpdate();
            return "Book checked out successfully";
        }
        catch (SQLException e){
            return "Unable to complete action. Please try again";
        }
    }

    /**
     * Retrieves user information from the database based on the given user name.
     * 
     * @param user_name the name of the user to retrieve information for
     * @return an ArrayList containing the user's name and contact information, or an error message if the user is not found
     */
    public ArrayList<String> get_user(String user_name){
        ArrayList<String> user = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM User WHERE name = '" + user_name + '\'');
            while (result.next()){
                user.add(result.getString("name"));
                user.add(result.getString("contact"));
            } 
        }
        catch (SQLException e){
            user.add("User of the name \'"+ user_name + "\' not found. Please try again");
        }
        if (user.isEmpty() == true){
            user.add("User of the name \'"+ user_name + "\' not found. Please try again");
        }
        return user;
    }

    /**
     * Retrieves the list of books rented by a specific user.
     * 
     * @param user_name the user object representing the user whose rented books are to be retrieved
     * @return an ArrayList of strings representing the titles of the rented books
     */
    public ArrayList<String> get_user_rented_books(User user_name){
        ArrayList<String> books = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT Books.title FROM Books INNER JOIN borrows ON Books.idBooks = borrows.idBooks INNER JOIN User ON User.idUser = borrows.idUser WHERE User.name = '" + user_name.get_name() + '\'');
            while (result.next()){
                books.add(result.getString("title"));
            } 
        }
        catch (SQLException e){
            books.add("Unable to complete action. Please try again");
        }
        return books;
    } 

    /**
     * Retrieves the list of users who have rented a specific book.
     * 
     * @param book_name The book for which to retrieve the rented users.
     * @return An ArrayList of Strings containing the names of the users who have rented the book.
     */
    public ArrayList<String> get_book_rented_users(Book book_name){
        ArrayList<String> users = new ArrayList<String>();
        try{ 
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT User.name FROM User INNER JOIN borrows ON User.idUser = borrows.idUser INNER JOIN Books ON Books.idBooks = borrows.idBooks WHERE Books.title = '" + book_name.get_title() + '\'');
            while (result.next()){
                users.add(result.getString("name"));
            } 
        }
        catch (SQLException e){
            users.add("Unable to complete action. Please try again");
        }
        return users;
    }
}