import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Organisation_Management_System_Services.*;
import Organisation_Management_System_Entities.*;
import Organisation_Management_System_Exceptions.*;
import javafx.scene.control.Alert;

public class GUI extends JFrame{

    private JPanel rootPanel;
    private JPanel upPanel;
    private JPanel leftPanel;
    private JButton button5Button;
    private JButton button4Button;
    private JButton button3Button;
    private JButton button2Button;
    private JButton button1Button;
    private JTextArea PENTRUAREALIZAACTIUNEATextArea;
    private JTextArea PENTRUOPTIUNILE2SITextArea;
    private JTextField textField1;
    private String input;

    public GUI(auditService auditService, OrganisationService organisationService, branchesServiceDB branchesDB, employeesServiceDB employeesDB, projectsServiceDB projectsDB, organisationsServiceDB organisationsDB, Connection connection){
        add(rootPanel);
        setTitle("ORGANISATION MANAGEMENT SYSTEM");
        setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.blue);
        //PENTRUAREALIZAACTIUNEATextArea.setBackground(Color.blue);
        //PENTRUOPTIUNILE2SITextArea.setBackground(Color.blue);

        button1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<Project> projectList = new ArrayList<>();
                try {
                    projectList = organisationService.getProjectsGUI(auditService);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI2 gui2 = new GUI2();
                gui2.action1(projectList);
            }
        });

        /*ENTERButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                input = textField1.getText();
            }
        });*/

        button2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println(input);
                input = textField1.getText();
                if(input.equals("Toate")){
                    List<String> listDep = new ArrayList<>();
                    try {
                        listDep = organisationService.getBusiestDepGUIv1(auditService);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GUI2 gui2 = new GUI2();
                    gui2.action2(listDep);
                }
                else{
                    List<String> listDep = new ArrayList<>();
                    try {
                        listDep = organisationService.getBusiesDepGUIV2(input, auditService);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GUI2 gui2 = new GUI2();
                    gui2.action2(listDep);
                }
            }
        });

        button3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                input = textField1.getText();
                //System.out.println(input);
                Organisation organisation = null;
                try {
                    organisation = organisationService.getEmployeesGUI(input, auditService);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI2 gui2 = new GUI2();
                gui2.action3(organisation);
            }
        });

        button4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<String> stringList = new ArrayList<>();
                try {
                    stringList = organisationService.getCountryGUI(auditService);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI2 gui2 = new GUI2();
                gui2.action4(stringList);
            }
        });

        button5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                organisationService.updateSituation(organisationsDB, branchesDB, employeesDB, projectsDB, connection);
                String output = organisationService.getOutput();
                //System.out.println(output);
                if(output.contains("finalizat")){
                    GUI2 gui2 = new GUI2();
                    gui2.action5(organisationService.getOutput());
                }
            }
        });
    }

}
