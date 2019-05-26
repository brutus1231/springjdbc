package pl.sda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Override
    public List<Customer> findAll() {
        return null;
    }
}
