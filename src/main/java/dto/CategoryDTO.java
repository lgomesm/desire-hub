package dto;

import jakarta.validation.constraints.NotBlank;

public final class CategoryDTO {
    private final String id;
    @NotBlank(message = "Category name cannot be blank")
    private final String name;

    public CategoryDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
