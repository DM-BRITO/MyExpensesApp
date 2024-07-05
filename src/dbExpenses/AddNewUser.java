package dbExpenses;

import dbUtility.ScreenUtils;
import Notifications.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewUser extends JFrame {

    //Declare username and member date which is required to load the Home screen correctly
    public static String rt_username;
    public static String rt_memberDate;
    public static String rt_isAdmin;
    boolean insertQueryFailed = false;

    //Declare labels required
    JLabel addAFriendLabel = new JLabel("Add a Friend!", SwingConstants.CENTER);
    JLabel newUsernameLabel = new JLabel("Username");
    JLabel newPasswordLabel = new JLabel("Password");
    JLabel firstNameLabel = new JLabel("First Name");
    JLabel lastNameLabel = new JLabel("Last Name");
    JLabel superUserLabel = new JLabel("Admin Perms");

    //Declare containers and textboxes/buttons
    Container container = getContentPane();
    JTextField usernameField = new JTextField(25);
    JTextField passwordField = new JTextField(25);
    JTextField firstNameField = new JTextField(25);
    JTextField lastNameField = new JTextField(25);
    JCheckBox superUserCheckBox = new JCheckBox("Super User!");
    JButton createNewAccount = new JButton("Create Account");
    JButton clearFields = new JButton("Reset");

    //Declare any global variables
    Font TitleFont;
    Font UserPrompts;

    public AddNewUser(){

        createMenu();
        setLayoutManager();
        createFonts();
        applyLabelFormatting();
        setLocationsAndSize();
        limitTextboxInputLength();
        buildNewUserScreen();

        CreateNewUserInDatabase createUser = new CreateNewUserInDatabase();
        createNewAccount.addActionListener(createUser);

        ResetUserFields reset = new ResetUserFields();
        clearFields.addActionListener(reset);

    }

    public void createMenu(){

        //Create the menu which allows the user to go back onto the login screen.
        // This menu will now create dependencies with the login process WARNING.

        JMenuBar menubar = new JMenuBar();
        add(menubar);

        JMenuItem back = new JMenuItem("Back To Main Menu");
        menubar.add(back);

        setJMenuBar(menubar);

        BackButton bck = new BackButton();
        back.addActionListener(bck);
    }

    public class BackButton implements ActionListener{
        public void actionPerformed(ActionEvent bck){
            ScreenUtils.openMainScreen(rt_username, rt_memberDate, rt_isAdmin);
            dispose();
        }
    }


    public void setLayoutManager(){
        container.setLayout(null);
        container.setBackground(new java.awt.Color(227, 120, 127));
    }

    public void createFonts(){
        TitleFont = new Font("Inter", Font.PLAIN, 30);
        UserPrompts = new Font("Inter", Font.PLAIN, 30);
    }

    public void setLocationsAndSize(){

        addAFriendLabel.setBounds(50, 40,180,30);
        newUsernameLabel.setBounds(95,160,180,30);
        newPasswordLabel.setBounds(300,160,180,30);
        firstNameLabel.setBounds(490,160,180,30);
        lastNameLabel.setBounds(690,160,180,30);
        superUserLabel.setBounds(900,160,180,30);

        usernameField.setBounds(80, 200, 170, 30);
        passwordField.setBounds(280, 200, 170, 30);
        firstNameField.setBounds(480, 200, 170, 30);
        lastNameField.setBounds(680, 200, 170, 30);
        superUserCheckBox.setBounds(925, 200, 130, 30);


        createNewAccount.setBounds(480, 300, 170, 30);
        clearFields.setBounds(480, 340, 170, 30);
    }

    public void applyLabelFormatting(){
        addAFriendLabel.setFont(TitleFont);
        newUsernameLabel.setFont(UserPrompts);
        newPasswordLabel.setFont(UserPrompts);
        firstNameLabel.setFont(UserPrompts);
        lastNameLabel.setFont(UserPrompts);
        superUserLabel.setFont(UserPrompts);

        addAFriendLabel.setForeground(Color.WHITE);
        newUsernameLabel.setForeground(Color.WHITE);
        newPasswordLabel.setForeground(Color.WHITE);
        firstNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setForeground(Color.WHITE);
        superUserLabel.setForeground(Color.WHITE);
    }

    public void limitTextboxInputLength(){

        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().length() >= 25) // limit text field to be no more than the database rules (25 Char MAX)
                    e.consume();
            }
        };

        // Add key listener to each text field
        usernameField.addKeyListener(keyAdapter);
        passwordField.addKeyListener(keyAdapter);
        firstNameField.addKeyListener(keyAdapter);
        lastNameField.addKeyListener(keyAdapter);


    }

    public void buildNewUserScreen(){

        //Adding labels to the container
        for (JLabel jLabel : Arrays.asList(addAFriendLabel, newUsernameLabel, newPasswordLabel, firstNameLabel, lastNameLabel, superUserLabel)) {
            container.add(jLabel);
        }

        //Adding Text fields to the container
        for (JTextField jTextField : Arrays.asList(usernameField, passwordField, firstNameField, lastNameField)) {
            container.add(jTextField);
        }

        container.add(superUserCheckBox);
        container.add(createNewAccount);
        container.add(clearFields);
    }

    public class CreateNewUserInDatabase implements ActionListener {
        public void actionPerformed(ActionEvent createUser) {

            //Disable the button for the duration of this script
            createNewAccount.setEnabled(false);

            //Debug line
//            System.out.println("Create New User");

            //Check all required fields have been inputted.
            if (Objects.equals(usernameField.getText(), "") || Objects.equals(passwordField.getText(), "") || Objects.equals(firstNameField.getText(), "") || Objects.equals(lastNameField.getText(), "")) {
                Notification.ErrorNotificationBadData();
            }else{

                //Declare both queries, one which will insert into the users table and the other will insert into user activity
                String insertIntoUsers = "INSERT INTO users(username, password,first_name, last_name, is_admin, register_date) VALUES (?,?,?,?,?, SYSDATE())";
                String insertIntoActivity = "INSERT INTO user_activity(username, actcod, value_effected, status, act_date) VALUES(?,'USRCR', ?, ?, SYSDATE())";

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
                     PreparedStatement pstmt = con.prepareStatement(insertIntoUsers))
                {

                    pstmt.setString(1, usernameField.getText());
                    pstmt.setString(2, passwordField.getText());
                    pstmt.setString(3, firstNameField.getText());
                    pstmt.setString(4, lastNameField.getText());

                    if (superUserCheckBox.isSelected() && rt_isAdmin.equals("1")){
                        pstmt.setBoolean(5, true);
                    }else {
                        pstmt.setBoolean(5, false);
                    }

                    pstmt.execute();

                   Notification.SuccessfulNotificationUserCreated();
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
                    pstmt.setString(2, usernameField.getText());

                    if (insertQueryFailed){
                        pstmt.setString(3, "FAIL");
                    }else {
                        pstmt.setString(3, "SUCCESS");
                    }

                    pstmt.execute();

                    //If the database is not found display on the screen to signify database issue.
                } catch (SQLException ex) {
                    Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
                    Notification.ErrorNotificationBadData();
                }

            }

            createNewAccount.setEnabled(true);
        }
    }

    public class ResetUserFields implements ActionListener {
        public void actionPerformed(ActionEvent reset) {
            usernameField.setText("");
            passwordField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            superUserCheckBox.setSelected(false);
        }
    }

    public static void main() {



    }
}
