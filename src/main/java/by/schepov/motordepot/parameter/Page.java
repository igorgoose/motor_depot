package by.schepov.motordepot.parameter;

public enum Page {

    FINISH_ORDER("/jsp/functions/finish_order.jsp"),
    REQUEST_VERIFICATION("/jsp/functions/request_verification.jsp"),
    MANAGEMENT("/jsp/management/management.jsp"),
    MANAGEMENT_REQUESTS("/jsp/management/management_requests.jsp"),
    MANAGEMENT_USERS("/jsp/management/management_users.jsp"),
    MANAGEMENT_CARS("/jsp/management/management_cars.jsp"),
    MANAGEMENT_ORDERS("/jsp/management/management_orders.jsp"),
    HOME("/index.jsp"),
    AUTHORIZE("/jsp/authorize.jsp"),
    SIGN_UP("/jsp/signup.jsp"),
    PROFILE("/jsp/profile.jsp"),
    ERROR("/jsp/error.jsp"),
    USER_DETAILS("/jsp/functions/user_details.jsp"),
    SUBMIT_ORDER("/jsp/functions/submit_order.jsp");

    private final String name;

    Page(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}