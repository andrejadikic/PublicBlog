package com.example.publicblog.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Post {
    private Long id;
    @NonNull
    @NotNull(message = "Author field is required")
    @NotEmpty(message = "Author field is required")
    private String author;
    @NonNull
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;
    @NonNull
    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;
    private List<Comment> comments = new CopyOnWriteArrayList<>();

    public Post(Long id, @NonNull String author, @NonNull String title, @NonNull String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
