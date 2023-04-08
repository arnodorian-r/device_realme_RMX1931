package android.content.res;

import oplus.content.res.OplusExtraConfiguration;

public abstract class OplusBaseConfiguration {
    public OplusExtraConfiguration mOplusExtraConfiguration;

    public OplusBaseConfiguration(OplusExtraConfiguration oplusExtraConfiguration) {
        mOplusExtraConfiguration = oplusExtraConfiguration;
    }

    public OplusExtraConfiguration getOplusExtraConfiguration() {
        return this.mOplusExtraConfiguration;
    }
}
