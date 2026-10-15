package regAuth.command;

import regAuth.service.UserService;
import regAuth.ui.ListPanel;

public class DeleteUserCommand implements Command{
    private UserService userService;
    private ListPanel listPanel;
    private String loginToDelete;

    public DeleteUserCommand(UserService userService, ListPanel listPanel, String loginToDelete) {
        this.userService = userService;
        this.listPanel = listPanel;
        this.loginToDelete = loginToDelete;
    }

    @Override
    public void execute() {
        userService.deleteUser(loginToDelete);
        listPanel.refreshList();
    }
}
