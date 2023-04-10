package manager;

import db.DBConnectionProvider;
import model.Company;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Company getById(int id) {
        Company company = new Company();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company where id = " + id);
            if (resultSet.next()) {
                return getCompanyByResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company");
            while (resultSet.next()) {
                companyList.add(getCompanyByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }
    public List<Company> getCountry(String country) {
        List<Company> companyList = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM company WHERE  COUNTRY = ?")) {
            ps.setString(1,country);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                companyList.add(getCompanyByResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    private Company getCompanyByResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setName(resultSet.getString("name"));
        company.setId(resultSet.getInt("id"));
        company.setCountry(resultSet.getString("country"));
        return company;
    }

    public void save(Company company) {
        String sql = "INSERT INTO company(name,country) values(?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // statement.executeUpdate("INSERT INTO company(name,country) " +
            //      "VALUES('" + company.getName() + "' , '" + company.getCountry() + "');");
            //  String sql = "INSERT INTO company(name,country) VALUES('%s', '%s'";
            ps.setString(1, company.getName());
            ps.setString(2, company.getCountry());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                company.setId(generatedKeys.getInt(1));
            }
            System.out.println("Company inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeById(int companyId) {
        String sql = "DELETE FROM company WHERE id = " + companyId;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Company company) {
        String sql = "UPDATE company SET name = '%s', country = '%s' WHERE id = %d";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(sql, company.getName(), company.getCountry(), company.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
