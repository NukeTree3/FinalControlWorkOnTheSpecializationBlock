import com.controllwork.javaprogramm.model.animals.Animal;
import com.controllwork.javaprogramm.model.animals.pets.Dog;
import com.controllwork.javaprogramm.model.service.Service;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_friends","root","matvei1220");
//        if(connection!=null){
//            System.out.println("ЯПИЯПИЯПИ");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM all_animals");
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name"));
//            }
//        }
//        else{
//            System.out.println("((((((((((");
//        }
        Service service = new Service();
        service.getAnimal("Стич");
        LocalDate date = LocalDate.now();
        ArrayList arrayList = new ArrayList<>();
        Dog animal = new Dog("Пуфик",date,arrayList);
        System.out.println(animal.getType());
        //service.addAnimal(animal);
        service.addAnimalCommand(animal,"ждать");
        service.getAnimalOrderByBirthdate();
        service.getAnimalOrderByBirthdate("2000-09-21","2024-11-17");
    }
}