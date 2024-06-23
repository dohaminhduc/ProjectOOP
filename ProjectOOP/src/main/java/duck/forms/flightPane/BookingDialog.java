package duck.forms.flightPane;

import com.formdev.flatlaf.FlatClientProperties;
import duck.manager.Passenger;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class BookingDialog extends JDialog {
    public BookingDialog(String from, String to, String date, String time) {
        init(from, to, date, time);
    }

    private void init(String from, String to, String date, String time){
        setTitle("Booking Form");
        setSize(600,400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        JPanel infoPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        infoPanel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblFirstName = new JLabel("First Name");
        JLabel lblLastName = new JLabel("Last Name");
        JTextField txtFirstName = new JTextField(13);
        JTextField txtLastName = new JTextField(13);
        panel1.add(lblFirstName);
        panel1.add(txtFirstName);
        panel1.add(lblLastName);
        panel1.add(txtLastName);


        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblYear = new JLabel("Year Of Birth");
        JComboBox comboYear =  new JComboBox(new String[]{"1954", "1955", "1956", "1957", "1958", "1959",
                "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
                "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
                "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
                "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
                "2020", "2021", "2022", "2023", "2024"});
        JLabel lblPhone = new JLabel("Phone Number: ");
        JTextField txtPhone = new JTextField(17);
        panel2.add(lblYear);
        panel2.add(comboYear);
        panel2.add(lblPhone);
        panel2.add(txtPhone);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblID = new JLabel("ID: ");
        JTextField txtID = new JTextField(15);
        JLabel lblGender = new JLabel("Gender");
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton others = new JRadioButton("Others");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(others);
        panel3.add(lblID);
        panel3.add(txtID);
        panel3.add(lblGender);
        panel3.add(male);
        panel3.add(female);
        panel3.add(others);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblAddress = new JLabel("Address");
        JTextField txtAddress = new JTextField(35);
        panel4.add(lblAddress);
        panel4.add(txtAddress);

        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveBTN = new JButton("Save");
        JButton clearBTN = new JButton("Clear");
        panel5.add(saveBTN);
        panel5.add(clearBTN);

        JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblFrom = new JLabel("From: " +from);
        JLabel lblTo = new JLabel("To: "+ to);
        JLabel lblDate = new JLabel("Date: "+date);
        JLabel lblTime = new JLabel("Time: " +time);
        panel6.add(lblFrom);
        panel6.add(Box.createHorizontalStrut(30));
        panel6.add(lblTo);
        panel6.add(Box.createHorizontalStrut(30));
        panel6.add(lblDate);
        panel6.add(Box.createHorizontalStrut(30));
        panel6.add(lblTime);


        saveBTN.addActionListener(e -> {
            try {
                // Get the input values
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String yearOfBirthStr = (String) comboYear.getSelectedItem();
                String address = txtAddress.getText();
                String id = txtID.getText();
                String phone = txtPhone.getText();
                String gender = male.isSelected() ? "Male" : "Female";

                // Validate inputs
                if (firstName.isEmpty()) {
                    throw new IllegalArgumentException("First name is required.");
                }
                if (lastName.isEmpty()) {
                    throw new IllegalArgumentException("Last name is required.");
                }
                if (yearOfBirthStr == null || yearOfBirthStr.isEmpty()) {
                    throw new IllegalArgumentException("Year of birth is required.");
                }
                int yearOfBirth;
                try {
                    yearOfBirth = Integer.parseInt(yearOfBirthStr);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Year of birth should be a valid number.");
                }
                if (address.isEmpty()) {
                    throw new IllegalArgumentException("Address is required.");
                }
                if (id.isEmpty()) {
                    throw new IllegalArgumentException("ID is required.");
                }
                if (phone.isEmpty()) {
                    throw new IllegalArgumentException("Phone number is required.");
                }

                // Additional check for phone and ID to be numbers
                try {
                    Long.parseLong(phone);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Phone number should be a valid number.");
                }
                try {
                    Long.parseLong(id);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("ID should be a valid number.");
                }

                // Create a Passenger object
                Passenger passenger = new Passenger(firstName, lastName, phone, yearOfBirth, gender, id, address);

                // Show the TicketDialog
                TicketDialog ticketDialog = new TicketDialog(passenger, from, to, date, time);
                ticketDialog.setVisible(true);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(BookingDialog.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BookingDialog.this, "An unexpected error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearBTN.addActionListener(e->{
            txtFirstName.setText("");
            txtLastName.setText("");
            txtAddress.setText("");
            txtID.setText("");
            txtPhone.setText("");
            male.setSelected(false);
            female.setSelected(false);
            others.setSelected(false);
            comboYear.setSelectedIndex(0);
        });
        infoPanel.add(panel6);
        infoPanel.add(panel1);
        infoPanel.add(panel2);
        infoPanel.add(panel3);
        infoPanel.add(panel4);
        infoPanel.add(panel5);
        add(infoPanel);
    }
}
