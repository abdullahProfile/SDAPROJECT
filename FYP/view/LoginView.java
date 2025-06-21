package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField idField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JComboBox<String> roleBox = new JComboBox<>(new String[]{"student", "supervisor", "groupleader"});
    public JButton loginBtn = new JButton("Login");
    public JButton signupBtn = new JButton("Signup");

    public LoginView() {
        setTitle("FYP Management Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        getContentPane().setBackground(new Color(230, 240, 255));

        JLabel title = new JLabel("FYP Management System");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(25, 25, 112));

        JLabel idLabel = new JLabel("User ID:");
        JLabel passLabel = new JLabel("Password:");
        JLabel roleLabel = new JLabel("Role:");

        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        signupBtn.setBackground(new Color(60, 179, 113));
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setFocusPainted(false);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(roleLabel, gbc);
        gbc.gridx = 1;
        add(roleBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(loginBtn, gbc);
        gbc.gridx = 1;
        add(signupBtn, gbc);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}