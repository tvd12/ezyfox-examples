package org.youngmonkeys.example.ezyhttp.login.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.example.ezyhttp.login.entity.AccessToken;

import java.time.LocalDateTime;
import java.util.List;

@EzyRepository
public interface AccessTokenRepository extends EzyDatabaseRepository<String, AccessToken> {
    List<AccessToken> findByExpireAtAndFirstIssueAt(LocalDateTime expireAt, LocalDateTime firstIssueAt);

    @EzyQuery(value = "Select count(e) from AccessToken e Where e.userId = ?0", nativeQuery = true)
    int countByUserId(long userId);
}
