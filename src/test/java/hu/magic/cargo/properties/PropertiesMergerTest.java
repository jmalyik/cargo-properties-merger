package hu.magic.cargo.properties;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * testing the different cases
 *
 */
public class PropertiesMergerTest
{
    PropertiesMerger merger = new PropertiesMerger();

    @Test
    public void testMixedMerge() throws FileNotFoundException, IOException {
        try(InputStream is = new FileInputStream("src/test/resources/t1.properties"); InputStream is2 = new FileInputStream("src/test/resources/t2.properties")){
            merger.addMergeItem(is);
            final Properties p2 = new Properties();
            p2.load(is2);
            merger.addMergeItem(p2);
            final InputStream resultIS = (InputStream) merger.performMerge();
            final Properties p = new Properties();
            p.load(resultIS);
            System.out.println(p);
            assertEquals(4, p.keySet().size());
            assertEquals("test", p.getProperty("test"));
            assertEquals("test1", p.getProperty("test1"));
            assertEquals("test2", p.getProperty("test2"));
            assertEquals("test3", p.getProperty("test3"));
        }
    }


    @Test
    public void testPropMerge() throws FileNotFoundException, IOException {
        try(InputStream is = new FileInputStream("src/test/resources/t1.properties"); InputStream is2 = new FileInputStream("src/test/resources/t2.properties")){
            final Properties p1 = new Properties();
            p1.load(is);
            merger.addMergeItem(p1);
            final Properties p2 = new Properties();
            p2.load(is2);
            merger.addMergeItem(p2);
            final InputStream resultIS = (InputStream) merger.performMerge();
            final Properties p = new Properties();
            p.load(resultIS);
            System.out.println(p);
            assertEquals(4, p.keySet().size());
            assertEquals("test", p.getProperty("test"));
            assertEquals("test1", p.getProperty("test1"));
            assertEquals("test2", p.getProperty("test2"));
            assertEquals("test3", p.getProperty("test3"));
        }
    }

    @Test
    public void testISMerge() throws FileNotFoundException, IOException {
        try(InputStream is = new FileInputStream("src/test/resources/t1.properties"); InputStream is2 = new FileInputStream("src/test/resources/t2.properties")){
            merger.addMergeItem(is);
            merger.addMergeItem(is2);
            final InputStream resultIS = (InputStream) merger.performMerge();
            final Properties p = new Properties();
            p.load(resultIS);
            System.out.println(p);
            assertEquals(4, p.keySet().size());
            assertEquals("test", p.getProperty("test"));
            assertEquals("test1", p.getProperty("test1"));
            assertEquals("test2", p.getProperty("test2"));
            assertEquals("test3", p.getProperty("test3"));
        }
    }

}
