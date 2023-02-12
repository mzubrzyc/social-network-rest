package app.modules.user.post.domain;

public interface PostModificationPort {

    void savePost(Post post) throws Exception;

    void deletePost(Post post);
}
