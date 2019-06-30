# Prime factor kata

The classic prime factor kata that Uncle Bob likes to do.

Outputs prime factors for a give number.

When done right, it's a really aha moment.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/prime-factor/src/main/java/com/grantburgess/PrimeFactor.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/prime-factor/src/test/java/com/grantburgess/PrimeFactorTest.java)

  
### Tests
- 1 -> empty list
- 2 -> 2
  - split flow if input is greater than 1
  - adds constant 2 to list
- 3 -> 3
  - constant to variable
  - makes you generalise and simply adds the number inputted 
- 4 -> 2, 2
  - when divisible by 2 add two to list and divide number by 2
  - e.g. n % 2 == 0; list.add(2); n /= 2;
- 6 -> 2, 3
- 8 -> 2, 2, 2
  - turns an if into a while. MIND BLOWN!
- 9 -> 3, 3
  - refactor into two for loops
