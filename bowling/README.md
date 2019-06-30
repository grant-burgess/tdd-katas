# Bowling kata

Simple bowling game score calculator.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/bowling/src/main/java/com/grantburgess/Game.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/bowling/src/test/java/com/grantburgess/GameTest.java)

### Score calculation
- Regular
  - first two balls in frame
- Spare
  - 10 + first ball after spare
- Strike
  - 10 + first two balls after strike
  
### Tests
- gutter game
- all ones
- one spare + plus two extra balls
- one strike + plus two extra balls
- perfect game i.e. 300