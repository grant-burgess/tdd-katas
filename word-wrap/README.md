# Word wrap kata

Wraps a sentence based on the input length.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/word-wrap/src/main/java/com/grantburgess/WordWrap.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/word-wrap/src/test/java/com/grantburgess/WordWrapTest.java)

  
### Tests
- should not wrap
  - wrap length 1 - null input
  - wrap length 1 - empty input
  - wrap length 1 - only spaces
  - wrap length 1 - single character
  - wrap length 3 - single character
- should wrap
  - wrap length 1 - 2 characters with no spaces i.e. `"xx"        -> "x\nx"`
  - wrap length 1 - 3 characters with no spaces i.e. `"xxx"       -> "x\nx\nx"`
  - wrap length 1 - 3 characters with spaces    i.e. `"x x x"     -> "x\nx\nx"`
  - wrap length 3 - 4 characters with one space i.e. `"x xxx"     -> "x\nxxx"`
  - wrap length 4 - wrap at space               i.e. `"word word" -> "word\nword"`
  - wrap length 5 - back track to first space   i.e. `"word word" -> "word\nword"`
  - integration - wrap length 83
  input
  ```
  "Lorem ipsum dolor sit amet, eam virtute scaevola ne. An sumo dolor intellegam cum. Duo laoreet accusamus ex, in ius habeo porro. At nec accusam sensibus torquatos. Adipiscing contentiones cu mea, ea harum graece eam. Ex eam modus copiosae iracundia, per an facete impedit laboramus."
  ```
  output 
  ```
  "Lorem ipsum dolor sit amet, eam virtute scaevola ne. An sumo dolor intellegam cum.\n"
  "Duo laoreet accusamus ex, in ius habeo porro. At nec accusam sensibus torquatos.\n"
  "Adipiscing contentiones cu mea, ea harum graece eam. Ex eam modus copiosae\n"
  "iracundia, per an facete impedit laboramus."
  ```

