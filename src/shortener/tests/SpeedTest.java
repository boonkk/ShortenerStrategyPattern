package shortener.tests;


import org.junit.Assert;
import org.junit.Test;
import shortener.Helper;
import shortener.Shortener;
import shortener.strategy.HashBiMapStorageStrategy;
import shortener.strategy.HashMapStorageStrategy;

import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for(int i=0; i<10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> keys = new HashSet<>();
        Long s1KeyTime = getTimeToGetIds(shortener1,origStrings,keys);
        keys.clear();
        Long s2KeyTime = getTimeToGetIds(shortener2,origStrings,keys);

        Assert.assertTrue(s1KeyTime>s2KeyTime);

        Set<String> strings = new HashSet<>();

        Long s1StringTime = getTimeToGetStrings(shortener1,keys,strings);
        strings.clear();
        Long s2StringTime = getTimeToGetStrings(shortener2,keys,strings);

        Assert.assertEquals(s1StringTime,s2StringTime,30);

    }
    public long getTimeToGetIds(Shortener shortener, Set <String> strings, Set<Long> ids) {
        long startTime = System.currentTimeMillis();
        for(String s : strings) {
            ids.add(shortener.getId(s));
        }
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long startTime = System.currentTimeMillis();
        for(Long l : ids) {
            strings.add(shortener.getString(l));
        }
        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }
}
