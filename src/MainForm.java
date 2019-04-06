import helpers.EmployeParser;
import helpers.SqliteWrapper;
import model.Department;
import model.Employee;
import model.FieldTypes;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainForm {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private int clickedTimes = 0;
    private JTable table;
    private JTextField txtValueDel;
    private JTextField textFieldTblName;
    private JTextField textFieldColumnName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainForm() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 646, 443);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(0, 0, 640, 393);
        frame.getContentPane().add(tabbedPane);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("View", null, panel_2, null);
        panel_2.setLayout(null);

        table = new JTable();
        table.setBounds(10, 338, 499, -277);
        panel_2.add(table);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.setBounds(10, 11, 89, 23);
        panel_2.add(btnDisplay);

        JScrollPane scrollPane_2 = new JScrollPane();
        tabbedPane.addTab("Insert", null, scrollPane_2, null);

        JPanel panel_1 = new JPanel();
        scrollPane_2.setViewportView(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setBounds(10, 11, 67, 14);
        panel_1.add(lblNewLabel);

        JTextPane textPaneName = new JTextPane();
        textPaneName.setBounds(89, 11, 67, 20);
        panel_1.add(textPaneName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(10, 36, 46, 14);
        panel_1.add(lblAge);

        JTextPane textPaneAge = new JTextPane();
        textPaneAge.setBounds(89, 36, 67, 20);
        panel_1.add(textPaneAge);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(10, 66, 46, 14);
        panel_1.add(lblSalary);

        JTextPane textPaneSalary = new JTextPane();
        textPaneSalary.setBounds(89, 60, 67, 20);
        panel_1.add(textPaneSalary);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                if (LabelDataValidation(lblNewLabel, textPaneName.getText())) return;
                if (LabelDataValidation(lblAge, textPaneAge.getText())) return;
                if (LabelDataValidation(lblSalary, textPaneSalary.getText())) return;


                sqliteWrapper.insert(textPaneName.getText(),
                        Integer.parseInt(textPaneAge.getText()),
                        Integer.parseInt(textPaneSalary.getText()));


            }

            private boolean LabelDataValidation(JLabel label, String text) {
                if (text.isEmpty()) {
                    label.setForeground(Color.RED);
                    return true;
                } else {
                    label.setForeground(Color.BLACK);
                }
                return false;
            }
        });

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(20, 138, 603, 203);
        panel_1.add(scrollPane_3);

        JTextArea textAreaQueryResult = new JTextArea();
        scrollPane_3.setViewportView(textAreaQueryResult);
        btnInsert.setBounds(10, 104, 89, 23);
        panel_1.add(btnInsert);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                textAreaQueryResult.setText(sqliteWrapper.selectAll());
            }
        });
        btnView.setBounds(109, 104, 89, 23);
        panel_1.add(btnView);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Delete", null, panel_3, null);
        panel_3.setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "Age", "Salary"}));
        comboBox.setBounds(130, 11, 89, 20);
        panel_3.add(comboBox);

        JLabel lblDeleteCriteria = new JLabel("Delete criteria");
        lblDeleteCriteria.setBounds(10, 14, 110, 14);
        panel_3.add(lblDeleteCriteria);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                sqliteWrapper.deleteQuery(comboBox.getSelectedItem().toString(), txtValueDel.getText());
            }
        });

        txtValueDel = new JTextField();
        txtValueDel.setText("value");
        txtValueDel.setBounds(229, 11, 101, 20);
        panel_3.add(txtValueDel);
        txtValueDel.setColumns(10);
        btnDelete.setBounds(10, 45, 89, 23);
        panel_3.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane();
        tabbedPane.addTab("Old", null, scrollPane, null);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(121, 12, 86, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(121, 43, 86, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 74, 414, 127);
        panel.add(scrollPane_1);

        JEditorPane editorPane = new JEditorPane();
        scrollPane_1.setViewportView(editorPane);
        editorPane.setEditable(false);
        editorPane.setAutoscrolls(true);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(245, 11, 89, 23);
        panel.add(btnNewButton_1);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(10, 11, 89, 23);
        panel.add(btnNewButton);

        JPanel panel_4 = new JPanel();
        tabbedPane.addTab("Free form", null, panel_4, null);
        panel_4.setLayout(null);

        JPanel panel_5 = new JPanel();
        tabbedPane.addTab("Create tables", null, panel_5, null);
        panel_5.setLayout(null);

        JLabel lblTableName = new JLabel("Table name");
        lblTableName.setBounds(10, 11, 78, 14);
        panel_5.add(lblTableName);

        textFieldTblName = new JTextField();
        textFieldTblName.setBounds(98, 8, 86, 20);
        panel_5.add(textFieldTblName);
        textFieldTblName.setColumns(10);

        JLabel lblColumn = new JLabel("Column");
        lblColumn.setBounds(10, 36, 78, 14);
        panel_5.add(lblColumn);

        textFieldColumnName = new JTextField();
        textFieldColumnName.setBounds(98, 33, 86, 20);
        panel_5.add(textFieldColumnName);
        textFieldColumnName.setColumns(10);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(194, 36, 89, 14);
        panel_5.add(lblType);

        JCheckBox chckbxPrimaryKey = new JCheckBox("Primary key");
        chckbxPrimaryKey.setBounds(388, 32, 117, 23);
        panel_5.add(chckbxPrimaryKey);

        JCheckBox chckbxAutoIncrement = new JCheckBox("Auto increment");
        chckbxAutoIncrement.setBounds(388, 58, 117, 23);
        panel_5.add(chckbxAutoIncrement);

        JCheckBox chckbxNotNull = new JCheckBox("Not null");
        chckbxNotNull.setBounds(388, 84, 117, 23);
        panel_5.add(chckbxNotNull);

        JCheckBox chckbxUnique = new JCheckBox("Unique");
        chckbxUnique.setBounds(388, 110, 117, 23);
        panel_5.add(chckbxUnique);

        JButton btnAddColumn = new JButton("Add");
        btnAddColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JComboBox comboBoxType = new JComboBox();
        comboBoxType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
            }
        });
        comboBoxType.setModel(new DefaultComboBoxModel(FieldTypes.values()));
        comboBoxType.setBounds(293, 33, 89, 20);
        panel_5.add(comboBoxType);

        JTextArea textAreaQueryTableCreation = new JTextArea();
        textAreaQueryTableCreation.setBounds(10, 140, 615, 179);
        panel_5.add(textAreaQueryTableCreation);
        btnAdd.setBounds(194, 7, 89, 23);
        panel_5.add(btnAdd);
        btnAddColumn.setBounds(522, 32, 89, 23);
        panel_5.add(btnAddColumn);

        JButton btnCreateTable = new JButton("Create table");
        btnCreateTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnCreateTable.setBounds(10, 330, 615, 23);
        panel_5.add(btnCreateTable);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                List<Employee> employeList = sqliteWrapper.selectAllAsEmployees();
                List<Department> departmentList = sqliteWrapper.selectAllDepartents();
                HashMap<Integer, Integer> mappingEmplDept = sqliteWrapper.getAllDepartmentsEmployeesMappings();
                String text = "";
                for (Employee employee : employeList
                ) {
                    if(mappingEmplDept.containsKey(Integer.parseInt(employee.getId()))) {
                        text += employee.getName() + " works in ";
                        for (Department dep:departmentList
                        ) {
                            if(dep.getId() ==
                                    mappingEmplDept.get(Integer.parseInt(employee.getId()))) {
                                text += dep.getName() + "\n";
                                break;
                            }

                        }
                    }

                    else {
                        //employee not in department

                    }
                }
                editorPane.setText(text);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmSettings = new JMenuItem("Settings");
        mnFile.add(mntmSettings);




    }
}

