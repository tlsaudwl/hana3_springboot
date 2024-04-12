package com.study.Pr03VM_teacher.dao;

import com.study.Pr03VM_teacher.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IProductDao {
    public List<ProductDto> list();
    public int insertProduct(ProductDto product);
    public int deleteProduct(int index);
    public int updateProduct(ProductDto product, int index);
    public Optional<ProductDto> getProductById(int index);
}
