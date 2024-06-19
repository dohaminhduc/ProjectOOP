package duck.forms.flightPane;

import duck.manager.Passenger;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class BookingDialog extends JDialog {
    public BookingDialog() {
        init();
    }
    private void init(){
        setTitle("Booking Form");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new MigLayout("fill, insets 20","[center]", "[center]"));

        JLabel lblFirstName = new JLabel("First Name");
        JLabel lblLastName = new JLabel("Last Name: ");
        JLabel lblYearOfBirth = new JLabel("Year of Birth: ");
        JLabel lblAddress = new JLabel("Address: ");
        JLabel lblID = new JLabel("ID: ");
        JLabel lblPhone = new JLabel("Phone: ");
        JLabel lblGender = new JLabel("Gender: ");

        JTextField txtFirstName = new JTextField(7);
        JTextField txtLastName = new JTextField(7);
        JTextField txtYearOfBirth = new JTextField(7);
        JTextField txtAddress = new JTextField(20);
        JTextField txtID = new JTextField(20);
        JTextField txtPhone = new JTextField(20);

        JRadioButton maleBTN = new JRadioButton("Male");
        JRadioButton femaleBTN = new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(maleBTN);
        group.add(femaleBTN);

        JButton saveBTN = new JButton("Save");
        JButton clearBTN = new JButton("Clear");

        saveBTN.addActionListener(e -> {
           try{
               String firstName = txtFirstName.getText();
               String lastName = txtLastName.getText();
               int yearOfBirth = Integer.parseInt(txtYearOfBirth.getText());
               String address = txtAddress.getText();
               String id = txtID.getText();
               String phone = txtPhone.getText();
               String gender = maleBTN.isSelected() ? "Male" : "Female";
               Passenger passenger = new Passenger(firstName,lastName,address, yearOfBirth,phone,gender);

           } catch (NumberFormatException ex){
               ex.printStackTrace();
               JOptionPane.showMessageDialog(BookingDialog.this, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
           }
        });

        clearBTN.addActionListener(e->{
            txtFirstName.setText("");
            txtYearOfBirth.setText("");
            txtAddress.setText("");
            txtID.setText("");
            txtPhone.setText("");
            maleBTN.setSelected(false);
            femaleBTN.setSelected(false);
        });
        add(lblFirstName);
        add(txtFirstName);
        add(lblLastName);
        add(txtLastName);
        add(lblYearOfBirth);
        add(txtYearOfBirth);
        add(lblAddress);
        add(txtAddress);
        add(lblID);
        add(txtID);
        add(lblPhone);
        add(txtPhone);
        add(lblGender);
        add(maleBTN);
        add(femaleBTN);
    }
}
