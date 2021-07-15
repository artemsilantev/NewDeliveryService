package mappers.impl.json;
import mappers.Mapper;
import model.Shop;
import utils.GsonUtils;

public class ShopJsonMapper implements Mapper<Shop, String> {

    @Override
    public Shop toTarget(String source)
    {
        return (Shop) GsonUtils.deserialize(source, Shop.class);
    }

    @Override
    public String toSource(Shop target) {
        return GsonUtils.serialize(target);
    }
}
