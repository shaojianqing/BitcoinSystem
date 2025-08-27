package sjq.bitcoin.script;

public enum VerifyFlag {

    P2SH, // Enable BIP16-style subscript evaluation.
    STRICTENC, // Passing a non-strict-DER signature or one with undefined hashtype to a checksig operation causes script failure.
    DERSIG, // Passing a non-strict-DER signature to a checksig operation causes script failure (softfork safe, BIP66 rule 1)
    LOW_S, // Passing a non-strict-DER signature or one with S > order/2 to a checksig operation causes script failure
    NULLDUMMY, // Verify dummy stack item consumed by CHECKMULTISIG is of zero-length.
    SIGPUSHONLY, // Using a non-push operator in the scriptSig causes script failure (softfork safe, BIP62 rule 2).
    MINIMALDATA, // Require minimal encodings for all push operations
    DISCOURAGE_UPGRADABLE_NOPS, // Discourage use of NOPs reserved for upgrades (NOP1-10)
    CLEANSTACK, // Require that only a single stack element remains after evaluation.
    CHECKLOCKTIMEVERIFY, // Enable CHECKLOCKTIMEVERIFY operation
    CHECKSEQUENCEVERIFY // Enable CHECKSEQUENCEVERIFY operation
}
