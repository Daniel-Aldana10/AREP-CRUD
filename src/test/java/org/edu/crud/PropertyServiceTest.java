package org.edu.crud;

import org.edu.crud.model.Property;
import org.edu.crud.repository.PropertyRepository;
import org.edu.crud.service.PropertyServiceimpl;
import org.hibernate.PropertyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceimpl propertyService;

    private Property property;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(1L);
        property.setAddress("Calle 123");
        property.setPrice(200000.0);
        property.setSize(80.0);
        property.setDescription("Casa de prueba");
    }

    // ---------- CREATE ----------
    @Test
    void testCreate_ValidProperty() {
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property saved = propertyService.create(property);

        assertNotNull(saved);
        assertEquals("Calle 123", saved.getAddress());
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void testCreate_InvalidAddress_ThrowsException() {
        property.setAddress("  ");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> propertyService.create(property));
        assertEquals("Address is required", ex.getMessage());
        verify(propertyRepository, never()).save(any());
    }

    @Test
    void testCreate_InvalidPrice_ThrowsException() {
        property.setPrice(-50.0);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> propertyService.create(property));
        assertEquals("Price must be positive and not null", ex.getMessage());
    }

    @Test
    void testCreate_InvalidDescriptionTooLong_ThrowsException() {
        property.setDescription("x".repeat(201));

        Exception ex = assertThrows(IllegalArgumentException.class, () -> propertyService.create(property));
        assertEquals("The description is required and must not exceed 200 characters.", ex.getMessage());
    }

    // ---------- FIND ALL ----------
    @Test
    void testFindAll_ReturnsList() {
        when(propertyRepository.findAll()).thenReturn(Arrays.asList(property));

        List<Property> list = propertyService.findAll();

        assertEquals(1, list.size());
        verify(propertyRepository, times(1)).findAll();
    }

    // ---------- FIND BY ID ----------
    @Test
    void testFindById_Found() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Property found = propertyService.findById(1L);

        assertNotNull(found);
        assertEquals("Calle 123", found.getAddress());
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(propertyRepository.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(PropertyNotFoundException.class, () -> propertyService.findById(99L));
        assertEquals("Property with id 99 not found" , ex.getMessage());
    }

    // ---------- UPDATE ----------
    @Test
    void testUpdate_FoundAndUpdated() {
        Property updated = new Property();
        updated.setId(1L);
        updated.setAddress("Calle nueva");
        updated.setPrice(300000.0);
        updated.setSize(100.0);
        updated.setDescription("Casa actualizada");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(updated);

        Property result = propertyService.update(updated);

        assertEquals("Calle nueva", result.getAddress());
        assertEquals(300000.0, result.getPrice());
    }

    @Test
    void testUpdate_NotFound_ThrowsException() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(PropertyNotFoundException.class, () -> propertyService.update(property));
        assertEquals("Property with id 1 not found", ex.getMessage());
    }

    // ---------- DELETE ----------
    @Test
    void testDelete_CallsRepository() {
        propertyService.delete(1L);

        verify(propertyRepository, times(1)).deleteById(1L);
    }
}
