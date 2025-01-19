package sjq.bitcoin.message.data;

public abstract class TransactionLockTime {

    public static final long THRESHOLD = 500000000;

    protected final long value;

    public TransactionLockTime(long value) {
        this.value = value;
    }

    public static TransactionLockTime of(long value) {
        if (value<0) {
            throw new IllegalArgumentException(String.format("lock time value is illegal, lock time:%d", value));
        }
        if (value<THRESHOLD) {
            return new HeightLockTime(value);
        } else {
            return new TimeLockTime(value);
        }
    }

    public long rawValue() {
        return value;
    }

    private static class HeightLockTime extends TransactionLockTime {
        private HeightLockTime(long value) {
            super(value);
        }
    }

    private static class TimeLockTime extends TransactionLockTime {
        private TimeLockTime(long value) {
            super(value);
        }
    }
}
