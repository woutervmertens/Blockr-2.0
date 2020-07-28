package com.swop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.View;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelTest {
    ViewModel vm;

    @BeforeEach
    void setUp() {
        vm = new MainViewModel(new Point(0,0),10,10,null);
    }

    @Test
    void isWithin() {
        assertTrue(vm.isWithin(5,5));
        assertFalse(vm.isWithin(11,11));
    }
}