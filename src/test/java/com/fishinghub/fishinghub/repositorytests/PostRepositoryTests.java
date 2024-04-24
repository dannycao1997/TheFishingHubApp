package com.fishinghub.fishinghub.repositorytests;
import com.fishinghub.fishinghub.entity.Post;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.PostRepository;
import com.fishinghub.fishinghub.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void setUp() {

        // setting up a user for the post tests

        testUser = new User();
        testUser.setUsername("fisssssssh123");
        testUser.setPassword("passssssword");
        testUser.setEmail("fishssss123@example.com");
        userRepository.save(testUser);
    }

    @Test
    public void testSavePost() {
        Post post = new Post();
        post.setContent("LIT fishing trip!");
        post.setUser(testUser);
        Post savedPost = postRepository.save(post);

        assertNotNull(savedPost);
        assertNotNull(savedPost.getId());
        assertEquals("LIT fishing trip!", savedPost.getContent());
        assertNotNull(savedPost.getUser());
    }

    @Test
    public void testFindPostById() {
        Post post = new Post();
        post.setContent("Bussin day at the lake");
        post.setUser(testUser);
        post = postRepository.save(post);

        Optional<Post> foundPost = postRepository.findById(post.getId());

        assertTrue(foundPost.isPresent());
        assertEquals("Bussin day at the lake", foundPost.get().getContent());
    }

    @Test
    public void testUpdatePost() {
        Post post = new Post();
        post.setContent("Just a regular degular day");
        post.setUser(testUser);
        post = postRepository.save(post);

        Post toUpdate = postRepository.findById(post.getId()).orElse(null);
        assertNotNull(toUpdate);

        toUpdate.setContent("Updated content lol");
        Post updatedPost = postRepository.save(toUpdate);

        assertEquals("Updated content lol", updatedPost.getContent());
    }

    @Test
    public void testDeletePost() {
        Post post = new Post();
        post.setContent("baddd fishing day");
        post.setUser(testUser);
        post = postRepository.save(post);

        assertNotNull(postRepository.findById(post.getId()));

        postRepository.delete(post);

        assertFalse(postRepository.findById(post.getId()).isPresent());
    }
}
