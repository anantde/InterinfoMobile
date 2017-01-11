package mConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MobMyConnection {
	Connection connection;
	public Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/ii");
			connection = dataSource.getConnection();

		} catch (NamingException e) {
			System.out.println("Nameing Exception...!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException...!");
			e.printStackTrace();
		}
		return connection;
	}
}
