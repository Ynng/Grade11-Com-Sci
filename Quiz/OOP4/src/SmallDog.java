
public class SmallDog extends Dog {
  private static int smallDogCount = 0;
  private double weight;
  public SmallDog(double weight){
    smallDogCount++;
    this.weight = weight;
  }

  public static int getSmallDogCount(){
    return smallDogCount;
  }

  public double intakeFood(){
    return Math.sqrt(weight);
  }

  public double outputWaste(){
    return intakeFood() * 0.5;
  }
}