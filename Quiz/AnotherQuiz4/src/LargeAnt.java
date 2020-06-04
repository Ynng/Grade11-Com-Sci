public class LargeAnt extends Ant {
  private static int largeAntCounter = 0;
  private double bodyLength;

  public LargeAnt(double bodyLength) {
    // default constructor of Ant is called implicitly.
    this.bodyLength = bodyLength;
    largeAntCounter++;
  }

  // although not stated in the question, it's only logical to include an assessor
  // for the largeAntCounter variable, otherwise no one can access it
  public static int getLargeAntCounter() {
    return largeAntCounter;
  }

  @Override // "@Override" is not necessary, but just good to have to avoid mistakes
  public double energyOutput() {
    return bodyLength * 1.34;
  }

  @Override
  public double volume() {
    // bodyLength * bodyLength is the same as squaring bodyLength, this way is
    // simpler to type
    return (bodyLength * bodyLength) * 1.78;
  }

  public double getBodyLength() {
    return bodyLength;
  }

  public void setBodyLength(double bodyLength) {
    this.bodyLength = bodyLength;
  }

  // Overriding equals() to compare two Complex objects 
  @Override
  public boolean equals(Object o) { 

      if (o == this)
          return true; 
      if(o == null) return false;
      if(getClass() != o.getClass())return false;

      LargeAnt ant = (LargeAnt) o; 
      
      if(ant.getBodyLength() != getBodyLength())return false;
      return true;
  } 

  @Override
  public String toString() {
    return String.format("%s: body length: %.2f | energy output: %.2f | volume: %.2f", getClass().getSimpleName(), bodyLength, energyOutput(), volume());
  }
}