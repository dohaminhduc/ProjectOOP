package duck.forms.flightPane;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import duck.manager.Passenger;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TicketDialog extends JDialog {
    static Random rd = new Random(); // Removed fixed seed

    public TicketDialog(Passenger passenger, String from, String to, String date, String time) {
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        init(passenger, from, to, date, time);
    }

    private void init(Passenger passenger, String from, String to, String date, String time) {
        setTitle("BOARDING PASS");
        setSize(new Dimension(490, 240));
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel boardingPanel = new JPanel(new MigLayout("insets 0", "[fill]", "[20][grow][20]"));

        boardingPanel.add(createColoredPanel(Color.CYAN, 600, 20), "dock north");
        boardingPanel.add(passengerPanel(passenger, from, to, date, time), "grow");
        boardingPanel.add(createColoredPanel(Color.CYAN, 600, 20), "dock south");

        add(boardingPanel);
    }

    private JComponent passengerPanel(Passenger passenger, String from, String to, String date, String time) {
        JPanel panel = new JPanel(new GridLayout(1, 3, 5, 0));

        JPanel panel1 = new JPanel(new GridLayout(3, 1, 0, 5));
        JPanel panel2 = new JPanel(new GridLayout(3, 1, 0, 5));
        JPanel panel3 = new JPanel(new MigLayout("fill, insets 0, wrap 1", "[center]", "[]10[]"));

        JLabel lblPassengerName = createStyledLabel("PASSENGER NAME:", passenger.getFirstName() + " " + passenger.getLastName());
        JLabel lblFlightDate = createStyledLabel("FLIGHT DATE:", date);
        JLabel lblFlightTime = createStyledLabel("FLIGHT TIME:", time);
        JLabel lblFlightFrom = createStyledLabel("FLIGHT FROM:", from);
        JLabel lblFlightTo = createStyledLabel("FLIGHT TO:", to);

        String fromFirst = from.substring(0, 1).toUpperCase();
        String toFirst = to.substring(0, 1).toUpperCase();
        int randomCode = rd.nextInt(50); // Generates a different random number each time
        JLabel lblFlightCode = createStyledLabel("FLIGHT NUMBER:", fromFirst + toFirst + randomCode);

        JLabel lblAirport = new JLabel("Vietnam Airlines");
        lblAirport.setFont(new Font("Arial", Font.PLAIN, 15));
        lblAirport.setAlignmentX(CENTER_ALIGNMENT);

        ImageIcon barcodeIcon = new ImageIcon(getClass().getResource("/duck/images/barcode1.jpg"));
        Image scaledBarcodeImage = barcodeIcon.getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledBarcodeIcon = new ImageIcon(scaledBarcodeImage);
        JLabel lblBarcode = new JLabel(scaledBarcodeIcon);
        lblBarcode.setHorizontalAlignment(SwingConstants.CENTER);

        panel1.add(lblPassengerName);
        panel1.add(lblFlightTime);
        panel1.add(lblFlightDate);

        panel2.add(lblFlightFrom);
        panel2.add(lblFlightTo);
        panel2.add(lblFlightCode);

        panel3.add(lblAirport, "align center");
        panel3.add(lblBarcode, "align center");

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        return panel;
    }

    private JPanel createColoredPanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(color);
        return panel;
    }

    private JLabel createStyledLabel(String title, String value) {
        JLabel label = new JLabel("<html><b>" + title + "</b><br>" + value + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
}