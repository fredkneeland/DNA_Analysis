class ColorGenerator {
  // [0] => Red Color from 0-255
  // [1] => Green Color from 0-255
  // [2] => Blue Color from 0-255
  public static final int[] COLOR_A = {0, 150, 136}; // teal-green
  public static final int[] COLOR_T = {194,24,91}; // red
  public static final int[] COLOR_C = {255,152,0}; // orange
  public static final int[] COLOR_G = {3,155,229}; //blue
  public static final int[] COLOR_DEFAULT = {0,0,0}; // black
  
  public static int[] colorGeneratorForLetter(Bases letter) {
     switch(letter) {
       case A:
         return COLOR_A;
       case T:
         return COLOR_T;
       case C:
         return COLOR_C;
       case G:
         return COLOR_G;
       default:
         return COLOR_DEFAULT;
      }
  } 

  public static int[] averageColorForLetterCounts(int aCount, int tCount, int gCount, int cCount) {
     double aMean, tMean, gMean, cMean = 0.0;
    
   }
  
    
}
