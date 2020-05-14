package by.schepov.motordepot.command.impl;

import by.schepov.motordepot.command.Action;
import by.schepov.motordepot.command.access.AccessChecker;
import by.schepov.motordepot.command.access.impl.EqualRoleAccessChecker;
import by.schepov.motordepot.command.access.impl.LessOrEqualRoleAccessChecker;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.exception.InvalidParameterException;
import by.schepov.motordepot.parameter.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum Command {

    SET_CAR_STATE_READY("set_car_state_ready", new SetCarStateReady(),
            new EqualRoleAccessChecker(Role.DRIVER), false),

    SET_CAR_STATE_BROKEN("set_car_state_broken", new SetCarStateBroken(),
            new EqualRoleAccessChecker(Role.DRIVER), false),

    REJECT_REQUEST("reject_request", new RejectRequest(),
            new EqualRoleAccessChecker(Role.ADMIN), false),

    REDIRECT("redirect", new Redirect(),
            new LessOrEqualRoleAccessChecker(Role.GUEST), true),

    UNBLOCK_USER("unblock", new UnblockUser(),
            new EqualRoleAccessChecker(Role.ADMIN), false),

    BLOCK_USER("block", new BlockUser(),
            new EqualRoleAccessChecker(Role.ADMIN), false),

    FINISH_ORDER("finish_order", new FinishOrder(),
            new EqualRoleAccessChecker(Role.DRIVER), false),

    REPORT_ORDER_COMPLETION("report_order_completion", new ReportOrderCompletion(),
            new EqualRoleAccessChecker(Role.DRIVER), true),

    VIEW_MY_CARS("view_my_cars", new ViewMyCars(), new EqualRoleAccessChecker(Role.DRIVER), true),

    VIEW_COMPLETED_ORDERS("view_completed_orders", new ViewCompletedOrders(),
            new EqualRoleAccessChecker(Role.DRIVER), true),

    VIEW_CURRENT_ORDER("view_current_order", new ViewCurrentOrder(),
            new EqualRoleAccessChecker(Role.DRIVER), true),

    VIEW_USER_ORDERS("view_user_orders", new ViewUserOrders(),
            new LessOrEqualRoleAccessChecker(Role.USER), true),

    VIEW_USER_REQUESTS("view_user_requests", new ViewUserRequests(),
            new LessOrEqualRoleAccessChecker(Role.USER), true),

    SUBMIT_ORDER("submit_order", new SubmitOrder(),
            new EqualRoleAccessChecker(Role.ADMIN), false),

    ASSIGN_CAR("assign_car", new AssignCar(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    VERIFY_REQUEST("verify_request", new VerifyRequest(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    CREATE_REQUEST("create_request", new CreateRequest(),
            new LessOrEqualRoleAccessChecker(Role.USER), false),

    VIEW_USER_DETAILS("user_details", new ViewUserDetails(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    VIEW_ORDERS("view_orders", new ViewOrders(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    VIEW_CARS("view_cars", new ViewCars(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    VIEW_USERS("view_users", new ViewUsers(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    VIEW_REQUESTS("view_requests", new ViewRequests(),
            new EqualRoleAccessChecker(Role.ADMIN), true),

    LOG_OUT("log_out", new LogOut(),
            new LessOrEqualRoleAccessChecker(Role.USER), false),

    VIEW_PROFILE("view_profile", new ViewProfile(),
            new LessOrEqualRoleAccessChecker(Role.USER), true),

    LOG_IN("log_in", new LogIn(),
            new LessOrEqualRoleAccessChecker(Role.GUEST), false),

    SIGN_UP("sign_up", new SignUp(),
            new LessOrEqualRoleAccessChecker(Role.GUEST), false);

    private final String name;
    private final Action action;
    private final AccessChecker accessChecker;
    private final boolean isIdempotent;

    Command(String name, Action action, AccessChecker accessChecker, boolean isIdempotent){
        this.name = name;
        this.action = action;
        this.accessChecker = accessChecker;
        this.isIdempotent = isIdempotent;
    }

    public AccessChecker getAccessChecker() {
        return accessChecker;
    }

    public String getName() {
        return name;
    }

    public boolean isIdempotent() {
        return isIdempotent;
    }

    public Page execute(HttpServletRequest request, HttpServletResponse response){
        return action.execute(request, response);
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
