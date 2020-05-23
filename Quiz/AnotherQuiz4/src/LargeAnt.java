public class LargeAnt extends Ant {
  private static int counter = 0;
  double bodyLength;

  public LargeAnt(double bodyLength) {
    // default constructor of Ant is called implicitly.
    this.bodyLength = bodyLength;
    counter++;
  }

  // although not stated in the question, it's only logical to include an assessor
  // for the counter variable, otherwise no one can access it
  public static double getCount() {
    return counter;
  }

  @Override// "@Override" is not necessary, but just good to have to avoid mistakes
  public double energyOutput() {
    return bodyLength * 1.34;
  }

  @Override
  public double volume() {
    //bodyLength * bodyLength is the same as squaring bodyLength, this way is simpler to type
    return (bodyLength * bodyLength) * 1.78;
  }
}