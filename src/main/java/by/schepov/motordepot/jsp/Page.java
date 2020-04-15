package by.schepov.motordepot.jsp;

public enum Page {

    MANAGEMENT("/jsp/management/management.jsp"),
    MANAGEMENT_REQUESTS("/jsp/management/management_requests.jsp"),
    MANAGEMENT_USERS("/jsp/management/management_users.jsp"),
    MANAGEMENT_CARS("/jsp/management/management_cars.jsp"),
    MANAGEMENT_ORDERS("/jsp/management/management_orders.jsp"),
    HOME("index.jsp"),
    AUTHORIZE("/jsp/authorize.jsp"),
    SIGN_UP("/jsp/signup.jsp"),
    PROFILE("/jsp/profile.jsp"),
    ERROR("/jsp/error.jsp"), USER_DETAILS("/jsp/functions/user_details.jsp");

    private String name;

    Page(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
