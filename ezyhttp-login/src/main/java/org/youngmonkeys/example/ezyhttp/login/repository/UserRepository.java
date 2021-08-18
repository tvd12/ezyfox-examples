package org.youngmonkeys.example.ezyhttp.login.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.example.ezyhttp.login.entity.User;

@EzyRepository
public interface UserRepository extends EzyDatabaseRepository<Long, User> {
}