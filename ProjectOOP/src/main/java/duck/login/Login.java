package duck.login;

import com.formdev.flatlaf.FlatClientProperties;
import duck.forms.UserDashboard;
import duck.manager.FormsManager;
import duck.manager.Serializer;
import duck.manager.User;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    public Login() {
        init();
    }
    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        JLabel usernameLabel = new JLabel("Username:");
        FontIcon userIcon = FontIcon.of(Dashicons.ADMIN_USERS);
        userIcon.setIconSize(16);
        userIcon.setIconColor(Color.black);
        usernameLabel.setIcon(userIcon);

        JLabel passwordLabel = new JLabel("Password:");
        FontIcon passwordIcon = FontIcon.of(Dashicons.LOCK);
        passwordIcon.setIconSize(16);
        passwordIcon.setIconColor(Color.black);
        passwordLabel.setIcon(passwordIcon);

        JPanel panel1 = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel1.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,"" +
                "showRevealButton:true");
        JLabel lbTitle = new JLabel("Have A Good Day!");
        lbTitle.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");
        JLabel description = new JLabel("Please sign in to access your account.");
        description.putClientProperty(FlatClientProperties.STYLE,"" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,3%);");
        panel1.add(lbTitle);
        panel1.add(description);
        panel1.add(usernameLabel, "gapy 10");
        panel1.add(txtUsername);
        panel1.add(passwordLabel, "gapy 10");
        panel1.add(txtPassword);
        panel1.add(btnLogin, "gapy 10");
        panel1.add(createSignUpLabel(), "gapy 10");
        add(panel1);

        btnLogin.addActionListener(e ->{
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());

            if (Serializer.fileExists("C:\\Users\\Duc\\Documents\\GitHub\\ProjectOOP\\ProjectOOP\\src\\main\\java\\duck\\database\\users", username+".dat")){
                User user = (User) Serializer.deserializeObject("C:\\Users\\Duc\\Documents\\GitHub\\ProjectOOP\\ProjectOOP\\src\\main\\java\\duck\\database\\users", username+".dat");
                if(password.equals(user.getPassword())) {
                    currentUser = user.getUsername();
                    JOptionPane.showMessageDialog(Login.this, "You have successfully logged in!");
                    FormsManager.getInstance().showForm(new UserDashboard());
                } else{
                    JOptionPane.showMessageDialog(this, "Incorrect password!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Username not found!");
                }
        });
    }

    private Component createSignUpLabel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 ,0));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "background:null");
        JButton regButton = new JButton("<html><a href=\"#\">Sign Up</a></html>");
        regButton.setContentAreaFilled(false);
        regButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        regButton.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Register());
        });
        JLabel noAccountLabel = new JLabel("Don't have an account?");
        panel.add(noAccountLabel);
        panel.add(regButton);
        return panel;
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    public static String currentUser;
}