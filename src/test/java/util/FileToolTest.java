package util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class FileToolTest {

    @Test
    public void readFile() {
        List<String> talks = FileTool.readFile("talks");
        talks.forEach(System.out::println);
        Assert.assertThat(talks.size(),is(19));
    }
}