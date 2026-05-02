package javaapplication7;

import javax.swing.*;
import java.sql.*;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fusername = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jlogin = new javax.swing.JButton();
        jsignup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));
        jPanel1.setLayout(null); // Absolute positioning to match your UI style

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOGIN FORM");
        jLabel1.setBounds(120, 20, 200, 30);

        jLabel3.setText("Username:");
        jLabel3.setBounds(50, 80, 80, 25);
        fusername.setBounds(140, 80, 150, 25);

        jLabel2.setText("Password:");
        jLabel2.setBounds(50, 120, 80, 25);
        passwordField.setBounds(140, 120, 150, 25);

        jlogin.setText("LOGIN");
        jlogin.setBounds(80, 180, 100, 30);
        jlogin.addActionListener(evt -> jloginActionPerformed(evt));

        jsignup.setText("SIGN UP");
        jsignup.setBounds(200, 180, 100, 30);
        jsignup.addActionListener(evt -> jsignupActionPerformed(evt));

        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(fusername);
        jPanel1.add(passwordField);
        jPanel1.add(jlogin);
        jPanel1.add(jsignup);

        getContentPane().add(jPanel1);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void jloginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = fusername.getText();
        String pass = new String(passwordField.getPassword());

        if (username.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vismanosddl", "root", "")) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // THIS IS THE FIX: Open the registration form
                new Student_reg().setVisible(true); 
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection error!");
            ex.printStackTrace();
        }
    }

    private void jsignupActionPerformed(java.awt.event.ActionEvent evt) {
        // new RegisterForm().setVisible(true);
        // this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }

    private javax.swing.JTextField fusername;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton jlogin, jsignup;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JPanel jPanel1;
}