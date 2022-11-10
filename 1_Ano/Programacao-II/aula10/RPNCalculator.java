import p2utils.Stack;

public class RPNCalculator {
    public static void main(String[] args) {
        Stack<Double> pilha = new Stack<Double>();
        double result = 0;
        System.out.println(args.length);
        for (String a : args){
            System.out.println(a);
        }
        for (int i = 0; i < args.length; i++) {
            try {
                double num = Double.parseDouble(args[i]);
                pilha.push(num);
            } catch (NumberFormatException e) {
                double temp2 = pilha.top();
                pilha.pop();
                double temp1 = pilha.top();
                pilha.pop();

                switch (args[i]) {
                    case "+":
                        result = temp1 + temp2;
                        break;
                    case "-":
                        result = temp1 - temp2;
                        break;
                    case "/":
                        result = temp1 / temp2;
                        break;
                    case "*":
                        result = temp1 * temp2;
                        break;
                    default:
                        System.err.print("Operando inválido.");
                        System.exit(1);
                        break;
                }
                pilha.push(result);
            }
        }
        System.out.print(pilha.top());

    }
}
