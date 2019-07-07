# Stack kata

The classic Stack kata.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/stack/src/main/java/com/grantburgess/BoundedStack.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/stack/src/test/java/com/grantburgess/StackTest.java)


Tips
- don't go for the gold, slowly build up your tests and production code
- remember, Red, Green and Refactor. Only write enough code to pass the test, no refactoring while trying to go green. 
- Don't only refactor your production code but test code as well
- keep it simple, if you get stuck then back track a couple of steps and try again.

### Tests
- stack empty upon creation
- one push and stack is not empty and has size one
- one push and one pop, stack is empty
- pushing passed limit overflows
- popping passed limit underflows
- two values pushed, popping one, size is one
  - this is likely the first point where you need to store the element pushed. See if you can get away with not using an array
- pushing one and two, two and one is popped
  - this is the point where you need to use an array. See if you can do this with the least amount of key strokes.
- stack created with negative capacity throws illegal capacity
- pushing one, calling top returns one, stack is still size one
- calling top on an empty stack throws empty
- pushing one and two, can find one and two. Returns the indices of the items
- pushing one and three and trying to find two returns minus one


### Nice to have tests
A zero capacity stack has a pre-defined behaviour. We may choose to extract an interface and split out into a bounded and zero capacity stack.

I chose to use the [Hierarchical Context Runner](https://github.com/bechte/junit-hierarchicalcontextrunner) within the test class for the zero capacity stack