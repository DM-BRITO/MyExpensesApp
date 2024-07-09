package dbExpenses;

import Notifications.Notification;
import dbUtility.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewExpenses extends JFrame {

    public static String rt_username;
    public static String rt_memberDate;
    public static String rt_isAdmin;

    //Declare menu variables
    JMenuBar menuBar = new JMenuBar();
    JMenuItem back = new JMenuItem("Back");

    //Declare container, buttons and table
    Container container = getContentPane();
    JButton refreshData = new JButton("Refresh Data");
    static DefaultTableModel displayResults = new DefaultTableModel();
    static JTable table = new JTable(displayResults);
    JScrollPane scrollPane = new JScrollPane(table);

    public ViewExpenses() {

        createMenu();
        setLayoutManager();
        setLocationsAndSize();
        buildNewUserScreen();
        refreshDataButton rdb = new refreshDataButton();
        refreshData.addActionListener(rdb);

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
        container.setLayout(new BorderLayout());
        container.setBackground(new java.awt.Color(227, 120, 127));
        displayResults.setColumnIdentifiers(new Object[]{"Transaction Reference", "Username", "Location", "Amount Paid", "Transaction Date"});
    }

    public void setLocationsAndSize(){
        refreshData.setBounds(450,40,200,30);
    }

    public static class refreshDataButton implements ActionListener {
        public void actionPerformed(ActionEvent rdb) {
            String query = "SELECT * FROM transactions where username = ?";
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses", "root", "Legodudu16");
                 PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, rt_username);
                ResultSet rs = pstmt.executeQuery();
                //clearing previous data from the model
                displayResults.setRowCount(0);
                //fill table with data

                if (rs.next()){
                    while(rs.next()){
                        Object[] row = new Object[] {rs.getObject(2), rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6)};
                        displayResults.addRow(row);
                }} else {
                    Notification.DataBaseNotificationNoRecordsFound();
                }

                table.setModel(displayResults);
            } catch (Exception ex) {
                Logger.getLogger(dbLogin.class.getName()).log(Level.SEVERE, null, ex);
               Notification.DataBaseNotificationUnexpectedDownTime();
            }
        }
    }

    public void buildNewUserScreen(){
        container.add(refreshData, BorderLayout.NORTH);

        container.add(scrollPane, BorderLayout.CENTER);
    }
}