package com.study.Pr03VM_mine;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
@Data
public class ProductRepository {
    public static List<Product> list = new ArrayList<>();
}
