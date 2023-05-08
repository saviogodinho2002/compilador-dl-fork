package inter;

import java.util.LinkedList;

public abstract class Node {
    private LinkedList<Node> children = new LinkedList<Node>();

    protected void addChild(Node node) {
        children.add(node);
    }

    protected LinkedList<Node> getChildren() {
        return children;
    }

    public String str() {
        return strTree("");
    }

    private String strTree(String ident) {
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        for (Node n : children) {
            sb.append("\n" + ident + "|-->");
            sb.append(n.strTree(ident + "    "));
        }
        return sb.toString();
    }
}
