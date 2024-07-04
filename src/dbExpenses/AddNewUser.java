package dbExpenses;

import dbUtility.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class AddNewUser extends JFrame {

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

    //Declare any global variables
    Font TitleFont;
    Font UserPrompts;

    public AddNewUser(){

        setLayoutManager();
        createFonts();
        applyLabelFormatting();
        setLocationsAndSize();
        limitTextboxInputLength();
        buildNewUserScreen();

        CreateNewUserInDatabase createUser = new CreateNewUserInDatabase();
        createNewAccount.addActionListener(createUser);

    }

    public static class CreateNewUserInDatabase implements ActionListener {
        public void actionPerformed(ActionEvent createUser) {
            //TODO: standardize the database connection and then implement it here.
            // Also this button must not be spam-able,
            // e.g. press it for a second then it blocks for a half a second.

            System.out.println("Create New User");
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
    }

    public static void main(String[] args) {

       ScreenUtils.openAddNewUser();

    }
}
