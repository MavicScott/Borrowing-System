package javaapplication7;

import javax.swing.JOptionPane;
import java.sql.*;

public class RegisterForm extends javax.swing.JFrame {

    public RegisterForm() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        SignUpBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");

        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20));
        jLabel1.setText("REGISTER FORM");
        jPanel2.add(jLabel1);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Full Name:");
        jLabel5.setText("Username/Email:");
        jLabel6.setText("Password:");
        jLabel7.setText("Already have an account?");

        SignUpBtn.setText("SIGN UP");
        loginBtn.setText("LOGIN");

        SignUpBtn.addActionListener(evt -> SignUpBtnActionPerformed(evt));
        loginBtn.addActionListener(evt -> loginBtnActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jname, 200, 200, 200)
                            .addComponent(email)
                            .addComponent(password)
                            .addComponent(SignUpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginBtn)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jname, 30, 30, 30))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(email, 30, 30, 30))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(password, 30, 30, 30))
                .addGap(25)
                .addComponent(SignUpBtn, 35, 35, 35)
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(loginBtn))
                .addGap(30)
        );

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createSequentialGroup()
                .addComponent(jPanel2, 60, 60, 60)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
    }

    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String name = jname.getText();
        String userEmail = email.getText();
        String pass = new String(password.getPassword());

        if (name.isEmpty() || userEmail.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vismanosddl", "root", "")) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userEmail);
            pst.setString(2, pass);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration Successful! Please login.");
            
            new LoginForm().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginForm().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RegisterForm().setVisible(true));
    }

    private javax.swing.JButton SignUpBtn, loginBtn;
    private javax.swing.JTextField email, jname;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel5, jLabel6, jLabel7;
    private javax.swing.JPanel jPanel1, jPanel2, jPanel4;
}