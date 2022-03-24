package org.design;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void InsertDeleteUndoRedo()
    {
        List<String> view = new ArrayList<>();
        TextPad textPad = new TextPad();
        textPad
                .insert(1, "Hello!")
                .insert(3, "World!")
                .view(view)
                .delete(3)
                .view(view)
        ;
        Assert.assertEquals("Invalid line 1", "Hello!", view.get(0));
        Assert.assertEquals("Invalid line 2", "World!", view.get(1));
        Assert.assertEquals("Invalid line @", "Hello!", view.get(2));
//        Assert.assertEquals("Invalid line 1",  );
        Assert.assertTrue ("Line 1 should be deleted", view.size() < 4);
    }
}
