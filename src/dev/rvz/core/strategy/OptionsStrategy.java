package dev.rvz.core.strategy;

import java.util.HashMap;

public class OptionsStrategy {

    private final HashMap<Integer, ScreenSystemOption> strategy;

    public OptionsStrategy(HashMap<Integer, ScreenSystemOption> strategy) {
        this.strategy = strategy;
    }

    public void selectScreen(Integer option) {
        strategy.get(option).running();
    }
}
