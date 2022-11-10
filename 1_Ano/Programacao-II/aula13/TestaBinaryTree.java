import p2utils.BinarySearchTree;

public class TestaBinaryTree {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree();
        BinarySearchTree<Integer> bst2 = new BinarySearchTree();

        String abc = "cdcfegnghijklmnopqrstuvabxyz";

        int i = 0;
        for (char c : abc.toCharArray()) {
            bst.set(Character.toString(c), i++);
        }

        
        bst.set("monkey", i);
        for (char c : abc.toCharArray()) {
            System.out.println(bst.get(Character.toString(c)));
        }

        testKeys(bst);
        testKeys(bst2);

        bst.remove("b");
        bst.remove("x");
        bst.remove("m");
        bst.remove("n");

        bst2.set("a", 1);
        bst2.remove("a");
        bst2.set("a", 3);

        System.out.println(bst2.get("a"));
        assert bst2.get("a") == 3;

        testSet(bst2);
        testSet(bst2);

        System.out.println(bst.toString());
        System.out.println(bst2.toString());
        System.out.println("Wowwee");
    }


    public static void testSet(BinarySearchTree<Integer> bst) {
        for (char a = 'a'; a <= 'z'; a++) {
            String chave = Character.toString(a);
            if (bst.contains(chave)) {
                bst.set(chave, bst.get(chave) + 1);
            } else {
                bst.set(chave, 1);
            }
        }
    }

    public static void testKeys(BinarySearchTree<Integer> bst) {
        for (String chave : bst.keys()) {
            bst.get(chave);
        }
    }
}
