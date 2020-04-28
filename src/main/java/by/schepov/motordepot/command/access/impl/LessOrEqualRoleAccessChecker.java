package by.schepov.motordepot.command.access.impl;

import by.schepov.motordepot.command.access.AccessChecker;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.exception.NullNeededRoleException;

public class LessOrEqualRoleAccessChecker implements AccessChecker {

    private final Role neededRole;

    public LessOrEqualRoleAccessChecker(Role neededRole){
        if(neededRole == null) throw new NullNeededRoleException();
        this.neededRole =  neededRole;
    }

    @Override
    public boolean canBeAccessedBy(Role role) {
        return role != null && role.getId() <= neededRole.getId();
    }
}
