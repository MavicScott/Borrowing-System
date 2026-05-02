package javaapplication7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Student_reg extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/vismanosddl";
    String user = "root";
    String pass = "";

    public Student_reg() {
        initComponents();
        loadData(); // Automatically fill table on start
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabelId = new javax.swing.JLabel("Id");
        jLabelName = new javax.swing.JLabel("Name");
        jLabelGender = new javax.swing.JLabel("Gender");
        jLabelInstructor = new javax.swing.JLabel("Instructor");
        
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtInstructor = new javax.swing.JTextField();
        
        rbMale = new javax.swing.JRadioButton("Male");
        rbFemale = new javax.swing.JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        
        jComboBox1 = new javax.swing.JComboBox<>(new String[] { "Volleyball", "Basketball", "Badminton" });
        jSpinner1 = new javax.swing.JSpinner();
        
        btnAdd = new javax.swing.JButton("Add");
        btnUpdate = new javax.swing.JButton("Update");
        btnDelete = new javax.swing.JButton("Delete");
        
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Registration System");

        jPanel1.setBackground(new java.awt.Color(230, 80, 40)); 
        jPanel1.setLayout(null);

        // Styling
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        jLabelId.setFont(labelFont); jLabelId.setBounds(30, 80, 50, 25);
        txtId.setBounds(100, 80, 160, 30);

        jLabelName.setFont(labelFont); jLabelName.setBounds(30, 130, 60, 25);
        txtName.setBounds(100, 130, 160, 30);

        jLabelGender.setFont(labelFont); jLabelGender.setBounds(30, 180, 70, 25);
        rbMale.setBounds(100, 180, 70, 30); rbMale.setOpaque(false);
        rbFemale.setBounds(175, 180, 85, 30); rbFemale.setOpaque(false);

        jLabelInstructor.setFont(labelFont); jLabelInstructor.setBounds(30, 230, 100, 25);
        txtInstructor.setBounds(100, 230, 160, 30);

        jComboBox1.setBounds(30, 280, 100, 30);
        jSpinner1.setBounds(140, 280, 50, 30);

        // Table with ID Column Added
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Id", "Name", "Instructor", "Equipment", "Unit"}
        ));
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBounds(300, 50, 430, 280);

        // Buttons
        btnAdd.setBounds(350, 350, 100, 35);
        btnAdd.addActionListener(e -> addStudent());

        btnUpdate.setBounds(470, 350, 100, 35);
        btnDelete.setBounds(590, 350, 100, 35);

        jPanel1.add(jLabelId); jPanel1.add(txtId);
        jPanel1.add(jLabelName); jPanel1.add(txtName);
        jPanel1.add(jLabelGender); jPanel1.add(rbMale); jPanel1.add(rbFemale);
        jPanel1.add(jLabelInstructor); jPanel1.add(txtInstructor);
        jPanel1.add(jComboBox1); jPanel1.add(jSpinner1);
        jPanel1.add(jScrollPane1);
        jPanel1.add(btnAdd); jPanel1.add(btnUpdate); jPanel1.add(btnDelete);

        getContentPane().add(jPanel1);
        setSize(780, 450);
        setLocationRelativeTo(null);
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); 
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "SELECT id, name, instructor, equipment, unit FROM registrations";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"), 
                    rs.getString("name"), 
                    rs.getString("instructor"), 
                    rs.getString("equipment"), 
                    rs.getString("unit")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addStudent() {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO registrations (id, name, gender, instructor, equipment, unit) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3, rbMale.isSelected() ? "Male" : "Female");
            pst.setString(4, txtInstructor.getText());
            pst.setString(5, jComboBox1.getSelectedItem().toString());
            pst.setString(6, jSpinner1.getValue().toString());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Saved!");
            loadData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtId, txtName, txtInstructor;
    private javax.swing.JRadioButton rbMale, rbFemale;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton btnAdd, btnUpdate, btnDelete;
    private javax.swing.JLabel jLabelId, jLabelName, jLabelGender, jLabelInstructor;
}