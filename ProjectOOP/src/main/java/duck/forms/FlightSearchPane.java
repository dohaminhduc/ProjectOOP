package duck.forms;

import duck.manager.Flight;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchPane extends BlurChild {
    private ArrayList<Flight> flights;
    private JPanel contentPanel;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;
    private NewTableModel table;
    public FlightSearchPane() {
        flights = new ArrayList<>();
        init();
    }
    private void init() {
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setOpaque(false);

        setLayout(new MigLayout("wrap 10, insets 15", "[right]", "[center]"));
        JLabel lblFrom = new JLabel("From:");
        JLabel lblTo = new JLabel("To:");
        JLabel lblDate = new JLabel("Date:");
        JLabel lblTime = new JLabel("Time:");
        JLabel lblMonth = new JLabel("Month:");
        JLabel lblYear = new JLabel("Year:");

         fromBox = new JComboBox(new String[]{"Quang Ninh","TPHCM","Lam Dong","Can Tho","Hue","Nghe An","Ha Noi", "Hai Phong",
                 "Da Nang", "Phu Quoc", "Ha Long", "Binh Dinh", "Khanh Hoa"});
         toBox = new JComboBox(new String[]{"Quang Ninh","TPHCM","Lam Dong","Can Tho","Hue","Nghe An","Ha Noi", "Hai Phong",
                 "Da Nang", "Phu Quoc", "Ha Long", "Binh Dinh", "Khanh Hoa"});

         fromBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateToBox();
                setOpaque(false);
            }
        });
        JComboBox monthBox = new JComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"});
        String[] dates = new String[31];
        for (int i = 0; i < 30; i++) {
            dates[i] = String.valueOf(i + 1);
        }
        JComboBox yearBox = new JComboBox(new String[]{"2024"});
        JComboBox<String> dateBox = new JComboBox<>(dates);
        JComboBox timeBox = new JComboBox(new String[]{"0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00",
                "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00","21:00","22:00","23:00"});

        JButton confirmBTN = new JButton("Confirm");
        confirmBTN.addActionListener(e -> {
            table.confirmFlight();
            confirmBTN.setFocusable(false);
        });

        JButton refreshButton = new JButton("Refresh");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            flights.clear();
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();
            String date = (String) dateBox.getSelectedItem();
            String year = (String) yearBox.getSelectedItem();
            int indexTime = timeBox.getSelectedIndex();

            int days = Integer.parseInt(date);
            int months = monthBox.getSelectedIndex() + 1; // ComboBox index starts from 0
            int years = Integer.parseInt(year);

            // Create LocalDate object
            LocalDate flightDate = LocalDate.of(years, months, days);
            for( int i = indexTime; i < timeBox.getItemCount(); i+=2 ) {
                String time = (String) timeBox.getItemAt(i);
                Flight flight = new Flight(from, to, time, flightDate);
                // Adding the flight information to the flights list
                flights.add(flight);
            }
            JOptionPane.showMessageDialog(contentPanel, "Flight Search Result", "Search Result", JOptionPane.PLAIN_MESSAGE);
            table.fillTable(flights);
            confirmBTN.setVisible(true);
        });

        refreshButton.addActionListener(e->{
            fromBox.setSelectedIndex(0);
            toBox.setSelectedIndex(0);
            dateBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            timeBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            flights.clear();
            confirmBTN.setVisible(false);
            table.fillTable(flights);
        });
        add(lblFrom, "cell 0 0");
        add(fromBox, "cell 1 0");
        add(lblTo, "cell 2 0");
        add(toBox, "cell 3 0");
        add(lblDate, "cell 4 0");
        add(dateBox, "cell 5 0");
        add(lblMonth, "cell 6 0");
        add(monthBox, "cell 7 0");
        add(lblYear, "cell 8 0");
        add(yearBox, "cell 9 0");
        add(lblTime, "cell 10 0");
        add(timeBox, "cell 11 0");
        add(searchButton, "cell 12 0");
        add(refreshButton, "cell 13 0, wrap");
        table = new NewTableModel();
        add(table, "span,grow,wrap");
        add(confirmBTN, "span, center");
        confirmBTN.setVisible(false);
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




