package com.example.publicblog.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Comment {
    private Long id;
    @NonNull
    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    private String name;
    @NonNull
    @NotNull(message = "Comment field is required")
    @NotEmpty(message = "Comment field is required")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
