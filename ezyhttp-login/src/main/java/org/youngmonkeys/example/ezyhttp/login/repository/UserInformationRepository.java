package org.youngmonkeys.example.ezyhttp.login.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;

@EzyRepository
public interface UserInformationRepository extends EzyDatabaseRepository<Long, UserInformation> {
}