package factory;

import dao.UserDAO;
import dao.UserDAOProxy;

public class DAOFactory {
	public static UserDAO getIUserDAOInstance() {
		return new UserDAOProxy();
	}
}
