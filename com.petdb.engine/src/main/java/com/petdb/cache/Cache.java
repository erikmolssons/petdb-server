package com.petdb.cache;

import com.petdb.parser.query.Key;
import com.petdb.parser.query.Keyword;
import com.petdb.parser.query.Value;
import com.petdb.transaction.Transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Cache {

    //TODO key expiration time?

    private final static Map<Key, Value> STORE = new HashMap<>();

    public String set(Key key, Value value) {
        Cache.STORE.put(key, value);
        return "OK";
    }

    public String get(Key key) {
        var value = Cache.STORE.get(key);
        if (value == null) {
            return String.format("Key = %s, not set", key.getData());
        }
        return value.getData();
    }

    public String delete(Key key) {
        Cache.STORE.remove(key);
        return Keyword.DELETE.toString();
    }

    public String commit(Transaction transaction) {
        Cache.STORE.putAll(transaction.getMap());
        return String.format(Keyword.COMMIT + ": %s", transaction.getUuid());
    }

    public int count() {
        return Cache.STORE.size();
    }

    public void flush() {
        Cache.STORE.clear();
    }

    public static Map<Key, Value> getSTORE() {
        return Collections.unmodifiableMap(Cache.STORE);
    }
}
