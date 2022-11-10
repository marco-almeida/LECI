public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree bst = new BinaryTree();

        bst.add(10);
        bst.add(2);
        bst.add(1);
        bst.add(4);
        bst.add(-1);
        bst.add(3);
        System.out.println(bst.size());
        System.out.println(bst.contains(-123));
        System.out.println(bst.remove(2));
        System.out.println(bst.contains(2));
        System.out.println(bst);
        // int i = 0;
        // for (char c : abc.toCharArray()) {
        // bst.set(Character.toString(c), i++);
        // }

        // bst.set("monkey", i);
        // for (char c : abc.toCharArray()) {
        // System.out.println(bst.get(Character.toString(c)));
        // }

        // testKeys(bst);
        // testKeys(bst2);

        // bst.remove("b");
        // bst.remove("x");
        // bst.remove("m");
        // bst.remove("n");

        // bst2.set("a", 1);
        // bst2.remove("a");
        // bst2.set("a", 3);

        // System.out.println(bst2.get("a"));
        // assert bst2.get("a") == 3;

        // testSet(bst2);
        // testSet(bst2);

        // System.out.println(bst.toString());
        // System.out.println(bst2.toString());
        // System.out.println("Wowwee");
        // }

        // public static void testSet(BinarySearchTree<Integer> bst) {
        // for (char a = 'a'; a <= 'z'; a++) {
        // String chave = Character.toString(a);
        // if (bst.contains(chave)) {
        // bst.set(chave, bst.get(chave) + 1);
        // } else {
        // bst.set(chave, 1);
        // }
        // }
        // }

        // public static void testKeys(BinarySearchTree<Integer> bst) {
        // for (String chave : bst.keys()) {
        // bst.get(chave);
        // }
    }
}