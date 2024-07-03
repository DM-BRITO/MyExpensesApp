package dbUtility;

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

}
