
public class NumberTriangle {
    public static void main(String[] args) {
       int count = 9; 
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= count; j++) {
                if(j<=count-i)System.out.print(".");
                else System.out.print(i);
            }
            System.out.println();
        }
    }
}
