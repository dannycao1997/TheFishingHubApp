package com.fishinghub.fishinghub.controllertests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishinghub.fishinghub.controller.PostController;
import com.fishinghub.fishinghub.entity.Post;
import com.fishinghub.fishinghub.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    private Post post;

    @Before
    public void setUp() {
        post = new Post();
        post.setId(1L);
        post.setContent("Hello World");
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void createPostTest() throws Exception {
        given(postService.createPost(any(Post.class))).willReturn(post);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token") // Replace "token" with the actual token if required
                        .with(csrf())) // Include CSRF token in the request
                .andExpect(status().isCreated()) // Expecting 201 status code
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.content").value(post.getContent()));
    }



    @Test
    @WithMockUser
    public void getAllPostsTest() throws Exception {
        given(postService.getAllPosts()).willReturn(Arrays.asList(post));

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(post.getId()))
                .andExpect(jsonPath("$[0].content").value(post.getContent()));
    }

    @Test
    @WithMockUser
    public void getPostByIdTest() throws Exception {
        given(postService.getPostById(post.getId())).willReturn(post);

        mockMvc.perform(get("/api/posts/{id}", post.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.content").value(post.getContent()));
    }

    @Test
    @WithMockUser
    public void updatePostTest() throws Exception {
        given(postService.updatePost(any(Post.class))).willReturn(post);

        mockMvc.perform(put("/api/posts/{id}", post.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.content").value(post.getContent()));
    }

    @Test
    @WithMockUser
    public void deletePostTest() throws Exception {
        mockMvc.perform(delete("/api/posts/{id}", post.getId())
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(postService).deletePost(post.getId());
    }
}
