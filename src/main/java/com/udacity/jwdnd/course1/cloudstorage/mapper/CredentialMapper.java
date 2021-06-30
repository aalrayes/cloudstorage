package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

        @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
        Credential findCredentials(Integer credentialid);

        @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
        List<Credential> getAllCredential(Integer userid);

        @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username},#{key}, #{password},#{userid})")
        @Options(useGeneratedKeys = true, keyProperty = "credentialid")
        Integer insert(Credential credential);

        @Update("UPDATE CREDENTIALS SET url = #{url},password = #{password} WHERE credentialid = #{credentialid}")
        int update(Credential credential);

        @Delete("DELETE FROM CREDINTIALS WHERE credentialid = #{credentialid}")
        void delete(Integer credentialid);

}
