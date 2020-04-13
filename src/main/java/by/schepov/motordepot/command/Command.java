package by.schepov.motordepot.command;

import by.schepov.motordepot.exception.InvalidParameterException;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum Command {

    VIEW_ORDERS("view_orders", new ViewOrders(), 1),
    VIEW_CARS("view_cars", new ViewCars(), 1),
    VIEW_USERS("view_users", new ViewUsers(), 1),
    VIEW_REQUESTS("view_requests", new ViewRequests(), 1),
    LOG_OUT("log_out", new LogOut(), 3),
    VIEW_PROFILE("view_profile", new ViewProfile(), 3),
    LOG_IN("log_in", new LogIn(), 4),
    SIGN_UP("sign_up", new SignUp(),4);

    private String name;
    private int accessLevel;
    private Executable executable;

    Command(String name, Executable executable, int accessLevel){
        this.name = name;
        this.executable = executable;
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public Executable getExecutable() {
        return executable;
    }

    public String getName() {
        return name;
    }

    public Page execute(HttpServletRequest request, HttpServletResponse response){
        return executable.execute(request, response);
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
