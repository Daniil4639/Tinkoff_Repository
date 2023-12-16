package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class NonLinearTransform {

    private static final int FUNCTION_COUNT = 5;

    private static final Function<Pair<Double, Double>, Pair<Double, Double>> SINUSOIDAL = params -> {
        double x = Math.sin(params.getLeft());
        double y = Math.sin(params.getRight());
        return new ImmutablePair<>(x, y);
    };

    private static final Function<Pair<Double, Double>, Pair<Double, Double>> SPHERICAL = params -> {
        double r = params.getLeft() * params.getLeft() + params.getRight() * params.getRight();
        double x = params.getLeft() / r;
        double y = params.getRight() / r;
        return new ImmutablePair<>(x, y);
    };

    private static final Function<Pair<Double, Double>, Pair<Double, Double>> POLAR = params -> {
        double x = Math.atan(params.getRight() / params.getLeft()) / Math.PI;
        double y = Math.sqrt(params.getLeft() * params.getLeft() + params.getRight() * params.getRight()) - 1;
        return new ImmutablePair<>(x, y);
    };

    private static final Function<Pair<Double, Double>, Pair<Double, Double>> HEART = params -> {
        double r = Math.sqrt(params.getLeft() * params.getLeft() + params.getRight() * params.getRight());
        double x = r * Math.sin(r * Math.atan(params.getRight() / params.getLeft()));
        double y = -r * Math.cos(r * Math.atan(params.getRight() / params.getLeft()));
        return new ImmutablePair<>(x, y);
    };

    private static final Function<Pair<Double, Double>, Pair<Double, Double>> DISK = params -> {
        double r = Math.sqrt(params.getLeft() * params.getLeft() + params.getRight() * params.getRight());
        double x = 1 / Math.PI * Math.atan(params.getRight() / params.getLeft()) * Math.sin(Math.PI * r);
        double y = 1 / Math.PI * Math.atan(params.getRight() / params.getLeft()) * Math.cos(Math.PI * r);
        return new ImmutablePair<>(x, y);
    };

    private static final List<Function<Pair<Double, Double>, Pair<Double, Double>>> NON_LINEAR_TRANSFORMS =
        new ArrayList<>(List.of(SINUSOIDAL, SPHERICAL, POLAR, HEART, DISK));

    private NonLinearTransform() {}

    public static Pair<Double, Double> eval(Pair<Double, Double> data, NonLinearType type) {
        Pair<Double, Double> result;

        switch (type) {
            case ALL -> {
                result =  NON_LINEAR_TRANSFORMS.get(ThreadLocalRandom.current().nextInt(0, FUNCTION_COUNT))
                    .apply(data);
            }
            case HEART -> {
                result = HEART.apply(data);
            }
            case POLAR -> {
                result = POLAR.apply(data);
            }
            case SPHERICAL -> {
                result = SPHERICAL.apply(data);
            }
            case SINUSOIDAL -> {
                result = SINUSOIDAL.apply(data);
            }
            case DISK -> {
                result = DISK.apply(data);
            }
            default -> {
                result = null;
            }
        }

        return result;
    }
}
