package org.youngmonkeys.example.ezyhttp.login.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.example.ezyhttp.login.entity.UserData;
import org.youngmonkeys.example.ezyhttp.login.entity.UserDataId;

@EzyRepository
public interface UserDataRepository extends EzyDatabaseRepository<UserDataId, UserData> {
}
