package dbUtility;

import dbExpenses.AddNewExpense;
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
        HomeScreen.loggedInUsername.setText("Current user");
        HomeScreen.getMemberSince.setText("2024");
        hs.setLocationRelativeTo(null);
        hs.setSize(1200,600);
        hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hs.setResizable(false);

    }

    public static void openMainScreen(String rt_username, String rt_memberDate, String rt_isAdmin) {

        HomeScreen hs = new HomeScreen();
        hs.setTitle("My Expenses - Home Page");
        HomeScreen.loggedInUsername.setText(rt_username);
        HomeScreen.getMemberSince.setText(rt_memberDate);
        HomeScreen.rt_isAdmin = rt_isAdmin;
        hs.setVisible(true);
        hs.setLocationRelativeTo(null);
        hs.setSize(1200,600);
        hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hs.setResizable(false);

    }

    public static void openAddNewUser(JLabel loggedInUsername, JLabel getMemberSince, String rt_isAdmin){

        AddNewUser adn = new AddNewUser();
        adn.setTitle("My Expenses - Add New User");
        AddNewUser.rt_username = loggedInUsername.getText();
        AddNewUser.rt_memberDate = getMemberSince.getText();
        AddNewUser.rt_isAdmin = rt_isAdmin;
        adn.setLocationRelativeTo(null);
        adn.setSize(1200,600);
        adn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adn.setResizable(false);
        adn.setVisible(true);

    }

    public static void openCreateNewExpense(JLabel loggedInUsername, JLabel getMemberSince, String rt_isAdmin){

        AddNewExpense ane = new AddNewExpense();
        ane.setTitle("My Expenses - Create New Expense");
        AddNewExpense.rt_username = loggedInUsername.getText();
        AddNewExpense.rt_memberDate = getMemberSince.getText();
        AddNewExpense.rt_isAdmin = rt_isAdmin;
        ane.setLocationRelativeTo(null);
        ane.setSize(1200,600);
        ane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ane.setResizable(false);
        ane.setVisible(true);

    }

}
