package com.marjade.roniet;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class FashionAppTest {

    // mocks
    private MockedStatic<SpringApplication> springApplicationMockedStatic = mockStatic(SpringApplication.class);

    @Test
    public void test() {
        // given
        String[] args = { "test" };

        // when
        FashionApp.main(args);

        // then
        springApplicationMockedStatic.verify(() -> SpringApplication.run(FashionApp.class, args),
                times(1)
        );
    }
}
