package com.thphatts.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class MainTest {
    @Test
    void shouldAddTwoNumbers(){
        int result = 2 + 3;
        assertEquals(5, result); // assertEquals(expected, actual): Kiểm tra kết quả mong đợi có bằng kết quả thực tế không.
    }
}