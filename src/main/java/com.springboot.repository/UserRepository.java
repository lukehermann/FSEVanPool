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

    @Query(value = "select u.history from User u where u.id = :id")
    String getHistory(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update User u set u.history = :history where u.id = :id")
    void updateHistory(@Param("history") String history, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("update User u set u.sunday = :change where u.id = :id")
    void updateSunday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.monday = :change where u.id = :id")
    void updateMonday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.tuesday = :change where u.id = :id")
    void updateTuesday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.wednesday = :change where u.id = :id")
    void updateWednesday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.thursday = :change where u.id = :id")
    void updateThursday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.friday = :change where u.id = :id")
    void updateFriday(@Param("id") int id, @Param("change") boolean change);

    @Transactional
    @Modifying
    @Query("update User u set u.saturday = :change where u.id = :id")
    void updateSaturday(@Param("id") int id, @Param("change") boolean change);
}
