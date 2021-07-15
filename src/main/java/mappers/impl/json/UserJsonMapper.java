package mappers.impl.json;
import mappers.Mapper;
import model.User;
import utils.GsonUtils;

public class UserJsonMapper implements Mapper<User, String> {

    @Override
    public User toTarget(String source)
    {
        return (User) GsonUtils.deserialize(source, User.class);
    }

    @Override
    public String toSource(User target) {
        return GsonUtils.serialize(target);
    }
}
