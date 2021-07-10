package utils.mappers;

import api.utils.mappers.CategoryMapper;
import dto.CategoryDTO;
import model.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.TypeToken;

import java.util.Collection;

public class CategoryMapperImpl implements CategoryMapper {

    private final ModelMapper mapper;

    public CategoryMapperImpl(){
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public CategoryDTO map(Category category){
      return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public Collection<CategoryDTO> mapCollection(Collection<Category> collection) {
        return mapper.map(collection, new TypeToken<Collection<CategoryDTO>>(){}.getType());
    }


}
