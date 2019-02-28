package maps;

import org.junit.Test;

import java.util.HashMap;

public class MyHashMapTest {

    @Test
    public void testMyHashMap() {
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(0, "Three");
        map1.put(null, "Null");

        //Некоторые элементы будут потеряны.
        //Связано это с тем, что нет разрешения коллизий.
        MyHashMap<Integer, String> map = new MyHashMap<>();
        System.out.println(map.insert(1, "One"));
        System.out.println(map.insert(2, "Two"));
        System.out.println(map.insert(3, "Three"));
        System.out.println(map.insert(4, "Four"));
        System.out.println(map.insert(null, "Null"));
        System.out.println(map.insert(444, "FourFourFour"));
        System.out.println(map.insert(555, "FiveFiveFive"));
        System.out.println(map.insert(666, "SixSixSix"));
        System.out.println(map.insert(777, "SevenSevenSeven"));
        System.out.println(map.insert(888, "EightEightEight"));
        System.out.println(map.insert(999, "NineNineNine"));
        System.out.println(map.insert(101010, "TenTenTen"));
        System.out.println(map.insert(111111, "ElevenElevenEleven"));
        System.out.println(map.insert(121212, "TwelveTwelveTwelve"));
        System.out.println(map.insert(131313, "ThirtingThirtingThirting"));
        System.out.println(map.insert(141414, "FourtingFourtingFourting"));
        System.out.println(map.insert(151515, "SixtinSixtinSixtin"));
        System.out.println(map.insert(161616, "SevenTeenSevenTeenSevenTeen"));
    }
}