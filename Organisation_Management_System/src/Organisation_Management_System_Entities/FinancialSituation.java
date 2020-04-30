package Organisation_Management_System_Entities;

public class FinancialSituation {
    private double income;
    private double cost;

    public FinancialSituation(){
        income = 0.0;
        cost = 0.0;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getCost() {
        return cost;
    }

    public double getIncome() {
        return income;
    }
}
