package javaapplication7;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Student_reg extends javax.swing.JFrame {

    public Student_reg() {
        initComponents();
        showTableData(); // Loads data into the table on startup
    }

    // Helper method to refresh the JTable
    private void showTableData() {
        try {
            Connection conn = connectionDB.connect();
            String sql = "SELECT * FROM borrowing_records";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear table before loading

            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("instructor"),
                    rs.getString("equipment"),
                    rs.getString("unit")
                });
            }
        } catch (SQLException e) {
            System.out.println("Table Refresh Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        // ... (Keep your existing initComponents() code here) ...
        // Ensure variable names match: 
        // jTextField1 (ID), jTextField2 (Name), JInstrutorName (Instructor)
        // jRadioButton1 (Male), jRadioButton2 (Female), jComboBox1 (Equipment), jSpinner2 (Unit)
    } // </editor-fold>                        

    // --- BUTTON: ADD ---
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String id = jTextField1.getText();
        String name = jTextField2.getText();
        String gender = jRadioButton1.isSelected() ? "Male" : "Female";
        String instructor = JInstrutorName.getText();
        String equip = jComboBox1.getSelectedItem().toString();
        String unit = jSpinner2.getValue().toString();

        try {
            Connection conn = connectionDB.connect();
            String sql = "INSERT INTO borrowing_records (student_id, name, gender, instructor, equipment, unit) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, instructor);
            pst.setString(5, equip);
            pst.setString(6, unit);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Added!");
            showTableData();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Add Error: " + e.getMessage());
        }
    }                                        

    // --- BUTTON: UPDATE ---
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            Connection conn = connectionDB.connect();
            String sql = "UPDATE borrowing_records SET name=?, gender=?, instructor=?, equipment=?, unit=? WHERE student_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, jTextField2.getText());
            pst.setString(2, jRadioButton1.isSelected() ? "Male" : "Female");
            pst.setString(3, JInstrutorName.getText());
            pst.setString(4, jComboBox1.getSelectedItem().toString());
            pst.setString(5, jSpinner2.getValue().toString());
            pst.setString(6, jTextField1.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated!");
            showTableData();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Update Error: " + e.getMessage());
        }
    }                                        

    // --- BUTTON: DELETE ---
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = connectionDB.connect();
                String sql = "DELETE FROM borrowing_records WHERE student_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, jTextField1.getText());
                pst.executeUpdate();
                showTableData();
                JOptionPane.showMessageDialog(this, "Deleted!");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Delete Error: " + e.getMessage());
            }
        }
    }                                        

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Student_reg().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JTextField JInstrutorName;
    private javax.swing.JButton jButton1; // ADD
    private javax.swing.JButton jButton2; // DELETE
    private javax.swing.JButton jButton3; // UPDATE
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1; // ID
    private javax.swing.JTextField jTextField2; // Name
    private javax.swing.JSpinner jSpinner2; // Unit
    // ... other variables ...
}