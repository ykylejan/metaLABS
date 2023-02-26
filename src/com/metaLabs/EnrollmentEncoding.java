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
import java.awt.RenderingHints;
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
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class EnrollmentEncoding extends javax.swing.JFrame {

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
                Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\MetaLabs\\src\\com\\database\\metalabsDatabase.db");
                PreparedStatement PS = connection.prepareStatement("INSERT INTO `student_enrollment`(`studentFirstName`, `studentMiddleName`, `studentLastName`, `studentBirthDate`, `studentBirthMonth`, `studentBirthYear`, `studentAddress`, `studentGender`, `studentCitizenship`, `studentContactNumber`, `studentMarital`, `studentReligion`, `studentMotherName`, `studentMotherContact`, `studentFatherName`, `studentFatherContact`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                PS.setString(1, studentFirstName);
                PS.setString(2, studentMiddleName);
                PS.setString(3, studentLastName);
                PS.setInt(4, Integer.parseInt(studentBirthDate));
                PS.setString(5, studentBirthMonth);
                PS.setInt(6, Integer.parseInt(studentBirthYear));
                PS.setString(7, studentAddress);
                PS.setString(8, studentGender);
                PS.setString(9, studentCitizenship);
                PS.setLong(10, studentContactNumber);
                PS.setString(11, studentMaritalStatus);
                PS.setString(12, studentReligion);
                PS.setString(13, studentMotherName);
                PS.setLong(14, studentMotherContact);
                PS.setString(15, studentFatherName);
                PS.setLong(16, studentFatherContact);

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

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    void Add_Courses_Action() {
        String courseSubjectCode = CESubjectCodeField.getText();
        String courseSubject = CESubjectBox.getSelectedItem().toString();
        double courseUnits = Double.parseDouble(CEUnitsField.getText());
        String courseTime = CETimeDateBox.getSelectedItem().toString();
        String courseRoom = CERoomBox.getSelectedItem().toString();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\MetaLabs\\src\\com\\database\\metalabsDatabase.db");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `course_enrollment`(`courseCode`, `courseTitle`, `courseUnits`, `courseTime`, `courseRoom`) VALUES(?,?,?,?,?)");
            
            ps.setString(1, courseSubjectCode);
            ps.setString(2, courseSubject);
            ps.setDouble(3, courseUnits);
            ps.setString(4, courseTime);
            ps.setString(5, courseRoom);
            
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
    }
    
    void Update_Courses_Table() {
        int columnCount;
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\MetaLabs\\src\\com\\database\\metalabsDatabase.db");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `course_enrollment`");
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            DefaultTableModel tableModel = (DefaultTableModel) CECourseListTable.getModel();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                Vector vector = new Vector();

                for (int i = 0; i <= columnCount; i++) {
                    vector.add(rs.getString("courseCode"));
                    vector.add(rs.getString("courseTitle"));
                    vector.add(rs.getInt("courseUnits"));
                    vector.add(rs.getString("courseTime"));
                    vector.add(rs.getString("courseRoom"));
                }
                tableModel.addRow(vector);
                tableModel.fireTableDataChanged();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }
    
    void Masterlist_Table() {
        int columnCount;
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\MetaLabs\\src\\com\\database\\metalabsDatabase.db");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `student_enrollment`");
            
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            DefaultTableModel tableModel = (DefaultTableModel) MLMasterlistTable.getModel();
            tableModel.setRowCount(0);
            
            while (rs.next()) {
                Vector vector = new Vector();

                for (int i = 0; i <= columnCount; i++) {
                    vector.add(rs.getInt("studentID"));
                    vector.add(rs.getString("studentLastName"));
                    vector.add(rs.getString("studentFirstName"));
                    vector.add(rs.getString("studentMiddleName"));
                    vector.add(null);
                    vector.add(null);
                    vector.add(null);
                }
                tableModel.addRow(vector);
                tableModel.fireTableDataChanged();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
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
        jPanel1 = new javax.swing.JPanel();
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
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        courseEnrollmentPanel = new javax.swing.JPanel();
        jPanel13 = new RoundedPanel(50, new Color(55,111,138));
        jLabel101 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jComboBox31 = new javax.swing.JComboBox<>();
        jLabel108 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox<>();
        jLabel109 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        enrollButton2 = new javax.swing.JButton();
        printPreviewButton2 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        CECourseListTable = new javax.swing.JTable();
        bottomPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchPanel2 = new RoundedPanel(30, new Color(55,111,138));
        jLabel88 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
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
        jTextField12 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        studentDetailsPanel = new javax.swing.JPanel();
        bottomPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        searchPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        studentDetailsTabbedPane = new javax.swing.JTabbedPane();
        personalInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        jTextField3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        parentsInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel75 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        addtionalInfoPanel = new RoundedPanel(50, new Color(55,111,138));
        jLabel79 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jLabel26.setText("Student No:");

        jLabel27.setForeground(new java.awt.Color(1, 1, 1));
        jLabel27.setText("Name:");

        jLabel28.setForeground(new java.awt.Color(1, 1, 1));
        jLabel28.setText("Year Level:");

        jLabel29.setForeground(new java.awt.Color(1, 1, 1));
        jLabel29.setText("564321");

        jLabel30.setForeground(new java.awt.Color(1, 1, 1));
        jLabel30.setText("Example D. Name");

        jLabel31.setForeground(new java.awt.Color(1, 1, 1));
        jLabel31.setText("Year 1");

        jLabel32.setForeground(new java.awt.Color(1, 1, 1));
        jLabel32.setText("Academic Year:");

        jLabel33.setForeground(new java.awt.Color(1, 1, 1));
        jLabel33.setText("2022-2023");

        jLabel34.setForeground(new java.awt.Color(1, 1, 1));
        jLabel34.setText("Semester:");

        jLabel35.setForeground(new java.awt.Color(1, 1, 1));
        jLabel35.setText("2nd Semester");

        jLabel36.setForeground(new java.awt.Color(1, 1, 1));
        jLabel36.setText("Term:");

        jLabel37.setForeground(new java.awt.Color(1, 1, 1));
        jLabel37.setText("First Term");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jLabel33))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel37))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel31)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(243, 242, 242));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 102, 255));
        jLabel38.setText("ABC129717070063");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(243, 242, 242));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel39.setForeground(new java.awt.Color(1, 1, 1));
        jLabel39.setText("Unit(s)");

        jLabel40.setForeground(new java.awt.Color(1, 1, 1));
        jLabel40.setText("Description");

        jLabel41.setForeground(new java.awt.Color(1, 1, 1));
        jLabel41.setText("Code");

        jLabel42.setForeground(new java.awt.Color(1, 1, 1));
        jLabel42.setText("Time and Days Sched ");

        jLabel43.setForeground(new java.awt.Color(1, 1, 1));
        jLabel43.setText("Sem./Term");

        jLabel44.setForeground(new java.awt.Color(1, 1, 1));
        jLabel44.setText("966");

        jLabel45.setForeground(new java.awt.Color(1, 1, 1));
        jLabel45.setText("Title");

        jLabel46.setForeground(new java.awt.Color(1, 1, 1));
        jLabel46.setText("NSTP 1");

        jLabel47.setForeground(new java.awt.Color(1, 1, 1));
        jLabel47.setText("NATIONAL SERVICE TRAINING PROGRAM 1");

        jLabel48.setForeground(new java.awt.Color(1, 1, 1));
        jLabel48.setText("3.0");

        jLabel49.setForeground(new java.awt.Color(1, 1, 1));
        jLabel49.setText("Sem");

        jLabel50.setForeground(new java.awt.Color(1, 1, 1));
        jLabel50.setText("8:00M-11:00M Sa");

        jLabel51.setForeground(new java.awt.Color(1, 1, 1));
        jLabel51.setText("965");

        jLabel52.setForeground(new java.awt.Color(1, 1, 1));
        jLabel52.setText("GPE 1");

        jLabel53.setForeground(new java.awt.Color(1, 1, 1));
        jLabel53.setText("MOVEMENT ENHANCEMENT");

        jLabel54.setForeground(new java.awt.Color(1, 1, 1));
        jLabel54.setText("2.0");

        jLabel55.setForeground(new java.awt.Color(1, 1, 1));
        jLabel55.setText("Sem");

        jLabel56.setForeground(new java.awt.Color(1, 1, 1));
        jLabel56.setText("10:00M-12:00A F");

        jLabel57.setForeground(new java.awt.Color(1, 1, 1));
        jLabel57.setText("961");

        jLabel58.setForeground(new java.awt.Color(1, 1, 1));
        jLabel58.setText("GE 2");

        jLabel59.setForeground(new java.awt.Color(1, 1, 1));
        jLabel59.setText("PURPOSIVE COMMUNICATION ");

        jLabel60.setForeground(new java.awt.Color(1, 1, 1));
        jLabel60.setText("6.0");

        jLabel61.setForeground(new java.awt.Color(1, 1, 1));
        jLabel61.setText("1st Term");

        jLabel62.setForeground(new java.awt.Color(1, 1, 1));
        jLabel62.setText("10:00M-12:00A F");

        jLabel63.setForeground(new java.awt.Color(1, 1, 1));
        jLabel63.setText("Room");

        jLabel64.setForeground(new java.awt.Color(1, 1, 1));
        jLabel64.setText("-");

        jLabel65.setForeground(new java.awt.Color(1, 1, 1));
        jLabel65.setText("MAT");

        jLabel66.setForeground(new java.awt.Color(1, 1, 1));
        jLabel66.setText("TEC206");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel63))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel48)
                                .addComponent(jLabel49)
                                .addComponent(jLabel50))
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel66)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59))))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jLabel68.setForeground(new java.awt.Color(1, 1, 1));
        jLabel68.setText("Date Printed:");

        jLabel69.setForeground(new java.awt.Color(1, 1, 1));
        jLabel69.setText("12/02/2023");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69))
                .addGap(93, 93, 93))
        );

        javax.swing.GroupLayout printFormPanelLayout = new javax.swing.GroupLayout(printFormPanel);
        printFormPanel.setLayout(printFormPanelLayout);
        printFormPanelLayout.setHorizontalGroup(
            printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printFormPanelLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(523, Short.MAX_VALUE))
        );
        printFormPanelLayout.setVerticalGroup(
            printFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printFormPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(printFormPanel, "card5");

        courseEnrollmentPanel.setBackground(new java.awt.Color(31, 48, 56));

        jPanel13.setBackground(new java.awt.Color(31, 48, 56));
        jPanel13.setPreferredSize(new java.awt.Dimension(1060, 221));

        jLabel101.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("College Department");

        jComboBox30.setBackground(new java.awt.Color(98, 161, 192));
        jComboBox30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jComboBox30.setForeground(new java.awt.Color(204, 255, 255));
        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "PS - Professional Schools", "CLE - College of Legal Education", "CAE - College of Accounting Education", "CAFAE - College of Architecture and Fine Arts Education", "CASE - College of Arts and Sciences Education", "CBAE - College of Business Administration Education", "CCE - College of Computing Education", "CCJE - College of Criminal Justice Education", "CEE - College of Engineering Education", "CHE - College of Hospitality Education", "CHSE - College of Health Science Education", "CTE - College of Teacher Education", "TS - Technical School", "BED - Basic Education" }));
        jComboBox30.setFocusable(false);
        jComboBox30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox30ActionPerformed(evt);
            }
        });

        jComboBox31.setBackground(new java.awt.Color(98, 161, 192));
        jComboBox31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jComboBox31.setForeground(new java.awt.Color(204, 255, 255));
        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox31.setFocusable(false);

        jLabel108.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Course Major");

        jComboBox32.setBackground(new java.awt.Color(98, 161, 192));
        jComboBox32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jComboBox32.setForeground(new java.awt.Color(204, 255, 255));
        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Year 1", "Year 2", "Year 3", "Year 4", "Year 5" }));
        jComboBox32.setFocusable(false);

        jLabel109.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("Year Level");

        jComboBox33.setBackground(new java.awt.Color(98, 161, 192));
        jComboBox33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jComboBox33.setForeground(new java.awt.Color(204, 255, 255));
        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1ST Sem", "2ND Sem" }));
        jComboBox33.setFocusable(false);

        jLabel110.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("Semester");

        jLabel111.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Term");

        jComboBox34.setBackground(new java.awt.Color(98, 161, 192));
        jComboBox34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jComboBox34.setForeground(new java.awt.Color(204, 255, 255));
        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1ST Term", "2ND Term" }));
        jComboBox34.setFocusable(false);

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
                        .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("COURSE(S) LIST AND SCHEDULE ");

        enrollButton2.setText("Enroll");
        enrollButton2.setFocusable(false);
        enrollButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollButton2ActionPerformed(evt);
            }
        });

        printPreviewButton2.setText("Print Preview");
        printPreviewButton2.setFocusable(false);
        printPreviewButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPreviewButton2ActionPerformed(evt);
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

        jTextField27.setBackground(new java.awt.Color(98, 161, 192));
        jTextField27.setForeground(new java.awt.Color(255, 255, 255));

        jTextField30.setBackground(new java.awt.Color(98, 161, 192));
        jTextField30.setDocument(new JTextFieldLimit(6));
        jTextField30.setForeground(new java.awt.Color(255, 255, 255));

        jLabel89.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Student ID");

        javax.swing.GroupLayout searchPanel2Layout = new javax.swing.GroupLayout(searchPanel2);
        searchPanel2.setLayout(searchPanel2Layout);
        searchPanel2Layout.setHorizontalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        searchPanel2Layout.setVerticalGroup(
            searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField30)
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField27)
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
        CESubjectBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Understandin the Self", "Readings in Philippine History", "The Contemporary World", "Mathematics in the Modern World", "Purposive Communication", "Art Appreciation", "Science, Technology and Society", "Ethics", "Life and Works of Rizal", "G.E. Electives", "Introduction to Language Studies", "Cross Cultural Communication" }));
        CESubjectBox.setFocusable(false);

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
        CETimeDateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "700M-800M", "800M-900M", "900M-1000M", "1000M-1100M", "1100M-1200A", "1230A-130A", "130A-230A", "230A-330A", "330A-430A", "430A-530E", " " }));
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
                                    .addComponent(enrollButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(printPreviewButton2))
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(courseEnrollmentPanelLayout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)))))
                .addContainerGap(194, Short.MAX_VALUE))
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
                    .addComponent(enrollButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printPreviewButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(209, Short.MAX_VALUE))
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

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
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

        jTextField1.setBackground(new java.awt.Color(98, 161, 192));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));

        jTextField2.setBackground(new java.awt.Color(98, 161, 192));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Year Level");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField2)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );

        studentDetailsTabbedPane.setBackground(new java.awt.Color(55, 111, 138));
        studentDetailsTabbedPane.setForeground(new java.awt.Color(255, 255, 255));

        personalInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        jTextField3.setBackground(new java.awt.Color(98, 161, 192));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Last Name");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("First Name");

        jTextField4.setBackground(new java.awt.Color(98, 161, 192));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));

        jTextField5.setBackground(new java.awt.Color(98, 161, 192));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Middle Name");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Age");

        jTextField6.setBackground(new java.awt.Color(98, 161, 192));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Address");

        jTextField7.setBackground(new java.awt.Color(98, 161, 192));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Gender");

        jTextField9.setBackground(new java.awt.Color(98, 161, 192));
        jTextField9.setForeground(new java.awt.Color(255, 255, 255));

        jTextField10.setBackground(new java.awt.Color(98, 161, 192));
        jTextField10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel71.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Marital Status");

        jLabel72.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Citizenship");

        jTextField11.setBackground(new java.awt.Color(98, 161, 192));
        jTextField11.setForeground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Religion");

        jTextField13.setBackground(new java.awt.Color(98, 161, 192));
        jTextField13.setForeground(new java.awt.Color(255, 255, 255));

        jTextField14.setBackground(new java.awt.Color(98, 161, 192));
        jTextField14.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addComponent(jTextField5))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(144, 144, 144)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField14))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10))
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addGap(127, 127, 127))
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField14)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                .addGap(37, 37, 37))
                            .addComponent(jTextField7)))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11)
                            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13)
                            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(52, 52, 52))
        );

        studentDetailsTabbedPane.addTab("Personal Information", personalInfoPanel);

        parentsInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        jLabel75.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Mother's Name");

        jTextField15.setBackground(new java.awt.Color(98, 161, 192));
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));

        jLabel76.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Contact No");

        jTextField16.setBackground(new java.awt.Color(98, 161, 192));
        jTextField16.setForeground(new java.awt.Color(255, 255, 255));

        jLabel77.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Father's Name");

        jTextField17.setBackground(new java.awt.Color(98, 161, 192));
        jTextField17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel78.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Contact No");

        jTextField18.setBackground(new java.awt.Color(98, 161, 192));
        jTextField18.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField18))
                    .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                .addGap(139, 139, 139))
        );
        parentsInfoPanelLayout.setVerticalGroup(
            parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentsInfoPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField16)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(parentsInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField18)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(167, 167, 167))
        );

        studentDetailsTabbedPane.addTab("Parents Information", parentsInfoPanel);

        addtionalInfoPanel.setBackground(new java.awt.Color(31, 48, 56));

        jLabel79.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Remarks");

        jTextField19.setBackground(new java.awt.Color(98, 161, 192));
        jTextField19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout addtionalInfoPanelLayout = new javax.swing.GroupLayout(addtionalInfoPanel);
        addtionalInfoPanel.setLayout(addtionalInfoPanelLayout);
        addtionalInfoPanelLayout.setHorizontalGroup(
            addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addtionalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );
        addtionalInfoPanelLayout.setVerticalGroup(
            addtionalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addtionalInfoPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        studentDetailsTabbedPane.addTab("Additional Information", addtionalInfoPanel);

        jButton5.setText("Delete");

        jButton7.setText("Update");

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
                .addContainerGap(252, Short.MAX_VALUE))
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
                .addContainerGap(179, Short.MAX_VALUE))
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

    private void jComboBox30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox30ActionPerformed

    private void enrollButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enrollButton2ActionPerformed

    private void printPreviewButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPreviewButton2ActionPerformed
        ChangeCard(printFormPanel);
    }//GEN-LAST:event_printPreviewButton2ActionPerformed

    private void courseNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMouseEntered
        SidePanel_SetColor(courseNewButton, courseNewButtonLabel);
    }//GEN-LAST:event_courseNewButtonLabelMouseEntered

    private void courseNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMouseExited
        SidePanel_ResetColor(courseNewButton, courseNewButtonLabel);
    }//GEN-LAST:event_courseNewButtonLabelMouseExited

    private void courseNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseNewButtonLabelMousePressed
        ChangeCard(courseEnrollmentPanel);
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

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void studentDetailsNewButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMouseEntered
        SidePanel_SetColor(studentDetailsNewButton, studentDetailsNewButtonLabel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMouseEntered

    private void studentDetailsNewButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMouseExited
        SidePanel_ResetColor(studentDetailsNewButton, studentDetailsNewButtonLabel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMouseExited

    private void studentDetailsNewButtonLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDetailsNewButtonLabelMousePressed
        ChangeCard(studentDetailsPanel);
    }//GEN-LAST:event_studentDetailsNewButtonLabelMousePressed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_CECourseButtonActionPerformed

    private void CEDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEDeleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CEDeleteButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

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
    private javax.swing.JButton CECourseButton;
    private javax.swing.JTable CECourseListTable;
    private javax.swing.JButton CEDeleteButton;
    private javax.swing.JComboBox<String> CERoomBox;
    private javax.swing.JComboBox<String> CESubjectBox;
    private javax.swing.JTextField CESubjectCodeField;
    private javax.swing.JComboBox<String> CETimeDateBox;
    private javax.swing.JTextField CEUnitsField;
    private javax.swing.JTable MLMasterlistTable;
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
    private javax.swing.JButton enrollButton2;
    private javax.swing.JPanel enrollmentNewButton;
    private javax.swing.JLabel enrollmentNewButtonLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox31;
    private javax.swing.JComboBox<String> jComboBox32;
    private javax.swing.JComboBox<String> jComboBox33;
    private javax.swing.JComboBox<String> jComboBox34;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
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
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel masterlistNewButton;
    private javax.swing.JLabel masterlistNewButtonLabel;
    private javax.swing.JPanel masterlistPanel;
    private javax.swing.JPanel parentsInfoPanel;
    private javax.swing.JPanel personalInfoPanel;
    private javax.swing.JPanel printFormPanel;
    private javax.swing.JButton printPreviewButton2;
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
