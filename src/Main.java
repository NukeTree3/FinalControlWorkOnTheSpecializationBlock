import com.controllwork.javaprogramm.view.ConsoleUI;
import com.controllwork.javaprogramm.view.View;

import java.io.IOException;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        View view = new ConsoleUI();
        view.start();
    }
}