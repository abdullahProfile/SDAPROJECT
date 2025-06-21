
package controller;

import view.LoginView;
import view.StudentDashboard;
import view.SupervisorDashboard;
import view.GroupLeaderDashboard;
import auth.AuthService;

import javax.swing.*;

public class LoginController {
    public LoginController() {
        LoginView loginView = new LoginView();
        AuthService authService = new AuthService();

        loginView.loginBtn.addActionListener(e -> {
            String role = loginView.roleBox.getSelectedItem().toString();
            String id = loginView.idField.getText();
            String pass = new String(loginView.passwordField.getPassword());
            if (authService.login(role, id, pass)) {
                JOptionPane.showMessageDialog(loginView, "Login Success!");
                loginView.dispose();
                switch (role) {
                    case "student" -> new StudentDashboard(id);
                    case "supervisor" -> new SupervisorDashboard(id);
                    case "groupleader" -> new GroupLeaderDashboard(id);
                }
            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid Credentials");
            }
        });

        loginView.signupBtn.addActionListener(e -> {
            String role = loginView.roleBox.getSelectedItem().toString();
            String id = loginView.idField.getText();
            String pass = new String(loginView.passwordField.getPassword());
            if (authService.signup(role, id, pass)) {
                JOptionPane.showMessageDialog(loginView, "Signup Successful");
            }
        });
    }
}
