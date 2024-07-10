package dbUtility;

import Notifications.Notification;
import dbExpenses.*;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void viewExpenses(JLabel loggedInUsername, JLabel getMemberSince, String rt_isAdmin){

        ViewExpenses ve = new ViewExpenses();
        ve.setTitle("My Expenses - Create New Expense");
        ViewExpenses.rt_username = loggedInUsername.getText();
        ViewExpenses.rt_memberDate = getMemberSince.getText();
        ViewExpenses.rt_isAdmin = rt_isAdmin;
        ve.setLocationRelativeTo(null);
        ve.setSize(1200,600);
        ve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ve.setResizable(false);
        ve.setVisible(true);

    }

    public static void Account(JLabel loggedInUsername, JLabel getMemberSince, String rt_isAdmin){

        Account viewAcc = new Account();
        viewAcc.setTitle("My Expenses - Create New Expense");
        Account.rt_username = loggedInUsername.getText();
        Account.rt_memberDate = getMemberSince.getText();
        Account.rt_isAdmin = rt_isAdmin;
        viewAcc.setLocationRelativeTo(null);
        viewAcc.setSize(1200,600);
        viewAcc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewAcc.setResizable(false);
        viewAcc.setVisible(true);

    }

    public void getWeekly(){



    }
}
