package com.example.finalproject.Model;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DBmanager {
    private Connection connection;
    public DBmanager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //region USER
    public User createUser(User user){
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO User VALUES (null,?,?,?,?);");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2=connection.prepareStatement("SELECT * FROM User where email=?");
            preparedStatement2.setString(1,user.getEmail());
            ResultSet resultSet=preparedStatement2.executeQuery();
            Integer id=null;
            if (resultSet.next()){
                id=resultSet.getInt("id");
            }
            createUserJointRole(id);
            preparedStatement.close();
            preparedStatement2.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
    public void createUserJointRole(Integer id){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO UserRoleJoint values (null,?,1)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public User getUserByID(Integer id) {
        User user=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM User  where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next()){
                user=new User(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getString("email"),resultSet.getDate("birthday").toLocalDate()
                        ,resultSet.getString("password"),null);
            }
            preparedStatement=connection.prepareStatement("SELECT Role.ID, Role.name from Role inner join UserRoleJoint on Role.ID=UserRoleJoint.role_id inner " +
                    "join User on User.id=UserRoleJoint.user_id where User.id=?");
            preparedStatement.setInt(1,user.getId());
            ArrayList<Role> roles=new ArrayList<>();
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                roles.add(new Role(resultSet.getInt("id"),resultSet.getString("name")));
            }
            user.setRoles(roles);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM User  where email=?");
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next()){
                user=new User(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getString("email"),resultSet.getDate("birthday").toLocalDate()
                        ,resultSet.getString("password"),null);
            }
            preparedStatement=connection.prepareStatement("SELECT Role.ID, Role.name from Role inner join UserRoleJoint on Role.ID=UserRoleJoint.role_id inner " +
                    "join User on User.id=UserRoleJoint.user_id where User.id=?");
            preparedStatement.setInt(1,user.getId());
            ArrayList<Role> roles=new ArrayList<>();
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                roles.add(new Role(resultSet.getInt("id"),resultSet.getString("name")));
            }
            user.setRoles(roles);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User>users=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM User;");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                List<Role>roles=new ArrayList<>();
                preparedStatement=connection.prepareStatement("SELECT Role.ID, Role.name from Role inner join UserRoleJoint on Role.ID=UserRoleJoint.role_id inner\n" +
                        "join User on User.id=UserRoleJoint.user_id where User.id=?");
                preparedStatement.setInt(1,resultSet.getInt("id"));
                ResultSet resultRole=preparedStatement.executeQuery();
                while (resultRole.next()){
                    roles.add(new Role(resultRole.getInt("id"),resultRole.getString("name")));
                }
                users.add(new User(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getString("email"),resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getString("password"),
                        roles));
            }
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return users;
    }
    //endregion

    //region Library
    public Book createBook(Book book) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO Book values (null,?,?,?)");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return book;
    }

    public Library createLibrary(Library library){
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO Library values (null,?,?)");
            preparedStatement.setString(1,library.getName());
            preparedStatement.setString(2, library.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return library;
    }

    public Library getLibByID(Integer id) {
        Library library=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT* from Library where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                library=new Library(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("address"),null);
            }

            PreparedStatement preparedStatement1=connection.prepareStatement("SELECT Book.id, Book.name,Book.author,Book.genre from Book, LibraryBookJoint, Library where LibraryBookJoint.book_id=Book.id and Library.id=LibraryBookJoint.library_id and Library.id=?");
            preparedStatement1.setInt(1,id);
            resultSet=preparedStatement1.executeQuery();
            List<Book>books=new ArrayList<>();
            while (resultSet.next()){
                books.add(new Book(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("author"), resultSet.getString("genre")));
            }
            library.setBooks(books);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return library;
    }

    public Book getBookByID(Integer id) {
        Book book=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT* from Book where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                book=new Book(resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("author"), resultSet.getString("genre"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return book;
    }

    public Library createLibraryBookJoint(Library library, Book book) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO LibraryBookJoint values (null,?,?)");
            preparedStatement.setInt(1,library.getId());
            preparedStatement.setInt(2,book.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            library=getLibByID(library.getId());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return library;
    }
    //endregion
}
