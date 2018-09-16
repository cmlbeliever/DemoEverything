package com.cml.learn.jooq;

import org.jooq.RecordContext;
import org.jooq.impl.DefaultRecordListener;

public class MyRecordListener extends DefaultRecordListener {
    @Override
    public void updateStart(RecordContext ctx) {
        super.updateStart(ctx);
        System.out.println("------------------------->" + ctx.record());
    }

    @Override
    public void updateEnd(RecordContext ctx) {
        super.updateEnd(ctx);
        System.out.println("---------------------------");
    }
}
