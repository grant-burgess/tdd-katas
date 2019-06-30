# Diamond kata

After inputting a desired size, prints out a diamond to the console.

Demonstrates how to incrementally build and test instead of "going for the gold" as Uncle Bob says.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/diamond/src/main/java/com/grantburgess/Diamond.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/diamond/src/test/java/com/grantburgess/DiamondTest.java)

  
### Tests
- zero length diamond
- size 1 - 1st row has no spaces
- test outer space
  - size 2 - 1st row has one space at each side `-X-`
  - size 3 - 1st row has two spaces at each side `--X--`
- test inner spaces at mid point
  - size 2 - 2nd row has one inner space `X-X`
  - size 3 - 3rd row has three inner spaces `X---X`
  - size 4 - 4th row has five inner spaces `X-----X`
- test inner and outer spaces
  - size 3 - 2nd row has one inner space and one outer space `-X-X-`
  - size 4 - 2nd row has one inner space and two outer spaces `--X-X--`
- multiple inner spaces
  - size 4 - 3rd row has three inner spaces and one outer space `-X---X-`
- integration tests
  - size 4 - top half of diamond
  ```
    ---X---
    --X-X--
    -X---X-
    X-----X
  ```
  - size 4 - full diamond. 
    - This test makes your realise that all you have to do is repeat the top half of the diamond in reverse order.