package com.artemsilantev.core.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.service.impl.CategoryServiceImpl;
import java.util.Collection;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Spy
  @InjectMocks
  CategoryServiceImpl categoryService;

  @Mock
  Mapper<CategoryDto, Category> mapper;

  @Mock
  CategoryRepository categoryRepository;

  @Test
  void testCreateNameExist() {
    var categoryDto = new CategoryDto(null, "testName", "", null);
    when(categoryRepository.isNameExists(anyString()))
        .thenReturn(true);
    assertThrows(IllegalEntityException.class, () ->
        categoryService.create(categoryDto));
  }

  @Test
  void testCreateNameNotExist() {
    var categoryDto = new CategoryDto(null, "testName", "", null);
    var expected = new CategoryDto(1L, "testName",
        "", null);
    when(categoryRepository.isNameExists(anyString()))
        .thenReturn(false);
    when(mapper.toTarget(any()))
        .thenReturn(expected);
    var actual = categoryService.create(categoryDto);
    assertEquals(expected, actual);
  }

  @Test
  void testUpdateNotAvailableName() {
    var categoryDto = new CategoryDto(1L, "testName", "", null);
    when(categoryRepository.isNameExists(anyString(), anyLong()))
        .thenReturn(true);
    assertThrows(IllegalEntityException.class, () ->
        categoryService.update(categoryDto));
  }

  @Test
  void testUpdateAvailableName() {
    var categoryDto = new CategoryDto(1L, "testName", "", null);
    var expected = new CategoryDto(1L, "testName",
        "", null);
    when(categoryRepository.isNameExists(anyString(), anyLong()))
        .thenReturn(false);
    when(mapper.toTarget(any()))
        .thenReturn(expected);
    var actual = categoryService.update(categoryDto);
    assertEquals(expected, actual);
  }

  @Test
  void testGetChildrenParentWithoutChildren() {
    when(categoryRepository.getChildrenCategories())
        .thenReturn(Lists.emptyList());
    var actual = categoryService.getChildren(1L);
    assertTrue(actual.isEmpty());
  }

  @Test
  void testGetChildrenParentWithChildren() {
    var categoryParent = new Category(null, null, null);
    categoryParent.setId(1L);
    var categoryChildren = new Category(null, null, categoryParent);
    var categoryChildren2 = new Category(null, null, new Category());
    var categoryDto = new CategoryDto();
    Collection<CategoryDto> expected = List.of(categoryDto);

    when(categoryRepository.get(anyLong()))
        .thenReturn(categoryParent);
    when(categoryRepository.getChildrenCategories())
        .thenReturn(List.of(categoryChildren, categoryChildren2));
    when(mapper.toTarget(any())).thenReturn(categoryDto);

    var actual = categoryService.getChildren(1L);
    assertEquals(expected, actual);
  }

}
