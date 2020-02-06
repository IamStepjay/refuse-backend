package com.oio.wms.services;

import com.oio.wms.domain.User;

public interface UserService extends CrudService<User, Long> {
    User findByUsername(String username);
}
