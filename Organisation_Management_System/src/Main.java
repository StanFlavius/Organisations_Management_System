import Organisation_Management_System_Entities.*;
import Organisation_Management_System_Entities.Time;
import Organisation_Management_System_Exceptions.ValoareaDepasitaException;
import Organisation_Management_System_Services.OrganisationService;
import Organisation_Management_System_Services.auditService;
import Organisation_Management_System_Services.dataOrganisations;
import Organisation_Management_System_Services.*;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static Statement statement = null;
    public static Connection connection;
    public static String url = "jdbc:sqlserver://flavius.database.windows.net:1433;database=FlaviusDB;user=flavius@flavius;password={Ff_123456};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

    public static void main(String[] args) throws ValoareaDepasitaException, IOException, SQLException, ClassNotFoundException {

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        auditService auditService = new auditService();

        //AVEM NEVOIE
        employeesServiceDB employeesDB = employeesServiceDB.getInstance();
        branchesServiceDB branchesDB = branchesServiceDB.getInstance();

        //IMPORTAM DATELE DESPRE IMPOZITE
        impozitTariServiceDB impoziteDB = impozitTariServiceDB.getInstance();
        Map<String, Double> data = impoziteDB.readData(connection);
        ImpozitTara impozitTara = new ImpozitTara();
        impozitTara.setHm(data);
        /*for(Map.Entry<String, Double> entry : data.entrySet()) {
            System.out.println(entry.getKey().toString() +  entry.getValue().toString());
        }*/

        //IMPORTAM DATELE DESPRE PROIECTELE DETINUTE
        projectService pService = projectService.getInstance();
        /*List<Project> projectList = new ArrayList<>();
        projectList = pService.getData();
        ALLProjects allProjects = new ALLProjects();
        allProjects.setAllProjects(projectList);
         */
        projectsServiceDB projectsDB = projectsServiceDB.getInstance();
        List<Project> projectList = new ArrayList<>();
        projectList = projectsDB.readData(connection);
        ALLProjects allProjects = new ALLProjects();
        allProjects.setAllProjects(projectList);
        /*Project p = new Project("Game1", 123000.0, 2);
        List<String>l = new ArrayList<>();
        l.add("Marco");
        l.add("Jonny");
        l.add("Alex");
        p.setEmployeeList(l);
        projectsDB.createData(connection,p);*/
        /*for(Project p : projectList){
            System.out.println(p.getName());
            System.out.println(p.getIncome());
            System.out.println(p.getTime());
            List<String> listEmp = p.getEmployeeList();
            for(String s : listEmp){
                System.out.println(s);
            }
            System.out.println();
        }

        //IMPORTAM DATELE DESPRE IMPOZITELE DIN DIFERITE TARI
        /*impozitTariService impoziteService = impozitTariService.getInstance();
        Map<String, Double> data = impoziteService.getData();*/ //o afisare

        ///IMPORTAM DATELE DESPRE ORGANIZATII
        dataOrganisations dataOrg = dataOrganisations.getInstance();

        organisationsServiceDB organisationsDB = organisationsServiceDB.getInstance();
        List<Organisation> organisationList = new ArrayList<>();
        organisationList = organisationsDB.readData(connection, branchesDB, projectsDB, employeesDB);
        OrganisationService organisationService = new OrganisationService();
        organisationService.setOrganisationList(organisationList);
        organisationService.setAllProjects(allProjects);

        //organisationService.displayOrganisations();

        //organisationService.addSomeData();

        Time time = new Time();

        GUI myGUI = new GUI(auditService, organisationService, branchesDB, employeesDB, projectsDB, organisationsDB, connection);
        myGUI.setVisible(true);

        /*String message = " ";
        while(true) {
            System.out.println("");
            System.out.println("Buna ziua");
            System.out.println("Alegeti o optiune de mai jos:");
            System.out.println("1.Exit"); //DA
            System.out.println("2.A trecut o luna"); //MODIF - DA
            System.out.println("3.Adauga o firma noua"); //MODIF - DA
            System.out.println("4.Desfiinteaza firmele cu profit negativ"); //MODIF - DA
            System.out.println("5.Adauga o noua sucursala"); //MODIF - DA
            System.out.println("6.Angajeaza o noua persoana"); //MODIF
            System.out.println("7.Adauga un proiect nou"); //da
            System.out.println("8.Arata-mi toate proiectele pe care le detin"); //DA
            System.out.println("9.Departamentele cele mai solicitate din organizatia X"); //DA
            System.out.println("10.Tara in care am cele mai profitabile afaceri"); //DA
            System.out.println("11.Arata situatia angajatilor din organizatia X"); //DA
            System.out.println("12.Afiseaza organizatiile dupa cifra de afaceri"); //DA
            System.out.println("13.Ne extindem intr-o tara noua"); //DA
            Integer option = sc.nextInt();

            if (option == 1) {
                break; 
            }

            if (option == 2) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("updateTime", d);

                time = organisationService.updateTime(time);
                organisationService.updateSituation(organisationsDB, branchesDB, employeesDB, projectsDB, connection);
                if (organisationService.checkOrganisation() != null) {
                    System.out.println("Luna aceasta organizatia " + organisationService.checkOrganisation().getName() + " a avut profit negativ");
                }
            }

            if (option == 3) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("addOrganisation", d);

                System.out.println("DA");
                organisationService.addOrganisation(time, dataOrg, impozitTara, organisationsDB, connection);
            }

            if (option == 4) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("bustOrganisation", d);

                organisationService.bustOrganisation(organisationsDB, branchesDB, employeesDB, projectsDB, connection);
            }

            if (option == 5) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("addBranch", d);

                System.out.println("La ce organizatie? ");
                String org = sc.next();
                organisationService.addBranch(org, impozitTara, organisationsDB, branchesDB, employeesDB, projectsDB, connection);
            }

            if (option == 6) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("hire", d);

                organisationService.hire(connection, branchesDB, employeesDB);
            }

            if (option == 7) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("addProject", d);

                System.out.println("La ce organizatie: ");
                String org = sc.next();
                organisationService.addProject(org, employeesDB, projectsDB, branchesDB, connection);
            }

            if (option == 8) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("getAllProjectsName", d);

                organisationService.getAllProjectsName();
            }

            if (option == 9) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("getBusiestDepartments", d);

                organisationService.getBusiestDepartments();
            }

            if (option == 10) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("getCountry", d);

                organisationService.getCountry();
            }

            if (option == 11) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("getEmployees", d);

                organisationService.getEmployees();
            }

            if (option == 12) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("displayOrganisations", d);

                organisationService.displayOrganisations();
            }

            if (option == 13){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("displayOrganisations", d);

                System.out.println("In ce tara? ");
                String country = sc.next();
                System.out.println("Spuneti va rog impozitul cerut in tara respectiva: ");
                Double impozit = Double.valueOf(sc.next());
                impozitTara.addNewCountry(country, impozit);
                impoziteDB.createData(connection, country, impozit);
            }
        }


        //UPDATE DATA ORGANISATIONS
        List<Organisation> organisationListFinal = new ArrayList<>();
        organisationListFinal = organisationService.getOrganisationList();
        dataOrg.updateData(organisationListFinal);

        //UPDATE DATA IMPOZITE
        //impoziteService.updateData(impozitTara);

        //UPDATE DATA PROJECTS
        pService.updateData(organisationService.getAllProjects().getProjectList());
        */

    }

}