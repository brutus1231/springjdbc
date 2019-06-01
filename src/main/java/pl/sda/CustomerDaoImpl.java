package pl.sda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

    @Autowired
    private DriverManagerDataSource dataSource;

    @PostConstruct
    public void init(){
        setDataSource(dataSource);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            Customer customer = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("AGE"))
                );
            }
            rs.close();
            ps.close();
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public List<Customer> findAllJdbcTemplate() {
        String sql = "SELECT * FROM CUSTOMER";
        List<Customer> customers = (List<Customer>)getJdbcTemplate().query(
                sql, new BeanPropertyRowMapper<>(Customer.class));
        return customers;
    }
}
