package dbExpenses;

import Notifications.Notification;
import dbUtility.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Account extends JFrame {

    //Declare menu variables
    JMenuBar menuBar = new JMenuBar();
    JMenuItem back = new JMenuItem("Back");

    //Declare variables for user identification
    public static String rt_username;
    public static String rt_memberDate;
    public static String rt_isAdmin;

    //Declare labels
    JLabel lbl_currUsername = new JLabel("Username");
    JLabel lbl_password = new JLabel("Password");
    JLabel lbl_firstName = new JLabel("First Name");
    JLabel lbl_lastName = new JLabel("Last Name");
    JLabel lbl_registeredDate = new JLabel("Registered Date");

    //Declare container, buttons, text fields
    Container container = getContentPane();
    JLabel getCurrUsername = new JLabel();
    JTextField getCurrpassword = new JTextField("",25);
    JTextField getFirstName = new JTextField("",25);
    JTextField getLastName = new JTextField("",25);
    JTextField getRegisteredDate = new JTextField("",25);

    //button to load account details
    JButton loadAccountDetails = new JButton("Load Account Details");
    JButton updateAccountDetails = new JButton("Update Account Details");

    public Account() {

        createMenu();
        setLayoutManager();
        setLocationsAndSize();
        buildNewUserScreen();

        loadAccount la = new loadAccount();
        loadAccountDetails.addActionListener(la);
        updateAccountDetails.addActionListener(la);

    }

    public class loadAccount implements ActionListener {
        public void actionPerformed(ActionEvent la) {
            if (Objects.equals(la.getActionCommand(), "Load Account Details")){
                fetchUserDetails();
            }else{
                updateUserDetails();
            }
        }
    }

    public void updateUserDetails(){
        String query = "update users set first_name = ?, last_name = ?, password = ? where username = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
             PreparedStatement pstmt = con.prepareStatement(query)) {

            if (getFirstName == null || getLastName == null || getCurrpassword == null || getCurrUsername == null) {
                Notification.ErrorNotificationBadData();
            }else {
                pstmt.setString(1, getFirstName.getText());
                pstmt.setString(2, getLastName.getText());
                pstmt.setString(3, getCurrpassword.getText());
                pstmt.setString(4, getCurrUsername.getText());

                pstmt.execute();
            }

            System.out.println(pstmt);

        } catch (SQLException ex) {
            Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
            Notification.DataBaseNotificationUnexpectedDownTime();
        }

    }

    public void fetchUserDetails() {
        String query = "SELECT username, first_name, last_name, password, register_date FROM users WHERE username = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, rt_username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    getCurrUsername.setText(rs.getString("username"));
                    getCurrpassword.setText(rs.getString("password"));
                    getFirstName.setText(rs.getString("first_name"));
                    getLastName.setText(rs.getString("last_name"));
                    getRegisteredDate.setText(rs.getString("register_date"));
                }else {
                    Notification.DataBaseNotificationNoRecordsFound();
                }
                con.close();
            }
            //If the database is not found display on the screen to signify database issue.
        } catch (SQLException ex) {
            Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
            Notification.DataBaseNotificationUnexpectedDownTime();
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

        lbl_currUsername.setBounds(50, 40,350,30);
        lbl_password.setBounds(50,160,260,30);
        lbl_firstName.setBounds(320,160,240,30);
        lbl_lastName.setBounds(570,160,240,30);
        lbl_registeredDate.setBounds(780,160,240,30);

        getCurrUsername.setBounds(200, 40,200,30);
        getCurrpassword.setBounds(50,200,260,30);
        getFirstName.setBounds(320,200,200,30);
        getLastName.setBounds(570,200,200,30);
        getRegisteredDate.setBounds(780,200,200,30);

        loadAccountDetails.setBounds(600,40,350,30);
        updateAccountDetails.setBounds(600,90,350,30);
    }

    public void buildNewUserScreen(){

        //Adding labels to the container
        for (JLabel jLabel : Arrays.asList(lbl_currUsername, lbl_password,
                lbl_firstName, lbl_lastName, lbl_registeredDate, getCurrUsername)) {
            container.add(jLabel);
        }

        //Adding Text fields to the container
        for (JTextField jTextField : Arrays.asList(getCurrpassword, getFirstName, getLastName, getRegisteredDate)) {
            container.add(jTextField);
        }

        container.add(loadAccountDetails);
        container.add(updateAccountDetails);
    }

}
