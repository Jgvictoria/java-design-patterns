package org.victoria.factory.registry;

import org.victoria.factory.factory.Factory;

public interface Builder<T> {

    void register(String label, Factory<T> factory);
}
