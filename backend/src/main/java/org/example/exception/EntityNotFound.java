package org.example.exception;

public class EntityNotFound extends IllegalArgumentException {
    public EntityNotFound() {
        super();
    }

    public EntityNotFound(Class<?> name, Object id) {
        super("Entity " + name.getSimpleName() + " with identificator " + id + " not found");
    }

}
