package br.com.farias.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ListTest {

    @Test
    // testando uma lista de simulação quando o tamanho é chamado. Deve retornar 10
    void testMockingList_When_SizeIsCalled_ShouldReturn10(){
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        Mockito.when(list.size()).thenReturn(10);
        // When / Act & // Then / Assert
        Assertions.assertEquals(10, list.size());
    }

    @Test
        // testando uma lista de simulação quando o tamanho é chamado. Deve retornar multiplos valores
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues(){
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        Mockito.when(list.size()).thenReturn(10).thenReturn(20);
        // When / Act & // Then / Assert
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals(20, list.size());
        Assertions.assertEquals(20, list.size());
    }

    @Test
        // testando uma lista de simulação quando o Get é chamado. Deve retornar Farias
    void testMockingList_When_GetIsCalled_ShouldReturnFarias(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        Mockito.when(list.get(0)).thenReturn("Farias");
        // When / Act & // Then / Assert
        Assertions.assertEquals("Farias", list.get(0));
        Assertions.assertNull(list.get(1));
    }

    @Test
        // testando uma lista de simulação quando o Get é chamado com argumento matcher. Deve retornar Farias
    void testMockingList_When_GetIsCalled_With_ArgumentMatcher_ShouldReturnFarias(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn("Farias");
        // When / Act & // Then / Assert
        Assertions.assertEquals("Farias", list.get(Mockito.anyInt()));

    }

    @Test
        // testando uma lista de simulação quando o Get é chamado com argumento matcher. Deve retornar Farias
    void testMockingList_When_ThrowsAnException(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt())).thenThrow(new RuntimeException("Foo Bar"));
        // When / Act & // Then / Assert
        Assertions.assertThrows(
                RuntimeException.class,
                // When / Act
                () -> {
                    list.get(Mockito.anyInt());
                    },
                () -> "Should have throw an RuntimeException"
        );

    }
}
