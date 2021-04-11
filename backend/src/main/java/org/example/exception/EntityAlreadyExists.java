package org.example.exception;

public class EntityAlreadyExists extends IllegalArgumentException {
    public EntityAlreadyExists() {
        super();
    }

    public EntityAlreadyExists(Class<?> name, Long id) {
        super("Entity " + name.getSimpleName() + " with id " + id + " already exists");
    }

}
