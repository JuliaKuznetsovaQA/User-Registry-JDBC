package regAuth;

public class FileDaoFactory implements DaoFactory{
    @Override
    public UserDao createDao() {
        return new UserDaoImplFile();
    }
}
