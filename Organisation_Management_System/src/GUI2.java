import Organisation_Management_System_Entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI2 {
    private JPanel panel1;
    private JButton button1 = new JButton("Back");

    public GUI2(){
    }

    public void action1(List<Project> projectList){
        JFrame window = new JFrame();
        window.setSize(450,450);
        Integer contor = 1;
        StringBuilder sbBIG = new StringBuilder();
        for(Project p : projectList) {
            StringBuilder sbIntro = new StringBuilder("\n" + "Date referitoare proiectului: " + contor.toString() + "\n");
            StringBuilder sbName = new StringBuilder("Nume: " + p.getName() + "\n");
            StringBuilder sbIncome = new StringBuilder("Income: " + p.getIncome().toString() + "\n");
            StringBuilder sb = new StringBuilder("Avem urmatorii employees: " + "\n");

            sbBIG.append(sbIntro);
            sbBIG.append(sbName);
            sbBIG.append(sbIncome);
            sbBIG.append(sb);
            for (String employee : p.getEmployeeList()) {
                //System.out.println(employee);
                StringBuilder sbEmp = new StringBuilder("    " + employee + "\n");
                sbBIG.append(sbEmp);
            }
            contor++;
        }
        button1.setBounds(250,200,120,35);
        window.add(button1);
        TextArea textArea = new TextArea(sbBIG.toString());
        textArea.setBounds(10,10,300,300);
        window.add(textArea);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }

    public void action2(List<String> listDep){
        JFrame window = new JFrame();
        window.setSize(450,450);
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "Departamentele cele mai solicitate sunt: " + "\n");
        for(String d : listDep){
            sb.append("     " + d + "\n");
        }
        button1.setBounds(250,200,120,35);
        window.add(button1);
        TextArea textArea = new TextArea(sb.toString());
        textArea.setBounds(10,10,300,300);
        window.add(textArea);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }

    public void action3(Organisation organisation){
        JFrame window = new JFrame();
        window.setSize(450,450);
        StringBuilder sb = new StringBuilder();
        for(Branch b : organisation.getBranchList()){
            sb.append("\n" + "In sucursala " + b.getLocation().getCountry() + " avem urmatoarea situatie: " + "\n");
            //System.out.println("In sucursala " + b.getLocation().getCountry() + "avem urmatoarea situatie: ");
            for(Department d : b.getDepartmentsList()){
                sb.append("     In departamentul " + d.getDenumire() + "\n");
                for(Employee e : d.getEmployeeList()){
                    String busy = new String();
                    if(e.getTask() == 0)
                        busy = " nu e ocupat";
                    else
                        busy = " e ocupat";
                    sb.append("          " + e.getName() + busy + "\n");
                    //System.out.println(e.getName() + busy);
                }
            }
        }
        button1.setBounds(250,200,120,35);
        window.add(button1);
        TextArea textArea = new TextArea(sb.toString());
        textArea.setBounds(10,10,300,300);
        window.add(textArea);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }

    public void action4(List<String> strings){
        JFrame window = new JFrame();
        window.setSize(450,450);
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "Tarile cu cele mai profitabile afaceri sunt: " + "\n");

        for(String s : strings){
            sb.append("    " + s + "\n");
        }

        button1.setBounds(250,200,120,35);
        window.add(button1);
        TextArea textArea = new TextArea(sb.toString());
        textArea.setBounds(10,10,300,300);
        window.add(textArea);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }

    public void action5(String output){
        JFrame window = new JFrame();
        window.setSize(450,450);
        button1.setBounds(250,200,120,35);
        window.add(button1);
        TextArea textArea = new TextArea(output.toString());
        textArea.setBounds(10,10,300,300);
        window.add(textArea);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.setVisible(true);
    }
}


