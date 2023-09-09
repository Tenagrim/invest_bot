package com.tenagrim.telegram.model.generator;

import com.tenagrim.telegram.model.interfaces.IdItemIdHolder;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.math.BigInteger;

public abstract class BaseItemIdGenerator implements ValueGenerator<Long> {
    @Override
    public Long generateValue(Session session, Object o) {
        if (o instanceof IdItemIdHolder) {
            IdItemIdHolder idItemIdHolder = (IdItemIdHolder) o;
            if (idItemIdHolder.getItemId() != null) {
                return idItemIdHolder.getItemId();
            } else {
                session.setHibernateFlushMode(FlushMode.COMMIT); //TODO костыль. Возможно, стоит вручную получать новый id при сохранении
                return ((BigInteger) session
                        .createNativeQuery(String.format("select nextval('%s');", getSequenceName()))
                        .getSingleResult()).longValue();
            }
        }
        throw new IllegalArgumentException("this should never happen");
    }

    protected abstract String getSequenceName();
}
