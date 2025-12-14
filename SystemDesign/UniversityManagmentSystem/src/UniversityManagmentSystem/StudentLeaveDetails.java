package UniversityManagmentSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentLeaveDetails extends JFrame {

    private final JComboBox<String> comboEmpID;
    private final JTable table;
    private static final Logger logger = Logger.getLogger(StudentLeaveDetails.class.getName());

    public StudentLeaveDetails() {
        setTitle("Students Leave Details");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(172, 241, 178));
        setLayout(null);

        JLabel heading = new JLabel("Search by Student Roll");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        comboEmpID = new JComboBox<>();
        comboEmpID.setBounds(180, 20, 150, 20);
        add(comboEmpID);

        try (Conn c = new Conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT roll FROM student");
            while (rs.next()) {
                comboEmpID.addItem(rs.getString("roll"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading student rolls", e);
        }

        table = new JTable();
        loadAllStudentLeaveData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 900, 600);
        add(scrollPane);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 25);
        searchButton.addActionListener(e -> searchAction());
        add(searchButton);

        JButton printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 25);
        printButton.addActionListener(e -> printAction());
        add(printButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 70, 80, 25);
        cancelButton.addActionListener(e -> cancelAction());
        add(cancelButton);

        setVisible(true);
    }

    private void loadAllStudentLeaveData() {
        try (Conn c = new Conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT * FROM studentleave");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading student leave data", e);
        }
    }

    private void searchAction() {
        String selectedRoll = (String) comboEmpID.getSelectedItem();
        if (selectedRoll != null && !selectedRoll.isEmpty()) {
            String query = "SELECT * FROM studentleave WHERE roll = ?";
            try (Conn c = new Conn();
                 PreparedStatement ps = c.con.prepareStatement(query)) {
                ps.setString(1, selectedRoll);
                ResultSet rs = ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during search", e);
            }
        }
    }

    private void printAction() {
        try {
            if (!table.print()) {
                logger.warning("User cancelled printing.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error printing table", e);
        }
    }

    private void cancelAction() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentLeaveDetails::new);
    }
}
