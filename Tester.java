import org.testng.Assert;
import org.testng.annotations.Test;

public class Tester {
    public static void main(String[] args){
        System.out.println(Integer.decode("--1231"));
    }
    @Test
    void testEmptyError(){
        try{
            Integer.decode("");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"Zero length string");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }
    @Test
    void testInput(){
        Assert.assertEquals(Integer.valueOf(-13213), Integer.decode("-13213"));
    }
    @Test
    void testTextInput(){
        try{
            Integer.decode("no decoding text");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"For input string: \"no decoding text\"");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }
    @Test
    void testInputFloat(){
        try{
            Integer.decode("2123,1323");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"For input string: \"2123,1323\"");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }
    @Test
    void testInputFloat2(){
        try{
            Integer.decode("2123.1323");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"For input string: \"2123.1323\"");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }
    @Test
    public void testPositiveNegativeInput() {
        Assert.assertEquals(Integer.valueOf(999), Integer.decode("+999"));
        Assert.assertEquals(Integer.valueOf(0), Integer.decode("-0"));
        Assert.assertEquals(Integer.valueOf(-1321), Integer.decode("-1321"));
        Assert.assertEquals(Integer.valueOf(0), Integer.decode("+0"));
    }
    @Test
    public void testMaxMinValuesOfInt(){
        Assert.assertEquals(Integer.valueOf(Integer.MAX_VALUE), Integer.decode(Integer.toString(Integer.MAX_VALUE)));
        Assert.assertEquals(Integer.valueOf(Integer.MIN_VALUE), Integer.decode(Integer.toString(Integer.MIN_VALUE)));
        Assert.assertEquals(Integer.valueOf(Integer.MIN_VALUE - 1), Integer.decode(Integer.toString(Integer.MIN_VALUE - 1)));
    }
    @Test
    public void testOverMemory(){
        try{
            Integer.decode("1111111111111111111111");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"For input string: \"1111111111111111111111\"");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }

    @Test
    public void test0xInput() {
        Assert.assertEquals(Integer.valueOf(257), Integer.decode("0x101"));
        Assert.assertEquals(Integer.valueOf(-257), Integer.decode("-0x101"));
        Assert.assertEquals(Integer.valueOf(257), Integer.decode("#101"));
        Assert.assertEquals(Integer.valueOf(-257), Integer.decode("-#101"));
        Assert.assertEquals(Integer.valueOf(-257), Integer.decode("-0x0000000000000000000101"));
    }
    @Test
    public void test10101Input() {
        Assert.assertEquals(Integer.valueOf(521), Integer.decode("01011"));
        Assert.assertEquals(Integer.valueOf(-521), Integer.decode("-01011"));
        Assert.assertEquals(Integer.valueOf(521), Integer.decode("0000000000000000001011"));
        Assert.assertEquals(Integer.valueOf(-521), Integer.decode("-00000000000001011"));
    }
    @Test
    public void testPlusWrongPositionInput() {
        // throw new NumberFormatException("Sign character in wrong position");    1 раз был index++ после знака
        // значит второй символ +- => ошибка
        try{
            Integer.decode("++2");
            throw new AssertionError("no throw exception");
        }
        catch (NumberFormatException e){
            Assert.assertEquals(e.getMessage(),"Sign character in wrong position");
        }
        catch (AssertionError e){
            throw new AssertionError(e.getMessage());
        }
        catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
    }
}
