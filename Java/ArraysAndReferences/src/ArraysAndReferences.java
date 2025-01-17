import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to test arrays, references, and arrays of references.
 *
 * @author Andrew Schneider
 *
 */
public final class ArraysAndReferences {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ArraysAndReferences() {
    }

    /**
     * Computes the product of the {@code NaturalNumber}s in the given array.
     *
     * @param nnArray
     *            the array
     * @return the product of the numbers in the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * productOfArrayElements =
     *    [nnArray[0] * nnArray[1] * ... * nnArray[nnArray.length-1]]
     * </pre>
     */
    private static NaturalNumber productOfArrayElements(
            NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        NaturalNumber product = new NaturalNumber2(1);
        int index = 0;
        while (index < nnArray.length) {
            NaturalNumber temp = new NaturalNumber2(nnArray[index]);
            product.multiply(temp);
            index++;
        }
        return product;
    }

    /**
     * Replaces each element of {@code nnArray} with the partial product of all
     * the elements in the incoming array, up to and including the current
     * element.
     *
     * @param nnArray
     *            the array
     * @updates nnArray
     * @requires nnArray.length > 0
     * @ensures <pre>
     * for all i: integer where (0 <= i < nnArray.length)
     *   (nnArray[i] = [#nnArray[0] * #nnArray[1] * ... * #nnArray[i]])
     * </pre>
     */
    private static void computePartialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        // TODO - fill in body
        System.out.println("** compute partial products **");
        NaturalNumber[] copy = new NaturalNumber2[5];
        for (int j = 0; j < nnArray.length; j++) {
            copy[j] = new NaturalNumber2(nnArray[j]);
        }
        int index = 0;
        int tracker = 0;
        while (index < nnArray.length) {
            while (tracker < index) {
                nnArray[index].multiply(new NaturalNumber2(copy[tracker]));
                tracker++;
            }
            index++;
            tracker = 0;
        }
        for (int i = 0; i < nnArray.length; i++) {
            System.out.print("nnArrary[" + i + "]: " + nnArray[i]);
            System.out.print("      copy[" + i + "]: " + copy[i]);
            System.out.println();
        }
        System.out.println();

    }

    /**
     * Creates and returns a new array of {@code NaturalNumber}s, of the same
     * size of the given array, containing the partial products of the elements
     * of the given array.
     *
     * @param nnArray
     *            the array
     * @return the array of partial products of the elements of the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * partialProducts.length = nnArray.length  and
     *  for all i: integer where (0 <= i < partialProducts.length)
     *    (partialProducts[i] = [nnArray[0] * nnArray[1] * ... * nnArray[i]])
     * </pre>
     */
    private static NaturalNumber[] partialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        System.out.println("** partial products **");
        NaturalNumber[] products = new NaturalNumber2[nnArray.length];
        for (int i = 0; i < nnArray.length; i++) {
            products[i] = new NaturalNumber2(nnArray[i]);
        }
        int index = 0;
        int tracker = 0;

        while (index < nnArray.length) {
            while (tracker < index) {
                products[index].multiply(new NaturalNumber2(nnArray[tracker]));
                tracker++;
            }
            index++;
            tracker = 0;
        }

        for (int i = 0; i < nnArray.length; i++) {
            System.out.print("nnArrary[" + i + "]: " + nnArray[i]);
            System.out.print("       products[" + i + "]: " + products[i]);
            System.out.println();
        }
        System.out.println();

        return products;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Initialize an array of NaturalNumbers with values 1 through 42.
         */
        NaturalNumber[] array = new NaturalNumber[5];
        NaturalNumber count = new NaturalNumber2(1);
        for (int i = 0; i < array.length; i++) {
            array[i] = new NaturalNumber2(count);
            count.increment();
        }
        /*
         * Compute and output the product of the numbers in the array (should be
         * 42!, i.e., the factorial of 42).
         */
        NaturalNumber product = productOfArrayElements(array);
        partialProducts(array);
        computePartialProducts(array);
        out.println(product);

        out.close();
    }

}
