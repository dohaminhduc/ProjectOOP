package duck.forms;

import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PreviewPane extends BlurChild {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private int currentIndex = 0;
    private final int totalPanels = 3;

    public PreviewPane() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a CardLayout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create and add panels to the CardLayout panel
        cardPanel.add(createPanelWithImage("", "/duck/images/camranh.jpg"), "1");
        cardPanel.add(createPanelWithImage("", "/duck/images/phu-quoc-airport.jpg"), "2");
        cardPanel.add(createPanelWithImage("", "/duck/images/san-bay-noi-bai.jpg"), "3");

        // Create navigation buttons
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousPanel();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextPanel();
            }
        });

        // Create small panels for the buttons
        JPanel previousButtonPanel = new JPanel();
        previousButtonPanel.add(previousButton);
        previousButtonPanel.setPreferredSize(new Dimension(100, 100)); // Adjust the size as needed

        JPanel nextButtonPanel = new JPanel();
        nextButtonPanel.add(nextButton);
        nextButtonPanel.setPreferredSize(new Dimension(100, 100)); // Adjust the size as needed

        // Add components to the main panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.weighty = 1.0;
        add(previousButtonPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        add(cardPanel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.1;
        add(nextButtonPanel, gbc);
    }

    private JPanel createPanelWithImage(String text, String imagePath) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);

        // Load image using ClassLoader
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = imageIcon.getImage();

        // Calculate scaled dimensions
        int maxWidth = 700 ; // Maximum width for the image
        int maxHeight = 600; // Maximum height for the image
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();

        // Scale the image if it exceeds the maximum dimensions
        if (width > maxWidth || height > maxHeight) {
            double scale = Math.min((double) maxWidth / width, (double) maxHeight / height);
            int newWidth = (int) (width * scale);
            int newHeight = (int) (height * scale);
            image = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        }

        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        return panel;
    }

    private void showPreviousPanel() {
        if (currentIndex > 0) {
            currentIndex--;
            cardLayout.show(cardPanel, "" + (currentIndex + 1));
        } else {
            currentIndex = totalPanels - 1; // Set index to the last panel
            cardLayout.show(cardPanel, "" + (currentIndex + 1));
        }
    }

    private void showNextPanel() {
        if (currentIndex < totalPanels - 1) {
            currentIndex++;
            cardLayout.show(cardPanel, "" + (currentIndex + 1));
        } else {
            currentIndex = 0; // Set index to the first panel
            cardLayout.show(cardPanel, "" + (currentIndex + 1));
        }
    }
}
