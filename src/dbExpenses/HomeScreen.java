package dbExpenses;

import dbUtility.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    //Declare menus
    JMenuBar menuBar;
    JMenuItem logout = new JMenuItem("Logout", SwingConstants.LEFT);

    public static String rt_isAdmin;

    //Declare labels
    public static JLabel loggedInUsername = new JLabel("", SwingConstants.RIGHT);
    JLabel loggedInAs = new JLabel("Currently logged in as", SwingConstants.RIGHT);
    JLabel welcomeLabel = new JLabel("Welcome to Your Expenses", SwingConstants.LEFT);
    JLabel thisWeekSpent = new JLabel("This week you've spent £", SwingConstants.LEFT);
    JLabel memberSince = new JLabel("Member Since", SwingConstants.RIGHT);
    public static JLabel getMemberSince = new JLabel("", SwingConstants.RIGHT);

    //Declare buttons and containers
    Container container = getContentPane();
    JButton createFriend = new JButton("Create a Friend");
    JButton createExpenses = new JButton("Create Expenses");
    JButton viewExpenses = new JButton("View Expenses");
    JButton account = new JButton("Account");


    public HomeScreen() {

        setLayoutManager();
        setLocationsAndSize();
        buildHomeScreen();

        /* Create the home screen action lister for all interactions */
        HomeButtons hb = new HomeButtons(this);
        createFriend.addActionListener(hb);
        createExpenses.addActionListener(hb);
        viewExpenses.addActionListener(hb);
        account.addActionListener(hb);
        logout.addActionListener(hb);

    }

    public void setLocationsAndSize(){

        /* Set up the JMenu */
        menuBar = new JMenuBar();
        menuBar.add(logout);
        add(menuBar);
        setJMenuBar(menuBar);

        //Create fonts used for elements
        Font topRightUserElementsFont = new Font("Inter", Font.PLAIN, 30);
        Font topRightMemeberElementsFont = new Font("Inter", Font.PLAIN, 15);
        Font welcomeLabelFont = new Font("Inter", Font.PLAIN, 50);
        Font thisweekspentFont = new Font("Inter", Font.PLAIN, 30);

        //Applying fonts
        loggedInUsername.setFont(topRightUserElementsFont);
        loggedInAs.setFont(topRightUserElementsFont);
        welcomeLabel.setFont(welcomeLabelFont);

        //Applying fonts and changing colours
        memberSince.setFont(topRightMemeberElementsFont);
        getMemberSince.setFont(topRightMemeberElementsFont);
        thisWeekSpent.setFont(thisweekspentFont);

        memberSince.setForeground(Color.white);
        getMemberSince.setForeground(Color.white);
        thisWeekSpent.setForeground(Color.white);

        //set bounds for the elements
        loggedInAs.setBounds(750,20, 400, 35);
        loggedInUsername.setBounds(750, 60, 400, 35);
        memberSince.setBounds(750, 90, 400, 30);
        getMemberSince.setBounds(750, 110, 400, 30);
        welcomeLabel.setBounds(30,150,620,80);
        thisWeekSpent.setBounds(30,220,620,50);

        //Set bounds for the buttons
        createFriend.setBounds(50,350, 200,50);
        createExpenses.setBounds(300,350, 200,50);
        viewExpenses.setBounds(550,350, 200,50);
        account.setBounds(800,350, 200,50);

    }

    public void setLayoutManager(){
        container.setLayout(null);
        container.setBackground(new java.awt.Color(227, 120, 127));
    }


    public void buildHomeScreen(){

        container.add(loggedInAs);
        container.add(loggedInUsername);
        container.add(memberSince);
        container.add(getMemberSince);
        container.add(welcomeLabel);
        container.add(thisWeekSpent);
        container.add(createFriend);
        container.add(createExpenses);
        container.add(viewExpenses);
        container.add(account);

    }

    public static class HomeButtons implements ActionListener {

        HomeScreen homeScreen;

        public HomeButtons(HomeScreen homeScreen) {
            this.homeScreen = homeScreen;
        }

            public void actionPerformed(ActionEvent hb) {

                //Guideline to check what getActionCommand shows
                //System.out.println(hb.getActionCommand());

                //TODO: Add in the actions. Currently the next steps are to create the create friend page,
                // then account, then the expenses view and finally adding an expense

                if ("Create a Friend".equals(hb.getActionCommand())) {
                    ScreenUtils.openAddNewUser(loggedInUsername, getMemberSince, rt_isAdmin);
                    homeScreen.dispose();
                } else if ("Create Expenses".equals(hb.getActionCommand())) {
                    ScreenUtils.openCreateNewExpense(loggedInUsername, getMemberSince, rt_isAdmin);
                    homeScreen.dispose();
                } else if ("View Expenses".equals(hb.getActionCommand())) {
                    ScreenUtils.viewExpenses(loggedInUsername, getMemberSince, rt_isAdmin);
                    homeScreen.dispose();
                } else if ("Account".equals(hb.getActionCommand())) {
                    // Handle "Account" action
                } else if ("Logout".equals(hb.getActionCommand())) {
                    ScreenUtils.openLoginScreen();
                    homeScreen.dispose();
                }

            }
    }

    //TODO: Test if I'm able to remove this main method as
    // I dont require it any more, else can I just make it blank?
    // yes its removable but wait till the end as currently good for testing.
    public static void main() {

        ScreenUtils.openMainScreenTEST();

    }
}
