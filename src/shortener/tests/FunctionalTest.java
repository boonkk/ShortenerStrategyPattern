package shortener.tests;


import org.junit.Assert;
import org.junit.Test;
import shortener.Shortener;
import shortener.strategy.*;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String string1 = "53453lfds";
        String string2 = "fsdf23FDS%#$%";
        String string3 = "53453lfds";


        long s1 = shortener.getId(string1);
        long s2 = shortener.getId(string2);
        long s3 = shortener.getId(string3);
        Assert.assertNotEquals(s1,s2);
        Assert.assertNotEquals(s2,s3);
        Assert.assertEquals(s1,s3);

        String st1 = shortener.getString(s1);
        String st2 = shortener.getString(s2);
        String st3 = shortener.getString(s3);

        Assert.assertEquals(string1, st1);
        Assert.assertEquals(string2, st2);
        Assert.assertEquals(string3, st3);

    }
    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hmss = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hmss);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy ohmss = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(ohmss);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy fss = new FileStorageStrategy();
        Shortener shortener = new Shortener(fss);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy hbmss = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hbmss);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy dhbmss = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(dhbmss);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy ohbmss = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ohbmss);
        testStorage(shortener);
    }
}
