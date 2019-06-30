# Name inverter kata

When inputting a name with honorifics or post nominals outputs the last name comma first name and any post nominals.

Teaches you to go slowly and start with degenerate tests i.e. null, empty string, spaces, simple input and slowly building up to the final test.

- [Production code](https://github.com/grant-burgess/tdd-katas/blob/master/name-inverter/src/main/java/com/grantburgess/NameInverter.java)
- [Tests](https://github.com/grant-burgess/tdd-katas/blob/master/name-inverter/src/test/java/com/grantburgess/NameInverterTest.java)

  
### Tests
- null input
- empty input
- only spaces
- simple name i.e. "Name"
- simple name with spaces
- first last returns last comma first i.e. "First Last" -> "Last, First"
- first last with extra spaces returns last comma first i.e. "  First   Last  " -> "Last, First"
- ignore honorifics 
  ```
   i.e. "Mr. First Last"  -> "Last, First"
        "Mrs. First Last" -> "Last, First"
        "Dr. First Last"  -> "Last, First"
   ```
- post nominals stay at the end
  ```
   i.e. "First Last PhD"  -> "Last, First PhD."
        "First Last  M.B Ch.B PhD." -> "Last, First M.B Ch.B PhD."
   ```
- integration test i.e "  Dr. Robert Martin esq. MBE" -> "Martin, Robert esq. MBE"
