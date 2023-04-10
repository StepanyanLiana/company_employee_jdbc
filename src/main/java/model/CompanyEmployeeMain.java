package model;

import db.DBConnectionProvider;
import manager.CompanyManager;
import manager.EmployeeManager;

import java.awt.event.HierarchyBoundsAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyEmployeeMain implements Command {
  private static Scanner scanner = new Scanner(System.in);
  private static CompanyManager companyManager = new CompanyManager();
  private static EmployeeManager employeeManager = new EmployeeManager();
    public static void main(String[] args) {
       boolean isRun = true;
       while (isRun) {
           Command.printCommands();
           String command = scanner.nextLine();
           switch (command) {
               case EXIT:
                   isRun = false;
                   break;
               case ADD_COMPANY:
                   addCompany();
                    break;
               case ADD_EMPLOYEE:
                   addEmployee();
                   break;
               case UPDATE_COMPANY_BY_ID:
                   updateCompanyById();
                   break;
               case PRINT_ALL_COMPANIES:
                   printAllCompanies();
                   break;
               case PRINT_COMPANIES_BY_COUNTRY:
                   printAllCompaniesByCountry();
                   break;
               case DELETE_COMPANIES_BY_ID:
                   deleteCompanyById();
                   break;
               case PRINT_ALL_EMPLOYEES:
                   printAllEmployees();
                   break;
               case PRINT_EMPLOYEES_BY_COMPANY:
                   printEmployeesByCompanyId();
                   break;
               case DELETE_EMPLOYEE_BY_ID:
                   deleteEmployeeById();
                   break;
               default:
                   System.out.println("invalid Command");
           }
       }
    }

    private static void updateCompanyById() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
        System.out.println("please choose company id");
        int id = Integer.parseInt(scanner.nextLine());
        if (companyManager.getById(id) != null) {
            System.out.println("please input company name,country");
            String companyStr = scanner.nextLine();
            String[] companyData = companyStr.split(",");
            Company company = new Company();
            company.setName(companyData[0]);
            company.setCountry(companyData[1]);
            companyManager.update(company);
            System.out.println("company was updated!");
        } else {
            System.out.println("company does not exists!");
        }
    }

    private static void deleteCompanyById() {
        List<Company> companyList = companyManager.getAll();
        for (Company company : companyList) {
            System.out.println(company);
        }
        System.out.println("please choose company id");
       int id = Integer.parseInt(scanner.nextLine());
       companyManager.removeById(id);
        System.out.println("company removed");
    }

    private static void printEmployeesByCompanyId() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
            System.out.println("please choose company id");
           int id = Integer.parseInt(scanner.nextLine());
            Company company1 = companyManager.getById(id);
            if (company1 != null) {
                List<Employee> allByCompanyId = employeeManager.getAllByCompanyId(id);
                for (Employee employee : allByCompanyId) {
                    System.out.println(employee);
                }
            }
        }
    }

    private static void deleteEmployeeById() {
        List<Employee> all = employeeManager.getAll();
        for (Employee employee : all) {
            System.out.println(employee);
        }
        System.out.println("please choose employee id");
       int id = Integer.parseInt(scanner.nextLine());
       employeeManager.removeById(id);
        System.out.println("employee removed");
    }
    private static void printAllEmployees() {
        List<Employee> all = employeeManager.getAll();
        for (Employee employee : all) {
            System.out.println(employee);
        }
    }

    private static void printAllCompaniesByCountry() {
        System.out.println("please input country");
        String country = scanner.nextLine();
        List<Company> byCompany = companyManager.getCountry(country);
        for (Company company : byCompany) {
            System.out.println(company);
        }
    }

    private static void printAllCompanies() {
        List<Company> all= companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
    }

    private static void addEmployee() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
        System.out.println("please choose companyId");
        int id = Integer.parseInt(scanner.nextLine());
        Company company = companyManager.getById(id);
        if (company != null) {
            System.out.println("please input employee name,surname,email");
            String employeeStr = scanner.nextLine();
            String[] employeeData = employeeStr.split(",");
            Employee employee = new Employee();
            employee.setCompany(company);
            employee.setName(employeeData[0]);
            employee.setSurname(employeeData[1]);
            employee.setEmail(employeeData[2]);
            employeeManager.save(employee);

        }
    }

    private static void addCompany() {
        System.out.println("please input company name,country");
        String companyStr = scanner.nextLine();
        String[] companyData = companyStr.split(",");
        Company company = new Company();
        company.setName(companyData[0]);
        company.setCountry(companyData[1]);
        companyManager.save(company);

    }

}
