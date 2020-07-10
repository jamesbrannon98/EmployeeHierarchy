abstract class Employee {
    private String fname;
    private String lname;
    private String ssn;

    public Employee(String fname, String lname, String ssn) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.ssn = ssn;
    }
    
    public String getfname(){
        return fname;
    }
    
    public void setfname(String fname){
        this.fname = fname;
    }
    
    public String getlname(){
        return lname;
    }
    
    public void setlname(String lname){
        this.lname = lname;
    }
    
    public String getssn(){
        return ssn;
}
    
    public void setssn(String ssn){
        this.ssn = ssn;
    }
    
    public abstract void raise(double percent);
    
    public  static  void  main(String args[]){
        Employee[] employees = new Employee[5];
        System.out.println("Employee Information.");
        CommissionEmployee employee1 = new CommissionEmployee("Fred", "Jones", "111-11-1111", 2000.0, .05);
        BasePlusCommissionEmployee employee2 = new BasePlusCommissionEmployee("Sue", "Smith", "222-22-2222", 3000.0, .05, 300);
        SalariedEmployee employee3 = new SalariedEmployee("Sha", "Yang", "333-33-3333", 1150.0);
        HourlyEmployee employee4 = new HourlyEmployee("Ian", "Tanning", "444-44-4444", 15.0, 50);
        HourlyEmployee employee5 = new HourlyEmployee("Angela", "Domchek", "555-55-5555", 20.0, 40);
        System.out.printf("%s%s%s%s%s",employee1,employee2,employee3,employee4,employee5);
        employee1.toString();
        employees[0] = employee1;
        employee2.toString();
        employees[1] = employee2;
        employee3.toString();
        employees[2] = employee3;
        employee4.toString();
        employees[3] = employee4;
        employee5.toString();
        employees[4] = employee5;
        
        /* Salaried employees get a 4% raise, 
        else the employee receives a 2% raise. */
        for (Employee employee : employees){
            if (employee instanceof SalariedEmployee){
                employee.raise(4); 
            }
            else{
                employee.raise(2);
            }
        }
        System.out.println("\nEmployee information after raises.");
        for (Employee employee : employees){
            System.out.print(employee.toString());
        }
    }
}

/* Each of the following classes extends the Employee class in order to process
data specific to the classification of the employee*/
class CommissionEmployee extends Employee{
    private double GrossSales;
    private double CommissionRate;
    
    public CommissionEmployee(String fname, String lname, String ssn, double GrossSales, double CommissionRate){
        super(fname, lname, ssn);
        if (GrossSales < 0.0)
            throw new IllegalArgumentException("Gross Sales must be >= 0.0");
        if (CommissionRate <= 0.0 || CommissionRate >= 1.0)
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        this.GrossSales = GrossSales;
        this.CommissionRate = CommissionRate;
    }
    
    public double getGrossSales(){
        return GrossSales;
    }
    
    public void setGrossSales(double GrossSales){
        if (GrossSales < 0.0)
            throw new IllegalArgumentException("Gross Sales must be >= 0.0");
        this.GrossSales = GrossSales;
    }
    
    public double getCommissionRate(){
        return CommissionRate/1;
    }
    
    public void setCommissionRate(double CommissionRate){
        if (CommissionRate <= 0.0 || CommissionRate >= 1.0)
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        this.CommissionRate = CommissionRate;
    }
    
    public double Earnings(){
        return getCommissionRate() * getGrossSales();
    }
    
    @Override
    public String toString(){
        return "Commissioned Employee: " + getfname() + " " + getlname() + " with ssn: " + getssn() + "\n" +
                "Gross Sales: $" + String.format("%.2f", getGrossSales()) + 
                "\nCommission Rate: " + getCommissionRate() + 
                "\nEarnings: $" + String.format("%.2f", Earnings()) + "\n";
    }
    
    @Override
    public void raise(double percent){
        this.CommissionRate += (this.CommissionRate * (percent/100.00));
        this.CommissionRate = Math.floor(this.CommissionRate*1000);
        this.CommissionRate = this.CommissionRate/1000;
    }
}

class BasePlusCommissionEmployee extends CommissionEmployee{
    private double BaseSalary;
    
    public BasePlusCommissionEmployee(String fname, String lname, String ssn, double GrossSales, double CommissionRate, double BaseSalary){
        super(fname, lname, ssn, GrossSales, CommissionRate);
        if (BaseSalary < 0.0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        this.BaseSalary = BaseSalary;
    }
    
    public double getBaseSalary(){
        return BaseSalary;
    }
    
    public void setBaseSalary(double BaseSalary){
        if (BaseSalary < 0.0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        this.BaseSalary = BaseSalary;
    }
    
    @Override
    public double Earnings(){
        return getBaseSalary() + super.Earnings();
    }
    
    @Override
    public String toString(){
        return "Base Salary Plus Commissioned Employee: " + getfname() + " " + getlname() +
                " with ssn: " + getssn() + "\n" +
                "Gross Sales: $" + String.format("%.2f", getGrossSales()) + 
                "\nCommission Rate: " + getCommissionRate() +
                "\nwith Base Salary of: $" + String.format("%.2f", getBaseSalary()) + 
                "\nEarnings: $" + String.format("%.2f", Earnings()) + "\n";
    }
    
    @Override
    public void raise(double percent){
        this.setCommissionRate((Math.floor((this.getCommissionRate() + (this.getCommissionRate() * (percent/100.00)))*1000))/1000);
        this.BaseSalary += this.BaseSalary * (percent/100.00);
    }
}

class SalariedEmployee extends Employee{
    private double salary;

    public SalariedEmployee(String fname, String lname, String ssn, double salary) {
        super(fname,lname,ssn);
        if (salary < 0.0)
            throw new IllegalArgumentException("Salary must be >= 0.0");
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 0.0)
            throw new IllegalArgumentException("Salary must be >= 0.0");
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return "Salaried Employee: " + getfname() + " " + getlname() + " with ssn: " 
                + getssn() + "\n" +
                "Salary: $" + String.format("%.2f", getSalary()) +
                "\nEarnings: $" + String.format("%.2f", getSalary()) + "\n";
    }
    
    @Override
    public void raise(double percent){
        this.salary += (this.salary * (percent/100.00));
    }
}


class HourlyEmployee extends Employee{

    private double hourlyWage;
    private double hoursWorked;

    public HourlyEmployee(String fname, String lname, String ssn, double hourlyWage, int hoursWorked) {
        super(fname,lname,ssn);
        if (hourlyWage < 0.0)
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");
        if (hoursWorked < 1 || hoursWorked > 168)
            throw new IllegalArgumentException("Hours worked must be >= 1 and <= 168");
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        if (hourlyWage < 0.0)
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");
        this.hourlyWage = hourlyWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 1 || hoursWorked > 168)
            throw new IllegalArgumentException("Hours worked must be >= 1 and <= 168");
        this.hoursWorked = hoursWorked;
    }

    public double Earnings() {
        double sum = 0;
        if (this.hoursWorked > 40){
            sum = 40* this.hourlyWage + (this.hoursWorked - 40) * 1.5 * this.hourlyWage;
        }else{
            sum = this.hoursWorked * this.hourlyWage;
        }
        return sum;
    }
    
    @Override
    public String toString() {
        return "Hourly Employee: " + getfname() + " " + getlname() + " with ssn: " + getssn() + "\n" +
                "Hourly Wage: $" + String.format("%.2f", hourlyWage) +
                "\nHours Worked: " + String.format("%.2f", hoursWorked) +
                "\nEarnings: $" + String.format("%.2f", Earnings()) + "\n";
    }
    
    @Override
    public void raise(double percent){
        this.hourlyWage += (this.hourlyWage * (percent/100.00));
    }
}