package mappers.impl.dto;

import dto.CategoryDTO;
import dto.UserDTO;
import mappers.Mapper;
import model.Category;
import model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserDTOMapper implements Mapper<UserDTO, User> {

    private final ModelMapper modelMapper;

    public UserDTOMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public UserDTO toTarget(User source) {
        return modelMapper.map(source,
                UserDTO.class);
    }

    @Override
    public User toSource(UserDTO target) {
        return modelMapper.map(target, User.class);
    }

}
