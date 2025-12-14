package UniversityManagmentSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textFieldName;
    JPasswordField passwordField;
    JButton login, back;

    Login() {
        System.out.println("[DEBUG] Starting Login Frame initialization...");

        setLayout(null);

        // ===== Background =====
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/logback.jpg"));
        Image bgImg = bgIcon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setBounds(0, 0, 600, 300);
        add(background);

        // ===== Username =====
        JLabel labelName = new JLabel("Username");
        labelName.setForeground(Color.BLACK);
        labelName.setBounds(40, 60, 100, 25);
        background.add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(150, 60, 150, 25);
        background.add(textFieldName);

        // ===== Password =====
        JLabel labelpass = new JLabel("Password");
        labelpass.setForeground(Color.BLACK);
        labelpass.setBounds(40, 110, 100, 25);
        background.add(labelpass);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 150, 25);
        background.add(passwordField);

        // ===== Buttons =====
        login = new JButton("Login");
        login.setBounds(40, 170, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        background.add(login);

        back = new JButton("Back");
        back.setBounds(180, 170, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        background.add(back);

        // ===== User Icon =====
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("icons/second.png"));
        Image userImg = userIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(userImg));
        img.setBounds(350, 50, 200, 200);
        background.add(img);

        // Frame settings
        setSize(600, 300);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        System.out.println("[DEBUG] Login Frame initialized successfully.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            System.out.println("[DEBUG] Login button clicked. Starting authentication...");

            String username = textFieldName.getText();
            String password = new String(passwordField.getPassword()); // safer way

            String query = "select * from login where username = ? and password = ?";

            try {
                System.out.println("[DEBUG] Attempting database connection...");
                Connection connection = Conn.getConnection();

                System.out.println("[DEBUG] Preparing SQL statement...");
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                System.out.println("[DEBUG] Executing query...");
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    System.out.println("[DEBUG] Login successful for user: " + username);
                    setVisible(false);

                    new main_class();

                } else {
                    System.out.println("[DEBUG] Login failed. Invalid username or password.");
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

                System.out.println("[DEBUG] Closing ResultSet, PreparedStatement, and Connection...");
                resultSet.close();
                ps.close();
                connection.close();
                System.out.println("[DEBUG] Database resources closed successfully.");

            } catch (Exception ex) {
                System.err.println("[ERROR] Exception occurred during login process:");
                ex.printStackTrace();
            }

            System.out.println("[DEBUG] Authentication process completed.");
        } else if (e.getSource() == back) {
            System.out.println("[DEBUG] Back button clicked. Closing Login Frame...");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        System.out.println("[DEBUG] Launching Login application...");
        new Login();
        System.out.println("[DEBUG] Login application started.");
    }
}
