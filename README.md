# lambda-function
### This library for string related lambda functions we can use those function as a pipeline to get some work done

- How to use it
  install the dependency
```
<dependency>
  <groupId>com.prashant</groupId>
  <artifactId>lambda-function</artifactId>
  <version>0.0.1</version>
</dependency>
```

- Then use `Func`  interface to get the predefined functions

```
System.out.println(Func.filterAlphabet.apply("123123%%%Alpha8989%%"));

System.out.println(Func.countWord
                .apply("Hello Hello hello Hello my my name is prashant"));

 System.out.println(Func.toLowerCase
              .andThen(Func.countWord)
              .apply("Hello hello Hello Hello my my name is prashant"));

System.out.println(Func.toUpperCase
                .andThen(Func.toString)
                .andThen(Func.removeDuplicateCharacter)
                .apply("aaaB"));
```




