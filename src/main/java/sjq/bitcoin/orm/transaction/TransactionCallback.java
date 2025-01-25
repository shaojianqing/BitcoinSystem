package sjq.bitcoin.orm.transaction;

public interface TransactionCallback<T> {
	
	T doTransaction();
}
