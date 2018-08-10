package com.cml.learn.validation;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.LengthDef;
import org.hibernate.validator.cfg.defs.NotNullDef;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: cml
 * @Date: 2018-08-09 17:12
 * @Description:
 */
public class V {
    public static void main(String[] args) {
//        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
//                .configure()
//                .failFast(true)
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();
        ConstraintMapping constraintMapping = configuration.createConstraintMapping();
        constraintMapping.type(V.class)
                .property("aa", ElementType.FIELD).constraint(new NotNullDef()).constraint(new LengthDef().min(10).max(20).message("长度错误{min}"));
//     .constraint( new SizeDef().min( 2 ).max( 14 ) )
        Validator validator = configuration.addMapping(constraintMapping)
                .buildValidatorFactory()
                .getValidator();
        V v = new V();
        v.aa = "ddddd2";
        Set<ConstraintViolation<V>> result = validator.validate(v);

        Iterator<ConstraintViolation<V>> it = result.iterator();
        while (it.hasNext()) {
            ConstraintViolation<V> r = it.next();
            System.out.println(r.getInvalidValue());
            System.out.println(r.getMessage());
        }


    }

    private String aa;

}
