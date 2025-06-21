package view;

import storage.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GroupLeaderDashboard extends JFrame {
    private JLabel welcomeLabel = new JLabel("Welcome Group Leader");
    private JButton createGroupBtn = new JButton("Create Group");
    private JButton viewGroupsBtn = new JButton("View All Groups");
    private JButton logoutBtn = new JButton("Logout");

    public GroupLeaderDashboard(String adminId) {
        setTitle("Group Leader Dashboard");
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(240, 240, 255));

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setForeground(new Color(60, 60, 120));

        styleButton(createGroupBtn);
        styleButton(viewGroupsBtn);
        styleButton(logoutBtn);

        add(welcomeLabel);
        add(createGroupBtn);
        add(viewGroupsBtn);
        add(logoutBtn);

        createGroupBtn.addActionListener(e -> createGroup());
        viewGroupsBtn.addActionListener(e -> viewGroups());
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
        button.setBackground(new Color(123, 104, 238));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 13));
    }

    private void createGroup() {
        String groupId = JOptionPane.showInputDialog(this, "Enter Group ID:");
        String member1 = JOptionPane.showInputDialog(this, "Enter Member 1 ID:");
        String member2 = JOptionPane.showInputDialog(this, "Enter Member 2 ID:");
        String member3 = JOptionPane.showInputDialog(this, "Enter Member 3 ID (optional):");

        if (groupId == null || member1 == null || member2 == null) {
            JOptionPane.showMessageDialog(this, "Group ID and at least 2 members required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String groupLine = groupId + "," + member1 + "," + member2;
        if (member3 != null && !member3.trim().isEmpty()) {
            groupLine += "," + member3.trim();
        }

        FileStorage.appendLine("group.txt", groupLine);
        JOptionPane.showMessageDialog(this, "Group created successfully!");
    }

    private void viewGroups() {
        List<String> groups = FileStorage.readLines("group.txt");
        StringBuilder builder = new StringBuilder("All Groups:");
        for (String g : groups) {
            builder.append(g).append("");
        }
        JTextArea area = new JTextArea(builder.toString());
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Groups", JOptionPane.INFORMATION_MESSAGE);
    }
}
