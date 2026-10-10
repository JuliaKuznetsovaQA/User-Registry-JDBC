package regAuth;

public class BdDaoFactory implements DaoFactory{
    @Override
    public UserDao createDao() {
        return new UserDaoImplBD();
    }
}
