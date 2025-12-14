package UniversityManagmentSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherLeaveDetails extends JFrame {

    private final JComboBox<String> comboEmpID;
    private final JTable table;
    private static final Logger logger = Logger.getLogger(TeacherLeaveDetails.class.getName());

    public TeacherLeaveDetails() {
        setTitle("Teacher Leave Details");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(172, 241, 178));
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee Id");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        comboEmpID = new JComboBox<>();
        comboEmpID.setBounds(180, 20, 150, 20);
        add(comboEmpID);

        try (Conn c = new Conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT empId FROM teacher");
            while (rs.next()) {
                comboEmpID.addItem(rs.getString("empId"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading employee IDs", e);
        }

        table = new JTable();
        loadAllTeacherLeaveData();

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

    private void loadAllTeacherLeaveData() {
        try (Conn c = new Conn()) {
            ResultSet rs = c.statement.executeQuery("SELECT * FROM teacherleave");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading teacher leave data", e);
        }
    }

    private void searchAction() {
        String selectedEmpId = (String) comboEmpID.getSelectedItem();
        if (selectedEmpId != null && !selectedEmpId.isEmpty()) {
            String query = "SELECT * FROM teacherleave WHERE empId = ?";
            try (Conn c = new Conn();
                 PreparedStatement ps = c.con.prepareStatement(query)) {
                ps.setString(1, selectedEmpId);
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
        SwingUtilities.invokeLater(TeacherLeaveDetails::new);
    }
}
