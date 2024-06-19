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

        JLabel phoneNumber = new JLabel("+1-234-567-8901");
        JLabel website = new JLabel("<html><a href=''>www.example.com</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.setForeground(Color.BLUE);
        website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openWebpage("http://www.example.com");
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
        JLabel twitterLink = new JLabel("<html><a href=''>Twitter</a></html>");
        twitterLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twitterLink.setForeground(Color.BLUE);
        twitterLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openWebpage("http://www.twitter.com");
            }
        });
        socialMediaPanel.add(facebookLink);
        socialMediaPanel.add(twitterLink);
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

