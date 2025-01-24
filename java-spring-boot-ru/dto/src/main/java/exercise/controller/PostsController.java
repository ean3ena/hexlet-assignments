package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var postsDTO = posts.stream()
                .map(this::toDTO)
                .toList();
        return postsDTO;
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var postsDTO = toDTO(post);
        return postsDTO;
    }

    private PostDTO toDTO(Post post) {

        var dto = new PostDTO();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());

        var comments = commentRepository.findByPostId(post.getId());
        var commentsDTO = comments.stream()
                .map(c -> {
                    var commentDTO = new CommentDTO();
                    commentDTO.setId(c.getId());
                    commentDTO.setBody(c.getBody());
                    return commentDTO;
                })
                .toList();
        dto.setComments(commentsDTO);

        return dto;
    }
}
// END
