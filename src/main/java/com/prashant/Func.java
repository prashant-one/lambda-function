package com.prashant;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Func {
    Function<String, String> trim = String::trim;
    Function<String, String> it = str -> str;
    Function<String, String> toUpperCase = String::toUpperCase;
    Function<String, String> toString = String::toString;
    Function<String, char[]> toCharArray = String::toCharArray;
    Function<String, String> toLowerCase = String::toLowerCase;
    Function<String, String> reverse = str -> String.valueOf(new StringBuilder(str).reverse());
    Function<String, String> filterAlphabet = str -> str.replaceAll("[^\\p{Alpha}]+", "");
    Function<String, String> filterDigit = str -> str.replaceAll("[^\\p{Digit}]+", "");
    Function<String, String> filterAlphanumeric = str -> str.replaceAll("[^\\p{Alnum}]+", "");
    Function<String, String> filterPunctuationCharacter = str -> str.replaceAll("[^\\p{Punct}]+", "");
    Function<String, String> filterVowels = str -> str.replaceAll("[^aeiouAEIOU]", "");
    Function<String, String> removeSpace = str -> str.replaceAll("\\s", "");
    Function<String, String> removeLastCharacter = str -> str.substring(0, str.length() - 1);
    Function<String, String> removeFirstCharacter = str -> str.substring(1);
    Function<String, Integer> parseToInt = Integer::valueOf;
    Function<String, Double> parseToDouble = Double::valueOf;
    Function<String, Long> parseToLong = Long::valueOf;
    Function<String, Float> parseToFloat = Float::valueOf;
    Function<String, Boolean> verifyNullOrEmpty = str -> str == null || str.isEmpty();
    Function<String, Boolean> verifyPalindrome = str -> reverse.andThen(it -> it.equals(str)).apply(str);
    Function<String, Map<String, Long>> countWord = str -> {
        String[] stringList = str.split("\\s+");
        return Arrays.stream(stringList).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    };

    Function<String, Map<Character, Long>> countCharacter = str -> {
        Character[] charList = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        return Arrays.stream(charList).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    };

    Function<String, String> removeDuplicateWord = str -> {
        StringBuilder result = new StringBuilder();
        List<String> resultString = Arrays.stream(str.split("\\s+")).toList().stream().distinct().toList();
        for (String s : resultString) {
            result.append(s);
            result.append(" ");
        }
        return String.valueOf(result);
    };

    Function<String, String> removeDuplicateCharacter = str -> {
        StringBuilder result = new StringBuilder();
        Character[] charArray = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        List<Character> charList = Arrays.stream(charArray).toList().stream().distinct().toList();
        for (Character s : charList) {
            result.append(s);
        }
        return String.valueOf(result);
    };

    Function<String, String> reverseEachWord = str -> {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> result = Arrays.stream(str.trim().split(" ")).toList();
        for (String s : result) {
            stringBuilder.append(reverse.apply(s));
            stringBuilder.append(" ");
        }
        return String.valueOf(stringBuilder);
    };

    BiFunction<String, String, String> encryptString = (str, key) -> {
        String encryptedString = "";
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            encryptedString = Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    };

    BiFunction<String, String, String> decryptString = (str, key) -> {
        String decryptedString = "";
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodedEncrypted = Base64.getDecoder().decode(str);
            byte[] decryptedBytes = cipher.doFinal(decodedEncrypted);
            decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    };


    static <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func) {
        Map<T, R> result = new HashMap<>();
        list.forEach(it -> result.put(it, func.apply(it)));
        return result;
    }


    static <T, R> List<R> mapWithFunction(List<T> list, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        list.forEach(it -> result.add(func.apply(it)));
        return result;
    }

    static <T> List<T> mapWithPredicate(List<T> list, Predicate<T> func) {
        List<T> result = new ArrayList<>();
        list.forEach(it -> {
            if (func.test(it))
                result.add(it);
        });
        return result;
    }


    // public static <T,R>


}
