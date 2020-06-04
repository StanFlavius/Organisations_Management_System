package Organisation_Management_System_Services;

import Organisation_Management_System_Entities.*;
import Organisation_Management_System_Exceptions.ValoareaDepasitaException;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrganisationService {
    static Scanner sc1 = new Scanner(System.in);
    private List<Organisation> organisationList;
    private ALLProjects allProjects;
    private String output;

    public String getOutput(){
        return output;
    }

    public void setAllProjects(ALLProjects allProjects) {
        this.allProjects = allProjects;
    }

    public OrganisationService(){
        this.organisationList = new ArrayList<>();
        allProjects = new ALLProjects();
    }

    public void setOrganisationList(List<Organisation> organisationList) {
        this.organisationList = organisationList;
    }

    public List<Organisation> getOrganisationList() {
        return organisationList;
    }

    public ALLProjects getAllProjects() {
        return allProjects;
    }

    public void doSORT(){
        Collections.sort(organisationList);
    }

    public void displayOrganisations(){
        for(Organisation o : organisationList){
            System.out.println("Nume: " + o.getName());
            System.out.println("Rating: " + o.getRating());
            System.out.println("Profit: " + o.getProfit());
            System.out.println("Data infiintarii: " + o.getYear() + " " + o.getMonth());
            List<Branch> branchList = o.getBranchList();
            Integer contor = 1;
            for(Branch b : branchList){
                System.out.println("Sucursala " + contor.toString());
                System.out.println("Informatii despre locatie: " + b.getLocation().getCountry() + " " + b.getLocation().getCity() + " " + b.getLocation().getStreet() + " " + b.getLocation().getNr());
                System.out.println("Impozitul: " + b.getImpozit());
                System.out.println("Situatia financiara: " + "Venit: " + b.getFinancialSituation().getIncome() + " Costuri: " + b.getFinancialSituation().getCost());
                System.out.println("Avem urmatoarea situatie a departamentelor: ");
                Integer contor2 = 1;
                for(Department d : b.getDepartmentsList()){
                    System.out.println(contor2 + ".Departamentul " + d.getDenumire());
                    System.out.println("    Avem angajatii: ");
                    for(Employee e : d.getEmployeeList()){
                        System.out.println("        " + e.getName() + " " + e.getSalary().toString() + " "  + e.getTask());
                    }
                    contor2++;
                }
                contor++;
                System.out.println("\n ");
            }

        }
    }

    /*public void addOrganisation(Organisation o){
        organisationList.add(o);
        System.out.println("DA");
        Collections.sort(organisationList);
    }*/

    /*public void addSomeData() {
        Organisation o1 = new Organisation("FlaviusSRL");
        o1.setProfit(0.0);
        o1.setYear(2019);
        o1.setMonth(10);
        Location location = new Location("Moscova", "Rusia", "ASD", 1);
        ImpozitTara imp = new ImpozitTara();
        //Branch branch = new Branch(location,imp.getImpozit("Rusia"));
        Branch branch = new Branch(location,12.0);
        Department_Marketing D_M = new Department_Marketing();
        Department_Finances D_F = new Department_Finances();
        Department_HR D_HR = new Department_HR();
        Department_Production D_P = new Department_Production();
        Department_RD D_RD = new Department_RD();
        branch.addDepertment(D_RD);
        branch.addDepertment(D_P);
        branch.addDepertment(D_HR);
        branch.addDepertment(D_F);
        branch.addDepertment(D_M);
        List<Department> departmentsList = branch.getDepartmentsList();
        Employee e1 = new Employee("Marco", 1200);
        Employee e2 = new Employee("Jonny", 1200);
        Employee e3 = new Employee("Alex", 1200);
        Employee e4 = new Employee("Putin", 1200);
        Employee e5 = new Employee("Northon", 1200);
        Employee e6 = new Employee("David", 1240);
        Employee e7 = new Employee("Harry", 1240);
        Employee e8 = new Employee("Aaron", 1240);
        Employee e9 = new Employee("Luke", 1240);
        Employee e10 = new Employee("Eric", 1240);
        Employee e11 = new Employee("Victor", 1240);
        Employee e12 = new Employee("Bruno", 1240);
        Employee e13 = new Employee("Scott", 1240);
        Employee e14 = new Employee("Anthony", 1240);
        Employee e15 = new Employee("Marcus", 1240);
        Employee e16 = new Employee("Mason", 1240);
        Employee e17 = new Employee("Odion", 1240);
        Employee e18 = new Employee("Jesse", 1240);
        Employee e19 = new Employee("Nemanja", 1240);
        Employee e20 = new Employee("Graham", 1240);

        for(Department d : departmentsList){
            if(d.getDenumire().compareTo("Research&Dev") == 0){
                d.addEmployee(e1);
                d.addEmployee(e2);
            }
            if(d.getDenumire().compareTo("Production") == 0){
                d.addEmployee(e3);
                d.addEmployee(e4);
            }
            if(d.getDenumire().compareTo("HR") == 0){
                d.addEmployee(e5);
                d.addEmployee(e6);
            }
            if(d.getDenumire().compareTo("Finances") == 0){
                d.addEmployee(e7);
                d.addEmployee(e8);
            }
            if(d.getDenumire().compareTo("Marketing") == 0){
                d.addEmployee(e9);
                d.addEmployee(e10);
            }
        }

        Project p1 = new Project("Game1", 12300000.0, 2, o1.getName(), branch.getLocation().getCountry());
        List<Employee> employeeList1 = new ArrayList<Employee>();
        employeeList1.add(e1);
        employeeList1.add(e2);
        employeeList1.add(e3);

        for(Department d : departmentsList){
            List<Employee> emp = new ArrayList<Employee>();
            for(Employee e : d.getEmployeeList()){
                if(e.getName().compareTo(e1.getName()) == 0 || e.getName().compareTo(e2.getName())==0 || e.getName().compareTo(e3.getName())==0){
                    e.setTask(1);
                }
            emp.add(e);
            }
            d.setEmployeeList(emp);
        }
        branch.setDepartmentsList(departmentsList);
        List<String> nameEmployees = new ArrayList<>();
        for(Employee e : employeeList1){
            nameEmployees.add(e.getName());
        }
        p1.setEmployeeList(nameEmployees);
        branch.addProject(p1);
        allProjects.addProject(p1);
        o1.addBranch(branch);
        organisationList.add(o1);



        Organisation o2 = new Organisation("StefanSRL");
        o2.setYear(2020);
        o2.setMonth(2);
        o2.setProfit(0.0);
        Location location2 = new Location("Bucuresti", "Romania", "qwerty", 1);
        //Branch branch2 = new Branch(location2,imp.getImpozit("Romania"));
        Branch branch2 = new Branch(location2,13.0);
        Department_Marketing D_M2 = new Department_Marketing();
        Department_Finances D_F2 = new Department_Finances();
        Department_HR D_HR2 = new Department_HR();
        Department_Production D_P2 = new Department_Production();
        Department_RD D_RD2 = new Department_RD();
        branch2.addDepertment(D_RD2);
        branch2.addDepertment(D_P2);
        branch2.addDepertment(D_HR2);
        branch2.addDepertment(D_F2);
        branch2.addDepertment(D_M2);
        List<Department> departmentsList2 = branch2.getDepartmentsList();
        for(Department d : departmentsList2){
            if(d.getDenumire().compareTo("Research&Dev") == 0){
                d.addEmployee(e11);
                d.addEmployee(e12);
            }
            if(d.getDenumire().compareTo("Production") == 0){
                d.addEmployee(e13);
                d.addEmployee(e14);
            }
            if(d.getDenumire().compareTo("HR") == 0){
                d.addEmployee(e15);
                d.addEmployee(e16);
            }
            if(d.getDenumire().compareTo("Finances") == 0){
                d.addEmployee(e17);
                d.addEmployee(e18);
            }
            if(d.getDenumire().compareTo("Marketing") == 0){
                d.addEmployee(e19);
                d.addEmployee(e20);
            }
        }
        Project p2 = new Project("Game2", 12300000.0, 3, o2.getName(), branch2.getLocation().getCountry());
        List<Employee> employeeList2 = new ArrayList<Employee>();;
        employeeList2.add(e11);
        employeeList2.add(e13);
        employeeList2.add(e15);

        for(Department d : departmentsList2){
            List<Employee> emp2 = new ArrayList<Employee>();
            for(Employee e : d.getEmployeeList()){
                if(e.getName().compareTo(e11.getName()) == 0 || e.getName().compareTo(e13.getName())==0 || e.getName().compareTo(e15.getName())==0){
                    e.setTask(1);
                }
                emp2.add(e);
            }
            d.setEmployeeList(emp2);
        }
        List<String> nameEmployees2 = new ArrayList<>();
        for(Employee e : employeeList2){
            nameEmployees2.add(e.getName());
        }
        p2.setEmployeeList(nameEmployees2);
        branch2.setDepartmentsList(departmentsList2);
        branch2.addProject(p2);
        allProjects.addProject(p2);
        o2.addBranch(branch2);
        organisationList.add(o2);
        doSORT();
    }*/

    public void addOrganisation(Time time, dataOrganisations dataOrg, ImpozitTara impozit, organisationsServiceDB organisationsServiceDB, Connection connection){
        System.out.println("Va rog sa adaugati urmatoarele detalii, referitor la organizatie: ");

        System.out.println("Numele firmei: ");
        String name = sc1.next();
        Organisation o = new Organisation(name);
        o.setProfit(0.0);
        o.setMonth(time.getMonth());
        o.setYear(time.getYear());

        System.out.println("Cate branch-uri are firma? ");
        Integer nrBranch = Integer.valueOf(sc1.next());
        Integer contor = 1;
        List<Branch> branchList = new ArrayList<>();
        for(int i = 0; i < nrBranch; i++){
            System.out.println("Acum am nevoie de date despre branch-ul " + contor.toString());

            System.out.println("Tara: ");
            String country = sc1.next();
            System.out.println("Orasul: ");
            String city = sc1.next();
            System.out.println("Strada: ");
            String street = sc1.next();
            System.out.println("Numarul: ");
            Integer nr = Integer.valueOf(sc1.next());
            Location location = new Location(city, country, street, nr);
            Double val_imp = impozit.getImpozit(country);
            System.out.println("Va anuntam ca impozitul in aceasta tara este de " + val_imp.toString() + " %");
            Branch newBranch = new Branch(location, val_imp);

            System.out.println("Acum trebuie sa introducem situatia departamentelor");
            List<Department> departmentList = new ArrayList<>();
            Department_RD D_RD = new Department_RD();
            departmentList.add(D_RD);
            Department_Production D_P = new Department_Production();
            departmentList.add(D_P);
            Department_HR D_HR = new Department_HR();
            departmentList.add(D_HR);
            Department_Finances D_F = new Department_Finances();
            departmentList.add(D_F);
            Department_Marketing D_M = new Department_Marketing();
            departmentList.add(D_M);
            newBranch.setDepartmentsList(departmentList);

            FinancialSituation finsit = new FinancialSituation();
            finsit.setIncome(0.0);
            finsit.setCost(0.0);
            newBranch.setFinancialSituation(finsit);

            List<Project> projectList = new ArrayList<>();
            newBranch.setProjectList(projectList);

            branchList.add(newBranch);
        }
        o.setBranchList(branchList);

        organisationList.add(o);
        organisationsServiceDB.createData(o, connection);
        doSORT();
        dataOrg.loadData(o);
        System.out.println("Organizatia a fost infiintata cu succes");
    }

    public Time updateTime(Time time){
        Time t = new Time();
        if(time.getMonth() + 1 == 13){
            t.setYear(time.getYear() + 1);
            t.setMonth(0);
        }
        t.setMonth(time.getMonth() + 1);
        System.out.println("Acum suntem in luna " + t.getMonth().toString() + " a anului " + t.getYear().toString());
        return t;
    }

    public Organisation checkOrganisation(){
        for(Organisation o : organisationList)
            if(o.getProfit() < 0)
                return o;
        return null;
    }

    public void bustOrganisation(organisationsServiceDB organisationsDB, branchesServiceDB branchesDB, employeesServiceDB employeesDB, projectsServiceDB projectsDB, Connection connection){
        if(organisationList.get(0).getProfit() >= 0) {
            System.out.println("Nu sunt firme cu profit negativ");
            return;
        }
        List<Organisation> orgs = new ArrayList<Organisation>();
        while(organisationList.get(0).getProfit() < 0){
            Organisation o = organisationList.get(0);
            organisationsDB.deleteData(connection, branchesDB, projectsDB, employeesDB, o);
            organisationList.remove(0);
        }
        System.out.println("Gata");
    }

    public void addBranch(String organisation, ImpozitTara impozit, organisationsServiceDB organisationsDB, branchesServiceDB branchesDB, employeesServiceDB employeesDB, projectsServiceDB projectsDB, Connection connection){
        for(Organisation o : organisationList){
            if(o.getName().compareTo(organisation) == 0){
                System.out.println("In ce tara? ");
                String country = sc1.next();
                System.out.println("In ce oras? ");
                String city = sc1.next();
                System.out.println("Pe ce strada? ");
                String street = sc1.next();
                System.out.println("La ce numar? ");
                Integer nr = Integer.valueOf(sc1.next());

                Location location = new Location(country, city, street, nr);
                Double val_imp = impozit.getImpozit(country);
                System.out.println("Va anuntam ca impozitul in aceasta tara este de " + val_imp.toString());
                Branch branch = new Branch(location,val_imp);

                FinancialSituation finsit = new FinancialSituation();
                finsit.setIncome(0.0);
                finsit.setCost(0.0);
                branch.setFinancialSituation(finsit);

                Department_Marketing D_M = new Department_Marketing();
                Department_Finances D_F = new Department_Finances();
                Department_HR D_HR = new Department_HR();
                Department_Production D_P = new Department_Production();
                Department_RD D_RD = new Department_RD();
                branch.addDepertment(D_RD);
                branch.addDepertment(D_P);
                branch.addDepertment(D_HR);
                branch.addDepertment(D_F);
                branch.addDepertment(D_M);

                o.addBranch(branch);

                organisationsDB.updateDataBranches(connection, branchesDB, projectsDB, employeesDB, o, branch);
            }
        }
    }

    public void hire(Connection connection, branchesServiceDB branchesDB, employeesServiceDB employeesDB) throws ValoareaDepasitaException {
        try {
            System.out.println("Dati-mi urmatoarele date despre aceasta persoana");
            System.out.println("nume: ");
            String name = sc1.next();
            System.out.println("salariul: ");
            Integer sal = sc1.nextInt();
            System.out.println("organizatia: ");
            String org = sc1.next();
            System.out.println("sucursala: ");
            String country = sc1.next();
            System.out.println("Departamentul: ");
            String dep = sc1.next();
            Employee e = new Employee(name, sal);
            for (Organisation o : organisationList) {
                if (o.getName().compareTo(org) == 0) {
                    for (Branch b : o.getBranchList()) {
                        if (b.getLocation().getCountry().compareTo(country) == 0) {
                            for (Department d : b.getDepartmentsList()) {
                                if (d.getDenumire().compareTo(dep) == 0) {
                                    if (d.getEmployeeList().size() == d.getMaxEmployee())
                                        throw new ValoareaDepasitaException("Nu mai poti adauga angajati aici. S-a atins numarul maxim. ");
                                    else {
                                        d.addEmployee(e);
                                        branchesDB.updateData(connection, b);
                                        employeesDB.createData(connection, e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (ValoareaDepasitaException v){
            System.out.println(v.getMessage());
        }
    }

    public Integer getFreeEmployees(Department d){
        Integer count = 0;
        for(Employee e : d.getEmployeeList()){
            if(e.getTask() == 0)
                count++;
        }
        return count;
    }

    public void addProject(String organisation, employeesServiceDB employeesDB, projectsServiceDB projectsDB, branchesServiceDB branchesDB, Connection connection){
        System.out.println("Am nevoie de urmataorele date: ");
        System.out.println("nume: ");
        String name = sc1.next();
        System.out.println("castig: ");
        Double income = sc1.nextDouble();
        System.out.println("durata: ");
        Integer time = sc1.nextInt();
        System.out.println("cati angajati din Finances");
        Integer nr_F = sc1.nextInt();
        System.out.println("cati angajati din HR");
        Integer nr_HR = sc1.nextInt();
        System.out.println("cati angajati din Marketing");
        Integer nr_M = sc1.nextInt();
        System.out.println("cati angajati din Production");
        Integer nr_P = sc1.nextInt();
        System.out.println("cati angajati din Research&D");
        Integer nr_RD = sc1.nextInt();
        List<Employee> employees = new ArrayList<Employee>();
        Integer unpp = 0;
        Integer task = 0;
        for(Organisation o : organisationList){
            if(o.getName().compareTo(organisation) == 0){
                Integer pp2 = 0;
                for(Branch b : o.getBranchList()){
                    Integer pp = 1;
                    for(Department d : b.getDepartmentsList()){
                        Integer count = getFreeEmployees(d);
                        if(d.getDenumire().compareTo("Research&Dev") == 0)
                            if(count < nr_RD){
                                pp = 0;
                                break;
                            }
                        if(d.getDenumire().compareTo("Production") == 0)
                            if(count < nr_P){
                                pp = 0;
                                break;
                            }
                        if(d.getDenumire().compareTo("HR") == 0)
                            if(count < nr_HR){
                                pp = 0;
                                break;
                            }
                        if(d.getDenumire().compareTo("Finances") == 0)
                            if(count < nr_F){
                                pp = 0;
                                break;
                            }
                        if(d.getDenumire().compareTo("Marketing") == 0)
                            if(count < nr_M){
                                pp = 0;
                                break;
                            }
                    }
                    if(pp == 1){
                        pp2 = 1;
                        unpp = 1;
                        System.out.println("Vom atribui proiectul sucursalei din " + b.getLocation().getCountry());
                        for(Department d : b.getDepartmentsList()){
                            if(d.getDenumire().compareTo("Research&Dev") == 0 && nr_RD != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_RD))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Production") == 0 && nr_P != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_P))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("HR") == 0 && nr_HR != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_HR))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Finances") == 0 && nr_F != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_F))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Marketing") == 0 && nr_M != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_M))
                                        break;
                                }
                            }
                        }
                        Project p = new Project(name, income, time);
                        List<String> nameEmployees = new ArrayList<>();
                        for(Employee e : employees){
                            nameEmployees.add(e.getName());
                            employeesDB.updateData(connection,e);
                        }

                        p.setEmployeeList(nameEmployees);
                        b.addProject(p);
                        allProjects.addProject(p);
                        branchesDB.updateData(connection, b);
                        projectsDB.createData(connection, p);
                        break;
                    }
                }
                if(pp2 == 0){
                    System.out.println("Nu s-a putut atribui proiectul organizatiei dorite");
                    System.out.println("Doriti sa il atribuim altei firme? ");
                    String message = sc1.next();
                    if(message.compareTo("Da") == 0){
                        task = 1;
                        break;
                    }
                }
            }
        }
        if(task == 1) {
            System.out.println("Da");
            Integer pp3 = 0;
            for (Organisation o : organisationList) {
                Integer pp2 = 0;
                for (Branch b : o.getBranchList()) {
                    Integer pp = 1;
                    for (Department d : b.getDepartmentsList()) {
                        Integer count = getFreeEmployees(d);
                        if (d.getDenumire().compareTo("Research&Dev") == 0)
                            if (count < nr_RD) {
                                pp = 0;
                                break;
                            }
                        if (d.getDenumire().compareTo("Production") == 0)
                            if (count < nr_P) {
                                pp = 0;
                                break;
                            }
                        if (d.getDenumire().compareTo("HR") == 0)
                            if (count < nr_HR) {
                                pp = 0;
                                break;
                            }
                        if (d.getDenumire().compareTo("Finances") == 0)
                            if (count < nr_F) {
                                pp = 0;
                                break;
                            }
                        if (d.getDenumire().compareTo("Marketing") == 0)
                            if (count < nr_M) {
                                pp = 0;
                                break;
                            }
                    }
                    if(pp == 1){
                        unpp = 1;
                        pp2 = 1;
                        System.out.println("Vom atribui proiectul organizatiei " + o.getName() + " cu  sucursala in " + b.getLocation().getCountry());
                        for(Department d : b.getDepartmentsList()){
                            if(d.getDenumire().compareTo("Research&Dev") == 0 && nr_RD != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_RD))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Production") == 0 && nr_P != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_P))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("HR") == 0 && nr_HR != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_HR))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Finances") == 0 && nr_F != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_F))
                                        break;
                                }
                            }
                            if(d.getDenumire().compareTo("Marketing") == 0 && nr_M != 0) {
                                Integer count2 = 0;
                                for (Employee e : d.getEmployeeList()) {
                                    if (e.getTask() == 0) {
                                        e.setTask(1);
                                        employees.add(e);
                                        count2++;
                                    }
                                    if (count2.equals(nr_M))
                                        break;
                                }
                            }
                        }
                        Project p = new Project(name, income, time);
                        List<String> nameEmployees = new ArrayList<>();
                        for(Employee e : employees){
                            nameEmployees.add(e.getName());
                        }
                        p.setEmployeeList(nameEmployees);
                        b.addProject(p);
                        allProjects.addProject(p);
                        branchesDB.updateData(connection, b);
                        projectsDB.createData(connection, p);
                        break;
                    }
                }
                if(pp2 == 1) {
                    pp3 = 1;
                    break;
                }
            }
        }
        if(unpp == 0) System.out.println("Nu s-a gasit nicio organizatie disponibila");
    }

    public void getAllProjectsName(){
        Integer contor = 1;
        for(Project p : allProjects.getProjectList()){
            System.out.println("Date referitoare proiectului: " + contor.toString());
            System.out.println("Nume: " + p.getName());
            System.out.println("Income/month: " + p.getIncome());
            System.out.println("Time: " + p.getTime());
            System.out.println("Avem urmatorii employees: ");
            for(String employee : p.getEmployeeList()){
                System.out.println("    " + employee);
            }
            contor++;
        }
    }

    public Double calculateSalariesDepartment(Department d, Double impozit){
        Double total = 0.0;
        for(Employee e : d.getEmployeeList()){
            total = total + (e.getSalary() - impozit / 100 * e.getSalary());
        }
        return total;
    }

    public void updateSituation(organisationsServiceDB organisationsDB, branchesServiceDB branchesDB, employeesServiceDB employeesDB, projectsServiceDB projectsServiceDB, Connection connection){

        StringBuilder sb = new StringBuilder();
        for(Organisation o : organisationList){
            Double total = 0.0;
            for(Branch b : o.getBranchList()){
                FinancialSituation sit = new FinancialSituation();
                Double cost = 0.0;
                for(Department d : b.getDepartmentsList()){
                    cost+=calculateSalariesDepartment(d,b.getImpozit());
                }
                sit.setCost(cost);

                Double income = 0.0;
                List<Project> newProjects = new ArrayList<Project>();
                List<String> nameEmployees = new ArrayList<String>();
                for(Project p : b.getProjectList()){
                    //System.out.println("               " + p.getName());
                    income+=p.getIncome();
                    p.setTime(p.getTime() - 1);
                    if(p.getTime() == 0){
                        for(String e : p.getEmployeeList()){
                            nameEmployees.add(e);
                        }
                        allProjects.setAllProjects(allProjects.getDifferentProjects(p.getName()));
                        //System.out.println("Proiectul " + p.getName() + " s-a finalizat.");
                        String out = "Proiectul " + p.getName() + " s-a finalizat." + "\n";
                        sb.append(out);
                        projectsServiceDB.deleteData(connection, p.getName());
                    }
                    else {
                        newProjects.add(p);
                        projectsServiceDB.updateData(connection, p);
                    }
                }

                for(Department d : b.getDepartmentsList()){
                    for(Employee e : d.getEmployeeList()){
                        if(nameEmployees.contains(e.getName())){
                            e.setTask(0);
                            employeesDB.updateData(connection, e);
                        }
                    }
                }

                b.setProjectList(newProjects);

                sit.setIncome(income);
                b.setFinancialSituation(sit);
                branchesDB.updateData(connection, b);
                total += (b.getFinancialSituation().getIncome() - b.getFinancialSituation().getCost());
            }
            /*List<Project> projectLists = new ArrayList<>();
            projectLists = allProjects.getProjectList();
            for(Project p : projectLists){
                p.setTime(p.getTime() - 1);
            }
            allProjects.setAllProjects(projectLists);*/
            o.setProfit(total);
            organisationsDB.updateData(connection, branchesDB, projectsServiceDB, employeesDB, o);
        }
        output = sb.toString();
        doSORT();
    }

    public void getEmployees(){
        System.out.println("Din ce organizatie doriti? ");
        String msg = sc1.next();

        for(Organisation o : organisationList){
            if(o.getName().compareTo(msg) == 0){
                for(Branch b : o.getBranchList()){
                    System.out.println("In sucursala "+ " b.getLocation().getCountry()" + "avem urmatoarea situatie");
                    for(Department d : b.getDepartmentsList()){
                        System.out.println("In departamentul " + d.getDenumire());
                        for(Employee e : d.getEmployeeList()){
                            String busy = new String();
                            if(e.getTask() == 0)
                                busy = " nu e ocupat";
                            else
                                busy = " e ocupat";
                            System.out.println(e.getName() + busy);
                        }
                    }
                }
            }
        }

    }

    public void getBusiestDepartments(){
        System.out.println("De la ce organizatie doriti? ");
        String msg = sc1.next();
        Integer task = 0;
        if(msg.compareTo("Toate") == 0)
            task = 1;

        Map<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("Finances", 0);
        hm.put("HR", 0);
        hm.put("Marketing", 0);
        hm.put("Production", 0);
        hm.put("Research&Dev", 0);
        if(task == 1){
            for(Organisation o : organisationList){
                for(Branch b : o.getBranchList()){
                    for(Department d : b.getDepartmentsList()){
                        Integer tot = 0;
                        for(Employee e : d.getEmployeeList()) {
                            if (e.getTask() == 1) {
                                tot++;
                            }
                        }
                        Integer val = 0;
                        Set< Map.Entry< String,Integer> > st = hm.entrySet();
                        for(Map.Entry< String,Integer> me : st) {
                            if (me.getKey().compareTo(d.getDenumire()) == 0) {
                                val = me.getValue();
                                break;
                            }
                        }
                        hm.put(d.getDenumire(), val + tot);
                    }
                }
            }
        }
        else{
            for(Organisation o : organisationList){
                if(o.getName().compareTo(msg) == 0){
                    for(Branch b : o.getBranchList()){
                        for(Department d : b.getDepartmentsList()){
                            Integer tot = 0;
                            for(Employee e : d.getEmployeeList()){
                                if(e.getTask() == 1){
                                    tot++;
                                }
                            }
                            Integer val = 0;
                            Set< Map.Entry< String,Integer> > st = hm.entrySet();
                            for(Map.Entry< String,Integer> me : st) {
                                if (me.getKey().compareTo(d.getDenumire()) == 0) {
                                    val = me.getValue();
                                    break;
                                }
                            }
                            hm.put(d.getDenumire(), val + tot);
                        }
                    }
                }
            }
        }
        Integer mx = 0;
        Set< Map.Entry< String,Integer> > st = hm.entrySet();
        for(Map.Entry< String,Integer> me : st){
            if(me.getValue() > mx)
                mx = me.getValue();
        }

        System.out.println("Departamentele cele mai solicitate sunt: ");

        for(Map.Entry< String,Integer> me : st){
            if(me.getValue().equals(mx))
                System.out.println(me.getKey());
        }
    }

    public void getCountry(){
        Map<String, Double> hm = new HashMap<String, Double>();

        for(Organisation o : organisationList){
            for(Branch b : o.getBranchList()){
                String name = b.getLocation().getCountry();
                Double profit = b.getFinancialSituation().getIncome() - b.getFinancialSituation().getCost() ;
                hm.put(name, profit);
            }
        }

        Double mx = 0.0;
        Set< Map.Entry< String,Double> > st = hm.entrySet();
        for(Map.Entry< String,Double> me : st){
            if(me.getValue() > mx)
                mx = me.getValue();
        }

        System.out.println("Tarile cu cele mai profitabile afaceri sunt: ");

        for(Map.Entry< String,Double> me : st){
            if(me.getValue().equals(mx))
                System.out.println(me.getKey());
        }
    }

    public List<Project> getProjectsGUI(auditService auditService) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date);
        auditService.addInAudit("getProjectsGUI", d);
        return allProjects.getProjectList();
    }

    public List<String> getBusiestDepGUIv1(auditService auditService) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = sdf.format(date);
        auditService.addInAudit("getBusiestDepGUIv1", dt);
        //TOATE ORGANIZATIILE
        Map<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("Finances", 0);
        hm.put("HR", 0);
        hm.put("Marketing", 0);
        hm.put("Production", 0);
        hm.put("Research&Dev", 0);
        for(Organisation o : organisationList){
            for(Branch b : o.getBranchList()){
                for(Department d : b.getDepartmentsList()){
                    Integer tot = 0;
                    for(Employee e : d.getEmployeeList()) {
                        if (e.getTask() == 1) {
                            tot++;
                        }
                    }
                    Integer val = 0;
                    Set< Map.Entry< String,Integer> > st = hm.entrySet();
                    for(Map.Entry< String,Integer> me : st) {
                        if (me.getKey().compareTo(d.getDenumire()) == 0) {
                            val = me.getValue();
                            break;
                        }
                    }
                    hm.put(d.getDenumire(), val + tot);
                }
            }
        }
        Integer mx = 0;
        Set< Map.Entry< String,Integer> > st = hm.entrySet();
        for(Map.Entry< String,Integer> me : st){
            if(me.getValue() > mx)
                mx = me.getValue();
        }

        List<String> listDep = new ArrayList<>();

        for(Map.Entry< String,Integer> me : st){
            if(me.getValue().equals(mx)) {
                //System.out.println(me.getKey() + " " + mx.toString());
                listDep.add(me.getKey() + " " + mx.toString());
            }
        }



        return  listDep;
    }

    public List<String> getBusiesDepGUIV2(String msg, auditService auditService) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = sdf.format(date);
        auditService.addInAudit("getBusiesDepGUIV2", dt);
        //DOAR O ORGANIZATIE
        Map<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("Finances", 0);
        hm.put("HR", 0);
        hm.put("Marketing", 0);
        hm.put("Production", 0);
        hm.put("Research&Dev", 0);
        for(Organisation o : organisationList){
            if(o.getName().compareTo(msg) == 0){
                for(Branch b : o.getBranchList()){
                    for(Department d : b.getDepartmentsList()){
                        Integer tot = 0;
                        for(Employee e : d.getEmployeeList()){
                            if(e.getTask() == 1){
                                tot++;
                            }
                        }
                        Integer val = 0;
                        Set< Map.Entry< String,Integer> > st = hm.entrySet();
                        for(Map.Entry< String,Integer> me : st) {
                            if (me.getKey().compareTo(d.getDenumire()) == 0) {
                                val = me.getValue();
                                break;
                            }
                        }
                        hm.put(d.getDenumire(), val + tot);
                    }
                }
            }
        }
        Integer mx = 0;
        Set< Map.Entry< String,Integer> > st = hm.entrySet();
        for(Map.Entry< String,Integer> me : st){
            if(me.getValue() > mx)
                mx = me.getValue();
        }

        List<String> listDep = new ArrayList<>();

        for(Map.Entry< String,Integer> me : st){
            if(me.getValue().equals(mx))
                listDep.add(me.getKey() + " " + mx.toString());
        }

        return  listDep;
    }

    public Organisation getEmployeesGUI(String org, auditService auditService) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date);
        auditService.addInAudit("getEmployeesGUI", d);
        Organisation organisation = new Organisation("a");
        for(Organisation o : organisationList) {
            if(o.getName().equals(org)) {
                organisation = o;
                break;
            }
        }
        return organisation;
    }

    public List<String> getCountryGUI(auditService auditService) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date);
        auditService.addInAudit("getCountry", d);
        Map<String, Double> hm = new HashMap<String, Double>();
        List<String> stringList = new ArrayList<>();

        for(Organisation o : organisationList){
            for(Branch b : o.getBranchList()){
                String name = b.getLocation().getCountry();
                Double profit = b.getFinancialSituation().getIncome() - b.getFinancialSituation().getCost() ;
                hm.put(name, profit);
            }
        }

        Double mx = 0.0;
        Set< Map.Entry< String,Double> > st = hm.entrySet();
        for(Map.Entry< String,Double> me : st){
            if(me.getValue() > mx)
                mx = me.getValue();
        }

        for(Map.Entry< String,Double> me : st){
            if(me.getValue().equals(mx))
                stringList.add("    " + me.getKey() + " " + mx.toString());
                //System.out.println(me.getKey());
        }
        return stringList;
    }
}