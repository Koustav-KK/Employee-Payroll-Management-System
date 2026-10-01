import java.util.Scanner;
class Employee
{

    private String name;
    private int id;
    private double baseSalary;
    private static int totalEmployees = 0;

    public Employee(String name, int id, double baseSalary)
    {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
        totalEmployees = totalEmployees + 1;

    }

    public Employee()
    {
        this.name = "Unknown";
        this.id = 0;
        this.baseSalary = 0;
        totalEmployees = totalEmployees + 1;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public double getBaseSalary()
    {
        return baseSalary;
    }

    public static int getTotalEmployees()
    {
        return totalEmployees;
    }

    protected double calculateSalary()
    {
        return baseSalary;
    }

    public void raiseSalary(int amount) {
        baseSalary = baseSalary + amount;
    }

    public void raiseSalary(double percent)
    {
        double increment = baseSalary * percent / 100;
        baseSalary = increment + baseSalary;
    }
}

class Manager extends Employee
{
    private int teamSize;

    public Manager(String name, int id, double baseSalary, int teamSize)
    {
        super(name,id,baseSalary);
        this.teamSize = teamSize;

    }

    @Override
    protected double calculateSalary() {
        return getBaseSalary() + teamSize * 500;
    }
}

class Developer extends Employee
{
    private int projectsCompleted;
    public Developer(String name, int id, double baseSalary, int projectsCompleted)
    {
        super(name, id, baseSalary);
        this.projectsCompleted = projectsCompleted;
    }

    @Override
    protected double calculateSalary() {
        return getBaseSalary() + projectsCompleted * 1000;
    }
}

class Intern extends Employee
{
    public Intern(String name, int id, double baseSalary)
    {
        super(name, id, baseSalary);
    }

    @Override
    protected double calculateSalary() {
        return getBaseSalary();
    }

}

public class main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        if(n <= 0) {
            System.out.println("Number of employees must be greater than 0.");
            return;
        }

        Employee[] staff = new Employee[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Employee " + (i + 1));

            System.out.println("Choose employee type:");
            System.out.println("1. General Employee");
            System.out.println("2. Manager");
            System.out.println("3. Developer");
            System.out.println("4. Intern");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if(choice < 1 || choice > 4)
            {
                System.out.println("Invalid Employee Type");
                i--;
                continue;
            }

            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            System.out.print("Enter base salary: ");
            double baseSalary = sc.nextDouble();

            if(baseSalary < 0)
            {
                System.out.println("Base Salary cannot be negative.");
                i--;
                continue;
            }

            switch (choice)
            {
                case 1:
                staff[i] = new Employee(name,id, baseSalary);
                break;

                case 2:
                System.out.print("Enter team size: ");
                int teamSize = sc.nextInt();

                staff[i] = new Manager(name, id, baseSalary, teamSize);
                break;

                case 3:
                System.out.print("Enter projects completed: ");
                int projectsCompleted = sc.nextInt();

                staff[i] = new Developer(name,id,baseSalary,projectsCompleted);
                break;

                case 4:
                staff[i] = new Intern(name,id,baseSalary);
                break;
            }

            System.out.print("Do you want to raise salary? (1 = Yes, 2 = No): ");
            int raiseChoice = sc.nextInt();

            switch (raiseChoice)
            {
                case 1:
                    System.out.print("1. Fixed Amount ");
                    System.out.println("2. Percentage");

                    System.out.print("Choose raise type: ");
                    int raiseType = sc.nextInt();

                    switch (raiseType)
                    {
                        case 1:
                            System.out.print("Enter raise amount: ");
                            int amount = sc.nextInt();

                            staff[i].raiseSalary(amount);
                            break;

                        case 2:
                            System.out.print("Enter raise percentage: ");
                            double percent = sc.nextDouble();

                            staff[i].raiseSalary(percent);
                            break;

                        default:
                            System.out.println("Invalid raise type");
                            break;
                    }
                    break;

                case 2:
                    break;

                default:
                    System.out.println("Invalid raise choice");
                    break;
            }

            System.out.println("Employee ID: " + staff[i].getId());
            System.out.println("Name: " + staff[i].getName());
            System.out.println("Base Salary: " + staff[i].getBaseSalary());
            System.out.println("Calculated Salary: " + staff[i].calculateSalary());
            System.out.println("---------------------------");

        }

        System.out.println("Total Employees: " + Employee.getTotalEmployees());

    }
}
