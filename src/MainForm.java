import helpers.SqliteWrapper;
import model.Department;
import model.Employee;
import model.FieldTypes;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
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

        JTextArea textAreaQueryTableCreation = new JTextArea();
        textAreaQueryTableCreation.setBounds(10, 140, 615, 179);
        panel_5.add(textAreaQueryTableCreation);


        List<String> myList = new ArrayList<>();

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(194, 7, 89, 23);
        panel_5.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "";

                text = "CREATE TABLE IF NOT EXISTS " + "\"" + textFieldTblName.getText() + "\"" + " ";
                myList.add(text);

                textAreaQueryTableCreation.setText(text);
                btnAdd.setEnabled(false);
                textFieldTblName.setEnabled(false);
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

        JButton btnAddColumn = new JButton("Add");
        btnAddColumn.setBounds(522, 32, 89, 23);
        panel_5.add(btnAddColumn);
        btnAddColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String text = "";
                String toReturn = "";
                String getComboText = "";

                if (chckbxPrimaryKey.isSelected()) {
                    chckbxAutoIncrement.setSelected(true);
                    chckbxAutoIncrement.setEnabled(false);
                    //getComboText = chckbxPrimaryKey.getText() +" " +chckbxAutoIncrement.getText();
                    getComboText += "PRIMARY KEY" + " " + "AUTOINCREMENT";
                    chckbxPrimaryKey.setEnabled(false);
                    chckbxPrimaryKey.setSelected(false);
                    chckbxAutoIncrement.setSelected(false);

                }
                if (chckbxNotNull.isSelected()) {
                    getComboText = "NOT NULL";
                    chckbxNotNull.setSelected(false);

                } else {
                    getComboText = "NULL";
                    chckbxNotNull.setSelected(false);

                }
                if (chckbxUnique.isSelected()) {
                    chckbxNotNull.setSelected(true);
                    getComboText = "UNIQUE NOT NULL";
                    chckbxUnique.setSelected(false);
                    chckbxNotNull.setSelected(false);
                }

                text = textFieldColumnName.getText() + " " + comboBoxType.getSelectedItem() + " " + getComboText;
                myList.add(text);
                int count = 0;
                for (String s : myList) {
                    if (count == 1) toReturn += "\n( ";
                    else if (count > 0 && count < myList.size()) toReturn += ",\n ";

                    count++;
                    toReturn += s;

                }
                toReturn += ");";


                textAreaQueryTableCreation.setText(toReturn);
            }
        });


        JButton btnCreateTable = new JButton("Create table");
        btnCreateTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                sqliteWrapper.createTable(textAreaQueryTableCreation.getText());
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

        JPanel panelJoin = new JPanel();
        tabbedPane.addTab("Join", null, panelJoin, null);
        panelJoin.setLayout(null);

        JComboBox comboBoxJoinTable1 = new JComboBox();

        comboBoxJoinTable1.setBounds(10, 160, 106, 20);
        panelJoin.add(comboBoxJoinTable1);

        JComboBox comboBoxJoinTable2 = new JComboBox();
        comboBoxJoinTable2.setBounds(205, 160, 127, 20);
        panelJoin.add(comboBoxJoinTable2);

        JComboBox comboBoxTable1 = new JComboBox();
        comboBoxTable1.setBounds(10, 73, 106, 20);
        comboBoxTable1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                comboBoxJoinTable1.removeAllItems();

                for (String column : sqliteWrapper.getAllColumns(
                        (String)comboBoxTable1.getSelectedItem())
                     ) {
                    comboBoxJoinTable1.addItem(column);

                }
            }
        });
        panelJoin.add(comboBoxTable1);

        JComboBox comboBoxTable2 = new JComboBox();
        comboBoxTable2.setBounds(205, 73, 122, 20);
        panelJoin.add(comboBoxTable2);

        JLabel lblTable = new JLabel("Table1");
        lblTable.setBounds(10, 48, 106, 14);
        panelJoin.add(lblTable);

        JLabel lblTable_1 = new JLabel("Table2");
        lblTable_1.setBounds(205, 48, 46, 14);
        panelJoin.add(lblTable_1);

        JButton btnGettables = new JButton("Get Tables");
        btnGettables.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqliteWrapper sqliteWrapper = new SqliteWrapper();
                List<String> tableList = sqliteWrapper.getAllTables();
                for (String table: tableList
                     ) {
                    comboBoxTable1.addItem(table);
                    comboBoxTable2.addItem(table);
                }

            }
        });
        btnGettables.setToolTipText("Click button to read all tables");
        btnGettables.setBounds(10, 14, 106, 23);
        panelJoin.add(btnGettables);

        JLabel lblJoinOn = new JLabel("Join on");
        lblJoinOn.setBounds(20, 104, 46, 14);
        panelJoin.add(lblJoinOn);

        JComboBox comboBoxJoinType = new JComboBox();
        comboBoxJoinType.setBounds(205, 104, 122, 20);
        panelJoin.add(comboBoxJoinType);

        JLabel lblTable_2 = new JLabel("Table2");
        lblTable_2.setBounds(205, 135, 46, 14);
        panelJoin.add(lblTable_2);

        JLabel lblTable_3 = new JLabel("Table1");
        lblTable_3.setBounds(10, 135, 46, 14);
        panelJoin.add(lblTable_3);



        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(10, 191, 615, 130);
        panelJoin.add(scrollPane_4);

        JTextArea textAreaJoinResult = new JTextArea();
        scrollPane_4.setViewportView(textAreaJoinResult);

        JButton btnCreateQuery = new JButton("Create query");
        btnCreateQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnCreateQuery.setBounds(10, 332, 106, 23);
        panelJoin.add(btnCreateQuery);

        JButton btnExecuteQuery = new JButton("Execute query");
        btnExecuteQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnExecuteQuery.setBounds(126, 332, 106, 23);
        panelJoin.add(btnExecuteQuery);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



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

