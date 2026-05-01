package javaapplication7;

import javax.swing.JOptionPane;

public class RegisterForm extends javax.swing.JFrame {

    public RegisterForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
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

        // MAIN PANEL
        jPanel1.setBackground(new java.awt.Color(255, 102, 0));

        // HEADER
        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20));
        jLabel1.setText("REGISTER FORM");
        jPanel2.add(jLabel1);

        // FORM PANEL
        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Full Name:");
        jLabel5.setText("Email:");
        jLabel6.setText("Password:");
        jLabel7.setText("Already have an account?");

        SignUpBtn.setText("SIGN UP");
        loginBtn.setText("LOGIN");

        // BUTTON ACTIONS
        SignUpBtn.addActionListener(evt -> SignUpBtnActionPerformed(evt));
        loginBtn.addActionListener(evt -> loginBtnActionPerformed(evt));

        // LAYOUT
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jname, 200, 200, 200)
                    .addComponent(email, 200, 200, 200)
                    .addComponent(password, 200, 200, 200)
                    .addComponent(SignUpBtn))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addComponent(jLabel7)
                .addGap(20)
                .addComponent(loginBtn)
                .addContainerGap())
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
                .addComponent(SignUpBtn)
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(loginBtn))
                .addGap(30)
        );

        // ADD PANELS
        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        mainLayout.setVerticalGroup(
            mainLayout.createSequentialGroup()
                .addComponent(jPanel2, 60, 60, 60)
                .addComponent(jPanel4)
        );

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }

    // SIGN UP BUTTON FUNCTION
    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String name = jname.getText();
        String userEmail = email.getText();
        String pass = new String(password.getPassword());

        if (name.isEmpty() || userEmail.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
        } else {
            JOptionPane.showMessageDialog(this, "Registration Successful!");

            // CLEAR FIELDS
            jname.setText("");
            email.setText("");
            password.setText("");

            // 👉 AUTO GO BACK TO LOGIN
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }

    // LOGIN BUTTON FUNCTION
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginForm().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RegisterForm().setVisible(true));
    }

    // VARIABLES
    private javax.swing.JButton SignUpBtn;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField email;
    private javax.swing.JTextField jname;
    private javax.swing.JPasswordField password;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
}