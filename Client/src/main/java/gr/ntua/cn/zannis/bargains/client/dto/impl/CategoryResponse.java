package gr.ntua.cn.zannis.bargains.client.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.ntua.cn.zannis.bargains.client.dto.meta.Meta;
import gr.ntua.cn.zannis.bargains.client.persistence.entities.Category;

import java.util.List;

/**
 * The wrapper class for {@link gr.ntua.cn.zannis.bargains.client.persistence.entities.Category} related
 * requests.
 * @author zannis <zannis.kal@gmail.com
 */
public class CategoryResponse extends RestResponseImpl<Category> {

    public CategoryResponse(@JsonProperty("category") Category category,
                            @JsonProperty("categories") List<Category> categories,
                            @JsonProperty("meta") Meta meta) {
        this.item = category;
        this.items = categories;
        this.meta = meta;
    }
}