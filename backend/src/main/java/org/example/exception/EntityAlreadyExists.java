package org.example.exception;

public class EntityAlreadyExists extends IllegalArgumentException {
    public EntityAlreadyExists() {
        super();
    }

    public EntityAlreadyExists(Class<?> name, Object id) {
        super("Entity " + name.getSimpleName() + " with identificator " + id + " already exists");
    }

}
