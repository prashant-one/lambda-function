import com.prashant.Func;
import org.junit.Assert;
import org.junit.Test;

public class TestFunc {

    public static String stringData ="123123Hello312&&&@@***Prashant5634";

    @Test
    public void verifyFiltersTest(){
        Assert.assertEquals("HelloPrashant",Func.filterAlphabet.apply(stringData));
        Assert.assertEquals("1231233125634",Func.filterDigit.apply(stringData));
        Assert.assertEquals("123123Hello312Prashant5634",Func.filterAlphanumeric.apply(stringData));
        Assert.assertEquals("&&&@@***",Func.filterPunctuationCharacter.apply(stringData));
    }

    @Test
    public void verifyEncryptionDecryptionTest(){
        Assert.assertEquals("S5aj3n4W+hmYH2Gc92Eet3rUq0QFm0tijo1V9zXLXtM7hdTkVDbh9Q==",Func.encryptString.apply(stringData, "Func"));
        Assert.assertEquals(stringData,Func.decryptString.apply("S5aj3n4W+hmYH2Gc92Eet3rUq0QFm0tijo1V9zXLXtM7hdTkVDbh9Q==", "Func"));
    }

    @Test
    public void verifyStringRemoveTest(){
        Assert.assertEquals("Hello my name is prashant ",Func.removeDuplicateWord.apply("Hello Hello Hello my my name is prashant"));
        Assert.assertEquals("Helo mynaisprht",Func.removeDuplicateCharacter.apply("Hello Hello Hello my my name is prashant"));
        Assert.assertEquals("AB",Func.toUpperCase.andThen(Func.toString).andThen(Func.removeDuplicateCharacter).apply("aaaBbBBA"));
    }

    @Test
    public void verifyReverseTest(){
        Assert.assertEquals(true,Func.verifyPalindrome.apply("LML"));
        Assert.assertEquals("olleH woh era uoy ",Func.reverseEachWord.apply("Hello how are you"));
    }

    @Test
    public void verifyNullOrEmptyTest(){
        Assert.assertEquals(true,Func.verifyNullOrEmpty.apply(""));
        Assert.assertEquals(true,Func.verifyNullOrEmpty.apply(null));
    }
}
