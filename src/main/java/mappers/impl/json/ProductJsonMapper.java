package mappers.impl.json;

import mappers.Mapper;
import model.Category;
import model.Product;
import org.apache.commons.lang3.StringUtils;
import utils.GsonUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class ProductJsonMapper implements Mapper<Product, String> {

    @Override
    public Product toTarget(String source) {

        return (Product) GsonUtils.deserialize(source, Product.class);
    }

    @Override
    public String toSource(Product target) {
        var categoriesOld = target.getCategories();
        if (categoriesOld!=null&&!categoriesOld.isEmpty()) {
           target.setCategories(categoriesOld.stream().map(categoryOld -> {
                        var categoryNew = new Category();
                        categoryNew.setId(categoryOld.getId());
                        return categoryNew;
                    }
            ).collect(Collectors.toList()));
        }
        String result = GsonUtils.serialize(target);
        target.setCategories(categoriesOld);
        return result;
    }
}
