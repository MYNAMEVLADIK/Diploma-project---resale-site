package ru.skypro.homework.service.mapping;

public interface MappingService<T, R> {

    /**
     * Замапили сущность в дто
     */
    T mapToDto(R entity);

    /**
     * Замапили дто в сущность
     */
    R mapToEntity(T dto);
}
