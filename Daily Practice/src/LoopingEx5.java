
public class LoopingEx5 {

	//Kevin Huang
	public static void main(String[] args) {
			System.out.print("x/y\t|\t");
			for(int ___=-2;___<=1;___++) {
				System.out.print(___+"\t");
			}
			System.out.println();
			System.out.println("-------------------------------------------");
		for(int __=-1;__<2;__++) {
			System.out.print(__+"\t|\t");
			for(int ___=-2;___<=1;___++) {
				System.out.print((int)(2*__-Math.pow(___,2))+"\t");
			}
			System.out.println();
		}
	}

}
