package shortener;



import shortener.strategy.HashBiMapStorageStrategy;
import shortener.strategy.HashMapStorageStrategy;
import shortener.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        testStrategy(hashMapStorageStrategy,10000);

        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        System.out.println("Shortened: " + shortener.getId(""));
        System.out.println("Shortened: " + shortener.getId("gfdgdfgdfgdf"));
        System.out.println("Shortened: " + shortener.getId("gregg34g3g43"));

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        HashSet<Long> result = new HashSet<>();
        for(String s : strings) {
            result.add(shortener.getId(s));
        }
        return result;
    }



    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        HashSet<String> result = new HashSet<>();
        for(Long s : keys) {
            result.add(shortener.getString(s));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        HashSet<String> testSet = new HashSet<>();
        for(int i=0; i<elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Set<Long> keys = new HashSet<>();

        long startTime = new Date().getTime();
        keys = getIds(shortener,testSet);
        long endTime = new Date().getTime();
        System.out.println(endTime - startTime);

        Set<String> values = new HashSet<>();

        startTime = new Date().getTime();
        values = getStrings(shortener,keys);
        endTime = new Date().getTime();
        System.out.println(endTime - startTime);

        boolean isSameContent = true;
        for(String s : testSet) {
            if(!values.contains(s))
                isSameContent = false;
        }
        if(testSet.size() != values.size())
            isSameContent = false;

        if(isSameContent) {
            System.out.println("The test passed.");
        } else {
            System.out.println("The test failed.");
        }
    }


}
