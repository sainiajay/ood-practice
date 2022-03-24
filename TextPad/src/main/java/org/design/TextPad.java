package org.design;

import java.util.*;

public class TextPad {
    final String EOF = "=======================";
    TreeMap<Integer, String> lines = new TreeMap<>();
    Stack<Map<Integer, String>> undoStack = new Stack<>();
    Stack<Map<Integer, String>> redoStack = new Stack<>();
    ArrayList<String> clipboard;

    public TextPad display() {
        for(int lineNumber = 1; lineNumber <= lines.lastKey(); lineNumber++) {
            String line = lines.get(lineNumber);
            if(line != null) {
                System.out.println(String.format("%d: %s", lineNumber, line));
            }
        }
        System.out.println(EOF);
        return this;
    }

    public TextPad display(int n, int m) {
        for(int lineNumber = n; lineNumber <= m && lineNumber <= lines.lastKey(); lineNumber++) {
            String line = lines.get(lineNumber);
            if(line != null) {
                System.out.println(String.format("%d: %s", lineNumber, line));
            }
        }
        System.out.println(EOF);
        return this;
    }


    public TextPad view(List<String> view) {
        for(int lineNumber = 1; lineNumber <= lines.lastKey(); lineNumber++) {
            String line = lines.get(lineNumber);
            if(line != null) {
                view.add(line);
            }
        }
        return this;
    }

    public TextPad view(int n, int m, List<String> view) {
        for(int lineNumber = n; lineNumber <= m && lineNumber <= lines.lastKey(); lineNumber++) {
            String line = lines.get(lineNumber);
            if(line != null) {
                view.add(line);
            }
        }
        return this;
    }

    public TextPad insert(int m, String text) {
        Map oldState = new HashMap<>();
        oldState.put(m, lines.get(m));
        undoStack.push(oldState);
        lines.put(m, text);
        return this;
    }

    public TextPad delete(int m) {
        if(!lines.containsKey(m) || lines.get(m) == null) return this;
        Map<Integer, String> oldState = new HashMap<>();
        oldState.put(m, lines.get(m));
        undoStack.push(oldState);
        lines.remove(m);
        return this;
    }

    public TextPad delete(int n, int m) {
        Map<Integer, String> oldState = new HashMap<>();
        for(int lineNumber = n; lineNumber <= m && lineNumber <= lines.lastKey(); lineNumber++) {
            if(lines.get(lineNumber) != null) {
                oldState.put(lineNumber, lines.get(lineNumber));
            }
        }
        undoStack.push(oldState);
        lines.subMap(n, m).clear();
        return this;
    }

    public TextPad copy(int n, int m) {
        clipboard = new ArrayList<>(lines.subMap(n, m).values());
        return this;
    }

    public TextPad paste(int n) {
        if(clipboard != null) {
            int row = n;
            List<String> tailLines = new LinkedList<>(lines.tailMap(n, true).values());
            Map<Integer, String> oldValues = new HashMap<>();
            for(String text: clipboard) {
                oldValues.put(row, lines.get(row));
                lines.put(row++, text);
            }
            for(String text: tailLines) {
                oldValues.put(row, lines.get(row));
                lines.put(row++, text);
            }
            undoStack.push(oldValues);
            clipboard = null;
        }
        return this;
    }

    public TextPad undo() {
        if(this.undoStack.isEmpty()) return this;
        Map<Integer, String> oldState = undoStack.pop();
        Map<Integer, String> currentState = new HashMap<>();
        for(Integer lineNumber: oldState.keySet()) {
            currentState.put(lineNumber, lines.get(lineNumber));
        }
        lines.putAll(oldState);
        redoStack.push(currentState);
        return this;
    }

    public TextPad redo() {
        if(this.redoStack.isEmpty()) return this;
        Map<Integer, String> oldState = redoStack.pop();
        Map<Integer, String> currentState = new HashMap<>();
        for(Integer lineNumber: oldState.keySet()) {
            currentState.put(lineNumber, lines.get(lineNumber));
        }
        lines.putAll(oldState);
        return this;
    }
}
