package shortener.strategy;

import java.io.Serializable;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey(){
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Entry))
            return false;
        Entry ent = (Entry) obj;
        return key.equals(ent.getKey()) && value.equals(ent.getValue());
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

}
