package regAuth.factory;

import regAuth.dao.UserDao;
import regAuth.dao.UserDaoImplFile;

public class FileDaoFactory implements DaoFactory {
    @Override
    public UserDao createDao() {
        return new UserDaoImplFile();
    }
}
