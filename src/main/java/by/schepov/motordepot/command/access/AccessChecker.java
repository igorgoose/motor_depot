package by.schepov.motordepot.command.access;

import by.schepov.motordepot.entity.Role;

public interface AccessChecker {
    boolean canBeAccessedBy(Role role);
}
