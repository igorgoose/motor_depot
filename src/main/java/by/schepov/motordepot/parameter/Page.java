package by.schepov.motordepot.parameter;

import by.schepov.motordepot.command.access.AccessChecker;
import by.schepov.motordepot.command.access.impl.EqualRoleAccessChecker;
import by.schepov.motordepot.command.access.impl.LessOrEqualRoleAccessChecker;
import by.schepov.motordepot.entity.Role;

public enum Page {

    FINISH_ORDER("/jsp/functions/finish_order.jsp", new EqualRoleAccessChecker(Role.DRIVER)),
    REQUEST_VERIFICATION("/jsp/functions/request_verification.jsp", new EqualRoleAccessChecker(Role.ADMIN)),
    MANAGEMENT("/jsp/management/management.jsp", new LessOrEqualRoleAccessChecker(Role.DRIVER)),
    MANAGEMENT_REQUESTS("/jsp/management/management_requests.jsp", new LessOrEqualRoleAccessChecker(Role.DRIVER)),
    MANAGEMENT_USERS("/jsp/management/management_users.jsp", new EqualRoleAccessChecker(Role.ADMIN)),
    MANAGEMENT_CARS("/jsp/management/management_cars.jsp", new LessOrEqualRoleAccessChecker(Role.DRIVER)),
    MANAGEMENT_ORDERS("/jsp/management/management_orders.jsp", new LessOrEqualRoleAccessChecker(Role.DRIVER)),
    HOME("/index.jsp", new LessOrEqualRoleAccessChecker(Role.GUEST)),
    AUTHORIZE("/jsp/authorize.jsp", new LessOrEqualRoleAccessChecker(Role.GUEST)),
    SIGN_UP("/jsp/signup.jsp", new LessOrEqualRoleAccessChecker(Role.GUEST)),
    ERROR("/jsp/error.jsp", new LessOrEqualRoleAccessChecker(Role.GUEST)),
    USER_DETAILS("/jsp/functions/user_details.jsp", new LessOrEqualRoleAccessChecker(Role.USER)),
    SUBMIT_ORDER("/jsp/functions/submit_order.jsp", new EqualRoleAccessChecker(Role.ADMIN));

    private final String name;
    private final AccessChecker accessChecker;

    Page(String name, AccessChecker accessChecker){
        this.accessChecker = accessChecker;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AccessChecker getAccessChecker() {
        return accessChecker;
    }
}
