package org.design;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        TextPad pad = new TextPad();
        pad.insert(5, "Hello")
                .display()
                .insert(7, "World!")
                .display(6, 10)
                .undo()
                .delete(5)
                .display()
                .undo()
                .display()
                .redo()
                .display()
                .redo()
                .display()
        ;
    }
}
