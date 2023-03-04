/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.metaLabs;

import com.metaLabs.JTextFieldLimit;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedDarkContrastIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.sqlite.SQLiteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class EnrollmentEncoding extends javax.swing.JFrame {

    String dbURL = "jdbc:sqlite:/C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\MetaLabs\\src\\com\\database\\metalabsDatabase.db";

    private ArrayList<JLabel> courseCodeLabels = new ArrayList<>();
    private ArrayList<JLabel> subjectLabels = new ArrayList<>();
    private ArrayList<JLabel> unitsLabels = new ArrayList<>();
    private ArrayList<JLabel> roomLabels = new ArrayList<>();
    private ArrayList<JLabel> timeLabels = new ArrayList<>();

    void Enroll_Student_Action() {
        String studentFirstName = SEFirstNameField.getText();
        String studentMiddleName = SEMiddleNameField.getText();
        String studentLastName = SELastNameField.getText();
        String studentBirthDate = SEBirthDateBox.getSelectedItem().toString();
        String studentBirthMonth = SEBirthMonthBox.getSelectedItem().toString();
        String studentBirthYear = SEBirthYearBox.getSelectedItem().toString();
        String studentAddress = SEAddressField.getText();
        String studentGender = SEGenderBox.getSelectedItem().toString();
        String studentCitizenship = SECitizenshipBox.getSelectedItem().toString();
        String studentMaritalStatus = SEMaritalStatusField.getSelectedItem().toString();
        String studentReligion = SEReligionBox.getSelectedItem().toString();
        String studentMotherName = SEMotherNameField.getText();
        String studentFatherName = SEFatherNameField.getText();

        int studentYearNum = Integer.parseInt(studentBirthYear);
        int studentAge = 2023 - studentYearNum;
        int id;

        if (studentFirstName.equals("") || studentMiddleName.equals("") || studentLastName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the student's name!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else if (studentBirthDate.equals(" ") || studentBirthMonth.equals(" ") || studentBirthYear.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Please enter the student's birth year details!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else if (studentAddress.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the student's details!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else if (studentGender.equals("") || studentCitizenship.equals("") || studentMaritalStatus.equals("") || studentReligion.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the student's details!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else if (studentMotherName.equals("") || studentFatherName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the student's parent details!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else if (SEContactNumberField.getText().equals("") || SEMotherContactField.getText().equals("") || SEFatherContactField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the contact details!", "Student Enrollment", JOptionPane.ERROR_MESSAGE);
        } else {
            long studentContactNumber = Long.parseLong(SEContactNumberField.getText());
            long studentMotherContact = Long.parseLong(SEMotherContactField.getText());
            long studentFatherContact = Long.parseLong(SEFatherContactField.getText());

            try {
                Connection connection = DriverManager.getConnection(dbURL);
                PreparedStatement PS = connection.prepareStatement("INSERT INTO `student_enrollment`(`studentFirstName`, `studentMiddleName`, "
                        + "`studentLastName`, `studentBirthDate`, `studentBirthMonth`, `studentBirthYear`, `studentAge`, `studentAddress`, "
                        + "`studentGender`, `studentCitizenship`, `studentContactNumber`, `studentMarital`, `studentReligion`, `studentMotherName`, "
                        + "`studentMotherContact`, `studentFatherName`, `studentFatherContact`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                PS.setString(1, studentFirstName);
                PS.setString(2, studentMiddleName);
                PS.setString(3, studentLastName);
                PS.setInt(4, Integer.parseInt(studentBirthDate));
                PS.setString(5, studentBirthMonth);
                PS.setInt(6, Integer.parseInt(studentBirthYear));
                PS.setInt(7, studentAge);
                PS.setString(8, studentAddress);
                PS.setString(9, studentGender);
                PS.setString(10, studentCitizenship);
                PS.setLong(11, studentContactNumber);
                PS.setString(12, studentMaritalStatus);
                PS.setString(13, studentReligion);
                PS.setString(14, studentMotherName);
                PS.setLong(15, studentMotherContact);
                PS.setString(16, studentFatherName);
                PS.setLong(17, studentFatherContact);

                PS.executeUpdate();

                JOptionPane.showMessageDialog(null, "Student Enrolled");
                Masterlist_Table();

                SEFirstNameField.setText("");
                SEMiddleNameField.setText("");
                SELastNameField.setText("");
                SEBirthDateBox.setSelectedIndex(0);
                SEBirthMonthBox.setSelectedIndex(0);
                SEBirthYearBox.setSelectedIndex(0);
                SEAddressField.setText("");
                SEGenderBox.setSelectedIndex(0);
                SECitizenshipBox.setSelectedIndex(0);
                SEContactNumberField.setText("");
                SEMaritalStatusField.setSelectedIndex(0);
                SEReligionBox.setSelectedIndex(0);
                SEMotherNameField.setText("");
                SEMotherContactField.setText("");
                SEFatherNameField.setText("");
                SEFatherContactField.setText("");

//                PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM `student_enrollment` WHERE `studentLastName` = ? AND `studentMiddleName`=? AND `studentFirstName`=?");
//                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO `student_course_enrolled` (`studentID`) VALUES (?)");
//
//                ps1.setString(1, studentLastName);
//                ps1.setString(2, studentMiddleName);
//                ps1.setString(3, studentFirstName);
//
//                ResultSet rs = ps1.executeQuery();
//
//                while (rs.next()) {
//                    id = rs.getInt(1);
//
//                    ps2.setInt(1, id);
//                    ps2.executeUpdate();
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void Enroll_Course_Action() {
        long studentId = Long.parseLong(CEStudentIDField.getText());
        String studentName = CEStudentNameField.getText();
        String studentCollegeDep = CECollegeDepBox.getSelectedItem().toString();
        String studentCourseMajor = CECourseMajorBox.getSelectedItem().toString();
        String studentYearLevel = CEYearLevelBox.getSelectedItem().toString();
        String studentSemester = CESemesterBox.getSelectedItem().toString();
        String studentTerm = CETermBox.getSelectedItem().toString();

        if (studentCollegeDep.contains("CAE")) {
            studentCollegeDep = "CAE";
        } else if (studentCollegeDep.contains("CAFAE")) {
            studentCollegeDep = "CAFAE";
        } else if (studentCollegeDep.contains("CCE")) {
            studentCollegeDep = "CCE";
        } else if (studentCollegeDep.contains("CCJE")) {
            studentCollegeDep = "CCJE";
        } else if (studentCollegeDep.contains("CEE")) {
            studentCollegeDep = "CEE";
        } else if (studentCollegeDep.contains("CHE")) {
            studentCollegeDep = "CHE";
        } else if (studentCollegeDep.contains("CHSE")) {
            studentCollegeDep = "CHSE";
        } else if (studentCollegeDep.contains("TS")) {
            studentCollegeDep = "TS";
        } else {
            studentCollegeDep = null;
        }

        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("UPDATE student_enrollment SET studentCollegeDep = ?, studentCourseMajor = ?, studentYearLevel = ?, studentSemester = ?, studentTerm = ?  WHERE studentID = ?");

            ps.setString(1, studentCollegeDep);
            ps.setString(2, studentCourseMajor);
            ps.setString(3, studentYearLevel);
            ps.setString(4, studentSemester);
            ps.setString(5, studentTerm);

            ps.setLong(6, studentId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Course Enrolled");
            Masterlist_Table();

            CEStudentIDField.setText("");
            CEStudentNameField.setText("");
            CECollegeDepBox.setSelectedIndex(0);
//            CECourseMajorBox.setSelectedIndex(0);

            CEYearLevelBox.setSelectedIndex(0);
            CESemesterBox.setSelectedIndex(0);
            CETermBox.setSelectedIndex(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void Add_Courses_Action() {
        int studentId = Integer.parseInt(CEStudentIDField.getText());
        int id;

        String courseSubjectCode = CESubjectCodeField.getText();
        String courseSubject = CESubjectBox.getSelectedItem().toString();
        double courseUnits = Double.parseDouble(CEUnitsField.getText());
        String courseTime = CETimeDateBox.getSelectedItem().toString();
        String courseRoom = CERoomBox.getSelectedItem().toString();

        try {
            Connection connection = DriverManager.getConnection(dbURL);
//            PreparedStatement ps = connection.prepareStatement("UPDATE student_course_enrolled SET courseCode = ?, courseTitle = ?, courseUnits = ?, courseTime = ?, courseRoom = ? WHERE studentID = ?");

            PreparedStatement ps = connection.prepareStatement("INSERT INTO student_course_enrolled(studentID, courseCode, courseTitle, courseUnits, courseTime, courseRoom) VALUES (?,?,?,?,?,?)");
//            ps.setString(1, courseSubjectCode);
//            ps.setString(2, courseSubject);
//            ps.setDouble(3, courseUnits);
//            ps.setString(4, courseTime);
//            ps.setString(5, courseRoom);
//            
//            ps.setInt(6, studentId);
            ps.setInt(1, studentId);
            ps.setString(2, courseSubjectCode);
            ps.setString(3, courseSubject);
            ps.setDouble(4, courseUnits);
            ps.setString(5, courseTime);
            ps.setString(6, courseRoom);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Course Added!");
            Update_Courses_Table();

            CESubjectBox.setSelectedIndex(0);
            CESubjectCodeField.setText("");
            CEUnitsField.setText("");
            CETimeDateBox.setSelectedIndex(0);
            CERoomBox.setSelectedIndex(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            Connection connection = DriverManager.getConnection(dbURL);
//            PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM student_course_enrolled WHERE studentID = ?");
//            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO student_course_enrolled (studentID) VALUES(?)");
//            
//            ps1.setInt(1, studentId);
//            ResultSet rs = ps1.executeQuery();
//            
//            if (rs.next()) {
//                id = rs.getInt(1);
//                
//                ps2.setInt(1, id);
//                ps2.executeUpdate();
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    void Update_Courses_Action() {
        DefaultTableModel tablemodel = (DefaultTableModel) CECourseListTable.getModel();
        int selectedIndex = CECourseListTable.getSelectedRow();

//        String courseCode = CESubjectCodeField.getText();
        String courseCode = tablemodel.getValueAt(selectedIndex, 0).toString();
        String studentId = CEStudentIDField.getText();

        String courseSubject = CESubjectBox.getSelectedItem().toString();
        String units = CEUnitsField.getText();
        String time = CETimeDateBox.getSelectedItem().toString();
        String room = CERoomBox.getSelectedItem().toString();

        try {
            Connection connection = DriverManager.getConnection(dbURL);
//            PreparedStatement ps = connection.prepareStatement("UPDATE `course_enrollment` SET `courseTitle`=?, `courseUnits`=?, `courseTime`=?, `courseRoom`=? WHERE `courseCode` = ?");
            PreparedStatement ps = connection.prepareStatement("UPDATE student_course_enrolled SET courseTitle = ?, courseTime = ?, courseRoom = ? WHERE studentID = ? AND courseCode = ?");

//            ps.setString(1, courseSubject);
//            ps.setString(2, units);
//            ps.setString(3, time);
//            ps.setString(4, room);
//
//            ps.setString(5, courseCode);
            ps.setString(1, courseSubject);
            ps.setString(2, time);
            ps.setString(3, room);

            ps.setInt(4, Integer.parseInt(studentId));
            ps.setString(5, courseCode);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Subject Entry Updated");
            Update_Courses_Table();

            CESubjectBox.setSelectedIndex(0);
            CESubjectCodeField.setText("");
            CEUnitsField.setText("");
            CETimeDateBox.setSelectedIndex(0);
            CERoomBox.setSelectedIndex(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void Delete_Courses_Action() {
        DefaultTableModel model = (DefaultTableModel) CECourseListTable.getModel();
        int selectedIndex = CECourseListTable.getSelectedRow();

        String studentId = CEStudentIDField.getText();
        try {
            String courseCode = model.getValueAt(selectedIndex, 0).toString();

            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete this subject entry?", "Course Enrollment", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                Connection connection = DriverManager.getConnection(dbURL);
//                PreparedStatement ps = connection.prepareStatement("DELETE FROM `course_enrollment` WHERE `courseCode` = ?");
                PreparedStatement ps = connection.prepareStatement("DELETE FROM student_course_enrolled WHERE studentID = ? AND courseCode = ?");

                ps.setInt(1, Integer.parseInt(studentId));
                ps.setString(2, courseCode);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Subject Entry Deleted");
                Update_Courses_Table();

                CESubjectBox.setSelectedIndex(0);
                CESubjectCodeField.setText("");
                CEUnitsField.setText("");
                CETimeDateBox.setSelectedIndex(0);
                CERoomBox.setSelectedIndex(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void Update_Courses_Table() {
        String studentID = CEStudentIDField.getText();

        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM student_course_enrolled WHERE studentID = ?");
//            PreparedStatement ps = connection.prepareStatement("SELECT c,courseCode, c.courseTitle, c.courseUnits, c.courseTime, c.courseRoom FROM student_course_enrolled c JOIN student_enrollment s ON s.studentID = c.studentID");
            ps.setString(1, studentID);

            ResultSet rs = ps.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) CECourseListTable.getModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("courseCode"));
                vector.add(rs.getString("courseTitle"));
                vector.add(rs.getString("courseUnits"));
                vector.add(rs.getString("courseTime"));
                vector.add(rs.getString("courseRoom"));

                tableModel.addRow(vector);
                tableModel.fireTableDataChanged();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void Masterlist_Table() {
        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `student_enrollment`");

            ResultSet rs = ps.executeQuery();

//            ResultSetMetaData rsmd = rs.getMetaData();
//            columnCount = rsmd.getColumnCount();
            DefaultTableModel tableModel = (DefaultTableModel) MLMasterlistTable.getModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getInt("studentID"));
                vector.add(rs.getString("studentLastName"));
                vector.add(rs.getString("studentFirstName"));
                vector.add(rs.getString("studentMiddleName"));
                vector.add(rs.getString("studentYearLevel"));
                vector.add(rs.getString("studentCollegeDep"));
                vector.add(rs.getString("studentCourseMajor"));

                tableModel.addRow(vector);
                tableModel.fireTableDataChanged();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void Masterlist_Table_Search() {
        String searchTerm = MLSearchField.getText();
        DefaultTableModel model = (DefaultTableModel) MLMasterlistTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        MLMasterlistTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
    }

    void Student_Details_Info_Action() {
//        long studentID = Long.parseLong(SDStudentIDField.getText());
        String studentID = SDStudentIDField.getText();

        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `student_enrollment` WHERE `studentID` = ?");
            ps.setString(1, studentID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SDFirstNameField.setText(rs.getString(2));
                SDLastNameField.setText(rs.getString(4));
                SDMiddleNameField.setText(rs.getString(3));
                SDAgeField.setText(Integer.toString(rs.getInt(8)));
                SDAddressField.setText(rs.getString(9));
                SDStudentContactField.setText(Long.toString(rs.getLong(12)));
                SDGenderField.setText(rs.getString(10));
                SDMaritalStatusField.setText(rs.getString(13));
                SDCitizenshipField.setText(rs.getString(11));
                SDReligionField.setText(rs.getString(14));

                SDMotherNameField.setText(rs.getString(15));
                SDMotherContactField.setText(Long.toString(rs.getLong(16)));
                SDFatherNameField.setText(rs.getString(17));
                SDFatherContactField.setText(Long.toString(rs.getLong(18)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void Student_Details_Update_Action() {
//        String studentID = SDStudentIDField.getText();
        int studentID = Integer.parseInt(SDStudentIDField.getText());

        String studentLastName = SDLastNameField.getText();
        String studentFirstName = SDFirstNameField.getText();
        String studentMiddleName = SDMiddleNameField.getText();
//        String studentAge = SDAgeField.getText();
        int studentAge = Integer.parseInt(SDAgeField.getText());
        String studentAddress = SDAddressField.getText();
        long studentContact = Long.parseLong(SDStudentContactField.getText());
        String studentGender = SDGenderField.getText();
        String studentMarital = SDMaritalStatusField.getText();
        String studentCitizenship = SDCitizenshipField.getText();
        String studentReligion = SDReligionField.getText();

        String motherName = SDMotherNameField.getText();
        long motherContact = Long.parseLong(SDMotherContactField.getText());
        String fatherName = SDFatherNameField.getText();
        long fatherContact = Long.parseLong(SDFatherContactField.getText());

        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("UPDATE `student_enrollment` SET `studentFirstName`=?, `studentMiddleName`=?, `studentLastName`=?, `studentAge`=?, `studentAddress`=?, `studentContactNumber`=?, `studentGender`=?, `studentMarital`=?, `studentCitizenship`=?, `studentReligion`=?, `studentMotherName`=?, `studentMotherContact`=?, `studentFatherName`=?, `studentFatherContact`=? WHERE `studentID` = ?");

            ps.setString(1, studentFirstName);
            ps.setString(2, studentMiddleName);
            ps.setString(3, studentLastName);
            ps.setInt(4, studentAge);
            ps.setString(5, studentAddress);
            ps.setLong(6, studentContact);
            ps.setString(7, studentGender);
            ps.setString(8, studentMarital);
            ps.setString(9, studentCitizenship);
            ps.setString(10, studentReligion);
            ps.setString(11, motherName);
            ps.setLong(12, motherContact);
            ps.setString(13, fatherName);
            ps.setLong(14, fatherContact);
            ps.setInt(15, studentID);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Details Updated");
            Masterlist_Table();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void Student_Details_Delete_Action() {
        int studentID = Integer.parseInt(SDStudentIDField.getText());

        try {
            int dialogRes = JOptionPane.showConfirmDialog(null, "Do you want to un-enroll this student?", "Student Details", JOptionPane.YES_NO_OPTION);

            if (dialogRes == JOptionPane.YES_OPTION) {
                Connection connection = DriverManager.getConnection(dbURL);
                PreparedStatement ps = connection.prepareStatement("DELETE FROM `student_enrollment` WHERE `studentID` = ?");

                ps.setInt(1, studentID);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student Dropped");
                Masterlist_Table();

                SDStudentIDField.setText("");

                SDFirstNameField.setText("");
                SDMiddleNameField.setText("");
                SDLastNameField.setText("");
                SDAgeField.setText("");
                SDAddressField.setText("");
                SDStudentContactField.setText("");
                SDGenderField.setText("");
                SDMaritalStatusField.setText("");
                SDCitizenshipField.setText("");
                SDReligionField.setText("");

                SDMotherNameField.setText("");
                SDMotherContactField.setText("");
                SDFatherNameField.setText("");
                SDFatherContactField.setText("");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void Print_Form_Action() {
        String studentId = CEStudentIDField.getText();

        courseCodeLabels.add(PFCourseCodeLabel1);
        subjectLabels.add(PFSubjectLabel1);
        unitsLabels.add(PFUnitsLabel1);
        roomLabels.add(PFRoomLabel1);
        timeLabels.add(PFTimeLabel1);

        courseCodeLabels.add(PFCourseCodeLabel2);
        subjectLabels.add(PFSubjectLabel2);
        unitsLabels.add(PFUnitsLabel2);
        roomLabels.add(PFRoomLabel2);
        timeLabels.add(PFTimeLabel2);

        courseCodeLabels.add(PFCourseCodeLabel3);
        subjectLabels.add(PFSubjectLabel3);
        unitsLabels.add(PFUnitsLabel3);
        roomLabels.add(PFRoomLabel3);
        timeLabels.add(PFTimeLabel3);

        courseCodeLabels.add(PFCourseCodeLabel4);
        subjectLabels.add(PFSubjectLabel4);
        unitsLabels.add(PFUnitsLabel4);
        roomLabels.add(PFRoomLabel4);
        timeLabels.add(PFTimeLabel4);

        courseCodeLabels.add(PFCourseCodeLabel5);
        subjectLabels.add(PFSubjectLabel5);
        unitsLabels.add(PFUnitsLabel5);
        roomLabels.add(PFRoomLabel5);
        timeLabels.add(PFTimeLabel5);

        courseCodeLabels.add(PFCourseCodeLabel6);
        subjectLabels.add(PFSubjectLabel6);
        unitsLabels.add(PFUnitsLabel6);
        roomLabels.add(PFRoomLabel6);
        timeLabels.add(PFTimeLabel6);

        courseCodeLabels.add(PFCourseCodeLabel7);
        subjectLabels.add(PFSubjectLabel7);
        unitsLabels.add(PFUnitsLabel7);
        roomLabels.add(PFRoomLabel7);
        timeLabels.add(PFTimeLabel7);

        courseCodeLabels.add(PFCourseCodeLabel8);
        subjectLabels.add(PFSubjectLabel8);
        unitsLabels.add(PFUnitsLabel8);
        roomLabels.add(PFRoomLabel8);
        timeLabels.add(PFTimeLabel8);

        try {
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM student_enrollment WHERE studentID = ?");
            PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM student_course_enrolled WHERE studentID = ?");
            ps.setString(1, studentId);
            ps1.setString(1, studentId);

            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();

            if (rs.next()) {
                PFStudentIDLabel.setText(rs.getString("studentID")); // or int
                PFStudentNameLabel.setText(rs.getString("studentFirstName") + " " + rs.getString("studentMiddleName") + " " + rs.getString("studentLastName"));
                PFCollegeDepLabel.setText(rs.getString("studentCollegeDep"));
                PFCourseMajorLabel.setText(rs.getString("studentCourseMajor"));
                PFYearLevelLabel.setText(rs.getString("studentYearLevel"));
                PFSemesterLabel.setText(rs.getString("studentSemester"));
                PFTermLabel.setText(rs.getString("studentTerm"));
            }

            int i = 0;
            
            while (rs1.next() && i < courseCodeLabels.size()) {
                courseCodeLabels.get(i).setText(rs1.getString("courseCode"));
                subjectLabels.get(i).setText(rs1.getString("courseTitle"));
                unitsLabels.get(i).setText(rs1.getString("courseUnits"));
                roomLabels.get(i).setText(rs1.getString("courseRoom"));
                timeLabels.get(i).setText(rs1.getString("courseTime"));

                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    void Back_Button_Action() {
        courseCodeLabels.clear();
        subjectLabels.clear();
        unitsLabels.clear();
        roomLabels.clear();
        timeLabels.clear();
        
        PFCourseCodeLabel1.setText("");
        PFCourseCodeLabel2.setText("");
        PFCourseCodeLabel3.setText("");
        PFCourseCodeLabel4.setText("");
        PFCourseCodeLabel5.setText("");
        PFCourseCodeLabel6.setText("");
        PFCourseCodeLabel7.setText("");
        PFCourseCodeLabel8.setText("");
        
        PFSubjectLabel1.setText("");
        PFSubjectLabel2.setText("");
        PFSubjectLabel3.setText("");
        PFSubjectLabel4.setText("");
        PFSubjectLabel5.setText("");
        PFSubjectLabel6.setText("");
        PFSubjectLabel7.setText("");
        PFSubjectLabel8.setText("");
        
        PFUnitsLabel1.setText("");
        PFUnitsLabel2.setText("");
        PFUnitsLabel3.setText("");
        PFUnitsLabel4.setText("");
        PFUnitsLabel5.setText("");
        PFUnitsLabel6.setText("");
        PFUnitsLabel7.setText("");
        PFUnitsLabel8.setText("");
        
        PFRoomLabel1.setText("");
        PFRoomLabel2.setText("");
        PFRoomLabel3.setText("");
        PFRoomLabel4.setText("");
        PFRoomLabel5.setText("");
        PFRoomLabel6.setText("");
        PFRoomLabel7.setText("");
        PFRoomLabel8.setText("");
        
        PFTimeLabel1.setText("");
        PFTimeLabel2.setText("");
        PFTimeLabel3.setText("");
        PFTimeLabel4.setText("");
        PFTimeLabel5.setText("");
        PFTimeLabel6.setText("");
        PFTimeLabel7.setText("");
        PFTimeLabel8.setText("");

    }

    void Print_Form_Save(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Print Record");

        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                graphics2D.scale(0.6, 0.6);

                panel.paint(graphics2D);

                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningResult = printerJob.printDialog();

        if (returningResult) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage());
            }
        }
    }

    void Date_Today() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        CEDatePrintedLabel.setText(date);
    }

    void OnClick_Table_Action() {
        DefaultTableModel tableModel = (DefaultTableModel) CECourseListTable.getModel();

        int selectedIndex = CECourseListTable.getSelectedRow();

        CESubjectCodeField.setText(tableModel.getValueAt(selectedIndex, 0).toString());
        CESubjectBox.setSelectedItem(tableModel.getValueAt(selectedIndex, 1).toString());
        CEUnitsField.setText(tableModel.getValueAt(selectedIndex, 2).toString());
        CETimeDateBox.setSelectedItem(tableModel.getValueAt(selectedIndex, 3).toString());
        CERoomBox.setSelectedItem(tableModel.getValueAt(selectedIndex, 4).toString());
    }

    void Change_Major_Courses() {
        if (CECollegeDepBox.getSelectedItem().equals("CAE - College of Accounting Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Accountancy");
            CECourseMajorBox.addItem("Bachelor of Science in Internal Auditing");
            CECourseMajorBox.addItem("Bachelor of Science in Accounting Information System");
            CECourseMajorBox.addItem("Bachelor of Science in Management Accounting");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CAFAE - College of Architecture and Fine Arts Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Architecture");
            CECourseMajorBox.addItem("Bachelor of Fine Arts and Design");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CCE - College of Computing Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Information Technology");
            CECourseMajorBox.addItem("Bachelor of Science in Computer Science");
            CECourseMajorBox.addItem("Bachelor of Science in Information Systems");
            CECourseMajorBox.addItem("Bachelor of Library and Information Science");
            CECourseMajorBox.addItem("Bachelor of Science in Entertainment and Multimedia Computing – Digital Animation");
            CECourseMajorBox.addItem("Bachelor of Science in Entertainment and Multimedia Computing – Game Development");
            CECourseMajorBox.addItem("Bachelor of Arts in Multimedia Arts");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CCJE - College of Criminal Justice Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Criminology");
            CECourseMajorBox.addItem("Bachelor of Science in Industrial Security Management");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CEE - College of Engineering Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Chemical Engineering");
            CECourseMajorBox.addItem("Bachelor of Science in Mechanical Engineering");
            CECourseMajorBox.addItem("Bachelor of Science in Electrical Engineering");
            CECourseMajorBox.addItem("Bachelor of Science in Electronics Engineering");
            CECourseMajorBox.addItem("Bachelor of Science in Computer Engineering");
            CECourseMajorBox.addItem("Bachelor of Science in Civil Engineering Major in Structural");
            CECourseMajorBox.addItem("Bachelor of Science in Civil Engineering Major in Water Resource");
            CECourseMajorBox.addItem("Bachelor of Science in Civil Engineering Major in Transportation");
            CECourseMajorBox.addItem("Bachelor of Science in Civil Engineering Major in Geotechnical");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CHE - College of Hospitality Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Hospitality Management");
            CECourseMajorBox.addItem("Bachelor of Science in Tourism Management");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("CHSE - College of Health Science Education")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Bachelor of Science in Nursing");
            CECourseMajorBox.addItem("Bachelor of Science in Pharmacy");
            CECourseMajorBox.addItem("Bachelor of Science in Medical Technology/Medical Laboratory Science");
            CECourseMajorBox.addItem("Bachelor of Science in Nutrition and Dietetics");
            CECourseMajorBox.setSelectedItem(null);
        } else if (CECollegeDepBox.getSelectedItem().equals("TS - Technical School")) {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.addItem("Automotive Servicing");
            CECourseMajorBox.addItem("Electronic Product Assembly and Servicing");
            CECourseMajorBox.addItem("Electrical Installation Maintenance");
            CECourseMajorBox.addItem("Caregiving");
            CECourseMajorBox.setSelectedItem(null);
        } else {
            CECourseMajorBox.removeAllItems();
            CECourseMajorBox.setSelectedItem(null);
        }
    }

    void Course_Set_Text_Action() {
        if (CESubjectBox.getSelectedItem().equals("Understanding the Self")) {
            CESubjectCodeField.setText("GE 1");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Readings in Philippine History")) {
            CESubjectCodeField.setText("GE 2");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("The Contemporary World")) {
            CESubjectCodeField.setText("GE 3");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Mathematics in the Modern World")) {
            CESubjectCodeField.setText("GE 4");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Purposive Communication")) {
            CESubjectCodeField.setText("GE 5");
            CEUnitsField.setText("6");
        } else if (CESubjectBox.getSelectedItem().equals("Art Appreciation")) {
            CESubjectCodeField.setText("GE 6");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Science, Technology and Society")) {
            CESubjectCodeField.setText("GE 7");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Ethics")) {
            CESubjectCodeField.setText("GE 8");
            CEUnitsField.setText("2");
        } else if (CESubjectBox.getSelectedItem().equals("Life and Works of Rizal")) {
            CESubjectCodeField.setText("GE 9");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("G.E. Electives")) {
            CESubjectCodeField.setText("GE 10");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Introduction to Language Studies")) {
            CESubjectCodeField.setText("GE 11");
            CEUnitsField.setText("3");
        } else if (CESubjectBox.getSelectedItem().equals("Cross Cultural Communication")) {
            CESubjectCodeField.setText("GE 12");
            CEUnitsField.setText("6");
        } else {
            CESubjectCodeField.setText("");
            CEUnitsField.setText("");
        }
    }

    void SidePanel_SetColor(JPanel bg, JLabel fg) {
        bg.setBackground(new Color(201, 251, 252));
        fg.setForeground(new Color(0, 0, 0));
    }

    void SidePanel_ResetColor(JPanel bg, JLabel fg) {
        bg.setBackground(new Color(33, 67, 83));
        fg.setForeground(new Color(255, 255, 255));
    }

    void ChangeCard(Component Card) {
        mainPanel.removeAll();
        mainPanel.add(Card);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * Creates new form EnrollmentEncoding
     */
    public EnrollmentEncoding() {
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel.removeAll();
        mainPanel.add(studentEnrollmentPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        Masterlist_Table();
        Update_Courses_Table();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        sidePanel = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        enrollmentNewButton = new javax.swing.JPanel();
        enrollmentNewButtonLabel = new javax.swing.JLabel();
        masterlistNewButton = new javax.swing.JPanel();
        masterlistNewButtonLabel = new javax.swing.JLabel();
        courseNewButton = new javax.swing.JPanel();
        courseNewButtonLabel = new javax.swing.JLabel();
        studentDetailsNewButton = new javax.swing.JPanel();
        studentDetailsNewButtonLabel = new javax.swing.JLabel();
        adminOnlyButton = new javax.swing.JPanel();
        adminOnlyButtonLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        printFormPanel = new javax.swing.JPanel();
        PFPanelToPrint = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        PFStudentNameLabel = new javax.swing.JLabel();
        PFCollegeDepLabel = new javax.swing.JLabel();
        PFCourseMajorLabel = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        PFYearLevelLabel = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        PFSemesterLabel = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        PFTermLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PFStudentIDLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        PFCourseCodeLabel1 = new javax.swing.JLabel();
        PFSubjectLabel1 = new javax.swing.JLabel();
        PFTimeLabel1 = new javax.swing.JLabel();
        PFCourseCodeLabel2 = new javax.swing.JLabel();
        PFSubjectLabel2 = new javax.swing.JLabel();
        PFTimeLabel2 = new javax.swing.JLabel();
        PFCourseCodeLabel3 = new javax.swing.JLabel();
        PFSubjectLabel3 = new javax.swing.JLabel();
        PFTimeLabel3 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        PFRoomLabel1 = new javax.swing.JLabel();
        PFRoomLabel2 = new javax.swing.JLabel();
        PFRoomLabel3 = new javax.swing.JLabel();
        PFCourseCodeLabel4 = new javax.swing.JLabel();
        PFSubjectLabel4 = new javax.swing.JLabel();
        PFRoomLabel4 = new javax.swing.JLabel();
        PFTimeLabel4 = new javax.swing.JLabel();
        PFCourseCodeLabel5 = new javax.swing.JLabel();
        PFSubjectLabel5 = new javax.swing.JLabel();
        PFRoomLabel5 = new javax.swing.JLabel();
        PFTimeLabel5 = new javax.swing.JLabel();
        PFCourseCodeLabel6 = new javax.swing.JLabel();
        PFCourseCodeLabel7 = new javax.swing.JLabel();
        PFCourseCodeLabel8 = new javax.swing.JLabel();
        PFSubjectLabel6 = new javax.swing.JLabel();
        PFSubjectLabel7 = new javax.swing.JLabel();
        PFSubjectLabel8 = new javax.swing.JLabel();
        PFRoomLabel6 = new javax.swing.JLabel();
        PFRoomLabel7 = new javax.swing.JLabel();
        PFRoomLabel8 = new javax.swing.JLabel();
        PFTimeLabel6 = new javax.swing.JLabel();
        PFTimeLabel7 = new javax.swing.JLabel();
        PFTimeLabel8 = new javax.swing.JLabel();
        PFUnitsLabel8 = new javax.swing.JLabel();
        PFUnitsLabel7 = new javax.swing.JLabel();
        PFUnitsLabel6 = new javax.swing.JLabel();
        PFUnitsLabel5 = new javax.swing.JLabel();
        PFUnitsLabel4 = new javax.swing.JLabel();
        PFUnitsLabel3 = new javax.swing.JLabel();
        PFUnitsLabel2 = new javax.swing.JLabel();
        PFUnitsLabel1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        CEDatePrintedLabel = new javax.swing.JLabel();
        PFPrintPanelButton = new javax.swing.JButton();
        PFBackButton = new javax.swing.JButton();
        courseEnrollmentPanel = new javax.swing.JPanel();
        jPanel13 = new RoundedPanel(50, new Color(55,111,138));
        jLabel101 = new javax.swing.JLabel();
        CECollegeDepBox = new javax.swing.JComboBox<>();
        CECourseMajorBox = new javax.swing.JComboBox<>();
        jLabel108 = new javax.swing.JLabel();
        CEYearLevelBox = new javax.swing.JComboBox<>();
        jLabel109 = new javax.swing.JLabel();
        CESemesterBox = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        CETermBox = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        CEEnrollButton = new javax.swing.JButton();
        CEPrintPreviewButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        CECourseListTable = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchPanel2 = new RoundedPanel(30, new Color(55,111,138));
        jLabel88 = new javax.swing.JLabel();
        CEStudentNameField = new javax.swing.JTextField();
        CEStudentIDField = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        CESearchButton = new javax.swing.JButton();
        jPanel14 = new RoundedPanel(50, new Color(55,111,138));
        jLabel112 = new javax.swing.JLabel();
        CESubjectBox = new javax.swing.JComboBox<>();
        jLabel113 = new javax.swing.JLabel();
        CESubjectCodeField = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        CEUnitsField = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        CETimeDateBox = new javax.swing.JComboBox<>();
        jLabel117 = new javax.swing.JLabel();
        CERoomBox = new javax.swing.JComboBox<>();
        CEAddButton = new javax.swing.JButton();
        CECourseButton = new javax.swing.JButton();
        CEDeleteButton = new javax.swing.JButton();
        studentEnrollmentPanel = new javax.swing.JPanel();
        bottomPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel15 = new RoundedPanel(50, new Color(55,111,138));
        SEFirstNameField = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        SEMiddleNameField = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        SELastNameField = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        SEBirthMonthBox = new javax.swing.JComboBox<>();
        SEBirthDateBox = new javax.swing.JComboBox<>();
        SEBirthYearBox = new javax.swing.JComboBox<>();
        jLabel130 = new javax.swing.JLabel();
        SEGenderBox = new javax.swing.JComboBox<>();
        jLabel131 = new javax.swing.JLabel();
        SEMaritalStatusField = new javax.swing.JComboBox<>();
        jLabel132 = new javax.swing.JLabel();
        SEAddressField = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        SEContactNumberField = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        SECitizenshipBox = new javax.swing.JComboBox<>();
        jLabel135 = new javax.swing.JLabel();
        SEReligionBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        SEEnrollButton = new javax.swing.JButton();
        jPanel3 = new RoundedPanel(50, new Color(55,111,138));
        SEMotherNameField = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        SEFatherNameField = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        SEMotherContactField = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        SEFatherContactField = new javax.swing.JTextField();
        masterlistPanel = new javax.swing.JPanel();
        bottomPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MLMasterlistTable = new javax.swing.JTable();
        MLSearchField = new javax.swing.JTextField();
        MLSearchButton = new javax.swing.JButton();
        studentDetailsPanel = new javax.swing.JPanel();
        bottomPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        searchPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel16 = new javax.swing.JLabel();
        SDStudentIDField = new javax.swing.JTextField();
        SDStudentYearLevelField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        SDSearchButton = new javax.swing.JButton();
        studentDetailsTabbedPane = new javax.swing.JTabbedPane();
        personalInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        SDLastNameField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        SDFirstNameField = new javax.swing.JTextField();
        SDMiddleNameField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        SDAgeField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        SDAddressField = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        SDGenderField = new javax.swing.JTextField();
        SDMaritalStatusField = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        SDCitizenshipField = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        SDReligionField = new javax.swing.JTextField();
        SDStudentContactField = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        parentsInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel75 = new javax.swing.JLabel();
        SDMotherNameField = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        SDMotherContactField = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        SDFatherNameField = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        SDFatherContactField = new javax.swing.JTextField();
        addtionalInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel79 = new javax.swing.JLabel();
        SDRemarksField = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        registerEmployeePanel = new javax.swing.JPanel();
        bottomPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        searchPanel3 = new RoundedPanel(50, new Color(55,111,138));
        jLabel84 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        searchPanel4 = new RoundedPanel(50, new Color(55,111,138));
        jLabel90 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/com/images/metaLabs_logo_cyan-2.png")).getImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topPanel.setBackground(new java.awt.Color(18, 36, 45));
        topPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 240, 243));
        jLabel1.setText("LABS");
        topPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, 44));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("meta");
        topPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 44));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/metaLabs_logo_2_small-2.png"))); // NOI18N
        topPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        logoutButton.setBackground(new java.awt.Color(255, 102, 102));
        logoutButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("LOGOUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        topPanel.add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 20, 130, -1));

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 60));

        sidePanel.setBackground(new java.awt.Color(33, 67, 83));
        sidePanel.setPreferredSize(new java.awt.Dimension(118, 646));
        sidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel81.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("MetaLabs");
        sidePanel.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 793, 122, 16));

        jLabel107.setBackground(new java.awt.Color(255, 255, 255));
        jLabel107.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(204, 204, 204));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setText("Student Enrollment Manager");
        sidePanel.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 815, 177, 16));

        enrollmentNewButton.setBackground(new java.awt.Color(33, 67, 83));
        enrollmentNewButton.setPreferredSize(new java.awt.Dimension(114, 63));

        enrollmentNewButtonLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        enrollmentNewButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        enrollmentNewButtonLabel.setText("     ENROLLMENT");
        enrollmentNewButtonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enrollmentNewButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enrollmentNewButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enrollmentNewButtonLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                enrollmentNewButtonLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout enrollmentNewButtonLayout = new javax.swing.GroupLayout(enrollmentNewButton);
        enrollmentNewButton.setLayout(enrollmentNewButtonLayout);
        enrollmentNewButtonLayout.setHorizontalGroup(
            enrollmentNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enrollmentNewButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        enrollmentNewButtonLayout.setVerticalGroup(
            enrollmentNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enrollmentNewButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(enrollmentNewButtonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sidePanel.add(enrollmentNewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, -1));

        masterlistNewButton.setBackground(new java.awt.Color(33, 67, 83));
        masterlistNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                masterlistNewButtonMousePressed(evt);
            }
        });

        masterlistNewButtonLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        masterlistNewButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        masterlistNewButtonLabel.setText("     MASTERLIST");
        masterlistNewButtonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        masterlistNewButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                masterlistNewButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                masterlistNewButtonLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                masterlistNewButtonLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout masterlistNewButtonLayout = new javax.swing.GroupLayout(masterlistNewButton);
        masterlistNewButton.setLayout(masterlistNewButtonLayout);
        masterlistNewButtonLayout.setHorizontalGroup(
            masterlistNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterlistNewButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        masterlistNewButtonLayout.setVerticalGroup(
            masterlistNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterlistNewButtonLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        sidePanel.add(masterlistNewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 210, -1));

        courseNewButton.setBackground(new java.awt.Color(33, 67, 83));
        courseNewButton.setPreferredSize(new java.awt.Dimension(210, 63));

        courseNewButtonLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        courseNewButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        courseNewButtonLabel.setText("     COURSE");
        courseNewButtonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        courseNewButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseNewButtonLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                courseNewButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                courseNewButtonLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                courseNewButtonLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout courseNewButtonLayout = new javax.swing.GroupLayout(courseNewButton);
        courseNewButton.setLayout(courseNewButtonLayout);
        courseNewButtonLayout.setHorizontalGroup(
            courseNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courseNewButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(courseNewButtonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        courseNewButtonLayout.setVerticalGroup(
            courseNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courseNewButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(courseNewButtonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sidePanel.add(courseNewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        studentDetailsNewButton.setBackground(new java.awt.Color(33, 67, 83));

        studentDetailsNewButtonLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        studentDetailsNewButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentDetailsNewButtonLabel.setText("     STUDENT DETAILS");
        studentDetailsNewButtonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentDetailsNewButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentDetailsNewButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentDetailsNewButtonLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentDetailsNewButtonLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout studentDetailsNewButtonLayout = new javax.swing.GroupLayout(studentDetailsNewButton);
        studentDetailsNewButton.setLayout(studentDetailsNewButtonLayout);
        studentDetailsNewButtonLayout.setHorizontalGroup(
            studentDetailsNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentDetailsNewButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        studentDetailsNewButtonLayout.setVerticalGroup(
            studentDetailsNewButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studentDetailsNewButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        sidePanel.add(studentDetailsNewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        adminOnlyButton.setBackground(new java.awt.Color(33, 67, 83));

        adminOnlyButtonLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        adminOnlyButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        adminOnlyButtonLabel.setText("     REGISTER EMPLOYEE");
        adminOnlyButtonLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminOnlyButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminOnlyButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminOnlyButtonLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                adminOnlyButtonLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout adminOnlyButtonLayout = new javax.swing.GroupLayout(adminOnlyButton);
        adminOnlyButton.setLayout(adminOnlyButtonLayout);
        adminOnlyButtonLayout.setHorizontalGroup(
            adminOnlyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminOnlyButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        adminOnlyButtonLayout.setVerticalGroup(
            adminOnlyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminOnlyButtonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        sidePanel.add(adminOnlyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, -1));

        getContentPane().add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 1080));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1300, 646));
        mainPanel.setLayout(new java.awt.CardLayout());

        printFormPanel.setBackground(new java.awt.Color(90, 118, 132));
        printFormPanel.setPreferredSize(new java.awt.Dimension(1710, 646));

        PFPanelToPrint.setBackground(new java.awt.Color(255, 255, 255));
        PFPanelToPrint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("UNIVERSITY OF MINDANAO");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 908, 20));

        jLabel14.setForeground(new java.awt.Color(1, 1, 1));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Republic of the Philippines");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 908, 20));

        jLabel15.setForeground(new java.awt.Color(1, 1, 1));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Department of Education");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 908, 20));

        jLabel20.setForeground(new java.awt.Color(1, 1, 1));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Matina, Davao City");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 908, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(1, 1, 1));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Davao Region");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 908, 20));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(1, 1, 1));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("CERTIFICATE OF MATRICULATION");
        jPanel2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 908, -1));

        jPanel6.setBackground(new java.awt.Color(243, 242, 242));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setForeground(new java.awt.Color(1, 1, 1));
        jLabel26.setText("Student Name:");

        jLabel27.setForeground(new java.awt.Color(1, 1, 1));
        jLabel27.setText("College Dep:");

        jLabel28.setForeground(new java.awt.Color(1, 1, 1));
        jLabel28.setText("Course Major:");

        PFStudentNameLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFStudentNameLabel.setText(" ");

        PFCollegeDepLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFCollegeDepLabel.setText(" ");

        PFCourseMajorLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseMajorLabel.setText(" ");

        jLabel32.setForeground(new java.awt.Color(1, 1, 1));
        jLabel32.setText("Year Level:");

        PFYearLevelLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFYearLevelLabel.setText(" ");

        jLabel34.setForeground(new java.awt.Color(1, 1, 1));
        jLabel34.setText("Semester:");

        PFSemesterLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFSemesterLabel.setText(" ");

        jLabel36.setForeground(new java.awt.Color(1, 1, 1));
        jLabel36.setText("Term:");

        PFTermLabel.setForeground(new java.awt.Color(1, 1, 1));
        PFTermLabel.setText(" ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFStudentNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFCollegeDepLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFCourseMajorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFTermLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFSemesterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PFYearLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(PFYearLevelLabel))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(PFStudentNameLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(PFSemesterLabel))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(PFCollegeDepLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(PFTermLabel))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(PFCourseMajorLabel)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(243, 242, 242));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PFStudentIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PFStudentIDLabel.setForeground(new java.awt.Color(51, 102, 255));
        PFStudentIDLabel.setText("ABC129717070063");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PFStudentIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PFStudentIDLabel)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(243, 242, 242));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel40.setForeground(new java.awt.Color(1, 1, 1));
        jLabel40.setText("Description");

        jLabel42.setForeground(new java.awt.Color(1, 1, 1));
        jLabel42.setText("Time ");

        jLabel45.setForeground(new java.awt.Color(1, 1, 1));
        jLabel45.setText("Title");

        PFCourseCodeLabel1.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel1.setText(" ");

        PFSubjectLabel1.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel1.setText(" ");

        PFTimeLabel1.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel1.setText(" ");

        PFCourseCodeLabel2.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel2.setText(" ");

        PFSubjectLabel2.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel2.setText(" ");

        PFTimeLabel2.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel2.setText(" ");

        PFCourseCodeLabel3.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel3.setText(" ");

        PFSubjectLabel3.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel3.setText(" ");

        PFTimeLabel3.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel3.setText(" ");

        jLabel63.setForeground(new java.awt.Color(1, 1, 1));
        jLabel63.setText("Room");

        PFRoomLabel1.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel1.setText(" ");

        PFRoomLabel2.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel2.setText(" ");

        PFRoomLabel3.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel3.setText(" ");

        PFCourseCodeLabel4.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel4.setText(" ");

        PFSubjectLabel4.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel4.setText(" ");

        PFRoomLabel4.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel4.setText(" ");

        PFTimeLabel4.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel4.setText(" ");

        PFCourseCodeLabel5.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel5.setText(" ");

        PFSubjectLabel5.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel5.setText(" ");

        PFRoomLabel5.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel5.setText(" ");

        PFTimeLabel5.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel5.setText(" ");

        PFCourseCodeLabel6.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel6.setText(" ");

        PFCourseCodeLabel7.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel7.setText(" ");

        PFCourseCodeLabel8.setForeground(new java.awt.Color(1, 1, 1));
        PFCourseCodeLabel8.setText(" ");

        PFSubjectLabel6.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel6.setText(" ");

        PFSubjectLabel7.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel7.setText(" ");

        PFSubjectLabel8.setForeground(new java.awt.Color(1, 1, 1));
        PFSubjectLabel8.setText(" ");

        PFRoomLabel6.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel6.setText(" ");

        PFRoomLabel7.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel7.setText(" ");

        PFRoomLabel8.setForeground(new java.awt.Color(1, 1, 1));
        PFRoomLabel8.setText(" ");

        PFTimeLabel6.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel6.setText(" ");

        PFTimeLabel7.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel7.setText(" ");

        PFTimeLabel8.setForeground(new java.awt.Color(1, 1, 1));
        PFTimeLabel8.setText(" ");

        PFUnitsLabel8.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel8.setText(" ");

        PFUnitsLabel7.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel7.setText(" ");

        PFUnitsLabel6.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel6.setText(" ");

        PFUnitsLabel5.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel5.setText(" ");

        PFUnitsLabel4.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel4.setText(" ");

        PFUnitsLabel3.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel3.setText(" ");

        PFUnitsLabel2.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel2.setText(" ");

        PFUnitsLabel1.setForeground(new java.awt.Color(1, 1, 1));
        PFUnitsLabel1.setText(" ");

        jLabel41.setForeground(new java.awt.Color(1, 1, 1));
        jLabel41.setText("Unit(s)");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PFCourseCodeLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFCourseCodeLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFCourseCodeLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PFSubjectLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFSubjectLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFUnitsLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFUnitsLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFUnitsLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PFUnitsLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFUnitsLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFUnitsLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFUnitsLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFUnitsLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PFRoomLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFRoomLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFRoomLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFRoomLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFRoomLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFRoomLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFRoomLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PFRoomLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PFTimeLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PFTimeLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFTimeLabel8))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFUnitsLabel8))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PFSubjectLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFRoomLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFRoomLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFRoomLabel3))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFCourseCodeLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFCourseCodeLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PFCourseCodeLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PFCourseCodeLabel4)
                            .addComponent(PFRoomLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PFCourseCodeLabel5)
                            .addComponent(PFRoomLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PFCourseCodeLabel6)
                            .addComponent(PFRoomLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PFCourseCodeLabel7)
                            .addComponent(PFRoomLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PFCourseCodeLabel8)
                            .addComponent(PFRoomLabel8))))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jLabel68.setForeground(new java.awt.Color(1, 1, 1));
        jLabel68.setText("Date Printed:");

        CEDatePrintedLabel.setForeground(new java.awt.Color(1, 1, 1));
        CEDatePrintedLabel.setText("12/02/2023");

        javax.swing.GroupLayout PFPanelToPrintLayout = new javax.swing.GroupLayout(PFPanelToPrint);
        PFPanelToPrint.setLayout(PFPanelToPrintLayout);
        PFPanelToPrintLayout.setHorizontalGroup(
            PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PFPanelToPrintLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PFPanelToPrintLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PFPanelToPrintLayout.createSequentialGroup()
                        .addGroup(PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PFPanelToPrintLayout.createSequentialGroup()
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CEDatePrintedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))))
        );
        PFPanelToPrintLayout.setVerticalGroup(
            PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PFPanelToPrintLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(PFPanelToPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(CEDatePrintedLabel))
                .addGap(93, 93, 93))
        );

        PFPrintPanelButton.setText("PRINT");
        PFPrintPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PFPrintPanelButtonActionPerformed(evt);
            }
        });

        PFBackButton.setText("Back");
        PFBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PFBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout printFormPanelLayout = new javax.swing.GroupLayout(printFormPanel);
        printFormPanel.setLayout(printFormPanelLayout);
        printFormPanelLayout.setHorizontalGroup(
            printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printFormPanelLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PFPanelToPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(printFormPanelLayout.createSequentialGroup()
                        .addComponent(PFBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PFPrintPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        printFormPanelLayout.setVerticalGroup(
            printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printFormPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(PFPanelToPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PFPrintPanelButton)
                    .addComponent(PFBackButton))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        mainPanel.add(printFormPanel, "card5");

        courseEnrollmentPanel.setBackground(new java.awt.Color(31, 48, 56));

        jPanel13.setBackground(new java.awt.Color(31, 48, 56));
        jPanel13.setPreferredSize(new java.awt.Dimension(1060, 221));

        jLabel101.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("College Department");

        CECollegeDepBox.setBackground(new java.awt.Color(98, 161, 192));
        CECollegeDepBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CECollegeDepBox.setForeground(new java.awt.Color(204, 255, 255));
        CECollegeDepBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "CAE - College of Accounting Education", "CAFAE - College of Architecture and Fine Arts Education", "CCE - College of Computing Education", "CCJE - College of Criminal Justice Education", "CEE - College of Engineering Education", "CHE - College of Hospitality Education", "CHSE - College of Health Science Education", "TS - Technical School" }));
        CECollegeDepBox.setFocusable(false);
        CECollegeDepBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CECollegeDepBoxActionPerformed(evt);
            }
        });

        CECourseMajorBox.setBackground(new java.awt.Color(98, 161, 192));
        CECourseMajorBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CECourseMajorBox.setForeground(new java.awt.Color(204, 255, 255));
        CECourseMajorBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        CECourseMajorBox.setFocusable(false);
        CECourseMajorBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CECourseMajorBoxActionPerformed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Course Major");

        CEYearLevelBox.setBackground(new java.awt.Color(98, 161, 192));
        CEYearLevelBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CEYearLevelBox.setForeground(new java.awt.Color(204, 255, 255));
        CEYearLevelBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Year 1", "Year 2", "Year 3", "Year 4", "Year 5" }));
        CEYearLevelBox.setFocusable(false);

        jLabel109.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("Year Level");

        CESemesterBox.setBackground(new java.awt.Color(98, 161, 192));
        CESemesterBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CESemesterBox.setForeground(new java.awt.Color(204, 255, 255));
        CESemesterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1ST Sem", "2ND Sem" }));
        CESemesterBox.setFocusable(false);

        jLabel110.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("Semester");

        jLabel111.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Term");

        CETermBox.setBackground(new java.awt.Color(98, 161, 192));
        CETermBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CETermBox.setForeground(new java.awt.Color(204, 255, 255));
        CETermBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1ST Term", "2ND Term" }));
        CETermBox.setFocusable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CECourseMajorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CEYearLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CESemesterBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CETermBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CECollegeDepBox, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CECollegeDepBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CECourseMajorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CEYearLevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CESemesterBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CETermBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("COURSE(S) LIST AND SCHEDULE ");

        CEEnrollButton.setText("Enroll");
        CEEnrollButton.setFocusable(false);
        CEEnrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEEnrollButtonActionPerformed(evt);
            }
        });

        CEPrintPreviewButton.setText("Print Preview");
        CEPrintPreviewButton.setFocusable(false);
        CEPrintPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEPrintPreviewButtonActionPerformed(evt);
            }
        });

        CECourseListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course Code", "Descriptive Title", "Unit(s)", "Time | Date", "Room"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CECourseListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CECourseListTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(CECourseListTable);
        if (CECourseListTable.getColumnModel().getColumnCount() > 0) {
            CECourseListTable.getColumnModel().getColumn(0).setMinWidth(120);
            CECourseListTable.getColumnModel().getColumn(0).setMaxWidth(120);
            CECourseListTable.getColumnModel().getColumn(2).setMinWidth(100);
            CECourseListTable.getColumnModel().getColumn(2).setMaxWidth(100);
            CECourseListTable.getColumnModel().getColumn(3).setMinWidth(150);
            CECourseListTable.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        bottomPanel.setBackground(new java.awt.Color(8, 17, 22));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("This section is reponsible for enrolling a student's courses for the semester/term.");

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel3)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Course Enrollment");

        searchPanel2.setBackground(new java.awt.Color(31, 48, 56));

        jLabel88.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Student Name");

        CEStudentNameField.setBackground(new java.awt.Color(98, 161, 192));
        CEStudentNameField.setForeground(new java.awt.Color(255, 255, 255));

        CEStudentIDField.setBackground(new java.awt.Color(98, 161, 192));
        CEStudentIDField.setDocument(new JTextFieldLimit(6));
        CEStudentIDField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel89.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Student ID");

        CESearchButton.setText("Search");
        CESearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CESearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanel2Layout = new javax.swing.GroupLayout(searchPanel2);
        searchPanel2.setLayout(searchPanel2Layout);
        searchPanel2Layout.setHorizontalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CEStudentNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CEStudentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(CESearchButton)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        searchPanel2Layout.setVerticalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CEStudentIDField)
                            .addComponent(CESearchButton))
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CEStudentNameField)
                        .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(31, 48, 56));
        jPanel14.setPreferredSize(new java.awt.Dimension(1060, 221));

        jLabel112.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setText("Course/Subject");

        CESubjectBox.setBackground(new java.awt.Color(98, 161, 192));
        CESubjectBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CESubjectBox.setForeground(new java.awt.Color(204, 255, 255));
        CESubjectBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Understanding the Self", "Readings in Philippine History", "The Contemporary World", "Mathematics in the Modern World", "Purposive Communication", "Art Appreciation", "Science, Technology and Society", "Ethics", "Life and Works of Rizal", "G.E. Electives", "Introduction to Language Studies", "Cross Cultural Communication" }));
        CESubjectBox.setFocusable(false);
        CESubjectBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CESubjectBoxActionPerformed(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("Course Code");

        CESubjectCodeField.setBackground(new java.awt.Color(98, 161, 192));
        CESubjectCodeField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CESubjectCodeField.setForeground(new java.awt.Color(204, 255, 255));

        jLabel114.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("Units");

        CEUnitsField.setBackground(new java.awt.Color(98, 161, 192));
        CEUnitsField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CEUnitsField.setForeground(new java.awt.Color(204, 255, 255));

        jLabel116.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setText("Time | Date");

        CETimeDateBox.setBackground(new java.awt.Color(98, 161, 192));
        CETimeDateBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CETimeDateBox.setForeground(new java.awt.Color(204, 255, 255));
        CETimeDateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "700M-800M", "800M-900M", "900M-1000M", "1000M-1100M", "1100M-1200A", "1230A-130A", "130A-230A", "230A-330A", "330A-430A", "430A-530E" }));
        CETimeDateBox.setFocusable(false);

        jLabel117.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setText("Room");

        CERoomBox.setBackground(new java.awt.Color(98, 161, 192));
        CERoomBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        CERoomBox.setForeground(new java.awt.Color(204, 255, 255));
        CERoomBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "H1A", "H1B", "H1C", "H1D", "H2A", "H2B", "H2C", "H2D", "TEC101", "TEC102", "TEC103", "TEC201", "TEC202", "TEC203" }));
        CERoomBox.setFocusable(false);

        CEAddButton.setText("Add");
        CEAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEAddButtonActionPerformed(evt);
            }
        });

        CECourseButton.setText("Update");
        CECourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CECourseButtonActionPerformed(evt);
            }
        });

        CEDeleteButton.setText("Delete");
        CEDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CESubjectCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(CEAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CECourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CEDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CEUnitsField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(CETimeDateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CERoomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(CESubjectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CESubjectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CESubjectCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CEUnitsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CERoomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CETimeDateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CECourseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CEAddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CEDeleteButton))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout courseEnrollmentPanelLayout = new javax.swing.GroupLayout(courseEnrollmentPanel);
        courseEnrollmentPanel.setLayout(courseEnrollmentPanelLayout);
        courseEnrollmentPanelLayout.setHorizontalGroup(
            courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(835, 835, 835))
                                .addComponent(searchPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                                    .addComponent(CEEnrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(CEPrintPreviewButton))
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)))))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        courseEnrollmentPanelLayout.setVerticalGroup(
            courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(searchPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(courseEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CEEnrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CEPrintPreviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(courseEnrollmentPanel, "card7");

        studentEnrollmentPanel.setBackground(new java.awt.Color(31, 48, 56));

        bottomPanel1.setBackground(new java.awt.Color(8, 17, 22));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("This section is reponsible for enrolling a student into the metaLABS database.");

        javax.swing.GroupLayout bottomPanel1Layout = new javax.swing.GroupLayout(bottomPanel1);
        bottomPanel1.setLayout(bottomPanel1Layout);
        bottomPanel1Layout.setHorizontalGroup(
            bottomPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel8)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        bottomPanel1Layout.setVerticalGroup(
            bottomPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student Enrollment");

        jPanel15.setBackground(new java.awt.Color(31, 48, 56));
        jPanel15.setPreferredSize(new java.awt.Dimension(771, 296));

        SEFirstNameField.setBackground(new java.awt.Color(98, 161, 192));
        SEFirstNameField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEFirstNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel125.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("First Name");

        jLabel126.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("Middle Name");

        SEMiddleNameField.setBackground(new java.awt.Color(98, 161, 192));
        SEMiddleNameField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEMiddleNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel127.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setText("Last Name");

        SELastNameField.setBackground(new java.awt.Color(98, 161, 192));
        SELastNameField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SELastNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel129.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setText("Date of Birth");

        SEBirthMonthBox.setBackground(new java.awt.Color(98, 161, 192));
        SEBirthMonthBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEBirthMonthBox.setForeground(new java.awt.Color(255, 255, 255));
        SEBirthMonthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        SEBirthDateBox.setBackground(new java.awt.Color(98, 161, 192));
        SEBirthDateBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEBirthDateBox.setForeground(new java.awt.Color(255, 255, 255));
        SEBirthDateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        SEBirthYearBox.setBackground(new java.awt.Color(98, 161, 192));
        SEBirthYearBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEBirthYearBox.setForeground(new java.awt.Color(255, 255, 255));
        SEBirthYearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895" }));

        jLabel130.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 255, 255));
        jLabel130.setText("Gender");

        SEGenderBox.setBackground(new java.awt.Color(98, 161, 192));
        SEGenderBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEGenderBox.setForeground(new java.awt.Color(255, 255, 255));
        SEGenderBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Male", "Female" }));

        jLabel131.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Marital Status");

        SEMaritalStatusField.setBackground(new java.awt.Color(98, 161, 192));
        SEMaritalStatusField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEMaritalStatusField.setForeground(new java.awt.Color(255, 255, 255));
        SEMaritalStatusField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Single", "Married", "Divorced", "Widowed" }));
        SEMaritalStatusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEMaritalStatusFieldActionPerformed(evt);
            }
        });

        jLabel132.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Address");

        SEAddressField.setBackground(new java.awt.Color(98, 161, 192));
        SEAddressField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEAddressField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel133.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setText("Contact Number");

        SEContactNumberField.setBackground(new java.awt.Color(98, 161, 192));
        SEContactNumberField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEContactNumberField.setForeground(new java.awt.Color(255, 255, 255));
        SEContactNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SEContactNumberFieldKeyTyped(evt);
            }
        });

        jLabel134.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setText("Citzenship");

        SECitizenshipBox.setBackground(new java.awt.Color(98, 161, 192));
        SECitizenshipBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SECitizenshipBox.setForeground(new java.awt.Color(255, 255, 255));
        SECitizenshipBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Filipino", "Cebuano", "Japanese", "American" }));

        jLabel135.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Religion");

        SEReligionBox.setBackground(new java.awt.Color(98, 161, 192));
        SEReligionBox.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEReligionBox.setForeground(new java.awt.Color(255, 255, 255));
        SEReligionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Catholic", "Islam", "Judaism", "Unaffiliated" }));
        SEReligionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEReligionBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEGenderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEMaritalStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SECitizenshipBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEReligionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(SEContactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEBirthMonthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEBirthDateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEBirthYearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEAddressField))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SEMiddleNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SELastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEMiddleNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SELastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEBirthMonthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEBirthDateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEBirthYearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEGenderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SECitizenshipBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEContactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEReligionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEMaritalStatusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        SEEnrollButton.setText("Enroll");
        SEEnrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEEnrollButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(31, 48, 56));

        SEMotherNameField.setBackground(new java.awt.Color(98, 161, 192));
        SEMotherNameField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEMotherNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel128.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(255, 255, 255));
        jLabel128.setText("Mother's Name");

        jLabel136.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setText("Father's Name");

        SEFatherNameField.setBackground(new java.awt.Color(98, 161, 192));
        SEFatherNameField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEFatherNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel137.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("Contact No");

        SEMotherContactField.setBackground(new java.awt.Color(98, 161, 192));
        SEMotherContactField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEMotherContactField.setForeground(new java.awt.Color(255, 255, 255));
        SEMotherContactField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SEMotherContactFieldKeyTyped(evt);
            }
        });

        jLabel138.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("Contact No");

        SEFatherContactField.setBackground(new java.awt.Color(98, 161, 192));
        SEFatherContactField.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        SEFatherContactField.setForeground(new java.awt.Color(255, 255, 255));
        SEFatherContactField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SEFatherContactFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEFatherNameField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEMotherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEFatherContactField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEMotherContactField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SEMotherContactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SEFatherContactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SEMotherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SEFatherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout studentEnrollmentPanelLayout = new javax.swing.GroupLayout(studentEnrollmentPanel);
        studentEnrollmentPanel.setLayout(studentEnrollmentPanelLayout);
        studentEnrollmentPanelLayout.setHorizontalGroup(
            studentEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentEnrollmentPanelLayout.createSequentialGroup()
                .addGroup(studentEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentEnrollmentPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottomPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentEnrollmentPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(studentEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(studentEnrollmentPanelLayout.createSequentialGroup()
                                .addComponent(SEEnrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        studentEnrollmentPanelLayout.setVerticalGroup(
            studentEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentEnrollmentPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(studentEnrollmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEEnrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(424, Short.MAX_VALUE))
        );

        mainPanel.add(studentEnrollmentPanel, "card7");

        masterlistPanel.setBackground(new java.awt.Color(31, 48, 56));

        bottomPanel2.setBackground(new java.awt.Color(8, 17, 22));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("This section holds the list for all the enrolled students within the database.");

        javax.swing.GroupLayout bottomPanel2Layout = new javax.swing.GroupLayout(bottomPanel2);
        bottomPanel2.setLayout(bottomPanel2Layout);
        bottomPanel2Layout.setHorizontalGroup(
            bottomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanel2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel10)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        bottomPanel2Layout.setVerticalGroup(
            bottomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Masterlist");

        MLMasterlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Last Name", "First Name", "Middle Name", "Year Level", "College", "Course Major"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(MLMasterlistTable);
        if (MLMasterlistTable.getColumnModel().getColumnCount() > 0) {
            MLMasterlistTable.getColumnModel().getColumn(0).setMinWidth(95);
            MLMasterlistTable.getColumnModel().getColumn(0).setMaxWidth(95);
            MLMasterlistTable.getColumnModel().getColumn(1).setMinWidth(150);
            MLMasterlistTable.getColumnModel().getColumn(1).setMaxWidth(150);
            MLMasterlistTable.getColumnModel().getColumn(2).setMinWidth(180);
            MLMasterlistTable.getColumnModel().getColumn(2).setMaxWidth(180);
            MLMasterlistTable.getColumnModel().getColumn(3).setMinWidth(150);
            MLMasterlistTable.getColumnModel().getColumn(3).setMaxWidth(150);
            MLMasterlistTable.getColumnModel().getColumn(4).setMinWidth(85);
            MLMasterlistTable.getColumnModel().getColumn(4).setMaxWidth(85);
            MLMasterlistTable.getColumnModel().getColumn(5).setMinWidth(85);
            MLMasterlistTable.getColumnModel().getColumn(5).setMaxWidth(85);
        }

        MLSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MLSearchFieldActionPerformed(evt);
            }
        });
        MLSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MLSearchFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MLSearchFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MLSearchFieldKeyTyped(evt);
            }
        });

        MLSearchButton.setText("Search");
        MLSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MLSearchButtonActionPerformed(evt);
            }
        });
        MLSearchButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MLSearchButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout masterlistPanelLayout = new javax.swing.GroupLayout(masterlistPanel);
        masterlistPanel.setLayout(masterlistPanelLayout);
        masterlistPanelLayout.setHorizontalGroup(
            masterlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterlistPanelLayout.createSequentialGroup()
                .addGroup(masterlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(masterlistPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottomPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(masterlistPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(masterlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(masterlistPanelLayout.createSequentialGroup()
                                .addComponent(MLSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MLSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        masterlistPanelLayout.setVerticalGroup(
            masterlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterlistPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(masterlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MLSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MLSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(masterlistPanel, "card7");

        studentDetailsPanel.setBackground(new java.awt.Color(31, 48, 56));

        bottomPanel3.setBackground(new java.awt.Color(8, 17, 22));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("This section focuses on the student's background and parent details..");

        javax.swing.GroupLayout bottomPanel3Layout = new javax.swing.GroupLayout(bottomPanel3);
        bottomPanel3.setLayout(bottomPanel3Layout);
        bottomPanel3Layout.setHorizontalGroup(
            bottomPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanel3Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel12)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        bottomPanel3Layout.setVerticalGroup(
            bottomPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Student Details");

        searchPanel.setBackground(new java.awt.Color(31, 48, 56));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student No");

        SDStudentIDField.setBackground(new java.awt.Color(98, 161, 192));
        SDStudentIDField.setForeground(new java.awt.Color(255, 255, 255));

        SDStudentYearLevelField.setBackground(new java.awt.Color(98, 161, 192));
        SDStudentYearLevelField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Year Level");

        SDSearchButton.setText("Search");
        SDSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDSearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SDStudentIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SDSearchButton)
                .addGap(106, 106, 106)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SDStudentYearLevelField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(SDStudentYearLevelField)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDStudentIDField)
                            .addComponent(SDSearchButton))
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );

        studentDetailsTabbedPane.setBackground(new java.awt.Color(55, 111, 138));
        studentDetailsTabbedPane.setForeground(new java.awt.Color(255, 255, 255));

        personalInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        SDLastNameField.setBackground(new java.awt.Color(98, 161, 192));
        SDLastNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Last Name");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("First Name");

        SDFirstNameField.setBackground(new java.awt.Color(98, 161, 192));
        SDFirstNameField.setForeground(new java.awt.Color(255, 255, 255));

        SDMiddleNameField.setBackground(new java.awt.Color(98, 161, 192));
        SDMiddleNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Middle Name");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Age");

        SDAgeField.setBackground(new java.awt.Color(98, 161, 192));
        SDAgeField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Address");

        SDAddressField.setBackground(new java.awt.Color(98, 161, 192));
        SDAddressField.setForeground(new java.awt.Color(255, 255, 255));
        SDAddressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SDAddressFieldActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Gender");

        SDGenderField.setBackground(new java.awt.Color(98, 161, 192));
        SDGenderField.setForeground(new java.awt.Color(255, 255, 255));

        SDMaritalStatusField.setBackground(new java.awt.Color(98, 161, 192));
        SDMaritalStatusField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel71.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Marital Status");

        jLabel72.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Citizenship");

        SDCitizenshipField.setBackground(new java.awt.Color(98, 161, 192));
        SDCitizenshipField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Religion");

        SDReligionField.setBackground(new java.awt.Color(98, 161, 192));
        SDReligionField.setForeground(new java.awt.Color(255, 255, 255));

        SDStudentContactField.setBackground(new java.awt.Color(98, 161, 192));
        SDStudentContactField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel74.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Contact No");

        javax.swing.GroupLayout personalInfoPanelLayout = new javax.swing.GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoPanelLayout);
        personalInfoPanelLayout.setHorizontalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDMiddleNameField))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDFirstNameField))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SDAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SDAgeField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(144, 144, 144)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDStudentContactField))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SDMaritalStatusField))
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SDGenderField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SDCitizenshipField))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SDReligionField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addGap(127, 127, 127))
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SDLastNameField)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SDStudentContactField)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDFirstNameField)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDMiddleNameField)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDAgeField)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                .addGap(37, 37, 37))
                            .addComponent(SDAddressField)))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDGenderField)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDMaritalStatusField)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDCitizenshipField)
                            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SDReligionField)
                            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(52, 52, 52))
        );

        studentDetailsTabbedPane.addTab("Personal Information", personalInfoPanel);

        parentsInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        jLabel75.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Mother's Name");

        SDMotherNameField.setBackground(new java.awt.Color(98, 161, 192));
        SDMotherNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel76.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Contact No");

        SDMotherContactField.setBackground(new java.awt.Color(98, 161, 192));
        SDMotherContactField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel77.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Father's Name");

        SDFatherNameField.setBackground(new java.awt.Color(98, 161, 192));
        SDFatherNameField.setForeground(new java.awt.Color(255, 255, 255));

        jLabel78.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Contact No");

        SDFatherContactField.setBackground(new java.awt.Color(98, 161, 192));
        SDFatherContactField.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout parentsInfoPanelLayout = new javax.swing.GroupLayout(parentsInfoPanel);
        parentsInfoPanel.setLayout(parentsInfoPanelLayout);
        parentsInfoPanelLayout.setHorizontalGroup(
            parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDFatherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDMotherNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDFatherContactField))
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SDMotherContactField, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                .addGap(139, 139, 139))
        );
        parentsInfoPanelLayout.setVerticalGroup(
            parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SDMotherNameField)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SDMotherContactField)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SDFatherNameField)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SDFatherContactField)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(167, 167, 167))
        );

        studentDetailsTabbedPane.addTab("Parents Information", parentsInfoPanel);

        addtionalInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        jLabel79.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Remarks");

        SDRemarksField.setBackground(new java.awt.Color(98, 161, 192));
        SDRemarksField.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout addtionalInfoPanelLayout = new javax.swing.GroupLayout(addtionalInfoPanel);
        addtionalInfoPanel.setLayout(addtionalInfoPanelLayout);
        addtionalInfoPanelLayout.setHorizontalGroup(
            addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addtionalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SDRemarksField, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        addtionalInfoPanelLayout.setVerticalGroup(
            addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addtionalInfoPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SDRemarksField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        studentDetailsTabbedPane.addTab("Additional Information", addtionalInfoPanel);

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout studentDetailsPanelLayout = new javax.swing.GroupLayout(studentDetailsPanel);
        studentDetailsPanel.setLayout(studentDetailsPanelLayout);
        studentDetailsPanelLayout.setHorizontalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottomPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(studentDetailsTabbedPane)))))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        studentDetailsPanelLayout.setVerticalGroup(
            studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentDetailsPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(studentDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(studentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        mainPanel.add(studentDetailsPanel, "card7");

        registerEmployeePanel.setBackground(new java.awt.Color(31, 48, 56));

        bottomPanel4.setBackground(new java.awt.Color(8, 17, 22));

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 204));
        jLabel24.setText("This section features an employee register system that is only exclusive for admins. ");

        javax.swing.GroupLayout bottomPanel4Layout = new javax.swing.GroupLayout(bottomPanel4);
        bottomPanel4.setLayout(bottomPanel4Layout);
        bottomPanel4Layout.setHorizontalGroup(
            bottomPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanel4Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel24)
                .addContainerGap(933, Short.MAX_VALUE))
        );
        bottomPanel4Layout.setVerticalGroup(
            bottomPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Register Employee");

        searchPanel3.setBackground(new java.awt.Color(31, 48, 56));

        jLabel84.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Username");

        jTextField21.setBackground(new java.awt.Color(98, 161, 192));
        jTextField21.setForeground(new java.awt.Color(255, 255, 255));

        jLabel86.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Password");

        jTextField23.setBackground(new java.awt.Color(98, 161, 192));
        jTextField23.setForeground(new java.awt.Color(255, 255, 255));
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Confirm Password");

        jTextField24.setBackground(new java.awt.Color(98, 161, 192));
        jTextField24.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout searchPanel3Layout = new javax.swing.GroupLayout(searchPanel3);
        searchPanel3.setLayout(searchPanel3Layout);
        searchPanel3Layout.setHorizontalGroup(
            searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        searchPanel3Layout.setVerticalGroup(
            searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        searchPanel4.setBackground(new java.awt.Color(31, 48, 56));

        jLabel90.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("First Name");

        jTextField25.setBackground(new java.awt.Color(98, 161, 192));
        jTextField25.setForeground(new java.awt.Color(255, 255, 255));

        jTextField26.setBackground(new java.awt.Color(98, 161, 192));
        jTextField26.setForeground(new java.awt.Color(255, 255, 255));

        jLabel91.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Last Name");

        jLabel92.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Gender");

        jTextField28.setBackground(new java.awt.Color(98, 161, 192));
        jTextField28.setForeground(new java.awt.Color(255, 255, 255));

        jTextField29.setBackground(new java.awt.Color(98, 161, 192));
        jTextField29.setForeground(new java.awt.Color(255, 255, 255));

        jLabel93.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Email");

        javax.swing.GroupLayout searchPanel4Layout = new javax.swing.GroupLayout(searchPanel4);
        searchPanel4.setLayout(searchPanel4Layout);
        searchPanel4Layout.setHorizontalGroup(
            searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel4Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104)
                .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        searchPanel4Layout.setVerticalGroup(
            searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField26)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField25)
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField29)
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField28)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setText("PERSONAL INFORMATION");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("ACCOUNT INFORMATION");

        jButton2.setText("Register");

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerEmployeePanelLayout = new javax.swing.GroupLayout(registerEmployeePanel);
        registerEmployeePanel.setLayout(registerEmployeePanelLayout);
        registerEmployeePanelLayout.setHorizontalGroup(
            registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottomPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(searchPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel118)
                                .addComponent(jLabel119)))))
                .addContainerGap(177, Short.MAX_VALUE))
            .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                    .addGap(156, 156, 156)
                    .addComponent(searchPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(488, Short.MAX_VALUE)))
        );
        registerEmployeePanelLayout.setVerticalGroup(
            registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel80)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel118)
                .addGap(188, 188, 188)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(444, Short.MAX_VALUE))
            .addGroup(registerEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(registerEmployeePanelLayout.createSequentialGroup()
                    .addGap(240, 240, 240)
                    .addComponent(searchPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(659, Short.MAX_VALUE)))
        );

        mainPanel.add(registerEmployeePanel, "card7");

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 1710, 1020));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enrollmentNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrollmentNewButtonLabelMouseEntered
        SidePanel_SetColor(enrollmentNewButton, enrollmentNewButtonLabel);
    }//GEN-LAST:event_enrollmentNewButtonLabelMouseEntered

    private void enrollmentNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrollmentNewButtonLabelMouseExited
        SidePanel_ResetColor(enrollmentNewButton, enrollmentNewButtonLabel);
    }//GEN-LAST:event_enrollmentNewButtonLabelMouseExited

    private void masterlistNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterlistNewButtonLabelMouseEntered
        SidePanel_SetColor(masterlistNewButton, masterlistNewButtonLabel);

    }//GEN-LAST:event_masterlistNewButtonLabelMouseEntered

    private void masterlistNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterlistNewButtonLabelMouseExited
        SidePanel_ResetColor(masterlistNewButton, masterlistNewButtonLabel);
    }//GEN-LAST:event_masterlistNewButtonLabelMouseExited

    private void masterlistNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterlistNewButtonLabelMousePressed
        ChangeCard(masterlistPanel);
    }//GEN-LAST:event_masterlistNewButtonLabelMousePressed

    private void enrollmentNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enrollmentNewButtonLabelMousePressed
        ChangeCard(studentEnrollmentPanel);
    }//GEN-LAST:event_enrollmentNewButtonLabelMousePressed

    private void CECollegeDepBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CECollegeDepBoxActionPerformed
        Change_Major_Courses();
    }//GEN-LAST:event_CECollegeDepBoxActionPerformed

    private void CEEnrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEEnrollButtonActionPerformed
        Enroll_Course_Action();
    }//GEN-LAST:event_CEEnrollButtonActionPerformed

    private void CEPrintPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEPrintPreviewButtonActionPerformed
        ChangeCard(printFormPanel);
        Print_Form_Action();
        Date_Today();
    }//GEN-LAST:event_CEPrintPreviewButtonActionPerformed

    private void courseNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMouseEntered
        SidePanel_SetColor(courseNewButton, courseNewButtonLabel);
    }//GEN-LAST:event_courseNewButtonLabelMouseEntered

    private void courseNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMouseExited
        SidePanel_ResetColor(courseNewButton, courseNewButtonLabel);
    }//GEN-LAST:event_courseNewButtonLabelMouseExited

    private void courseNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMousePressed
        ChangeCard(courseEnrollmentPanel);
        Back_Button_Action();
    }//GEN-LAST:event_courseNewButtonLabelMousePressed

    private void SEMaritalStatusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEMaritalStatusFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEMaritalStatusFieldActionPerformed

    private void SEReligionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEReligionBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEReligionBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SEEnrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEEnrollButtonActionPerformed
        Enroll_Student_Action();
    }//GEN-LAST:event_SEEnrollButtonActionPerformed

    private void MLSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MLSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MLSearchFieldActionPerformed

    private void MLSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MLSearchButtonActionPerformed

    }//GEN-LAST:event_MLSearchButtonActionPerformed

    private void studentDetailsNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMouseEntered
        SidePanel_SetColor(studentDetailsNewButton, studentDetailsNewButtonLabel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMouseEntered

    private void studentDetailsNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMouseExited
        SidePanel_ResetColor(studentDetailsNewButton, studentDetailsNewButtonLabel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMouseExited

    private void studentDetailsNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMousePressed
        ChangeCard(studentDetailsPanel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMousePressed

    private void SDAddressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDAddressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SDAddressFieldActionPerformed

    private void masterlistNewButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterlistNewButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_masterlistNewButtonMousePressed

    private void adminOnlyButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminOnlyButtonLabelMouseEntered
        SidePanel_SetColor(adminOnlyButton, adminOnlyButtonLabel);
    }//GEN-LAST:event_adminOnlyButtonLabelMouseEntered

    private void adminOnlyButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminOnlyButtonLabelMouseExited
        SidePanel_ResetColor(adminOnlyButton, adminOnlyButtonLabel);
    }//GEN-LAST:event_adminOnlyButtonLabelMouseExited

    private void adminOnlyButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminOnlyButtonLabelMousePressed
        ChangeCard(registerEmployeePanel);
    }//GEN-LAST:event_adminOnlyButtonLabelMousePressed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        int q = JOptionPane.showConfirmDialog(null, "Are you sure?", "Logout", JOptionPane.YES_NO_OPTION);

        if (q == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
        } else {

        }


    }//GEN-LAST:event_logoutButtonActionPerformed

    private void SEContactNumberFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SEContactNumberFieldKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_SEContactNumberFieldKeyTyped

    private void SEMotherContactFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SEMotherContactFieldKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_SEMotherContactFieldKeyTyped

    private void SEFatherContactFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SEFatherContactFieldKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_SEFatherContactFieldKeyTyped

    private void CEAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEAddButtonActionPerformed
        Add_Courses_Action();
    }//GEN-LAST:event_CEAddButtonActionPerformed

    private void CECourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CECourseButtonActionPerformed
        Update_Courses_Action();
    }//GEN-LAST:event_CECourseButtonActionPerformed

    private void CEDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEDeleteButtonActionPerformed
        Delete_Courses_Action();
    }//GEN-LAST:event_CEDeleteButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void CECourseMajorBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CECourseMajorBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CECourseMajorBoxActionPerformed

    private void CESubjectBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CESubjectBoxActionPerformed
        Course_Set_Text_Action();
    }//GEN-LAST:event_CESubjectBoxActionPerformed

    private void SDSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SDSearchButtonActionPerformed
        Student_Details_Info_Action();
    }//GEN-LAST:event_SDSearchButtonActionPerformed

    private void CECourseListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CECourseListTableMouseClicked
        OnClick_Table_Action();
    }//GEN-LAST:event_CECourseListTableMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Student_Details_Update_Action();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Student_Details_Delete_Action();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void MLSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MLSearchFieldKeyTyped

        Masterlist_Table_Search();
    }//GEN-LAST:event_MLSearchFieldKeyTyped

    private void MLSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MLSearchFieldKeyPressed

    }//GEN-LAST:event_MLSearchFieldKeyPressed

    private void MLSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MLSearchFieldKeyReleased

    }//GEN-LAST:event_MLSearchFieldKeyReleased

    private void MLSearchButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MLSearchButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MLSearchButtonKeyPressed

    private void CESearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CESearchButtonActionPerformed
        Update_Courses_Table();
    }//GEN-LAST:event_CESearchButtonActionPerformed

    private void PFPrintPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PFPrintPanelButtonActionPerformed
        Print_Form_Save(PFPanelToPrint);
    }//GEN-LAST:event_PFPrintPanelButtonActionPerformed

    private void PFBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PFBackButtonActionPerformed
        ChangeCard(this);
        Back_Button_Action();
    }//GEN-LAST:event_PFBackButtonActionPerformed

    private void courseNewButtonLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_courseNewButtonLabelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
//            FlatSolarizedDarkContrastIJTheme.setup();
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnrollmentEncoding().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CEAddButton;
    private javax.swing.JComboBox<String> CECollegeDepBox;
    private javax.swing.JButton CECourseButton;
    private javax.swing.JTable CECourseListTable;
    private javax.swing.JComboBox<String> CECourseMajorBox;
    private javax.swing.JLabel CEDatePrintedLabel;
    private javax.swing.JButton CEDeleteButton;
    private javax.swing.JButton CEEnrollButton;
    private javax.swing.JButton CEPrintPreviewButton;
    private javax.swing.JComboBox<String> CERoomBox;
    private javax.swing.JButton CESearchButton;
    private javax.swing.JComboBox<String> CESemesterBox;
    private javax.swing.JTextField CEStudentIDField;
    private javax.swing.JTextField CEStudentNameField;
    private javax.swing.JComboBox<String> CESubjectBox;
    private javax.swing.JTextField CESubjectCodeField;
    private javax.swing.JComboBox<String> CETermBox;
    private javax.swing.JComboBox<String> CETimeDateBox;
    private javax.swing.JTextField CEUnitsField;
    private javax.swing.JComboBox<String> CEYearLevelBox;
    private javax.swing.JTable MLMasterlistTable;
    private javax.swing.JButton MLSearchButton;
    private javax.swing.JTextField MLSearchField;
    private javax.swing.JButton PFBackButton;
    private javax.swing.JLabel PFCollegeDepLabel;
    private javax.swing.JLabel PFCourseCodeLabel1;
    private javax.swing.JLabel PFCourseCodeLabel2;
    private javax.swing.JLabel PFCourseCodeLabel3;
    private javax.swing.JLabel PFCourseCodeLabel4;
    private javax.swing.JLabel PFCourseCodeLabel5;
    private javax.swing.JLabel PFCourseCodeLabel6;
    private javax.swing.JLabel PFCourseCodeLabel7;
    private javax.swing.JLabel PFCourseCodeLabel8;
    private javax.swing.JLabel PFCourseMajorLabel;
    private javax.swing.JPanel PFPanelToPrint;
    private javax.swing.JButton PFPrintPanelButton;
    private javax.swing.JLabel PFRoomLabel1;
    private javax.swing.JLabel PFRoomLabel2;
    private javax.swing.JLabel PFRoomLabel3;
    private javax.swing.JLabel PFRoomLabel4;
    private javax.swing.JLabel PFRoomLabel5;
    private javax.swing.JLabel PFRoomLabel6;
    private javax.swing.JLabel PFRoomLabel7;
    private javax.swing.JLabel PFRoomLabel8;
    private javax.swing.JLabel PFSemesterLabel;
    private javax.swing.JLabel PFStudentIDLabel;
    private javax.swing.JLabel PFStudentNameLabel;
    private javax.swing.JLabel PFSubjectLabel1;
    private javax.swing.JLabel PFSubjectLabel2;
    private javax.swing.JLabel PFSubjectLabel3;
    private javax.swing.JLabel PFSubjectLabel4;
    private javax.swing.JLabel PFSubjectLabel5;
    private javax.swing.JLabel PFSubjectLabel6;
    private javax.swing.JLabel PFSubjectLabel7;
    private javax.swing.JLabel PFSubjectLabel8;
    private javax.swing.JLabel PFTermLabel;
    private javax.swing.JLabel PFTimeLabel1;
    private javax.swing.JLabel PFTimeLabel2;
    private javax.swing.JLabel PFTimeLabel3;
    private javax.swing.JLabel PFTimeLabel4;
    private javax.swing.JLabel PFTimeLabel5;
    private javax.swing.JLabel PFTimeLabel6;
    private javax.swing.JLabel PFTimeLabel7;
    private javax.swing.JLabel PFTimeLabel8;
    private javax.swing.JLabel PFUnitsLabel1;
    private javax.swing.JLabel PFUnitsLabel2;
    private javax.swing.JLabel PFUnitsLabel3;
    private javax.swing.JLabel PFUnitsLabel4;
    private javax.swing.JLabel PFUnitsLabel5;
    private javax.swing.JLabel PFUnitsLabel6;
    private javax.swing.JLabel PFUnitsLabel7;
    private javax.swing.JLabel PFUnitsLabel8;
    private javax.swing.JLabel PFYearLevelLabel;
    private javax.swing.JTextField SDAddressField;
    private javax.swing.JTextField SDAgeField;
    private javax.swing.JTextField SDCitizenshipField;
    private javax.swing.JTextField SDFatherContactField;
    private javax.swing.JTextField SDFatherNameField;
    private javax.swing.JTextField SDFirstNameField;
    private javax.swing.JTextField SDGenderField;
    private javax.swing.JTextField SDLastNameField;
    private javax.swing.JTextField SDMaritalStatusField;
    private javax.swing.JTextField SDMiddleNameField;
    private javax.swing.JTextField SDMotherContactField;
    private javax.swing.JTextField SDMotherNameField;
    private javax.swing.JTextField SDReligionField;
    private javax.swing.JTextField SDRemarksField;
    private javax.swing.JButton SDSearchButton;
    private javax.swing.JTextField SDStudentContactField;
    private javax.swing.JTextField SDStudentIDField;
    private javax.swing.JTextField SDStudentYearLevelField;
    private javax.swing.JTextField SEAddressField;
    private javax.swing.JComboBox<String> SEBirthDateBox;
    private javax.swing.JComboBox<String> SEBirthMonthBox;
    private javax.swing.JComboBox<String> SEBirthYearBox;
    private javax.swing.JComboBox<String> SECitizenshipBox;
    private javax.swing.JTextField SEContactNumberField;
    private javax.swing.JButton SEEnrollButton;
    private javax.swing.JTextField SEFatherContactField;
    private javax.swing.JTextField SEFatherNameField;
    private javax.swing.JTextField SEFirstNameField;
    private javax.swing.JComboBox<String> SEGenderBox;
    private javax.swing.JTextField SELastNameField;
    private javax.swing.JComboBox<String> SEMaritalStatusField;
    private javax.swing.JTextField SEMiddleNameField;
    private javax.swing.JTextField SEMotherContactField;
    private javax.swing.JTextField SEMotherNameField;
    private javax.swing.JComboBox<String> SEReligionBox;
    private javax.swing.JPanel addtionalInfoPanel;
    private javax.swing.JPanel adminOnlyButton;
    private javax.swing.JLabel adminOnlyButtonLabel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel bottomPanel1;
    private javax.swing.JPanel bottomPanel2;
    private javax.swing.JPanel bottomPanel3;
    private javax.swing.JPanel bottomPanel4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel courseEnrollmentPanel;
    private javax.swing.JPanel courseNewButton;
    private javax.swing.JLabel courseNewButtonLabel;
    private javax.swing.JPanel enrollmentNewButton;
    private javax.swing.JLabel enrollmentNewButtonLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel masterlistNewButton;
    private javax.swing.JLabel masterlistNewButtonLabel;
    private javax.swing.JPanel masterlistPanel;
    private javax.swing.JPanel parentsInfoPanel;
    private javax.swing.JPanel personalInfoPanel;
    private javax.swing.JPanel printFormPanel;
    private javax.swing.JPanel registerEmployeePanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel searchPanel2;
    private javax.swing.JPanel searchPanel3;
    private javax.swing.JPanel searchPanel4;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel studentDetailsNewButton;
    private javax.swing.JLabel studentDetailsNewButtonLabel;
    private javax.swing.JPanel studentDetailsPanel;
    private javax.swing.JTabbedPane studentDetailsTabbedPane;
    private javax.swing.JPanel studentEnrollmentPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;

        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }
}
