package com.cml.learn.jooq;

import org.jooq.Configuration;
import org.jooq.ExecuteContext;
import org.jooq.impl.DSL;
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
        System.out.println("================>renderEnd：" + ctx.sql());
        Configuration configuration = ctx.configuration();
        String inlined = DSL.using(configuration).renderInlined(ctx.query());
//        ctx.sql(inlined);
        System.out.println("inlined:" + inlined);

    }

    @Override
    public void executeStart(ExecuteContext ctx) {
        super.executeStart(ctx);
        Configuration configuration = ctx.configuration();
        String inlined = DSL.using(configuration).renderInlined(ctx.query());
        ctx.sql(inlined + " where id = 1 ");
        System.out.println("===============executeStart:" + ctx.sql());
//        System.out.println("===============executeStart:" + ctx.routine().getName());
    }

    @Override
    public void executeEnd(ExecuteContext ctx) {
        super.executeEnd(ctx);
        System.out.println("end:" + ctx.sql());
    }

    @Override
    public void bindStart(ExecuteContext ctx) {
        super.bindStart(ctx);
        System.out.println("================>bindStart");
    }

    @Override
    public void bindEnd(ExecuteContext ctx) {
        super.bindEnd(ctx);
        System.out.println("================>bindEnd:" + ctx.sql());
    }
}
