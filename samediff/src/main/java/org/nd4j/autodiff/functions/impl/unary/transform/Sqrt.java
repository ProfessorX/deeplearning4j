package org.nd4j.autodiff.functions.impl.unary.transform;

import org.nd4j.autodiff.ArrayField;
import org.nd4j.autodiff.functions.AbstractUnaryFunction;
import org.nd4j.autodiff.functions.DifferentialFunction;
import org.nd4j.autodiff.samediff.SameDiff;

public class Sqrt extends AbstractUnaryFunction<ArrayField> {

    public Sqrt(SameDiff sameDiff, DifferentialFunction<ArrayField> i_v, Object[] extraArgs) {
        super(sameDiff, i_v, extraArgs);
    }


    @Override
    public ArrayField doGetValue() {
        return sameDiff.getArrayFactory().sqrt(arg().getValue(true));
    }

    @Override
    public double getReal() {
        return Math.sqrt(arg().getReal());
    }

    @Override
    public DifferentialFunction<ArrayField> diff(DifferentialFunction<ArrayField> i_v) {
        return ((sameDiff.getFunctionFactory().sqrt(arg()).inverse())
                .div(sameDiff.getFunctionFactory().val(sameDiff.getArrayFactory().one(getResultShape()).mul(2L))))
                .mul(arg().diff(i_v));
    }


    @Override
    public String functionName() {
        return new org.nd4j.linalg.api.ops.impl.transforms.Sqrt().name();
    }
}
