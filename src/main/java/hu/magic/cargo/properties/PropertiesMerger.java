package hu.magic.cargo.properties;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.cargo.module.merge.MergeException;
import org.codehaus.cargo.module.merge.MergeProcessor;

/**
 * properties fájlok merge-elését végző merge processzor
 * @author jmalyik
 *
 */
public class PropertiesMerger implements MergeProcessor
{
    private final List<Properties> propertiesList = new ArrayList<>();

    @Override
    public void addMergeItem(Object mergeItem) throws MergeException
    {
        if (mergeItem instanceof Properties)
        {
            final Properties propItem = (Properties) mergeItem;
            propertiesList.add(propItem);
        }else if(mergeItem instanceof InputStream) {
            try(InputStream is = (InputStream) mergeItem){
                final Properties propItem = new Properties();
                propItem.load(is);
                propertiesList.add(propItem);
            }catch(final IOException e) {
                throw new MergeException("PropertiesMerger was unable to read inputStream", e);
            }
        }
        else
        {
            final String clazz = mergeItem != null ? mergeItem.getClass().getName() : null;
            throw new MergeException("PropertiesMerger can only merge Properties, " + clazz + " is not good!");
        }
    }

    @Override
    public Object performMerge() throws MergeException
    {
        try {
            if (propertiesList.isEmpty())
            {
                return null;
            }
            final Properties merged = new Properties();

            /**
             *  @see {@link Properties#putAll}
             */
            for(final Properties p : propertiesList) {
                merged.putAll(p);
            }
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            merged.store(bos, StandardCharsets.ISO_8859_1.name());
            return new ByteArrayInputStream(bos.toByteArray());
        }catch(final Exception e) {
            throw new MergeException("PropertiesMerger error", e);
        }
    }
}
