package duck.forms;

import duck.manager.Flight;
import duck.manager.Reservation;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class NewTableModel extends BlurChild {
    private DefaultTableModel model;
    private JTable table;
    private Reservation reservation;
    public NewTableModel() {
        init();
    }
    private void init() {
        setLayout(new MigLayout("fillx", "[grow]", "[grow]"));
            model = new DefaultTableModel();
            model.addColumn("From");
            model.addColumn("To");
            model.addColumn("Time");
            model.addColumn("Date");

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        fillTable(reservation.getFlightList());
    }
    public void fillTable(ArrayList<Flight> list){
        model.setRowCount(0);
        for(Flight flight : list){
            model.addRow(new String[]{
                    flight.getFrom(),
                    flight.getTo(),
                    flight.getTime(),
                    flight.getDate()
            });
        }
    }
}

