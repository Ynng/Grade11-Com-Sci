
// The ant class, although not stated in the question, has to be abstract.
// The reason being that no specific implementation details about the energyOutput and Volume behaviour was given for the Ant class.
// There is just no way of implementing these behaviour, so it's only logical to make them abstract, which requires the entire class to be made abstract
public abstract class Ant {
  private static int counter = 0;

  public Ant() {
    counter++;
  }

  // although not stated in the question, it's only logical to include an assessor
  // for the counter variable, otherwise no one can access it
  public static double getCount() {
    return counter;
  }

  abstract double energyOutput();

  abstract double volume();
}