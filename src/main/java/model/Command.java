package model;

public interface Command {
    String EXIT = "0";
    String ADD_COMPANY = "1";
    String ADD_EMPLOYEE = "2";
    String UPDATE_COMPANY_BY_ID = "3";
    String PRINT_ALL_COMPANIES = "4";
    String PRINT_COMPANIES_BY_COUNTRY = "5";
    String DELETE_COMPANIES_BY_ID = "6";
    String PRINT_ALL_EMPLOYEES = "7";
    String PRINT_EMPLOYEES_BY_COMPANY = "8";
    String DELETE_EMPLOYEE_BY_ID = "9";
    static void printCommands() {
        System.out.println("please input " + EXIT + " for exit");
        System.out.println("please input " + ADD_COMPANY + " for add company");
        System.out.println("please input " + ADD_EMPLOYEE + " FOR ADD EMPLOYEE");
        System.out.println("please input " + UPDATE_COMPANY_BY_ID + " for update company by  id");
        System.out.println("please input " + PRINT_ALL_COMPANIES + " for print all companies");
        System.out.println("please input " + PRINT_COMPANIES_BY_COUNTRY + " for print companies by country");
        System.out.println("please input " + DELETE_COMPANIES_BY_ID + " for delete company bi ID");
        System.out.println("please input " + PRINT_ALL_EMPLOYEES + " for print all employees");
        System.out.println("please input " + PRINT_EMPLOYEES_BY_COMPANY + " for print employees by company");
        System.out.println("please input " + DELETE_EMPLOYEE_BY_ID + " for delete employee by ID");
    }
}
