package by.schepov.motordepot.command;

public enum CommandName {

    LOG_IN(new LogIn(), 4),
    SIGN_UP(new SignUp(),4);

    private int accessLevel;
    private Command command;

    CommandName(Command command, int accessLevel){
        this.command = command;
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public Command getCommand() {
        return command;
    }
}
