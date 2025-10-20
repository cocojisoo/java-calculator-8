package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;       
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 빈_문자열_입력() {
        assertSimpleTest(() -> {
            run(" ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 공백만_입력() {
        assertSimpleTest(() -> {
            run("   ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 단일_숫자() {
        assertSimpleTest(() -> {
            run("5");
            assertThat(output()).contains("결과 : 5");
        });
    }

    @Test
    void 기본_구분자_쉼표() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본_구분자_콜론() {
        assertSimpleTest(() -> {
            run("1:2:3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본_구분자_복합_혼용() {
        assertSimpleTest(() -> {
            run("1,2,3:4:5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_구분자_세미콜론() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_구분자_다른_문자() {
        assertSimpleTest(() -> {
            run("//#\\n10#12#14");
            assertThat(output()).contains("결과 : 36");
        });
    }

    @Test
    void 커스텀_구분자_특수문자_별표() {
        assertSimpleTest(() -> {
            run("//*\\n5*3*5");
            assertThat(output()).contains("결과 : 13");
        });
    }

    @Test
    void 두자리_이상_숫자() {
        assertSimpleTest(() -> {
            run("40,50:20");
            assertThat(output()).contains("결과 : 110");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
