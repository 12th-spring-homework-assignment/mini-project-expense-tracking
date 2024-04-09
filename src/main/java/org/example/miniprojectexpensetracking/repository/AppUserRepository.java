package org.example.miniprojectexpensetracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectexpensetracking.configuration.UuidTypeHandler;
import org.example.miniprojectexpensetracking.model.AppUser;
import org.example.miniprojectexpensetracking.model.dto.request.AppUserRequest;
import org.example.miniprojectexpensetracking.model.dto.response.AppUserResponse;

import java.util.UUID;

@Mapper
public interface AppUserRepository {

    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "profileImage", column = "profile_image")
    })
    @Select("SELECT * FROM users WHERE email = #{email};")
    AppUser findByEmail(String email);

    @ResultMap("appUserMapper")
    @Select("INSERT INTO users (email, password, profile_image) VALUES (#{appUser.email}, #{appUser.password}, #{appUser.profileImage}) RETURNING *")
    AppUser register(@Param("appUser") AppUserRequest appUserRequest);

    @Update("UPDATE users SET password = #{password} WHERE email = #{email}")
    void forget(String email, String password);

    @Result(property = "userId", column = "user_id")
    @Result(property = "profileImage", column = "profile_image")
    @Select("SELECT user_id, email, profile_image FROM users WHERE user_id = #{userId}")
    AppUserResponse findUserById(UUID userId);
}
