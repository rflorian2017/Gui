import helpers.SqliteWrapper;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.ActionEvent;

public class MainForm {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private int clickedTimes = 0;
    private JTable table;
    private JTextField txtValueDel;

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
        frame.setBounds(100, 100, 646, 443);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(20, 11, 524, 382);
        frame.getContentPane().add(tabbedPane);

        JScrollPane scrollPane = new JScrollPane();
        tabbedPane.addTab("Old", null, scrollPane, null);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(10, 11, 89, 23);
        panel.add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(121, 12, 86, 20);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(121, 43, 86, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(245, 11, 89, 23);
        panel.add(btnNewButton_1);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 74, 414, 127);
        panel.add(scrollPane_1);

        JEditorPane editorPane = new JEditorPane();
        scrollPane_1.setViewportView(editorPane);
        editorPane.setEditable(false);
        editorPane.setAutoscrolls(true);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {



            }
        });

        JScrollPane scrollPane_2 = new JScrollPane();
        tabbedPane.addTab("Insert", null, scrollPane_2, null);

        JPanel panel_1 = new JPanel();
        scrollPane_2.setViewportView(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
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

//        JTextArea textAreaQueryResult = new JTextArea();
//        textAreaQueryResult.setBounds(10, 138, 497, 203);
//        textAreaQueryResult.setAutoscrolls(true);
//        panel_1.add(textAreaQueryResult);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(20, 138, 497, 203);
        panel_1.add(scrollPane_3);

        JTextArea textAreaQueryResult = new JTextArea();
        scrollPane_3.setViewportView(textAreaQueryResult);


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
                if(text.isEmpty()) {
                    label.setForeground(Color.RED);
                    return true;
                }
                else {
                    label.setForeground(Color.BLACK);
                }
                return false;
            }
        });
        btnInsert.setBounds(10, 104, 89, 23);
        panel_1.add(btnInsert);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("View", null, panel_2, null);
        panel_2.setLayout(null);

        table = new JTable();
        table.setBounds(10, 338, 499, -326);
        panel_2.add(table);

        JScrollPane scrollPaneTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);

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
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "age", "salary"}));
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
        btnDelete.setBounds(10, 45, 89, 23);
        panel_3.add(btnDelete);

        txtValueDel = new JTextField();
        txtValueDel.setText("value");
        txtValueDel.setBounds(229, 11, 101, 20);
        panel_3.add(txtValueDel);
        txtValueDel.setColumns(10);
    }
}

