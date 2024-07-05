package dbUtility;

import dbExpenses.AddNewUser;
import dbExpenses.HomeScreen;
import dbExpenses.dbLogin;

import javax.swing.*;

public class ScreenUtils {

    public static void openLoginScreen() {
        dbLogin gui = new dbLogin();
        gui.setTitle("My Expenses - Login");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setBounds(10, 10, 370, 600);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

    //TODO: In future this can be removed :)
    public static void openMainScreenTEST() {

        HomeScreen hs = new HomeScreen();
        hs.setTitle("My Expenses - Home Page");
        hs.setVisible(true);
        hs.loggedInUsername.setText("Current user");
        hs.getMemberSince.setText("2024");
        hs.setLocationRelativeTo(null);
        hs.setSize(1200,600);
        hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hs.setResizable(false);

    }

    public static void openMainScreen(String rt_username, String rt_memberDate, String rt_isAdmin) {

        HomeScreen hs = new HomeScreen();
        hs.setTitle("My Expenses - Home Page");
        hs.loggedInUsername.setText(rt_username);
        hs.getMemberSince.setText(rt_memberDate);
        hs.rt_isAdmin = rt_isAdmin;
        hs.setVisible(true);
        hs.setLocationRelativeTo(null);
        hs.setSize(1200,600);
        hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hs.setResizable(false);

    }

    public static void openAddNewUser(JLabel loggedInUsername, JLabel getMemberSince, String rt_isAdmin){

        AddNewUser adn = new AddNewUser();
        adn.setTitle("My Expenses - Add New User");
        adn.rt_username = loggedInUsername.getText();
        adn.rt_memberDate = getMemberSince.getText();
        adn.rt_isAdmin = rt_isAdmin;
        adn.setLocationRelativeTo(null);
        adn.setSize(1200,600);
        adn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adn.setResizable(false);
        adn.setVisible(true);

    }

}
