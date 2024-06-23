package duck.forms;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class ContactPane extends BlurChild {
    public ContactPane() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap 2, insets 20", "[right][fill]", "[center]"));

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JLabel lblWebsite = new JLabel("Website:");
        JLabel lblSocialMedia = new JLabel("Social Media:");

        JLabel phoneNumber = new JLabel("0974 357 317");
        JLabel website = new JLabel("<html><a href=''>www.vietjetair.com</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.setForeground(Color.BLUE);
        website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openWebpage("https://www.vietjetair.com");
            }
        });

        JPanel socialMediaPanel = new JPanel(new MigLayout("wrap 2", "[fill]"));
        socialMediaPanel.setOpaque(false);

        JLabel facebookLink = new JLabel("<html><a href=''>Facebook</a></html>");
        facebookLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        facebookLink.setForeground(Color.BLUE);
        facebookLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openWebpage("http://www.facebook.com");
            }
        });

        socialMediaPanel.add(facebookLink);
        add(lblPhoneNumber);
        add(phoneNumber);
        add(lblWebsite);
        add(website);
        add(lblSocialMedia);
        add(socialMediaPanel);
    }
    private void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URI(urlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

