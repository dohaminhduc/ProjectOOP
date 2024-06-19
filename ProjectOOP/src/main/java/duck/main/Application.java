package duck.main;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import duck.login.Login;
import duck.manager.FormsManager;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public Application() {
        init();
    }
    private void init(){
        setTitle("Flight Reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1250,800));
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new Login());
        FormsManager.getInstance().initApplication(this);
    }
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatMacDarkLaf.registerCustomDefaultsSource("src/main/resources/duck/themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY,Font.PLAIN,13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }
}
