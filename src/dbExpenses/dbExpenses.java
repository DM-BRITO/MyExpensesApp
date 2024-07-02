package dbExpenses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class dbExpenses extends JFrame {

    //new layout v0.2
    Container container = getContentPane();
    JLabel successfulOrFail  = new JLabel("");
    JLabel screenTitle = new JLabel("Welcome to My Expenses");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    public dbExpenses() {

        //new ui
        Font loginResultFont = new Font("Serif", Font.BOLD, 20);
        Font titleFont = new Font("Serif", Font.BOLD, 22);

        screenTitle.setFont(titleFont);
        successfulOrFail.setFont(loginResultFont);

        getContentPane().setBackground(new java.awt.Color(213, 157, 93));
        container.setLayout(null);

        screenTitle.setBounds(55, 100, 400, 30);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        successfulOrFail.setBounds(125, 350, 200, 30);

        container.add(screenTitle);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(successfulOrFail);


        logingin VerifyUserDetails = new logingin();
        loginButton.addActionListener(VerifyUserDetails);
        resetButton.addActionListener(VerifyUserDetails);

        showuserpassword sup = new showuserpassword();
        showPassword.addActionListener(sup);


    }

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


    public class logingin implements ActionListener {

        public void actionPerformed(ActionEvent VerifyUserDetails) {

            String optionSelected = VerifyUserDetails.getActionCommand();
            if (optionSelected.equals("LOGIN")) {

                String userAuthentication = "select * from users where username = '" + userTextField.getText() + "'";

                //check the output for the user auth
//                System.out.println(userAuthentication);

                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(userAuthentication);

                    if (!rs.next()) {
                        successfulOrFail.setText("Login Failed");
                    } else {
                        String rt_username = rs.getString("username");
                        String rt_password = rs.getString("password");


                        if (rt_username.equals(userTextField.getText()) && rt_password.equals(passwordField.getText())) {
                            successfulOrFail.setText("Login Successful");
                        } else {
                            successfulOrFail.setText("Login Failed");
                        }
                    }

                    con.close();

                } catch(SQLException ex){
                    Logger.getLogger(dbExpenses.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }

            }else {

                userTextField.setText("");
                passwordField.setText("");
                showPassword.setSelected(false);
                successfulOrFail.setText("");
            }


        }
    }

    public static void main(String[] args) {


        dbExpenses gui = new dbExpenses();
        gui.setTitle("My Expenses - Login");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setBounds(10, 10, 370, 600);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        dbExpenses db = new dbExpenses();
        db.createConnection();

    }


    void createConnection() {
        //declare variables for the connection to the database
        String db_user = "root";
        String db_password = "Legodudu16";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses",db_user , db_password);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbExpenses.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}