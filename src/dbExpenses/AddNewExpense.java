package dbExpenses;

import Notifications.Notification;
import dbUtility.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewExpense extends JFrame {

    //declare variables for user identification
    public static String rt_username;
    public static String rt_memberDate;
    public static String rt_isAdmin;
    boolean insertQueryFailed = false;

    //Declare menu variables
    JMenuBar menuBar = new JMenuBar();
    JMenuItem back = new JMenuItem("Back");

    //Declare container, buttons, text fields
    Container container = getContentPane();

    //Declare labels required
    JLabel createNewExpense = new JLabel("Create a New Expense!", SwingConstants.CENTER);
    JLabel tranRef = new JLabel("Transaction Reference", SwingConstants.CENTER);
    JLabel locationExpense = new JLabel("Location Expense", SwingConstants.CENTER);
    JLabel amountPaid = new JLabel("Amount Paid", SwingConstants.CENTER);
    JLabel transactionDate = new JLabel("Transaction Date", SwingConstants.CENTER);

    //Declare textboxes
    JTextField tf_tranRef = new JTextField();
    JTextField tf_locationExpense = new JTextField();
    JTextField tf_amountPaid = new JTextField();
    JTextField tf_transactionDate = new JTextField();

    //Declare buttons
    JButton submitExpense = new JButton("Submit Expense");

    public AddNewExpense() {

        createMenu();
        setLayoutManager();
        setLocationsAndSize();
        buildNewUserScreen();
        labelFormatting();

        submitButton sub = new submitButton();
        submitExpense.addActionListener(sub);

    }

    public class submitButton implements ActionListener{
        public void actionPerformed(ActionEvent sub){

            if (!Objects.equals(tf_tranRef.getText(), "") || !Objects.equals(tf_locationExpense.getText(), "") || !Objects.equals(tf_amountPaid.getText(), "") || !Objects.equals(tf_transactionDate.getText(), "") ){
                System.out.println("This does exist");

                String insertIntoUsers = "INSERT INTO transactions(tran_ref, username, location, amount_paid, tran_date)VALUES(?,?,?,?,?)";
                String insertIntoActivity = "INSERT INTO user_activity(username, actcod, value_effected, status, act_date) VALUES(?,'NEWEX', ?, ?, SYSDATE())";

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
                     PreparedStatement pstmt = con.prepareStatement(insertIntoUsers))
                {

                    pstmt.setString(1, tf_tranRef.getText());
                    pstmt.setString(2,rt_username);
                    pstmt.setString(3, tf_locationExpense.getText());
                    pstmt.setString(4, tf_amountPaid.getText());
                    pstmt.setString(5, tf_transactionDate.getText());

                    pstmt.execute();

                    Notification.SuccessfulNotificationExpenseCreated();
                    //If the database is not found display on the screen to signify database issue.
                } catch (SQLException ex) {
                    Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
                    Notification.ErrorNotificationBadData();
                    insertQueryFailed = true;
                }

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
                     PreparedStatement pstmt = con.prepareStatement(insertIntoActivity))
                {

                    pstmt.setString(1, rt_username);
                    pstmt.setString(2, tf_tranRef.getText());
                    if (insertQueryFailed){
                        pstmt.setString(3, "FAIL");
                    }else {
                        pstmt.setString(3, "SUCCESS");
                    }

                    pstmt.execute();

                    //If the database is not found display on the screen to signify database issue.
                } catch (SQLException ex) {
                    Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else {
                Notification.ErrorNotificationBadData();
            }

        }
    }

    public void createMenu() {
        add(menuBar);
        menuBar.add(back);
        setJMenuBar(menuBar);
        BackButton bck = new BackButton();
        back.addActionListener(bck);
    }

    class BackButton implements ActionListener {
        public void actionPerformed(ActionEvent bck) {
            ScreenUtils.openMainScreen(rt_username, rt_memberDate, rt_isAdmin);
            dispose();
        }
    }

    public void setLayoutManager(){
        container.setLayout(null);
        container.setBackground(new java.awt.Color(227, 120, 127));
    }

    public void setLocationsAndSize(){

        createNewExpense.setBounds(50, 40,350,30);
        tranRef.setBounds(50,160,260,30);
        locationExpense.setBounds(320,160,240,30);
        amountPaid.setBounds(570,160,240,30);
        transactionDate.setBounds(780,160,240,30);

        tf_tranRef.setBounds(100, 200, 170, 30);
        tf_locationExpense.setBounds(360, 200, 170, 30);
        tf_amountPaid.setBounds(600, 200, 170, 30);
        tf_transactionDate.setBounds(810, 200, 180, 30);

        submitExpense.setBounds(400, 300, 180, 30);
    }

    public void labelFormatting(){

        Font TitleFont = new Font("Inter", Font.PLAIN, 30);
        Font UserPrompts = new Font("Inter", Font.PLAIN, 24);

        createNewExpense.setFont(TitleFont);
        tranRef.setFont(UserPrompts);
        locationExpense.setFont(UserPrompts);
        amountPaid.setFont(UserPrompts);
        transactionDate.setFont(UserPrompts);

        createNewExpense.setForeground(Color.WHITE);
        tranRef.setForeground(Color.WHITE);
        locationExpense.setForeground(Color.WHITE);
        amountPaid.setForeground(Color.WHITE);
        transactionDate.setForeground(Color.WHITE);

    }


    public void buildNewUserScreen(){

        //Adding labels to the container
        for (JLabel jLabel : Arrays.asList(createNewExpense, tranRef, locationExpense, amountPaid, transactionDate)) {
            container.add(jLabel);
        }

        //Adding Text fields to the container
        for (JTextField jTextField : Arrays.asList(tf_tranRef, tf_locationExpense, tf_amountPaid, tf_transactionDate)) {
            container.add(jTextField);
        }

        container.add(submitExpense);
//        container.add(clearFields);
    }


}
