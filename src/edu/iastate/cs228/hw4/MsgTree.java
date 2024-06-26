package edu.iastate.cs228.hw4;
import java.util.Stack;

/**
 * @author Connor Grim
 */

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
    public MsgTree(String encodingString) {
        //Check for valid encoding string
        if (encodingString == null || encodingString.length() < 2) {
            return;
        }

        //Creates stack and pushes root node
        Stack<MsgTree> stk = new Stack<>();
        int idx = 0;
        this.payloadChar = encodingString.charAt(idx++);
        stk.push(this);
        MsgTree cur = this;
        int lastOpt = 1;

        //Preorder traversal
        while (idx < encodingString.length()) {
            MsgTree node = new MsgTree(encodingString.charAt(idx++));
            if (lastOpt == 1) {
                cur.left = node;
                if (node.payloadChar == '^') {
                    cur = stk.push(node);
                    lastOpt = 1;
                } else {
                    if (!stk.empty())
                        cur = stk.pop();
                    lastOpt = 0;
                }
            } else { // lastOpt is out
                cur.right = node;
                if (node.payloadChar == '^') {
                    cur = stk.push(node);
                    lastOpt = 1;
                } else {
                    if (!stk.empty())
                        cur = stk.pop();
                    lastOpt = 0;
                }
            }
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
