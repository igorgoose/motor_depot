package by.schepov.motordepot.command;

import by.schepov.motordepot.exception.InvalidParameterException;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum Command {

    LOG_IN("log_in", new LogIn(), 4),
    SIGN_UP("sign_up", new SignUp(),4),
    LANGUAGE("language", new Language(), 4),
    REDIRECT("redirect", new Redirect(), 4);

    private String name;
    private int accessLevel;
    private Executable command;

    Command(String name, Executable command, int accessLevel){
        this.name = name;
        this.command = command;
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public Executable getCommand() {
        return command;
    }

    public Page execute(HttpServletRequest request, HttpServletResponse response){
        return command.execute(request, response);
    }

    public static Command getCommandByName(String name){
        for (Command item: Command.values()) {
            if(item.name.equals(name)){
                return item;
            }
        }
        throw new InvalidParameterException("Parameter passed: " + name);
    }
}
