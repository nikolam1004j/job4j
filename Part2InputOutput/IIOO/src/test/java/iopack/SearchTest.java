package iopack;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SearchTest {

    @Test
    public void test() {
        Search search = new Search();
        List<File> files = search.files(System.getProperty("java.io.tmpdir") + "\\javaTemp", Arrays.asList("tmp", "ico"));
        Assert.assertEquals(files.stream().filter(file -> file.getAbsolutePath().toLowerCase().endsWith("tmp")).count(), 6);
        Assert.assertEquals(files.stream().filter(file -> file.getAbsolutePath().toLowerCase().endsWith("ico")).count(), 4);
    }

}