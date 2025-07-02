package sjq.bitcoin.service.data;

import sjq.bitcoin.script.ScriptType;

public interface BitcoinAddress {

    ScriptType getScriptType();

    String getStringFormat();
}
