
package view;

import storage.FileStorage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SupervisorDashboard extends JFrame {
    private JLabel welcomeLabel;
    private JButton assignProjectBtn;
    private JButton evaluateGroupBtn;
    private JButton logoutBtn;
    private String supervisorId;

    public SupervisorDashboard(String supervisorId) {
        this.supervisorId = supervisorId;
        setTitle("Supervisor Dashboard");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        getContentPane().setBackground(new Color(230, 255, 240));

        welcomeLabel = new JLabel("Welcome Supervisor: " + supervisorId);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 100, 0));

        assignProjectBtn = new JButton("Assign Project");
        evaluateGroupBtn = new JButton("Evaluate Group");
        logoutBtn = new JButton("Logout");

        styleButton(assignProjectBtn);
        styleButton(evaluateGroupBtn);
        styleButton(logoutBtn);

        // Layout setup
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(assignProjectBtn, gbc);

        gbc.gridx = 1;
        add(evaluateGroupBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(logoutBtn, gbc);

        // Button actions
        assignProjectBtn.addActionListener(this::assignProject);
        evaluateGroupBtn.addActionListener(this::evaluateGroup);
        logoutBtn.addActionListener(e -> {
            dispose();
            new controller.LoginController();
        });

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(46, 139, 87));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void assignProject(ActionEvent e) {
        String groupId = JOptionPane.showInputDialog(this, "Enter Group ID:");
        String projectTitle = JOptionPane.showInputDialog(this, "Enter Project Title:");
        if (groupId != null && projectTitle != null && !groupId.trim().isEmpty() && !projectTitle.trim().isEmpty()) {
            FileStorage.appendLine("project.txt", groupId + "," + projectTitle);
            JOptionPane.showMessageDialog(this, "Project Assigned.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void evaluateGroup(ActionEvent e) {
        String groupId = JOptionPane.showInputDialog(this, "Enter Group ID:");
        String criteria = JOptionPane.showInputDialog(this, "Enter Evaluation Criteria:");
        String marks = JOptionPane.showInputDialog(this, "Enter Marks:");
        if (groupId != null && criteria != null && marks != null &&
            !groupId.trim().isEmpty() && !criteria.trim().isEmpty() && !marks.trim().isEmpty()) {
            FileStorage.appendLine("evaluation.txt", groupId + "," + criteria + "," + marks);
            JOptionPane.showMessageDialog(this, "Evaluation Saved.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
}
