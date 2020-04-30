package com.example.shopping.constant;

import com.example.shopping.dto.Item;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Getter
public class Items {
    private List<Item> items = List.of(
            Item.builder().id(1).price(250).name("note").build(),
            Item.builder().id(2).price(300).name("pen").build(),
            Item.builder().id(3).price(100).name("eraser").build()
            );

}
