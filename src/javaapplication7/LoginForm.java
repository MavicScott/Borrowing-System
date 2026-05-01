package javaapplication7;

import javax.swing.JOptionPane;
import java.sql.*;

public class LoginForm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(LoginForm.class.getName());

    public LoginForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
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

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20));
        jLabel1.setText("LOGIN FORM");

        jLabel2.setText("Password:");
        jLabel3.setText("Username:");

        jlogin.setText("LOGIN");
        jsignup.setText("SIGN UP");

        // ACTIONS
        jlogin.addActionListener(evt -> jloginActionPerformed(evt));
        jsignup.addActionListener(evt -> jsignupActionPerformed(evt));

        // LAYOUT
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fusername, 200, 200, 200)
                    .addComponent(passwordField, 200, 200, 200)))
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addComponent(jlogin, 100, 100, 100)
                .addGap(20)
                .addComponent(jsignup, 100, 100, 100))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30)
                .addComponent(jLabel1)
                .addGap(30)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jLabel3)
                    .addComponent(fusername, 30, 30, 30))
                .addGap(20)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jLabel2)
                    .addComponent(passwordField, 30, 30, 30))
                .addGap(30)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jlogin)
                    .addComponent(jsignup))
                .addGap(30)
        );

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
    }

    // LOGIN FUNCTION
    private void jloginActionPerformed(java.awt.event.ActionEvent evt) {

        String username = fusername.getText();
        String pass = new String(passwordField.getPassword());

        if (username.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vismanosddl",
                    "root",
                    ""
            )) {
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, pass);
                
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    
                    // OPEN DASHBOARD (optional)
                    // new Dashboard().setVisible(true);
                    // this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                    passwordField.setText("");
                }
                
                rs.close();
                pst.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage());
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // SIGNUP BUTTON
    private void jsignupActionPerformed(java.awt.event.ActionEvent evt) {
        new RegisterForm().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }

    // VARIABLES
    private javax.swing.JTextField fusername;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton jlogin;
    private javax.swing.JButton jsignup;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JPanel jPanel1;
}