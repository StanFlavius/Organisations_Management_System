import Organisation_Management_System_Entities.*;
import Organisation_Management_System_Exceptions.ValoareaDepasitaException;
import Organisation_Management_System_Services.OrganisationService;
import Organisation_Management_System_Services.auditService;
import Organisation_Management_System_Services.dataOrganisations;
import Organisation_Management_System_Services.impozitTariService;
import Organisation_Management_System_Services.projectService;
import com.sun.org.apache.xpath.internal.operations.Or;
import sun.security.timestamp.TSRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ValoareaDepasitaException, IOException {

        auditService auditService = new auditService();

        //IMPORTAM DATELE DESPRE PROIECTELE DETINUTE
        projectService pService = projectService.getInstance();
        List<Project> projectList = new ArrayList<>();
        projectList = pService.getData();
        ALLProjects allProjects = new ALLProjects();
        allProjects.setAllProjects(projectList);

        //IMPORTAM DATELE DESPRE IMPOZITELE DIN DIFERITE TARI
        impozitTariService impoziteService = impozitTariService.getInstance();
        Map<String, Double> data = impoziteService.getData();
        ImpozitTara impozitTara = new ImpozitTara();
        impozitTara.setHm(data);

        ///IMPORTAM DATELE DESPRE ORGANIZATII
        dataOrganisations dataOrg = dataOrganisations.getInstance();
        List<Organisation> organisationList = new ArrayList<>();
        organisationList = dataOrg.getData();
        OrganisationService organisationService = new OrganisationService();
        organisationService.setOrganisationList(organisationList);
        organisationService.setAllProjects(allProjects);

        //organisationService.displayOrganisations();

        //organisationService.addSomeData();

        Time time = new Time();

        String message = " ";
        while(true) {
            System.out.println("");
            System.out.println("Buna ziua");
            System.out.println("Alegeti o optiune de mai jos:");
            System.out.println("1.Exit"); //DA
            System.out.println("2.A trecut o luna"); //DA
            System.out.println("3.Adauga o firma noua"); //DA
            System.out.println("4.Desfiinteaza firmele cu profit negativ"); //DA
            System.out.println("5.Adauga o noua sucursala"); //DA
            System.out.println("6.Angajeaza o noua persoana"); //DA
            System.out.println("7.Adauga un proiect nou"); //DA
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
                organisationService.updateSituation();
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
                organisationService.addOrganisation(time, dataOrg, impozitTara);
            }

            if (option == 4) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("bustOrganisation", d);

                organisationService.bustOrganisation();
            }

            if (option == 5) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("addBranch", d);

                System.out.println("La ce organizatie? ");
                String org = sc.next();
                organisationService.addBranch(org, impozitTara);
            }

            if (option == 6) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("hire", d);

                organisationService.hire();
            }

            if (option == 7) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(date);
                auditService.addInAudit("addProject", d);

                System.out.println("La ce organizatie: ");
                String org = sc.next();
                organisationService.addProject(org);
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
            }
        }

        //UPDATE DATA ORGANISATIONS
        List<Organisation> organisationListFinal = new ArrayList<>();
        organisationListFinal = organisationService.getOrganisationList();
        dataOrg.updateData(organisationListFinal);

        //UPDATE DATA IMPOZITE
        impoziteService.updateData(impozitTara);

        //UPDATE DATA PROJECTS
        pService.updateData(organisationService.getAllProjects().getProjectList());
    }
}