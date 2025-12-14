package UniversityManagmentSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener{

    main_class() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.png"));
        Image i2 = i1.getImage().getScaledInstance(1530, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        // For menu bar
        JMenuBar mb = new JMenuBar();

        //----------------------------------(New information)----------------------------------
        JMenu newInfo = new JMenu("New Information");
        newInfo.setForeground(Color.BLACK);
        mb.add(newInfo);

        //For faculty information:
        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.WHITE);
        newInfo.add(facultyInfo);

        //Student information:
        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.WHITE);
        newInfo.add(studentInfo);

        //----------------------------------(Details)----------------------------------
        JMenu details = new JMenu("Vide Details");
        details.setForeground(Color.BLACK);
        mb.add(details);

        //For faculty
        JMenuItem facultyDetails = new JMenuItem("View Faculty Details");
        facultyDetails.setBackground(Color.WHITE);
        details.add(facultyDetails);

        //Student
        JMenuItem studentDetails = new JMenuItem("View Student Details");
        studentDetails.setBackground(Color.WHITE);
        details.add(studentDetails);

        //----------------------------------(Leave)----------------------------------
        JMenu leave = new JMenu("Apply For Leave");
        leave.setForeground(Color.BLACK);
        mb.add(leave);

        //For faculty
        JMenuItem facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.setBackground(Color.WHITE);
        leave.add(facultyLeave);

        //Student
        JMenuItem studentLeave = new JMenuItem("Student Leave");
        studentLeave.setBackground(Color.WHITE);
        leave.add(studentLeave);

        //----------------------------------(Leave Details)----------------------------------
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.BLACK);
        mb.add(leaveDetails);

        //For faculty
        JMenuItem facultyLeaveDetails = new JMenuItem("View Faculty Leave Details");
        facultyLeaveDetails.setBackground(Color.WHITE);
        leaveDetails.add(facultyLeaveDetails);

        //Student
        JMenuItem studentLeaveDetails = new JMenuItem("View Student Leave Deatils");
        studentLeaveDetails.setBackground(Color.WHITE);
        leaveDetails.add(studentLeaveDetails);

        //----------------------------------(Exam)----------------------------------------
        JMenu exam = new JMenu("Exam");
        exam.setForeground(Color.BLACK);
        mb.add(exam);

        JMenuItem ExaminationDetails = new JMenuItem("Examination Results");
        ExaminationDetails.setBackground(Color.WHITE);
        exam.add(ExaminationDetails);

        JMenuItem EnterExam = new JMenuItem("Enter Marks");
        EnterExam.setBackground(Color.WHITE);
        exam.add(EnterExam);

        //----------------------------------(UpdateInfo)----------------------------------------
        JMenu UpdateInfo = new JMenu("Update Details");
        UpdateInfo.setForeground(Color.BLACK);
        mb.add(UpdateInfo);

        //For faculty
        JMenuItem FacultyUpdateInfo = new JMenuItem("Update Faculty Details");
        FacultyUpdateInfo.setBackground(Color.WHITE);
        UpdateInfo.add(FacultyUpdateInfo);

        //Student
        JMenuItem StudentUpdateInfo = new JMenuItem("Update Student Details");
        StudentUpdateInfo.setBackground(Color.WHITE);
        UpdateInfo.add(StudentUpdateInfo);

        //----------------------------------(Fee)----------------------------------------
        JMenu Fee = new JMenu("Fee");
        Fee.setForeground(Color.BLACK);
        mb.add(Fee);

        JMenuItem FeesStructure = new JMenuItem("Fees Structure");
        FeesStructure.setBackground(Color.WHITE);
        Fee.add(FeesStructure);

        JMenuItem FeeForm = new JMenuItem("Students Fee Form");
        FeeForm.setBackground(Color.WHITE);
        Fee.add(FeeForm);

        //----------------------------------(Utility)----------------------------------------
        JMenu Utility = new JMenu("Utility");
        Utility.setForeground(Color.BLACK);
        mb.add(Utility);

        JMenuItem Calculator = new JMenuItem("Calculator");
        Calculator.setBackground(Color.WHITE);
        Calculator.addActionListener(this);
        Utility.add(Calculator);

        JMenuItem Notepad = new JMenuItem("Notepad");
        Notepad.setBackground(Color.WHITE);
        Notepad.addActionListener(this);
        Utility.add(Notepad);

        //----------------------------------(About)----------------------------------------
        JMenu About = new JMenu("Fee Details");
        About.setForeground(Color.BLACK);
        mb.add(About);

        JMenuItem about = new JMenuItem("About");
        about.setBackground(Color.WHITE);
        About.add(about);

        //----------------------------------(Exit)----------------------------------------
        JMenu Exit = new JMenu("Exit");
        Exit.setForeground(Color.BLACK);
        mb.add(Exit);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        Exit.add(exit);




        // attach menu bar to frame
        setJMenuBar(mb);

        setSize(1540, 850);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sm = e.getActionCommand().trim();

        if (sm.equalsIgnoreCase("Exit")) {

            dispose();       // *********** close the frame ******

            System.exit(0);
        } else if (sm.equalsIgnoreCase("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new main_class();
    }
}
