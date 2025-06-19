package view;

import storage.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentDashboard extends JFrame {
    public JLabel welcomeLabel = new JLabel();
    public JButton viewProjectBtn = new JButton("View My Project");
    public JButton viewGroupBtn = new JButton("View My Group");
    public JButton logoutBtn = new JButton("Logout");
    private String studentId;

    public StudentDashboard(String studentId) {
        this.studentId = studentId;
        setTitle("Student Dashboard");
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(245, 250, 255));

        welcomeLabel.setText("Welcome Student: " + studentId);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setForeground(new Color(30, 30, 100));

        styleButton(viewProjectBtn);
        styleButton(viewGroupBtn);
        styleButton(logoutBtn);

        add(welcomeLabel);
        add(viewProjectBtn);
        add(viewGroupBtn);
        add(logoutBtn);

        viewGroupBtn.addActionListener(this::viewGroup);
        viewProjectBtn.addActionListener(this::viewProject);
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
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 13));
    }

    private void viewGroup(ActionEvent e) {
        List<String> groups = FileStorage.readLines("group.txt");
        for (String line : groups) {
            String[] ids = line.split(",");
            for (String id : ids) {
                if (id.trim().equals(studentId)) {
                    JOptionPane.showMessageDialog(this, "Group Members: " + line);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "You are not in any group.");
    }

    private void viewProject(ActionEvent e) {
        List<String> groups = FileStorage.readLines("group.txt");
        String groupId = null;
        for (String line : groups) {
            String[] ids = line.split(",");
            for (String id : ids) {
                if (id.trim().equals(studentId)) {
                    groupId = ids[0];
                    break;
                }
            }
            if (groupId != null) break;
        }

        if (groupId == null) {
            JOptionPane.showMessageDialog(this, "You are not assigned to a group.");
            return;
        }

        List<String> projects = FileStorage.readLines("project.txt");
        for (String proj : projects) {
            if (proj.contains(groupId)) {
                JOptionPane.showMessageDialog(this, "Your Project: " + proj);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No project assigned to your group.");
    }