package services.impl;

import dto.CategoryDTO;
import dto.UserDTO;
import mappers.Mapper;
import model.Category;
import model.User;
import repositories.UserRepository;
import services.UserService;

public class UserServiceImpl extends AbstractServiceImpl<UserDTO, User>
    implements UserService {

    public UserServiceImpl(Mapper<UserDTO, User> mapper, UserRepository userRepository) {
        super(mapper, userRepository);
    }

    @Override
    public UserDTO create(User entity) {
        for(User user: abstractRepository.getAll()){
            if(user.getEmail().equals(entity.getEmail())) {
                entity.setId(user.getId());
                return mapperDTO.toTarget(entity);
            }
        }
        entity.setId(abstractRepository.create(entity).getId());
        return mapperDTO.toTarget(entity);
    }
}
