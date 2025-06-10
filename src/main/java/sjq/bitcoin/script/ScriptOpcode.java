package sjq.bitcoin.script;

public enum ScriptOpCode {

    OP_PUSH_0(ScriptConstant.OP_PUSH_0, "PUSH(0)"),
    OP_PUSH_1(ScriptConstant.OP_PUSH_0, "PUSH(1)"),
    OP_PUSH_2(ScriptConstant.OP_PUSH_2, "PUSH(2)"),
    OP_PUSH_3(ScriptConstant.OP_PUSH_3, "PUSH(3)"),
    OP_PUSH_4(ScriptConstant.OP_PUSH_4, "PUSH(4)"),
    OP_PUSH_5(ScriptConstant.OP_PUSH_5, "PUSH(5)"),
    OP_PUSH_6(ScriptConstant.OP_PUSH_6, "PUSH(6)"),
    OP_PUSH_7(ScriptConstant.OP_PUSH_7, "PUSH(7)"),
    OP_PUSH_8(ScriptConstant.OP_PUSH_8, "PUSH(8)"),
    OP_PUSH_9(ScriptConstant.OP_PUSH_9, "PUSH(9)"),
    OP_PUSH_10(ScriptConstant.OP_PUSH_10, "PUSH(10)"),
    OP_PUSH_11(ScriptConstant.OP_PUSH_11, "PUSH(11)"),
    OP_PUSH_12(ScriptConstant.OP_PUSH_12, "PUSH(12)"),
    OP_PUSH_13(ScriptConstant.OP_PUSH_13, "PUSH(13)"),
    OP_PUSH_14(ScriptConstant.OP_PUSH_14, "PUSH(14)"),
    OP_PUSH_15(ScriptConstant.OP_PUSH_15, "PUSH(15)"),
    OP_PUSH_16(ScriptConstant.OP_PUSH_16, "PUSH(16)"),
    OP_PUSH_17(ScriptConstant.OP_PUSH_17, "PUSH(17)"),
    OP_PUSH_18(ScriptConstant.OP_PUSH_18, "PUSH(18)"),
    OP_PUSH_19(ScriptConstant.OP_PUSH_19, "PUSH(19)"),
    OP_PUSH_20(ScriptConstant.OP_PUSH_20, "PUSH(20)"),
    OP_PUSH_21(ScriptConstant.OP_PUSH_21, "PUSH(21)"),
    OP_PUSH_22(ScriptConstant.OP_PUSH_22, "PUSH(22)"),
    OP_PUSH_23(ScriptConstant.OP_PUSH_23, "PUSH(23)"),
    OP_PUSH_24(ScriptConstant.OP_PUSH_24, "PUSH(24)"),
    OP_PUSH_25(ScriptConstant.OP_PUSH_25, "PUSH(25)"),
    OP_PUSH_26(ScriptConstant.OP_PUSH_26, "PUSH(26)"),
    OP_PUSH_27(ScriptConstant.OP_PUSH_27, "PUSH(27)"),
    OP_PUSH_28(ScriptConstant.OP_PUSH_28, "PUSH(28)"),
    OP_PUSH_29(ScriptConstant.OP_PUSH_29, "PUSH(29)"),
    OP_PUSH_30(ScriptConstant.OP_PUSH_30, "PUSH(30)"),
    OP_PUSH_31(ScriptConstant.OP_PUSH_31, "PUSH(31)"),
    OP_PUSH_32(ScriptConstant.OP_PUSH_32, "PUSH(32)"),
    OP_PUSH_33(ScriptConstant.OP_PUSH_33, "PUSH(33)"),
    OP_PUSH_34(ScriptConstant.OP_PUSH_34, "PUSH(34)"),
    OP_PUSH_35(ScriptConstant.OP_PUSH_35, "PUSH(35)"),
    OP_PUSH_36(ScriptConstant.OP_PUSH_36, "PUSH(36)"),
    OP_PUSH_37(ScriptConstant.OP_PUSH_37, "PUSH(37)"),
    OP_PUSH_38(ScriptConstant.OP_PUSH_38, "PUSH(38)"),
    OP_PUSH_39(ScriptConstant.OP_PUSH_39, "PUSH(39)"),
    OP_PUSH_40(ScriptConstant.OP_PUSH_40, "PUSH(40)"),
    OP_PUSH_41(ScriptConstant.OP_PUSH_41, "PUSH(41)"),
    OP_PUSH_42(ScriptConstant.OP_PUSH_42, "PUSH(42)"),
    OP_PUSH_43(ScriptConstant.OP_PUSH_43, "PUSH(43)"),
    OP_PUSH_44(ScriptConstant.OP_PUSH_44, "PUSH(44)"),
    OP_PUSH_45(ScriptConstant.OP_PUSH_45, "PUSH(45)"),
    OP_PUSH_46(ScriptConstant.OP_PUSH_46, "PUSH(46)"),
    OP_PUSH_47(ScriptConstant.OP_PUSH_47, "PUSH(47)"),
    OP_PUSH_48(ScriptConstant.OP_PUSH_48, "PUSH(48)"),
    OP_PUSH_49(ScriptConstant.OP_PUSH_49, "PUSH(49)"),
    OP_PUSH_50(ScriptConstant.OP_PUSH_50, "PUSH(50)"),
    OP_PUSH_51(ScriptConstant.OP_PUSH_51, "PUSH(51)"),
    OP_PUSH_52(ScriptConstant.OP_PUSH_52, "PUSH(52)"),
    OP_PUSH_53(ScriptConstant.OP_PUSH_53, "PUSH(53)"),
    OP_PUSH_54(ScriptConstant.OP_PUSH_54, "PUSH(54)"),
    OP_PUSH_55(ScriptConstant.OP_PUSH_55, "PUSH(55)"),
    OP_PUSH_56(ScriptConstant.OP_PUSH_56, "PUSH(56)"),
    OP_PUSH_57(ScriptConstant.OP_PUSH_57, "PUSH(57)"),
    OP_PUSH_58(ScriptConstant.OP_PUSH_58, "PUSH(58)"),
    OP_PUSH_59(ScriptConstant.OP_PUSH_59, "PUSH(59)"),
    OP_PUSH_60(ScriptConstant.OP_PUSH_60, "PUSH(60)"),
    OP_PUSH_61(ScriptConstant.OP_PUSH_61, "PUSH(61)"),
    OP_PUSH_62(ScriptConstant.OP_PUSH_62, "PUSH(62)"),
    OP_PUSH_63(ScriptConstant.OP_PUSH_63, "PUSH(63)"),
    OP_PUSH_64(ScriptConstant.OP_PUSH_64, "PUSH(64)"),
    OP_PUSH_65(ScriptConstant.OP_PUSH_65, "PUSH(65)"),
    OP_PUSH_66(ScriptConstant.OP_PUSH_66, "PUSH(66)"),
    OP_PUSH_67(ScriptConstant.OP_PUSH_67, "PUSH(67)"),
    OP_PUSH_68(ScriptConstant.OP_PUSH_68, "PUSH(68)"),
    OP_PUSH_69(ScriptConstant.OP_PUSH_69, "PUSH(69)"),
    OP_PUSH_70(ScriptConstant.OP_PUSH_70, "PUSH(70)"),
    OP_PUSH_71(ScriptConstant.OP_PUSH_71, "PUSH(71)"),
    OP_PUSH_72(ScriptConstant.OP_PUSH_72, "PUSH(72)"),
    OP_PUSH_73(ScriptConstant.OP_PUSH_73, "PUSH(73)"),
    OP_PUSH_74(ScriptConstant.OP_PUSH_74, "PUSH(74)"),
    OP_PUSH_75(ScriptConstant.OP_PUSH_75, "PUSH(75)"),

    OP_PUSHDATA1(ScriptConstant.OP_PUSHDATA1, "PUSHDATA1"),
    OP_PUSHDATA2(ScriptConstant.OP_PUSHDATA2, "PUSHDATA2"),
    OP_PUSHDATA4(ScriptConstant.OP_PUSHDATA4, "PUSHDATA4"),
    OP_NEGATE1(ScriptConstant.OP_NEGATE1, "NEGATE1"),

    OP_IF(ScriptConstant.OP_IF, "IF"),
    OP_NOTIF(ScriptConstant.OP_NOTIF, "NOTIF"),
    OP_ELSE(ScriptConstant.OP_ELSE, "ELSE"),
    OP_ENDIF(ScriptConstant.OP_ENDIF, "ENDIF"),
    OP_VERIFY(ScriptConstant.OP_VERIFY, "VERIFY"),
    OP_RETURN(ScriptConstant.OP_RETURN, "RETURN"),

    OP_TOALTSTACK(ScriptConstant.OP_TOALTSTACK, "TOALTSTACK"),
    OP_FROMALTSTACK(ScriptConstant.OP_FROMALTSTACK, "FROMALTSTACK"),
    OP_IFDUP(ScriptConstant.OP_IFDUP, "IFDUP"),
    OP_DEPTH(ScriptConstant.OP_DEPTH, "DEPTH"),
    OP_DROP(ScriptConstant.OP_DROP, "DROP"),
    OP_DUP(ScriptConstant.OP_DUP, "DUP"),
    OP_NIP(ScriptConstant.OP_NIP, "NIP"),
    OP_OVER(ScriptConstant.OP_OVER, "OVER"),
    OP_PICK(ScriptConstant.OP_PICK, "PICK"),
    OP_ROLL(ScriptConstant.OP_ROLL, "ROLL"),
    OP_ROT(ScriptConstant.OP_ROT, "ROT"),
    OP_SWAP(ScriptConstant.OP_SWAP, "SWAP"),
    OP_TUCK(ScriptConstant.OP_TUCK, "TUCK"),
    OP_DROP2(ScriptConstant.OP_DROP2, "DROP2"),
    OP_DUP2(ScriptConstant.OP_DUP2, "DUP2"),
    OP_DUP3(ScriptConstant.OP_DUP3, "DUP3"),
    OP_OVER2(ScriptConstant.OP_OVER2, "OVER2"),
    OP_ROT2(ScriptConstant.OP_ROT2, "ROT2"),
    OP_SWAP2(ScriptConstant.OP_SWAP2, "SWAP2"),

    OP_CAT(ScriptConstant.OP_CAT, "CAT"),
    OP_SUBSTR(ScriptConstant.OP_SUBSTR, "SUBSTR"),
    OP_LEFT(ScriptConstant.OP_LEFT, "LEFT"),
    OP_RIGHT(ScriptConstant.OP_RIGHT, "RIGHT"),
    OP_SIZE(ScriptConstant.OP_SIZE, "SIZE"),

    OP_ADD1(ScriptConstant.OP_ADD1, "ADD1"),
    OP_SUB1(ScriptConstant.OP_SUB1, "SUB1"),
    OP_MUL2(ScriptConstant.OP_MUL2, "MUL2"),
    OP_DIV2(ScriptConstant.OP_DIV2, "DIV2"),

    OP_NEGATE(ScriptConstant.OP_NEGATE, "NEGATE"),
    OP_ABS(ScriptConstant.OP_ABS, "ABS"),
    OP_NOT(ScriptConstant.OP_NOT, "NOT"),
    OP_NOTEQUAL0(ScriptConstant.OP_NOTEQUAL0, "NOTEQUAL0"),
    OP_ADD(ScriptConstant.OP_ADD, "ADD"),
    OP_SUB(ScriptConstant.OP_SUB, "SUB"),
    OP_MUL(ScriptConstant.OP_MUL, "MUL"),
    OP_DIV(ScriptConstant.OP_DIV, "OP_DIV"),
    OP_MOD(ScriptConstant.OP_MOD, "OP_MOD"),

    OP_LSHIFT(ScriptConstant.OP_LSHIFT, "LSHIFT"),
    OP_RSHIFT(ScriptConstant.OP_RSHIFT, "RSHIFT"),
    OP_BOOLAND(ScriptConstant.OP_BOOLAND, "BOOLAND"),
    OP_BOOLOR(ScriptConstant.OP_BOOLOR, "BOOLOR"),
    OP_NUMEQUAL(ScriptConstant.OP_NUMEQUAL, "NUMEQUAL"),
    OP_NUMEQUALVERIFY(ScriptConstant.OP_NUMEQUALVERIFY, "NUMEQUALVERIFY"),
    OP_NUMNOTEQUAL(ScriptConstant.OP_NUMNOTEQUAL, "NUMNOTEQUAL"),
    OP_LESSTHAN(ScriptConstant.OP_LESSTHAN, "LESSTHAN"),
    OP_GREATERTHAN(ScriptConstant.OP_GREATERTHAN, "GREATERTHAN"),
    OP_LESSTHANOREQUAL(ScriptConstant.OP_LESSTHANOREQUAL, "LESSTHANOREQUAL"),
    OP_GREATERTHANOREQUAL(ScriptConstant.OP_GREATERTHANOREQUAL, "GREATERTHANOREQUAL"),
    OP_MIN(ScriptConstant.OP_MIN, "MIN"),
    OP_MAX(ScriptConstant.OP_MAX, "MAX"),
    OP_WITHIN(ScriptConstant.OP_WITHIN, "WITHIN"),

    OP_INVERT(ScriptConstant.OP_INVERT, "INVERT"),
    OP_AND(ScriptConstant.OP_AND, "AND"),
    OP_OR(ScriptConstant.OP_OR, "OR"),
    OP_XOR(ScriptConstant.OP_XOR, "XOR"),
    OP_EQUAL(ScriptConstant.OP_EQUAL, "EQUAL"),
    OP_EQUALVERIFY(ScriptConstant.OP_EQUALVERIFY, "EQUALVERIFY"),

    OP_RIPEMD160(ScriptConstant.OP_RIPEMD160, "RIPEMD160"),
    OP_SHA1(ScriptConstant.OP_SHA1, "SHA1"),
    OP_SHA256(ScriptConstant.OP_SHA256, "SHA256"),
    OP_HASH160(ScriptConstant.OP_HASH160, "HASH160"),
    OP_HASH256(ScriptConstant.OP_HASH256, "HASH256"),
    OP_CODESEPARATOR(ScriptConstant.OP_CODESEPARATOR, "CODESEPARATOR"),
    OP_CHECKSIG(ScriptConstant.OP_CHECKSIG, "CHECKSIG"),
    OP_CHECKSIGVERIFY(ScriptConstant.OP_CHECKSIGVERIFY, "CHECKSIGVERIFY"),
    OP_CHECKMULTISIG(ScriptConstant.OP_CHECKMULTISIG, "CHECKMULTISIG"),
    OP_CHECKMULTISIGVERIFY(ScriptConstant.OP_CHECKMULTISIGVERIFY, "CHECKMULTISIGVERIFY"),
    OP_CHECKSIGADD(ScriptConstant.OP_CHECKSIGADD, "CHECKSIGADD"),

    OP_CHECKLOCKTIMEVERIFY(ScriptConstant.OP_CHECKLOCKTIMEVERIFY, "CHECKLOCKTIMEVERIFY"),
    OP_CHECKSEQUENCEVERIFY(ScriptConstant.OP_CHECKSEQUENCEVERIFY, "CHECKSEQUENCEVERIFY"),

    OP_NOP(ScriptConstant.OP_NOP, "EQUAL"),
    OP_NOP1(ScriptConstant.OP_NOP1, "EQUAL"),
    OP_NOP4(ScriptConstant.OP_NOP4, "EQUAL"),
    OP_NOP5(ScriptConstant.OP_NOP5, "EQUAL"),
    OP_NOP6(ScriptConstant.OP_NOP6, "EQUAL"),
    OP_NOP7(ScriptConstant.OP_NOP7, "EQUAL"),
    OP_NOP8(ScriptConstant.OP_NOP8, "EQUAL"),
    OP_NOP9(ScriptConstant.OP_NOP9, "EQUAL"),
    OP_NOP10(ScriptConstant.OP_NOP10, "EQUAL"),

    OP_RESERVED(ScriptConstant.OP_RESERVED, "RESERVED"),
    OP_RESERVED1(ScriptConstant.OP_RESERVED1, "RESERVED1"),
    OP_RESERVED2(ScriptConstant.OP_RESERVED2, "RESERVED2"),
    OP_VER(ScriptConstant.OP_VER, "VER"),
    OP_VERIF(ScriptConstant.OP_VERIF, "VERIF"),
    OP_VERNOTIF(ScriptConstant.OP_VERNOTIF, "VERNOTIF"),
    OP_INVALIDOPCODE(ScriptConstant.OP_INVALIDOPCODE, "INVALIDOPCODE");

    private final short code;

    private final String name;

    ScriptOpCode(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ScriptOpCode getScriptOpCodeByCode(short code) {
        for(ScriptOpCode opCode:values()) {
            if (opCode.getCode() == code) {
                return opCode;
            }
        }
        throw new ScriptException(String.format("script opCode does not exist with code:%d", code));
    }

    public short getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("OpCode[code='%d',name='%s']", code, name);
    }
}
