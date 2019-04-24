package com.springboot.repository;

import com.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("update User u set u.routes = :routes where u.id = :id")
    void updateRoutes(@Param("routes") String routes, @Param("id") int id);

    @Query(value = "select u.routes from User u where u.id = :id")
    String getRoutes(@Param("id") int id);
}
