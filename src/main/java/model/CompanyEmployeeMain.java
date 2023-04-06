package model;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyEmployeeMain {
  private static Scanner scanner = new Scanner(System.in);
  private static Connection connection = DBConnectionProvider.getInstance().getConnection();
    public static void main(String[] args) {
        System.out.println("please input company name,country");
        String companyStr = scanner.nextLine();
        String[] companyData = companyStr.split(",");
        Company company = new Company();
        company.setName(companyData[0]);
        company.setCountry(companyData[1]);
        saveCompanyTaDB(company);
        List<Company> companyList = getAllCompaniesFromDB();
        for (Company company1 : companyList) {
            System.out.println(company1);
        }
    }

    private static List<Company> getAllCompaniesFromDB() {
        List<Company> companyList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String country = resultSet.getString("country");
                String name = resultSet.getString("name");
                Company company = new Company();
                company.setName(name);
                company.setId(id);
                company.setCountry(country);
                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    private static void saveCompanyTaDB(Company company) {
        try {
            Statement statement = connection.createStatement();
         statement.executeUpdate("INSERT INTO company(name,country) " +
                 "VALUES('" + company.getName() + "' , '" + company.getCountry() + "');");
            System.out.println("Company inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
