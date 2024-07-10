package dbExpenses;

import dbUtility.ScreenUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class dbLogin extends JFrame {

    // Declare all JFrame assets required, assign them if possible
    Container container = getContentPane();
    JLabel successfulOrFail = new JLabel("");
    JLabel screenTitle = new JLabel("Welcome to My Expenses");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");



    public dbLogin() {

        // Create fonts required for the login screen.
        Font loginResultFont = new Font("Serif", Font.BOLD, 20);
        Font titleFont = new Font("Serif", Font.BOLD, 22);

        //Setting the fonts to the two JLabels which should be in serif
        screenTitle.setFont(titleFont);
        successfulOrFail.setFont(loginResultFont);

        //Setting a background colour and layout using a container.
        getContentPane().setBackground(new java.awt.Color(227, 120, 127));
        showPassword.setBackground(new java.awt.Color(227, 120, 127));
        container.setLayout(null);

        //Managing where things appear on the screen
        screenTitle.setBounds(55, 100, 400, 30);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        successfulOrFail.setBounds(125, 350, 200, 30);

        //Add all onto the container
        container.add(screenTitle);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(successfulOrFail);

        //If the login or reset buttons are selected I want to run VerifyUserDetails
        logingin VerifyUserDetails = new logingin();
        loginButton.addActionListener(VerifyUserDetails);
        resetButton.addActionListener(VerifyUserDetails);

        //Show password toggle
        showuserpassword sup = new showuserpassword();
        showPassword.addActionListener(sup);
    }

    //Either show or hide the password depending on checkbox status
    public class showuserpassword implements ActionListener {
        public void actionPerformed(ActionEvent sup) {
            if (sup.getSource() == showPassword) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        }
    }

    //Get the option the user selected and then either login or reset the fields, to login getText and run it into the login method.
    public class logingin implements ActionListener {
        public void actionPerformed(ActionEvent VerifyUserDetails) {
            String optionSelected = VerifyUserDetails.getActionCommand();
            if (optionSelected.equals("LOGIN")) {
                login(userTextField.getText(), new String(passwordField.getPassword()));
            } else {
                userTextField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                successfulOrFail.setText("");
            }
        }
    }

    //Connect to the SQL database and check whether this user exists and if so verify the password match.
    private void login(String username, String password) {
        String query = "SELECT username, first_name, last_name, password, is_admin, YEAR(register_date) FROM users WHERE username = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String rt_username = rs.getString("username");
                    String rt_password = rs.getString("password");
                    String rt_memberDate = rs.getString("YEAR(register_date)").substring(0,4);
                    String rt_isAdmin = rs.getString("is_admin");

                    if (rt_username.equals(username) && rt_password.equals(password)) {
                        successfulOrFail.setText("Login Successful");

                        ScreenUtils.openMainScreen(rt_username, rt_memberDate, rt_isAdmin);
                        con.close();
                        dispose();

                    } else {
                        successfulOrFail.setText("Login Failed");
                        con.close();
                    }
                } else {
                    successfulOrFail.setText("Login Failed");
                    con.close();
                }
            }
            //If the database is not found display on the screen to signify database issue.
        } catch (SQLException ex) {
            Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
            successfulOrFail.setText("Database error");
        }
    }

    public static void main() {
        //Main method to initialise the program.
        ScreenUtils.openLoginScreen();
    }
}