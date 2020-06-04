package Organisation_Management_System_Entities;

import java.util.ArrayList;
import java.util.List;

public class ALLProjects {
    private List<Project> allProjects;

    public ALLProjects(){
        this.allProjects = new ArrayList<Project>();
    }

    public List<Project> getProjectList() {
        return allProjects;
    }

    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    public List<Project> getDifferentProjects(String name){
        List<Project> newProjects = new ArrayList<Project>();
        for(Project p : allProjects) {
            if(!p.getName().equals(name)) {
                newProjects.add(p);
            }
        }
        return newProjects;
    }

    public void addProject(Project project){
        allProjects.add(project);
    }
}
