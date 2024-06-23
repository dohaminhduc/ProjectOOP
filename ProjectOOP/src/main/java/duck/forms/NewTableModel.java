package duck.forms;

import duck.forms.flightPane.BookingDialog;
import duck.manager.Flight;
import duck.manager.Reservation;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class NewTableModel extends JScrollPane {
    private DefaultTableModel model;
    private JTable table;
    private Reservation reservation;

    public NewTableModel() {
        init();
    }

    private void init() {
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);
        getViewport().setOpaque(false);
        getVerticalScrollBar().setUnitIncrement(10);
        getVerticalScrollBar().setOpaque(false);

        model = new DefaultTableModel();
        model.addColumn("From");
        model.addColumn("To");
        model.addColumn("Time");
        model.addColumn("Date");

        table = new JTable(model);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        // Create an instance of the custom renderer
        DefaultTableCellRenderer centerRenderer = new CenterRenderer();

        // Set the custom renderer for each column
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        setViewportView(table);
    }

    public void fillTable(ArrayList<Flight> flights) {
        model.setRowCount(0);
        for (Flight flight : flights) {
            model.addRow(new String[]{
                    flight.getFrom(),
                    flight.getTo(),
                    flight.getTime(),
                    String.valueOf(flight.getDate())
            });
        }
    }
    public void confirmFlight() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to book tickets for this flight ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                String from = (String) table.getValueAt(row, 0);
                String to = (String) table.getValueAt(row, 1);
                String time = (String) table.getValueAt(row, 2);
                String date = (String) table.getValueAt(row, 3);

                BookingDialog bookingDialog = new BookingDialog(from, to, date, time);
                bookingDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a flight first.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
class CenterRenderer extends DefaultTableCellRenderer {
    public CenterRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
