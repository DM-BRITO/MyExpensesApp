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
        JOptionPane.showMessageDialog(null,"Success - Expense Created", "New Expense Created", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void DataBaseNotificationUnexpectedDownTime(){
        JOptionPane.showMessageDialog(null,"Failed - Database out of reach, please ensure that it's running", "Database is Not Reachable", JOptionPane.ERROR_MESSAGE);
    }

    public static void DataBaseNotificationNoRecordsFound(){
        JOptionPane.showMessageDialog(null,"Failed - There is no data to display here", "No Data Found", JOptionPane.ERROR_MESSAGE);
    }

}