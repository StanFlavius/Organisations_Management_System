package Organisation_Management_System_Entities;

import java.util.ArrayList;
import java.util.List;

public class Organisation implements Comparable<Organisation>{
    private String name;
    private Double rating;
    private Double profit;
    private Integer year;
    private Integer month;
    private List<Branch> branchList;

    public Organisation(String name){
        this.name = name;
        this.rating = 0.0;
        this.profit = 0.0;
        this.branchList = new ArrayList<Branch>();
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

    public void setProfit(Double profit){
        this.profit = profit;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public void addBranch(Branch b){
        this.branchList.add(b);
    }

    public String getName(){
        return this.name;
    }

    public Double getRating(){
        return this.rating;
    }

    public Double getProfit(){
        return this.profit;
    }

    public List<Branch> getBranchList(){
        return this.branchList;
    }

    @Override
    public int compareTo(Organisation o) {
        if(this.profit.equals(o.profit))
            return 0;
        else
            if(this.profit < o.profit)
                return -1;
            else
                return 1;
    }
}
