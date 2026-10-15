package regAuth.factory;

import regAuth.dao.UserDao;

public interface DaoFactory {

    UserDao createDao();
}
