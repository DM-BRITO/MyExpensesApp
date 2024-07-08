package Notifications;

import javax.swing.*;

public class Notification {

    public static void ErrorNotificationBadData() {
        JOptionPane.showMessageDialog(null, "Error - Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void SuccessfulNotificationUserCreated() {
        JOptionPane.showMessageDialog(null, "Success - User Created", "New User Created", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void SuccessfulNotificationExpenseCreated(){
        JOptionPane.showMessageDialog(null,"Sucess - Expense Created", "New Expense Created", JOptionPane.INFORMATION_MESSAGE);
    }

}