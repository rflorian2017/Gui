import helpers.DepartmentParser;
import model.Company;
import model.Department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainForm {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private int clickedTimes = 0;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 11, 414, 127);
        frame.getContentPane().add(scrollPane_1);

        JEditorPane editorPane = new JEditorPane();
        scrollPane_1.setViewportView(editorPane);
        editorPane.setEditable(false);
        editorPane.setAutoscrolls(true);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                HashMap<String, ArrayList<String>> companies = new HashMap<String, ArrayList<String>>();
                File folder = new File("C:\\tmp\\companies\\company_javarullz");

                List<Department> departmentList  = new ArrayList<>();
                for(File file:folder.listFiles()) {
                    try {
                        departmentList.add(DepartmentParser.parseDepartmentFile(
                                file.getPath()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                for(String company: companies.keySet()) {
                    editorPane.setText(editorPane.getText() + "\n" + "Company: " + company + "has the employees:\n");
                    for(String employee_details: companies.get(company)) {
                        editorPane.setText(editorPane.getText() + "model.Employee id: " + employee_details.split(",")[0]
                                +"\nmodel.Employee name: " + employee_details.split(",")[1] +
                                "\nmodel.Employee age: " + employee_details.split(",")[2] +
                                "\nmodel.Employee salary: " + employee_details.split(",")[3] +"\n"
                        );
                    }
                }


            }
        });


        btnNewButton.setBounds(10, 149, 89, 23);
        frame.getContentPane().add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(136, 149, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(136, 180, 86, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickedTimes ++;
                if(clickedTimes < 5) {

                    if(clickedTimes < 4) {
                        if(clickedTimes == 2) {
                            JOptionPane.showMessageDialog(frame, "That tickles!");
                        }
                        else
                            JOptionPane.showMessageDialog(frame, "Ooo, go on now!");
                    }
                }
                else {
                    if(textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
                        return;
                    }
                    else {
                        try {
                            Helper.method("C:\\\\tmp\\\\companies\\\\company_javarullz", Integer.parseInt(textField.getText()), Integer.parseInt(textField_1.getText()));
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        btnNewButton_1.setBounds(244, 149, 89, 23);
        frame.getContentPane().add(btnNewButton_1);


    }
}

class Helper {
    public static void method(String str, int a, int b) throws IOException {
        new File(str).mkdirs();
        for(int i=0; i< a; i++ ) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(str + "\\dept" + i + ".txt"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                writer.write("id,name,age,salary\r\n");
                for(int j=0; j< b; j++) {
                    Random rand = new Random(100);

                    writer.write("id" + rand.nextInt() + ",name"+rand.nextInt() + "," + rand.nextInt() + "," + new Random(10000).nextInt()+"\r\n");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            writer.close();
        }

    }
}
