package edu.iastate.cs228.hw4;
import java.util.Stack;

public class MsgTree {
    public char payloadChar;
    public MsgTree left;
    public MsgTree right;
    private static int index = 0;
    private static String binCode;

    /**
     * Constructor for building the tree by encoding string
     *
     * @param encodingString
     */
    public MsgTree(String encodingString){
        if (encodingString.length() < 2 || encodingString == null){
            return;
        }
        this.payloadChar = encodingString.charAt(index);
        index++;
        this.left = new MsgTree(encodingString.charAt(index));
        if (this.left.payloadChar == '^'){
            this.left = new MsgTree(encodingString);
        }
        index++;
        this.right = new MsgTree(encodingString.charAt(index));
        if (this.right.payloadChar == '^'){
            this.right = new MsgTree(encodingString);
        }
    }

    /**
     * Constructor for a single node w/ no children
     *
     * @param payloadChar
     */
    public MsgTree(char payloadChar){
        left = null;
        right = null;
        this.payloadChar = payloadChar;
    }

    public static void printCodes(MsgTree root, String code){
        System.out.println("character code\n-------------------------");
        for (char ch : code.toCharArray()){
            if (ch != '^') {
                getCode(root, ch, binCode = "");
                System.out.println("   " + (ch == '\n' ? "\\n" : ch + " ") + "   " + binCode);
            }
        }
    }

    private static boolean getCode(MsgTree root, char ch, String path){
        if (root != null){
            if (root.payloadChar == ch){
                binCode = path;
                return true;
            }
            return getCode(root.left, ch, path + "0") || getCode(root.right, ch, path + "1");
        }
        return false;
    }
}
