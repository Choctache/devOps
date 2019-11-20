package ch.noseryoung.devOps.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class RootControllerTest {

    @Test
    void getAnswer() {
        Assertions.assertThat(1).isEqualTo(1);
    }
}