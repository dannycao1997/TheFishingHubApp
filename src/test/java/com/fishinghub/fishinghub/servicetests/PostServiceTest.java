package com.fishinghub.fishinghub.servicetests;
import com.fishinghub.fishinghub.entity.Post;
import com.fishinghub.fishinghub.repository.PostRepository;
import com.fishinghub.fishinghub.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        post = new Post();
        post.setId(1L);
        post.setContent("First post about fishing");
    }

    @Test
    public void testCreatePost() {
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post createdPost = postService.createPost(post);
        assertNotNull(createdPost);
        assertEquals(Long.valueOf(1), createdPost.getId());
        verify(postRepository).save(post);
    }

    @Test
    public void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post));
        List<Post> posts = postService.getAllPosts();
        assertFalse(posts.isEmpty());
        assertEquals(1, posts.size());
        verify(postRepository).findAll();
    }

    @Test
    public void testGetPostById() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post foundPost = postService.getPostById(1L);
        assertNotNull(foundPost);
        assertEquals("First post about fishing", foundPost.getContent());
        verify(postRepository).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetPostByIdNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        postService.getPostById(1L);
    }

    @Test
    public void testUpdatePost() {
        when(postRepository.save(post)).thenReturn(post);
        post.setContent("Updated post content");
        Post updatedPost = postService.updatePost(post);
        assertNotNull(updatedPost);
        assertEquals("Updated post content", updatedPost.getContent());
        verify(postRepository).save(post);
    }

    @Test
    public void testDeletePost() {
        doNothing().when(postRepository).deleteById(1L);
        postService.deletePost(1L);
        verify(postRepository).deleteById(1L);
    }
}
