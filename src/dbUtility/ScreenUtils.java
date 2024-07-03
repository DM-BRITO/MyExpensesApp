package dbUtility;

import dbExpenses.HomeScreen;
import dbExpenses.dbLogin;
import javax.swing.JFrame;

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

    public static void openMainScreen(String rt_username, String rt_memberDate) {

        HomeScreen hs = new HomeScreen();
        hs.setTitle("My Expenses - Home Page");
        hs.loggedInUsername.setText(rt_username);
        hs.getMemberSince.setText(rt_memberDate);
        hs.setVisible(true);
        hs.setLocationRelativeTo(null);
        hs.setSize(1200,600);
        hs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hs.setResizable(false);

    }

}
