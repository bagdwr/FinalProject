package com.example.finalproject.Model;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
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


    public User editUser(User user) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE User set name=?, birthday=?, password=? where id=?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setDate(2,Date.valueOf(user.getBirthday()));
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
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

    public void createLibraryUserJoint(Library library, User user) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO LibraryUserJoint values (null,?,?)");
            preparedStatement.setInt(1,library.getId());
            preparedStatement.setInt(2,user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //endregion

    //region News
    public Genre createGenre(Genre genre) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Genre values (null,?)");
            preparedStatement.setString(1,genre.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return genre;
    }

    public News createNews(News news){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO News values (null,?,?,?)");
            preparedStatement.setString(1,news.getTitle());
            preparedStatement.setString(2,news.getMessage());
            preparedStatement.setInt(3,news.getGenre().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return news;
    }

    public Genre genreById(Integer genreId) {
        Genre genre=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Genre where id=?");
            preparedStatement.setInt(1,genreId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                genre=new Genre(resultSet.getInt("id"),resultSet.getString("name"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return genre;
    }

    public News getNewsById(Integer newsId){
        News news=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM News where id=?");
            preparedStatement.setInt(1,newsId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                news=new News(resultSet.getInt("id"),resultSet.getString("title"),resultSet.getString("message"),genreById(resultSet.getInt("genre_id")));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return news;
    }

    public void deleteNewsById(Integer id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE From News where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<News> getAllNews() {
        List<News>newsList=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT* FROM News INNER JOIN Genre on News.genre_id=Genre.id");
            ResultSet resultSet=preparedStatement.executeQuery();
            newsList=new ArrayList<>();
            while (resultSet.next()){
                newsList.add(new News(resultSet.getInt("News.id"), resultSet.getString("title"),resultSet.getString("message"),genreById(resultSet.getInt("genre_id"))));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return newsList;
    }
    //endregion

    //region Job
    public Vacancy createVacancy(Vacancy vacancy) {
        try {
             PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Vacancy values (null,?,?,?)");
             preparedStatement.setString(1,vacancy.getName());
             preparedStatement.setInt(2,vacancy.getSalary());
             preparedStatement.setDouble(3,vacancy.getPoints().doubleValue());
             preparedStatement.executeUpdate();
             preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return vacancy;
    }

    public JobCenter createJobCenter(JobCenter jobCenter){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO JobCenter values (null,?,?)");
            preparedStatement.setString(1,jobCenter.getName());
            preparedStatement.setString(2,jobCenter.getLocation());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jobCenter;
    }

    public Vacancy getVacById(Integer vac_id) {
        Vacancy vacancy=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Vacancy where id=?");
            preparedStatement.setInt(1,vac_id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                vacancy=new Vacancy(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("salary"), BigDecimal.valueOf(resultSet.getDouble("points")));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return vacancy;
    }

    public JobCenter getJobCenterById(Integer jobId){
        JobCenter jobCenter=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * From JobCenter where id=?");
            preparedStatement.setInt(1,jobId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                jobCenter=new JobCenter(jobId, resultSet.getString("name"),resultSet.getString("location"),null);
            }
            PreparedStatement preparedStatement1=connection.prepareStatement("SELECT Vacancy.id,Vacancy.name,Vacancy.salary, Vacancy.points from Vacancy INNER JOIN JobCenterVacancyJoint on Vacancy.id=JobCenterVacancyJoint.vacancy_id INNER JOIN JobCenter ON JobCenterVacancyJoint.jobcenter_id=JobCenter.id where JobCenter.id=?");
            preparedStatement1.setInt(1,jobId);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            List<Vacancy>vacancies=new ArrayList<>();
            while (resultSet1.next()){
                vacancies.add(new Vacancy(resultSet1.getInt("id"),resultSet1.getString("name"),resultSet1.getInt("salary"),BigDecimal.valueOf(resultSet1.getDouble("points"))));
            }
            jobCenter.setVacancies(vacancies);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return jobCenter;
    }

    public JobCenter createJobVacJoint(Integer job_id, Integer vac_id) {
        JobCenter jobCenter=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO JobCenterVacancyJoint values (null,?,?)");
            preparedStatement.setInt(1,job_id);
            preparedStatement.setInt(2,vac_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            jobCenter=getJobCenterById(job_id);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return jobCenter;
    }

    public Vacancy editVacancy(Vacancy vacancy) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE Vacancy set name=?, salary=?, points=? where id=?");
            preparedStatement.setString(1, vacancy.getName());
            preparedStatement.setInt(2,vacancy.getSalary());
            preparedStatement.setDouble(3,vacancy.getPoints().doubleValue());
            preparedStatement.setInt(4,vacancy.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return vacancy;
    }

    public void deleteVacancy(Integer id) {
        try {
            PreparedStatement preparedStatement1=connection.prepareStatement("DELETE FROM JobCenterVacancyJoint where vacancy_id=?");
            preparedStatement1.setInt(1,id);
            preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM Vacancy where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //endregion
}
