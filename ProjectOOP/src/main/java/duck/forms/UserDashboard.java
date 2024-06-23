package duck.forms;

import duck.manager.IndexChangeListener;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends BlurBackground implements IndexChangeListener {
    public UserDashboard() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/duck/images/background.jpg"));
        setOverlay(new StyleOverlay(new Color(20,20,20, 255),0.2f));
        setImage(icon.getImage());
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill", "[fill]", "[fill]"));
        userSystemMenu = new UserSystemMenu();
        add(userSystemMenu, "dock west,gap 6 6 6 6 width 280!");
        cards = new JPanel(new CardLayout());
        flightSearchPane = new FlightSearchPane();
        previewPane = new PreviewPane();
        contactPane = new ContactPane();
        cards.add(flightSearchPane, FLIGHTSEARCH_PANE);
        cards.add(previewPane, PREVIEW_PANE);
        cards.add(contactPane, CONTACT_PANE);
        add(cards, "grow");
        switchPane(UserSystemMenu.index);
        userSystemMenu.addIndexChangeListener(this);
        setOpaque(false);
    }
    private void switchPane(int index){
        CardLayout cl = (CardLayout)cards.getLayout();
        if(index == 0){
            setOpaque(false);
            cl.show(cards, FLIGHTSEARCH_PANE);
        } else if (index == 1) {
            setOpaque(false);
            cl.show(cards, PREVIEW_PANE);
        } else if (index == 2) {
            cl.show(cards, CONTACT_PANE);
        }
    }
    private duck.forms.UserSystemMenu userSystemMenu;
    private FlightSearchPane flightSearchPane;
    private PreviewPane previewPane;
    private ContactPane contactPane;
    private JPanel cards;
    final static String FLIGHTSEARCH_PANE = "FlightSearch Pane";
    final static String PREVIEW_PANE = "Preview Pane";
    final static String CONTACT_PANE = "Contact Pane";

    @Override
    public void onIndexChanged(int newIndex) {
        switchPane(newIndex);
    }
}
