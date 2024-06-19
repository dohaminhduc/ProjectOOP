package duck.forms;

import duck.manager.Flight;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchPane extends BlurChild {
    private ArrayList<Flight> flights;
    private NewTableModel tableModel;
    private JPanel contentPanel;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;
    public FlightSearchPane() {
        flights = new ArrayList<>();
        init();
    }
    private void init() {
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setOpaque(false);

        tableModel = new NewTableModel();


        JPanel panel1 = new JPanel();
        panel1.setLayout(new MigLayout("wrap 10, insets 15", "[right]", "[center]"));
        JLabel lblFrom = new JLabel("From:");
        JLabel lblTo = new JLabel("To:");
        JLabel lblDate = new JLabel("Date:");
        JLabel lblTime = new JLabel("Time:");
        JLabel lblMonth = new JLabel("Month:");
        JLabel lblYear = new JLabel("Year:");

         fromBox = new JComboBox(new String[]{"Ha Noi", "Hai Phong", "Da Nang", "Phu Quoc", "Ha Long", "Binh Dinh", "Khanh Hoa"});
         toBox = new JComboBox(new String[]{"Ha Noi", "Hai Phong", "Da Nang", "Phu Quoc", "Ha Long", "Binh Dinh", "Khanh Hoa"});

         fromBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateToBox();
                setOpaque(false);
            }
        });

        JComboBox monthBox = new JComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        String[] dates = new String[31];
        for (int i = 0; i < 30; i++) {
            dates[i] = String.valueOf(i + 1);
        }
        JComboBox yearBox = new JComboBox(new String[]{"2024","2025","2026"});
        JComboBox<String> dateBox = new JComboBox<>(dates);
        JComboBox timeBox = new JComboBox(new String[]{"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"});

        JButton refreshButton = new JButton("Refresh");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();
            String date = (String) dateBox.getSelectedItem();
            String month = (String) monthBox.getSelectedItem();
            String year = (String) yearBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();
            int indexTime = timeBox.getSelectedIndex();
            for( int i = indexTime; i < timeBox.getItemCount(); i+=2 ) {
                Flight flight = new Flight(from, to, date, month, year, time);
                // Adding the flight information to the flights list
                flights.add(flight);
            }
            JOptionPane.showMessageDialog(contentPanel, "Flight Search Result", "Search Result", JOptionPane.PLAIN_MESSAGE);
            tableModel.fillTable(flights);
            tableModel.setVisible(true);
        });

        refreshButton.addActionListener(e->{
            fromBox.setSelectedIndex(0);
            toBox.setSelectedIndex(0);
            dateBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            timeBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
        });
        panel1.add(lblFrom, "cell 0 0");
        panel1.add(fromBox, "cell 1 0");
        panel1.add(lblTo, "cell 2 0");
        panel1.add(toBox, "cell 3 0");
        panel1.add(lblDate, "cell 4 0");
        panel1.add(dateBox, "cell 5 0");
        panel1.add(lblMonth, "cell 6 0");
        panel1.add(monthBox, "cell 7 0");
        panel1.add(lblYear, "cell 8 0");
        panel1.add(yearBox, "cell 9 0");
        panel1.add(lblTime, "cell 10 0");
        panel1.add(timeBox, "cell 11 0");
        panel1.add(searchButton, "cell 12 0");
        panel1.add(refreshButton, "cell 13 0");

        contentPanel.add(panel1);
        contentPanel.add(tableModel);
    }
    private void updateToBox() {
        // Get selected item from "From" box
        String selectedItem = (String) fromBox.getSelectedItem();

        // Get all items from "To" box
        List<String> toItems = new ArrayList<>();
        for (int i = 0; i < toBox.getItemCount(); i++) {
            toItems.add((String) toBox.getItemAt(i));
            setOpaque(false);
        }

        // Remove selected item from "To" box
        toItems.remove(selectedItem);

        // Update "To" box with modified list
        toBox.setModel(new DefaultComboBoxModel<>(toItems.toArray(new String[0])));
        setOpaque(false);
    }
}
