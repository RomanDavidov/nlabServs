package com.example.nlabservs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionaresDAO {
    private static String BUILD_SERVS_AND_SPECIMENS =
            "select ce.text as company_ext_name from solution_med.company_ext ce" ;
   public List<String> findAll() {
        try (var connection = Connection_class.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(BUILD_SERVS_AND_SPECIMENS)) {
            var resultSet = preparedStatement.executeQuery();
            List<String> companies = new ArrayList<>();
            while (resultSet.next()) {
                companies.add(String.valueOf(buildDictionaries(resultSet).getCompany_ext_name()));
            }
            System.out.println(companies);
            return companies;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
    private static final DictionaresDAO INSTANCE = new DictionaresDAO();

    public static DictionaresDAO getInstance() {
        return INSTANCE;
    }

    private Companies buildDictionaries(ResultSet resultSet) throws SQLException {
        return new Companies(
                //resultSet.getInt("company_ext_id"),
                resultSet.getString("company_ext_name"));
    }
}
