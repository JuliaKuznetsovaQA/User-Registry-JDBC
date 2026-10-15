package regAuth.factory;

import regAuth.dao.UserDao;
import regAuth.dao.UserDaoImplBD;

public class BdDaoFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
        return new UserDaoImplBD();
    }
}
