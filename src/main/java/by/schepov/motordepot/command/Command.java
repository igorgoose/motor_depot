package by.schepov.motordepot.command;

import by.schepov.motordepot.exception.InvalidParameterException;
import by.schepov.motordepot.jsp.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum Command {

    REDIRECT("redirect", new Redirect(), 4),
    UNBLOCK_USER("unblock", new UnblockUser(), 1),
    BLOCK_USER("block", new BlockUser(), 1),
    FINISH_ORDER("finish_order", new FinishOrder(), 2),
    REPORT_ORDER_COMPLETION("report_order_completion", new ReportOrderCompletion(), 2),
    VIEW_MY_CARS("view_my_cars", new ViewMyCars(), 2),
    VIEW_COMPLETED_ORDERS("view_completed_orders", new ViewCompletedOrders(), 2),
    VIEW_CURRENT_ORDER("view_current_order", new ViewCurrentOrder(), 2),
    VIEW_USER_ORDERS("view_user_orders", new ViewUserOrders(), 3),
    VIEW_USER_REQUESTS("view_user_requests", new ViewUserRequests(), 3),
    SUBMIT_ORDER("submit_order", new SubmitOrder(), 1),
    ASSIGN_CAR("assign_car", new AssignCar(), 1),
    VERIFY_REQUEST("verify_request", new VerifyRequest(), 1),
    CREATE_REQUEST("create_request", new CreateRequest(), 3),
    VIEW_USER_DETAILS("user_details", new ViewUserDetails(), 1),
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
