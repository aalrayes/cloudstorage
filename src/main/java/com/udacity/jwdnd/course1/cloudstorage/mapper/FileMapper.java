package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(String fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getAllFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize,userid,filedata) VALUES(#{notetitle}, #{notedescription}, #{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void delete(Integer fileId);
}