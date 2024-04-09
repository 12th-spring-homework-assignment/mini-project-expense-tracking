package org.example.miniprojectexpensetracking.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectexpensetracking.configuration.UuidTypeHandler;
import org.example.miniprojectexpensetracking.model.Otp;
import org.example.miniprojectexpensetracking.model.dto.request.OtpRequest;

import java.util.UUID;

@Mapper
public interface OtpRepository {

    @Select("SELECT * FROM otps WHERE user_id = #{userId} ORDER BY issued_at DESC LIMIT 1")
    @Results(id = "otpMapper", value = {
            @Result(property = "otpId", column = "otp_id"),
            @Result(property = "otpCode", column = "otp_code"),
            @Result(property = "issuedAt", column = "issued_at")
    })
    Otp findOptByUserId(UUID userId);

    @Insert("INSERT INTO otps (otp_code, issued_at, expiration, verify, user_id) VALUES (#{otp.otpCode}, #{otp.issuedAt}, #{otp.expiration}, #{otp.verify}, #{otp.userId})")
    void saveOpt(@Param("otp") OtpRequest otpRequest);

    @Select("SELECT * FROM otps WHERE otp_code = #{otpCode}")
    @ResultMap("otpMapper")
    Otp findOtpByOtpCode(String otpCode);

    @Update("UPDATE otps SET verify = true WHERE otp_code = #{otpCode}")
    void verify(String otpCode);
}
