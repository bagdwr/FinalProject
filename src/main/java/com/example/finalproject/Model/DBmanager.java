package com.example.finalproject.Model;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;

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
    //endregion
}
