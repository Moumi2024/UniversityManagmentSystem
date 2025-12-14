package UniversityManagmentSystem;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import java.util.Random;

public class AddFaculty extends JFrame implements ActionListener {

    JTextField textName, textFather, textAddress, textPhone, textEmail, textM10, textM12, textAadhar;
    JLabel empText;
    JDateChooser fdob;
    JComboBox<String> courseBox, DepartmentBox;
    JButton submit, cancel;

    Random ran = new Random();
    long f4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddFaculty() {
        getContentPane().setBackground(new Color(22, 30, 74));

        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(330, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        heading.setForeground(Color.LIGHT_GRAY);
        add(heading);

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 100, 30);
        name.setFont(new Font("serif", Font.BOLD, 20));
        name.setForeground(Color.WHITE);
        add(name);

        textName = new JTextField();
        textName.setBounds(150, 150, 200, 30);
        add(textName);

        // Father Name
        JLabel FatherName = new JLabel("Father's Name");
        FatherName.setBounds(400, 150, 200, 30);
        FatherName.setFont(new Font("serif", Font.BOLD, 20));
        FatherName.setForeground(Color.WHITE);
        add(FatherName);

        textFather = new JTextField();
        textFather.setBounds(600, 150, 200, 30);
        add(textFather);

        // Employee ID
        JLabel empID = new JLabel("Employee ID");
        empID.setBounds(50, 200, 200, 30);
        empID.setFont(new Font("serif", Font.BOLD, 20));
        empID.setForeground(Color.WHITE);
        add(empID);

        empText = new JLabel("1111" + f4);
        empText.setBounds(200, 200, 200, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        empText.setForeground(Color.WHITE);
        add(empText);

        // Date of Birth
        JLabel DOB = new JLabel("Date of Birth");
        DOB.setBounds(400, 200, 200, 30);
        DOB.setFont(new Font("serif", Font.BOLD, 20));
        DOB.setForeground(Color.WHITE);
        add(DOB);

        fdob = new JDateChooser();
        fdob.setBounds(600, 200, 150, 30);
        add(fdob);

        // Address
        JLabel Address = new JLabel("Address");
        Address.setBounds(50, 250, 200, 30);
        Address.setFont(new Font("serif", Font.BOLD, 20));
        Address.setForeground(Color.WHITE);
        add(Address);

        textAddress = new JTextField();
        textAddress.setBounds(200, 250, 150, 30);
        add(textAddress);

        // Phone
        JLabel PhoneNo = new JLabel("Phone No.");
        PhoneNo.setBounds(400, 250, 200, 30);
        PhoneNo.setFont(new Font("serif", Font.BOLD, 20));
        PhoneNo.setForeground(Color.WHITE);
        add(PhoneNo);

        textPhone = new JTextField();
        textPhone.setBounds(600, 250, 150, 30);
        add(textPhone);

        // Email
        JLabel Email = new JLabel("Email");
        Email.setBounds(50, 300, 200, 30);
        Email.setFont(new Font("serif", Font.BOLD, 20));
        Email.setForeground(Color.WHITE);
        add(Email);

        textEmail = new JTextField();
        textEmail.setBounds(200, 300, 150, 30);
        add(textEmail);

        // Class X
        JLabel M10 = new JLabel("Class X (%)");
        M10.setBounds(400, 300, 200, 30);
        M10.setFont(new Font("serif", Font.BOLD, 20));
        M10.setForeground(Color.WHITE);
        add(M10);

        textM10 = new JTextField();
        textM10.setBounds(600, 300, 150, 30);
        add(textM10);

        // Class XII
        JLabel M12 = new JLabel("Class XII (%)");
        M12.setBounds(50, 350, 200, 30);
        M12.setFont(new Font("serif", Font.BOLD, 20));
        M12.setForeground(Color.WHITE);
        add(M12);

        textM12 = new JTextField();
        textM12.setBounds(200, 350, 150, 30);
        add(textM12);

        // Aadhar
        JLabel AadharNo = new JLabel("Aadhar Number");
        AadharNo.setBounds(400, 350, 200, 30);
        AadharNo.setFont(new Font("serif", Font.BOLD, 20));
        AadharNo.setForeground(Color.WHITE);
        add(AadharNo);

        textAadhar = new JTextField();
        textAadhar.setBounds(600, 350, 150, 30);
        add(textAadhar);

        // Qualification
        JLabel Qualification = new JLabel("Qualification");
        Qualification.setBounds(50, 400, 200, 30);
        Qualification.setFont(new Font("serif", Font.BOLD, 20));
        Qualification.setForeground(Color.WHITE);
        add(Qualification);

        String[] course = {"B.Tech", "BBA", "MBA", "BSC", "MSC", "MCA"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(200, 400, 150, 30);
        add(courseBox);

        // Department
        JLabel Department = new JLabel("Department");
        Department.setBounds(400, 400, 200, 30);
        Department.setFont(new Font("serif", Font.BOLD, 20));
        Department.setForeground(Color.WHITE);
        add(Department);

        String[] department = {"ECE", "CSE", "ME", "CE", "EE", "IT"};
        DepartmentBox = new JComboBox<>(department);
        DepartmentBox.setBounds(600, 400, 150, 30);
        add(DepartmentBox);

        // Submit
        submit = new JButton("Submit");
        submit.setBounds(280, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        // Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(550, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(990, 700);
        setLocation(350, 50);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = textName.getText();
            String fname = textFather.getText();
            String empID = empText.getText();
            Date dob = fdob.getDate();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textEmail.getText();
            String class_x = textM10.getText();
            String class_XII = textM12.getText();
            String aadhar = textAadhar.getText();
            String education = (String) courseBox.getSelectedItem();
            String department = (String) DepartmentBox.getSelectedItem();

            if (name.isEmpty() || empID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Employee ID are required.");
                return;
            }

            String insertSql = "INSERT INTO teacher " +
                    "(name, fname, empID, dob, address, phone, email, class_x, class_XII, aadhar, education, department) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = Conn.getConnection();
                 PreparedStatement pst = conn.prepareStatement(insertSql)) {

                pst.setString(1, name);
                pst.setString(2, fname);
                pst.setString(3, empID);

                if (dob != null) {
                    pst.setDate(4, new java.sql.Date(dob.getTime()));
                } else {
                    pst.setNull(4, Types.DATE);
                }

                pst.setString(5, address);
                pst.setString(6, phone);
                pst.setString(7, email);
                pst.setString(8, class_x);
                pst.setString(9, class_XII);
                pst.setString(10, aadhar);
                pst.setString(11, education);
                pst.setString(12, department);

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Details Inserted Successfully!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Insert failed.");
                }

            } catch (Exception ex) {
                System.err.println("[ERROR] Database error: " + ex);
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddFaculty();
    }
}
