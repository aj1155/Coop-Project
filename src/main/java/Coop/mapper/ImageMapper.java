package Coop.mapper;

import java.util.List;

import Coop.model.Image;

public interface ImageMapper {
    List<Image> selectByUserId(int userId);
    Image selectById(int id);
    void insert(Image image);

}
