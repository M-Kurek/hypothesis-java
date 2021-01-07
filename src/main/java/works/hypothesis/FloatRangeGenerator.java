package works.hypothesis;

import works.hypothesis.strategies.Strategy;

import java.util.Random;

/**
 * Created by david on 4/9/16.
 */
public class FloatRangeGenerator implements Strategy<Float> {
    private final int floatRange = 4;
    private final float right;
    private final float left;
    private final HypothesisDataDistribution distribution;

    FloatRangeGenerator(float left, float right) {
        this.left = left;
        this.right = right;
        this.distribution = new HypothesisDataDistribution() {

            @Override
            public void generateData(Random random, byte[] target, int index, int nbytes) {
                if (nbytes != floatRange) {
                    throw new RuntimeException("Bad number of bytes " + nbytes);
                }
                float f = random.nextFloat() * (right - left) + left;
                ByteUtils.intToBytes(target, index, Float.floatToIntBits(f));
            }
        };
    }


    @Override
    public Float doDraw(TestData data) {
        byte[] value = data.drawBytes(floatRange, this.distribution);
        float f = Float.intBitsToFloat(ByteUtils.intFromBytes(value, 0));
        data.assume(f >= left);
        data.assume(f <= right);
        return f;
    }

}
