package UniversityManagmentSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton search, print, update, add, cancel, showAll;
    DefaultTableModel model;

    TeacherDetails(){
        getContentPane().setBackground(new Color(210,252,218));

        JLabel heading = new JLabel("Search by Faculty ID");
        heading.setBounds(20,20,150,20);
        add(heading);

        choice = new Choice();
        choice.setBounds(180,20,150,20);
        add(choice);

        // Load faculty IDs using your Conn class
        loadFacultyIDs();

        // Initialize table
        model = new DefaultTableModel();
        table = new JTable(model);
        loadAllFaculty();

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0,100,900,600);
        add(js);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        showAll = new JButton("Show All");
        showAll.setBounds(110,70,80,20);
        showAll.addActionListener(this);
        add(showAll);

        print = new JButton("Print");
        print.setBounds(200,70,80,20);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(300,70,80,20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(390,70,80,20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(480,70,80,20);
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setSize(900,700);
        setLocation(300,100);
        setVisible(true);
    }

    private void loadFacultyIDs() {
        try (Connection connection = Conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select empID from teacher")) {

            choice.removeAll();
            while (resultSet.next()){
                choice.add(resultSet.getString("empID"));
            }
        }catch (SQLException e){
            System.err.println("Error loading faculty IDs: " + e.getMessage());
        }
    }

    private void loadAllFaculty() {
        try (Connection connection = Conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from teacher")) {

            populateTable(resultSet);
        } catch (SQLException e) {
            System.err.println("Error loading faculty: " + e.getMessage());
        }
    }

    private void populateTable(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Clear existing data
            model.setRowCount(0);
            model.setColumnCount(0);

            // Add column names
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            model.setColumnIdentifiers(columnNames);

            // Add rows
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.err.println("Error populating table: " + e.getMessage());
        }
    }

    private void performSearch() {
        String selectedFacultyID = choice.getSelectedItem();

        if (selectedFacultyID == null || selectedFacultyID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a faculty ID to search!",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String query = "select * from teacher where empID= ?";

        try (Connection connection = Conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, selectedFacultyID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this,
                        "No faculty found with ID: " + selectedFacultyID,
                        "Faculty Not Found",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            populateTable(resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error occurred while searching: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Error searching faculty: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            performSearch();
        } else if (e.getSource() == showAll) {
            loadAllFaculty();
        } else if (e.getSource() == print){
            try {
                table.print();
            }catch (Exception ex){
                System.err.println("Error printing: " + ex.getMessage());
            }
        } else if (e.getSource() == add) {
            setVisible(false);
            try {
                new AddFaculty();
            } catch (Exception ex) {
                setVisible(true);
                JOptionPane.showMessageDialog(this, "AddFacultyDetails class not found");
            }
        } else if (e.getSource() == update) {
            setVisible(false);
            try {
                new UpdateFacultyDetails();
            } catch (Exception ex) {
                setVisible(true);
                JOptionPane.showMessageDialog(this, "UpdateFacultyDetails class not found");
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
