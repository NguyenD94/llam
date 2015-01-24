package .repository;

import .domain.User;


public interface UserRepository extends <User,>{

    @Query("select user from User user left join fetch user.authoritys where user.id =:id")
    User findOneWithEagerRelationships(@Param("id") Long id);

}
