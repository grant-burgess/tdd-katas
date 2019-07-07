package com.grantburgess;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(HierarchicalContextRunner.class)
public class StackTest {
    private Stack stack;

    private void assertStackEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    private void assertStackSize(int expected) {
        assertFalse(stack.isEmpty());
        assertEquals(expected, stack.size());
    }

    @Before
    public void setUp() {
        stack = Stack.make(2);
    }

    @Test
    public void stack_is_empty_upon_creation() {
        assertStackEmpty();
    }

    @Test
    public void one_push_stack_is_not_empty_and_size_one() {
        stack.push(1);

        assertStackSize(1);
    }

    @Test
    public void one_push_one_pop_stack_is_empty() {
        stack.push(1);
        stack.pop();

        assertStackEmpty();
    }

    @Test(expected = Stack.Overflow.class)
    public void pushing_passed_limit_overflows() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test(expected = Stack.Underflow.class)
    public void popping_passed_limit_underflows() {
        stack.push(1);
        stack.pop();
        stack.pop();
    }

    @Test
    public void two_values_are_pushed_when_popping_size_is_one() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertStackSize(1);
    }

    @Test
    public void one_and_two_is_pushed_two_and_one_is_popped() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertStackEmpty();
    }

    @Test(expected = Stack.IllegalCapacity.class)
    public void stack_created_with_negative_size_throws_illegal_capacity() {
        new BoundedStack(-1);
    }

    @Test
    public void one_is_pushed_one_is_on_top() {
        stack.push(1);

        assertEquals(1, stack.top());
        assertStackSize(1);
    }

    @Test(expected = Stack.Empty.class)
    public void calling_top_on_empty_stack_throws_empty() {
        stack.top();
    }

    @Test
    public void one_two_pushed_find_one_two() {
        stack.push(1);
        stack.push(2);

        assertEquals(1, stack.find(1));
        assertEquals(0, stack.find(2));
    }

    @Test
    public void one_three_pushed_when_trying_to_find_two_returns_minus_one() {
        stack.push(1);
        stack.push(3);

        assertEquals(-1, stack.find(2));
    }

    public class zero_capacity_stack_has_a_pre_defined_behaviour {
        private Stack stack;

        @Before
        public void setUp() {
            stack = Stack.make(0);
        }

        @Test
        public void is_type_zero_capacity_stack() {
            assertEquals(ZeroCapacityStack.class, stack.getClass());
        }

        @Test(expected = Stack.Overflow.class)
        public void push_overflows() {
            stack.push(1);
        }

        @Test(expected = BoundedStack.Underflow.class)
        public void pop_underflows() {
            stack.pop();
        }

        @Test(expected = Stack.Empty.class)
        public void top_throws_empty() {
            stack.top();
        }

        @Test
        public void find_always_returns_minus_one() {
            assertEquals(-1, stack.find(2));
        }
    }
}
