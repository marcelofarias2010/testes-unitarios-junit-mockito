package br.com.farias.business;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

public class ListWithBDDTest {

    @Test
    // testando uma lista de simulação quando o tamanho é chamado. Deve retornar 10
    void testMockingList_When_SizeIsCalled_ShouldReturn10(){
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        BDDMockito.given(list.size()).willReturn(10);
        // When / Act & // Then / Assert
        assertThat( list.size(),is(10));
    }

    @Test
        // testando uma lista de simulação quando o tamanho é chamado. Deve retornar multiplos valores
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues(){
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        BDDMockito.given(list.size()).willReturn(10).willReturn(20);
        // When / Act & // Then / Assert
        assertThat(list.size(),is(10));
        assertThat(list.size(),is(20));
        assertThat(list.size(),is(20));
    }

    @Test
        // testando uma lista de simulação quando o Get é chamado. Deve retornar Farias
    void testMockingList_When_GetIsCalled_ShouldReturnFarias(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        BDDMockito.given(list.get(0)).willReturn("Farias");
        // When / Act & // Then / Assert
        assertThat(list.get(0),is("Farias"));
        assertNull(list.get(1));
    }

    @Test
        // testando uma lista de simulação quando o Get é chamado com argumento matcher. Deve retornar Farias
    void testMockingList_When_GetIsCalled_With_ArgumentMatcher_ShouldReturnFarias(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        BDDMockito.given(list.get(Mockito.anyInt())).willReturn("Farias");
        // When / Act & // Then / Assert
        assertThat(list.get(Mockito.anyInt()),is("Farias"));

    }

    @Test
        // testando uma lista de simulação quando o Get é chamado com argumento matcher. Deve retornar Farias
    void testMockingList_When_ThrowsAnException(){
        // Given / Arrange
        var list = Mockito.mock(List.class);
        BDDMockito.given(list.get(Mockito.anyInt())).willThrow(new RuntimeException("Foo Bar"));
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
