package com.dpt.TNetwork.net.listener;

import com.dpt.TNetwork.net.parse.IBaseParser;
import com.dpt.TNetwork.net.parse.JacksonParser;

/**
 * Created by dupengtao on 2014/6/16.
 */
public abstract class AbNetClientTOListener<T> implements INetClientStrListener{

    private final IBaseParser mParser;
    private final Class<T> mClazz;

    public AbNetClientTOListener(IBaseParser parser, Class<T> clazz) {
        mParser=parser;
        mClazz=clazz;
    }

    public AbNetClientTOListener(Class<T> clazz){
        this(JacksonParser.getInstance(),clazz);
    }

    protected T parse (String context){
        return mParser.toParse(context,mClazz);
    }

    @Override
    public void onSuccess(String content, String[] otherMsg) {
        T t = parse(content);
        onSuccess(t,otherMsg);
    }

    public abstract void onSuccess(T t,String[] otherMsg);

}
