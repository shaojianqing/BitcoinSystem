package sjq.bitcoin.script;

public interface ScriptConstant {

    short OP_FALSE = 0x00;
    short OP_PUSH_0 = 0x00; // push empty vector
    short OP_PUSH_1 = 0x01;
    short OP_PUSH_2 = 0x02;
    short OP_PUSH_3 = 0x03;
    short OP_PUSH_4 = 0x04;
    short OP_PUSH_5 = 0x05;
    short OP_PUSH_6 = 0x06;
    short OP_PUSH_7 = 0x07;
    short OP_PUSH_8 = 0x08;
    short OP_PUSH_9 = 0x09;
    short OP_PUSH_10 = 0x0a;
    short OP_PUSH_11 = 0x0b;
    short OP_PUSH_12 = 0x0c;
    short OP_PUSH_13 = 0x0d;
    short OP_PUSH_14 = 0x0e;
    short OP_PUSH_15 = 0x0f;
    short OP_PUSH_16 = 0x10;
    short OP_PUSH_17 = 0x11;
    short OP_PUSH_18 = 0x12;
    short OP_PUSH_19 = 0x13;
    short OP_PUSH_20 = 0x14;
    short OP_PUSH_21 = 0x15;
    short OP_PUSH_22 = 0x16;
    short OP_PUSH_23 = 0x17;
    short OP_PUSH_24 = 0x18;
    short OP_PUSH_25 = 0x19;
    short OP_PUSH_26 = 0x1a;
    short OP_PUSH_27 = 0x1b;
    short OP_PUSH_28 = 0x1c;
    short OP_PUSH_29 = 0x1d;
    short OP_PUSH_30 = 0x1e;
    short OP_PUSH_31 = 0x1f;
    short OP_PUSH_32 = 0x20;
    short OP_PUSH_33 = 0x21;
    short OP_PUSH_34 = 0x22;
    short OP_PUSH_35 = 0x23;
    short OP_PUSH_36 = 0x24;
    short OP_PUSH_37 = 0x25;
    short OP_PUSH_38 = 0x26;
    short OP_PUSH_39 = 0x27;
    short OP_PUSH_40 = 0x28;
    short OP_PUSH_41 = 0x29;
    short OP_PUSH_42 = 0x2a;
    short OP_PUSH_43 = 0x2b;
    short OP_PUSH_44 = 0x2c;
    short OP_PUSH_45 = 0x2d;
    short OP_PUSH_46 = 0x2e;
    short OP_PUSH_47 = 0x2f;
    short OP_PUSH_48 = 0x30;
    short OP_PUSH_49 = 0x31;
    short OP_PUSH_50 = 0x32;
    short OP_PUSH_51 = 0x33;
    short OP_PUSH_52 = 0x34;
    short OP_PUSH_53 = 0x35;
    short OP_PUSH_54 = 0x36;
    short OP_PUSH_55 = 0x37;
    short OP_PUSH_56 = 0x38;
    short OP_PUSH_57 = 0x39;
    short OP_PUSH_58 = 0x3a;
    short OP_PUSH_59 = 0x3b;
    short OP_PUSH_60 = 0x3c;
    short OP_PUSH_61 = 0x3d;
    short OP_PUSH_62 = 0x3e;
    short OP_PUSH_63 = 0x3f;
    short OP_PUSH_64 = 0x40;
    short OP_PUSH_65 = 0x41;
    short OP_PUSH_66 = 0x42;
    short OP_PUSH_67 = 0x43;
    short OP_PUSH_68 = 0x44;
    short OP_PUSH_69 = 0x45;
    short OP_PUSH_70 = 0x46;
    short OP_PUSH_71 = 0x47;
    short OP_PUSH_72 = 0x48;
    short OP_PUSH_73 = 0x49;
    short OP_PUSH_74 = 0x4a;
    short OP_PUSH_75 = 0x4b;
    short OP_PUSHDATA1 = 0x4c;
    short OP_PUSHDATA2 = 0x4d;
    short OP_PUSHDATA4 = 0x4e;
    short OP_NEGATE1 = 0x4f;
    short OP_RESERVED = 0x50;
    short OP_0 = 0x00;
    short OP_1 = 0x51;
    short OP_TRUE = OP_1;
    short OP_2 = 0x52;
    short OP_3 = 0x53;
    short OP_4 = 0x54;
    short OP_5 = 0x55;
    short OP_6 = 0x56;
    short OP_7 = 0x57;
    short OP_8 = 0x58;
    short OP_9 = 0x59;
    short OP_10 = 0x5a;
    short OP_11 = 0x5b;
    short OP_12 = 0x5c;
    short OP_13 = 0x5d;
    short OP_14 = 0x5e;
    short OP_15 = 0x5f;
    short OP_16 = 0x60;

    // control
    short OP_NOP = 0x61;
    short OP_VER = 0x62;
    short OP_IF = 0x63;
    short OP_NOTIF = 0x64;
    short OP_VERIF = 0x65;
    short OP_VERNOTIF = 0x66;
    short OP_ELSE = 0x67;
    short OP_ENDIF = 0x68;
    short OP_VERIFY = 0x69;
    short OP_RETURN = 0x6a;

    // stack ops
    short OP_TOALTSTACK = 0x6b;
    short OP_FROMALTSTACK = 0x6c;
    short OP_DROP2 = 0x6d;
    short OP_DUP2 = 0x6e;
    short OP_DUP3 = 0x6f;
    short OP_OVER2 = 0x70;
    short OP_ROT2 = 0x71;
    short OP_SWAP2 = 0x72;
    short OP_IFDUP = 0x73;
    short OP_DEPTH = 0x74;
    short OP_DROP = 0x75;
    short OP_DUP = 0x76;
    short OP_NIP = 0x77;
    short OP_OVER = 0x78;
    short OP_PICK = 0x79;
    short OP_ROLL = 0x7a;
    short OP_ROT = 0x7b;
    short OP_SWAP = 0x7c;
    short OP_TUCK = 0x7d;

    // splice ops
    short OP_CAT = 0x7e;
    short OP_SUBSTR = 0x7f;
    short OP_LEFT = 0x80;
    short OP_RIGHT = 0x81;
    short OP_SIZE = 0x82;

    // bit logic
    short OP_INVERT = 0x83;
    short OP_AND = 0x84;
    short OP_OR = 0x85;
    short OP_XOR = 0x86;
    short OP_EQUAL = 0x87;
    short OP_EQUALVERIFY = 0x88;
    short OP_RESERVED1 = 0x89;
    short OP_RESERVED2 = 0x8a;

    // numeric
    short OP_ADD1 = 0x8b;
    short OP_SUB1 = 0x8c;
    short OP_MUL2 = 0x8d;
    short OP_DIV2 = 0x8e;
    short OP_NEGATE = 0x8f;
    short OP_ABS = 0x90;
    short OP_NOT = 0x91;
    short OP_NOTEQUAL0 = 0x92;
    short OP_ADD = 0x93;
    short OP_SUB = 0x94;
    short OP_MUL = 0x95;
    short OP_DIV = 0x96;
    short OP_MOD = 0x97;
    short OP_LSHIFT = 0x98;
    short OP_RSHIFT = 0x99;
    short OP_BOOLAND = 0x9a;
    short OP_BOOLOR = 0x9b;
    short OP_NUMEQUAL = 0x9c;
    short OP_NUMEQUALVERIFY = 0x9d;
    short OP_NUMNOTEQUAL = 0x9e;
    short OP_LESSTHAN = 0x9f;
    short OP_GREATERTHAN = 0xa0;
    short OP_LESSTHANOREQUAL = 0xa1;
    short OP_GREATERTHANOREQUAL = 0xa2;
    short OP_MIN = 0xa3;
    short OP_MAX = 0xa4;
    short OP_WITHIN = 0xa5;

    // EccKey
    short OP_RIPEMD160 = 0xa6;
    short OP_SHA1 = 0xa7;
    short OP_SHA256 = 0xa8;
    short OP_HASH160 = 0xa9;
    short OP_HASH256 = 0xaa;
    short OP_CODESEPARATOR = 0xab;
    short OP_CHECKSIG = 0xac;
    short OP_CHECKSIGVERIFY = 0xad;
    short OP_CHECKMULTISIG = 0xae;
    short OP_CHECKMULTISIGVERIFY = 0xaf;
    short OP_CHECKSIGADD = 0xba;

    // block state
    /** Check lock time of the block. shortreduced in BIP 65, replacing OP_NOP2 */
    short OP_CHECKLOCKTIMEVERIFY = 0xb1;
    short OP_CHECKSEQUENCEVERIFY = 0xb2;

    // expansion
    short OP_NOP1 = 0xb0;
    /** Deprecated by BIP 65 */
    @Deprecated
    short OP_NOP2 = OP_CHECKLOCKTIMEVERIFY;
    /** Deprecated by BIP 112 */
    @Deprecated
    short OP_NOP3 = OP_CHECKSEQUENCEVERIFY;
    short OP_NOP4 = 0xb3;
    short OP_NOP5 = 0xb4;
    short OP_NOP6 = 0xb5;
    short OP_NOP7 = 0xb6;
    short OP_NOP8 = 0xb7;
    short OP_NOP9 = 0xb8;
    short OP_NOP10 = 0xb9;
    short OP_INVALIDOPCODE = 0xff;
}
