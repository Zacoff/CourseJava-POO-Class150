package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter department's name: ");
        String departmentName = sc.nextLine();
        System.out.print("Enter worker data \n");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Level: ");
        String workLevel = sc.nextLine().toUpperCase();
        System.out.print("Base Salary: ");
        double baseSalary = sc.nextDouble();

        Worker worker = new Worker(name, WorkerLevel.valueOf(workLevel), baseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker? ");
        int numberContracts = sc.nextInt();

        for (int i = 1; i <= numberContracts; i++) {
            System.out.printf("Enter contract #%d data: %n", i);
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.println();
        System.out.println("Enter month and year to calculate income: ");
        String monthAndYear[] = (sc.next()).split("/");
        int month = Integer.parseInt(monthAndYear[0]);
        int year = Integer.parseInt(monthAndYear[1]);
        System.out.printf("Name: %s %n", worker.getName());
        System.out.printf("Department: %s %n", worker.getDepartment().getName());
        System.out.printf("Income for %s: %.2f", Arrays.stream(monthAndYear).collect(Collectors.joining("/")), worker.income(year, month));
        
        sc.close();
    }
}
