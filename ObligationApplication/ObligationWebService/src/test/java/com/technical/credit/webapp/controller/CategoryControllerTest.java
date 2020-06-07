package com.technical.credit.webapp.controller;


import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CategoryControllerTest extends AbstractControllerTest {

    @MockBean
    CategoryFacade categoryFacade;

    @Test
    @WithMockUser(authorities = "create_category")
    public void testSave() throws Exception {
        final CategoryData requestCategoryData = new CategoryData();
        final CategoryData responseCategoryData = new CategoryData();
        final String jsonBody = parseToJson(requestCategoryData);
        final String url = "/api/v1/categories";

        when(categoryFacade.save(any(CategoryData.class))).thenReturn(responseCategoryData);

        mvc.perform(MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)).andExpect(MockMvcResultMatchers.status().isCreated());
        verify(categoryFacade).save(requestCategoryData);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "create_profile")
    public void testSaveWithNotExistAttribute() throws Exception {
        final CategoryData requestCategoryData = new CategoryData();
        final String jsonBody = parseToJson(requestCategoryData);
        final String url = "/api/v1/categories";

        when(categoryFacade.save(any(CategoryData.class))).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)).andExpect(MockMvcResultMatchers.status().isBadRequest());
        verify(categoryFacade).save(requestCategoryData);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "read_category")
    public void testSearchById() throws Exception {
        final String url = "/api/v1/categories/1";
        final CategoryData categoryData = new CategoryData();

        when(categoryFacade.search(1L)).thenReturn(categoryData);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(categoryFacade).search(1L);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "read_category")
    public void testSearchByIdNotFound() throws Exception {
        final String url = "/api/v1/categories/1";

        when(categoryFacade.search(1L)).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(categoryFacade).search(1L);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "delete_categories")
    public void testSuccessfulDeleteBookById() throws Exception {
        final String url = "/api/v1/categories/1";

        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(categoryFacade).delete(1L);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "delete_category")
    public void testAttemptToDeleteNotExistBook() throws Exception {
        final String url = "/api/v1/categories/1";

        doThrow(ModelNotFoundException.class).when(categoryFacade).delete(1L);

        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(categoryFacade).delete(1);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "read_category")
    public void testSearchByQuery() throws Exception {
        final String url = "/api/v1/categories/search";
        final String query = "Free text";
        final String sortField = "Sort field";
        final boolean desc = true;
        final int page = 0;
        final int limit = 100;
        List<CategoryData> categories = Collections.singletonList(new CategoryData());

        when(categoryFacade.search(query, sortField, desc, page, limit)).thenReturn(categories);

        final String responseBody = mvc.perform(MockMvcRequestBuilders.get(url)
                .param("query", query)
                .param("sortField", sortField)
                .param("desc", Boolean.toString(desc))
                .param("page", "0")
                .param("limit", "100"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(1, parseFromJson(responseBody, ArrayList.class).size());
        verify(categoryFacade).search(query, sortField, desc, page, limit);
        verifyNoMoreInteractions(categoryFacade);
    }

    @Test
    @WithMockUser(authorities = "read_category_by_parentId")
    public void testSearchByParentCategoryId() throws Exception {
        final String url = "/api/v1/categories/searchByParentId/1";
        Set<CategoryData> categories = new HashSet<>();
        categories.add(new CategoryData());

        when(categoryFacade.findByParentCategoryId(1L)).thenReturn(categories);
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(categoryFacade).search(1L);
        verifyNoMoreInteractions(categoryFacade);
    }

}