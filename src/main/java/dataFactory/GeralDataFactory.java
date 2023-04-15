package dataFactory;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class GeralDataFactory extends DataFactory{
    //providePaginasETamanhosDePagina
    public static Stream<Arguments> provideIdsInvalidos() {
        return Stream.of(
            Arguments.of("" + faker.number().numberBetween(-100, -1)),
            Arguments.of("e"),
//                Arguments.of(""),
//                Arguments.of(" "),
//                Arguments.of(faker.numerify("# #")),
            Arguments.of(faker.numerify("#a#")),
            Arguments.of(faker.numerify("#.#")),
            Arguments.of(faker.numerify("#-#")),
            Arguments.of(faker.numerify("#+#")),
            Arguments.of(faker.numerify("#*#"))
        );
    }
    public static Stream<Arguments> providePaginasETamanhosDePaginaValidos() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(0, 10),
            Arguments.of(1, 20),
            Arguments.of(2, 30)
        );
    }
    public static Stream<Arguments> providePaginasETamanhosDePaginaInvalidos() {
        return Stream.of(
            Arguments.of("0", "0"),
            Arguments.of(""+faker.number().numberBetween(-100, -1), "0"),
            Arguments.of("0", faker.number().numberBetween(-100, -1) + ""),
            Arguments.of(""+faker.number().numberBetween(-100, -1), faker.number().numberBetween(-100, -1)+""),
            Arguments.of(faker.number().numberBetween(-100, -1)+"", faker.numerify("#+#")),
            Arguments.of(""+0, faker.numerify("#+#")),
            Arguments.of(faker.numerify("#+#"), faker.numerify("#+#"))
        );
    }
}
