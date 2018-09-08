package com.cml.learn.jooq;

import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.stereotype.Component;

//@Component
public class MyExecuteListener extends DefaultExecuteListener {
    @Override
    public void start(ExecuteContext ctx) {
        super.start(ctx);
        System.out.println("================>start");
    }

    @Override
    public void renderStart(ExecuteContext ctx) {
        super.renderStart(ctx);
        System.out.println("================>renderStart");
    }

    @Override
    public void renderEnd(ExecuteContext ctx) {
        super.renderEnd(ctx);
        System.out.println("================>renderEnd");
    }

    @Override
    public void bindStart(ExecuteContext ctx) {
        super.bindStart(ctx);
        System.out.println("================>bindStart");
    }

    @Override
    public void bindEnd(ExecuteContext ctx) {
        super.bindEnd(ctx);
        System.out.println("================>bindEnd:"+ctx.sql());
    }
}
